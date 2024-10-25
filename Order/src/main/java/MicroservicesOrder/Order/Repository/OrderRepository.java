package MicroservicesOrder.Order.Repository;

import MicroservicesOrder.Order.Model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
