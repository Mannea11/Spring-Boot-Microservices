package MicroServiceCart.Cart.Service;

import MicroServiceCart.Cart.DTO.CartDTO;
import MicroServiceCart.Cart.DTO.CartItemDTO;
import MicroServiceCart.Cart.DTO.ProductDTO;
import MicroServiceCart.Cart.Model.Cart;
import MicroServiceCart.Cart.Model.CartItem;
import MicroServiceCart.Cart.Repository.CartItemRepository;
import MicroServiceCart.Cart.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${jwt.secret}")
    private String secretKey;

    public void addItemToCart(CartItemDTO cartItemDTO, String token) {
        String username = extractUsernameFromJwt(token);
        ProductDTO product = restTemplate.getForObject("http://localhost:8080/products/id/" + cartItemDTO.getProductId(), ProductDTO.class);
        CartItem cartItem = new CartItem();
        cartItem.setProductId(cartItemDTO.getProductId());
        cartItem.setProductName(product.getName());
        cartItem.setPrice(product.getPrice());

        Cart cart = getOrCreateCart(username);
        cartItem.setCart(cart);
        cartItemRepository.save(cartItem);

        updateTotalPrice(cart);
    }


    private String extractUsernameFromJwt(String token) {
        token = token.trim().replace("Bearer ", "");
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    private Cart getOrCreateCart(String username) {
        Optional<Cart> optionalCart = cartRepository.findByUsername(username);
        return optionalCart.orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUsername(username);
            return cartRepository.save(newCart);
        });
    }

    public void deleteCart(String token) {
        String username = extractUsernameFromJwt(token);
        Optional<Cart> optionalCart = cartRepository.findByUsername(username);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cartRepository.delete(cart);
        } else {
            System.out.println("varukorg ej hittad");
        }
    }

    private void updateTotalPrice(Cart cart) {
        List<CartItem> cartItems = cart.getCartItems();
        double totalPrice = 0.0;
        for (CartItem item : cartItems) {
            totalPrice += item.getPrice();
        }
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
    }


    public List<String> getAllCartItems(String token) {
        String username = extractUsernameFromJwt(token);
        Optional<Cart> optionalCart = cartRepository.findByUsername(username);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            List<String> cartItemsInfo = new ArrayList<>();
            for (CartItem item : cart.getCartItems()) {
                String itemInfo = item.getProductName() + " - " + item.getPrice() + " kr";
                cartItemsInfo.add(itemInfo);
            }
            return cartItemsInfo;
        } else {
            System.out.println("Varukorgen kunde inte hittas för användaren: " + username);
            return Collections.emptyList();
        }
    }

    public CartDTO getFullCart(String token) {
        String username = extractUsernameFromJwt(token);
        Optional<Cart> optionalCart = cartRepository.findByUsername(username);
        if (optionalCart.isPresent()) {
            return convertToCartDTO(optionalCart.get());
        } else {
            return null;
        }
    }

    private CartDTO convertToCartDTO(Cart cart) {
        List<CartItemDTO> cartItemDTOs = cart.getCartItems().stream().map(this::convertToCartItemDTO).collect(Collectors.toList());
        return new CartDTO(cart.getCartId(),cart.getUsername(), cart.getTotalPrice(), cartItemDTOs);
    }

    private CartItemDTO convertToCartItemDTO(CartItem item) {
        return new CartItemDTO(item.getProductId(), item.getProductName(), item.getPrice());
    }
}
