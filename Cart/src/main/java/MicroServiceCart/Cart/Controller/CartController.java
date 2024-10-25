package MicroServiceCart.Cart.Controller;

import MicroServiceCart.Cart.DTO.CartDTO;
import MicroServiceCart.Cart.DTO.CartItemDTO;
import MicroServiceCart.Cart.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {


    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addItemToCart(@RequestBody CartItemDTO cartItemDTO, @RequestHeader("Authorization") String token) {
        cartService.addItemToCart(cartItemDTO, token);
        return ResponseEntity.ok("Produkt tillagd i varukorgen");
    }

    @DeleteMapping("/deletecart")
    public ResponseEntity<String> deleteCart(@RequestHeader("Authorization") String token) {
        cartService.deleteCart(token);
        return ResponseEntity.ok("Varukorgen är raderad");
    }

    @GetMapping("/cartitems")
    public ResponseEntity<List<String>> getAllCartItems(@RequestHeader("Authorization") String token) {
        List<String> cartItemsInfo = cartService.getAllCartItems(token);
        return ResponseEntity.ok(cartItemsInfo);
    }

    @GetMapping("/fullcart")
    public ResponseEntity<CartDTO> getFullCart(@RequestHeader("Authorization") String token) {
        CartDTO cartDTO = cartService.getFullCart(token);
        if (cartDTO != null) {
            return ResponseEntity.ok(cartDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
