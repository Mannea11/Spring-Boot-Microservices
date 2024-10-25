package MicroServiceCart.Cart.DTO;

public class CartItemDTO {

    private int productId;
    private String productName;
    private double price;
    private int cartId;

    public CartItemDTO(int productId, String productName, double price,int cartId) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.cartId = cartId;
    }
    public CartItemDTO() {
    }
    public CartItemDTO(int productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
}