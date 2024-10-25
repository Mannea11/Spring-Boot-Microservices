package MicroservicesOrder.Order.DTO;

import java.util.List;

public class CartDTO {
    private List<CartItemDTO> cartItems;
    private Double totalPrice;

    public CartDTO(List<CartItemDTO> cartItems, Double totalPrice) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
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

    @Override
    public String toString() {
        return "CartDTO{" +
                "items=" + cartItems +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
