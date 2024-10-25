package MicroservicesOrder.Order.Repository;

import MicroservicesOrder.Order.Model.OrderItem;;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

}
