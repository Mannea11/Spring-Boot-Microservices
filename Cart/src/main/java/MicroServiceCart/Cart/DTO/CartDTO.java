package MicroServiceCart.Cart.DTO;
import MicroServiceCart.Cart.Model.CartItem;
import jakarta.persistence.*;
import java.util.List;


public class CartDTO {

    private int cartId;

    private String username;

    private List<CartItemDTO> cartItems;

    private Double totalPrice;

    public CartDTO() {
    }

    public CartDTO(int cartId, String username,Double totalPrice, List<CartItemDTO> cartItems) {
        this.cartId = cartId;
        this.username = username;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
    }

    public CartDTO(String username, Double totalPrice, List<CartItemDTO> cartItems) {
        this.username = username;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
