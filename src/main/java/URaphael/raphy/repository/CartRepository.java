package URaphael.raphy.repository;

import URaphael.raphy.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByCustomerId(Long customerId);
    Cart findByCustomerIdAndProductCode(Long customerId, String productCode);
}
