package URaphael.raphy.repository;

import URaphael.raphy.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
    Customer findByVerificationToken(String token);
}
