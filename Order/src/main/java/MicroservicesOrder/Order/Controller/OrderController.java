package MicroservicesOrder.Order.Controller;



import MicroservicesOrder.Order.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<String> createOrderFromCart(@RequestHeader("Authorization") String token) {
        try {
            return orderService.createOrderFromCart(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ett fel inträffade: " + e.getMessage());
        }
    }
}

