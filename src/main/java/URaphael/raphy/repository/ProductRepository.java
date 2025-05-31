package URaphael.raphy.repository;

import URaphael.raphy.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductCode(String productCode);
    List<Product> findByCategory(String category);
    List<Product> findByActive(boolean active);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% OR p.description LIKE %:keyword%")
    List<Product> searchProducts(String keyword);

    List<Product> findByCategoryAndActive(String category, boolean active);
}
