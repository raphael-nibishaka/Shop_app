package URaphael.raphy.repository;

import URaphael.raphy.model.Purchased;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface PurchasedRepository extends JpaRepository<Purchased, Long> {
    List<Purchased> findByCustomerId(Long customerId);
    List<Purchased> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
