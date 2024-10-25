package MicroServiceCart.Cart.Repository;

import MicroServiceCart.Cart.Model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
    Optional<Cart> findByUsername(String username);
}