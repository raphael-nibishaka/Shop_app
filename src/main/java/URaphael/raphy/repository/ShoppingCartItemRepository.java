package URaphael.raphy.repository;

import URaphael.raphy.model.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
    List<ShoppingCartItem> findByCustomerId(Long customerId);
    ShoppingCartItem findByCustomerIdAndProductCode(Long customerId, String productCode);
}
