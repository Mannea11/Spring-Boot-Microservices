package MicroservicesOrder.Order.Service;

import MicroservicesOrder.Order.DTO.CartDTO;
import MicroservicesOrder.Order.DTO.CartItemDTO;
import MicroservicesOrder.Order.Model.Order;
import MicroservicesOrder.Order.Model.OrderItem;
import MicroservicesOrder.Order.Repository.OrderRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderService {

        @Autowired
        private OrderRepository orderRepository;

        @Autowired
        private RestTemplate restTemplate;

        @Value("${jwt.secret}")
        private String secretKey;
    public ResponseEntity<String> createOrderFromCart(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<CartDTO> cartResponse = restTemplate.exchange(
                "http://localhost:9090/cart/fullcart",
                HttpMethod.GET, entity, CartDTO.class);

        if (cartResponse.getStatusCode().is2xxSuccessful() && cartResponse.getBody() != null) {
            return convertCartToOrder(cartResponse.getBody(), token);
        } else {
            return ResponseEntity.status(cartResponse.getStatusCode()).body("Kunde inte hämta varukorgen");
        }
    }


    private ResponseEntity<String> convertCartToOrder(CartDTO cart, String token) {
        Order order = new Order();
        order.setUsername(extractUsernameFromJwt(token));
        order.setTotalPrice(cart.getTotalPrice());

        List<OrderItem> orderItems = convertCartItemsToOrderItems(cart.getCartItems(), order);
        order.setItems(orderItems);
        orderRepository.save(order);

        return ResponseEntity.ok("Order skapad från varukorgen: " + order);
    }


    private List<OrderItem> convertCartItemsToOrderItems(List<CartItemDTO> cartItems, Order order) {
        if (cartItems == null) {
            return new ArrayList<>();
        }
        return cartItems.stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setProductName(cartItem.getProductName());
            orderItem.setPrice(cartItem.getPrice());
            return orderItem;
        }).collect(Collectors.toList());
    }

    private String extractUsernameFromJwt(String token) {
        System.out.println(secretKey);
        token = token.trim().replace("Bearer ", "");
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    }

