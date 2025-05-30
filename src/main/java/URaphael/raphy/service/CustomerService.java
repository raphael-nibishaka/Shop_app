package URaphael.raphy.service;

import URaphael.raphy.model.Customer;
import URaphael.raphy.repository.CustomerRepository;
import URaphael.raphy.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Customer registerCustomer(Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()) != null) {
            throw new RuntimeException("Email already registered");
        }

        // Generate verification token
        String verificationToken = UUID.randomUUID().toString();
        customer.setVerificationToken(verificationToken);
        customer.setVerified(false);

        // Hash the password before saving
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        // Save the customer
        Customer savedCustomer = customerRepository.save(customer);

        // Send verification email
        String verificationLink = "http://localhost:8080/customers/verify-email?token=" + verificationToken;
        String emailBody = String.format(
            "<h2>Welcome to our service!</h2>" +
            "<p>Dear %s,</p>" +
            "<p>Thank you for registering. Please click the link below to verify your email address:</p>" +
            "<p><a href='%s'>Verify Email</a></p>" +
            "<p>If you did not register for this service, please ignore this email.</p>",
            customer.getFirstName(),
            verificationLink
        );

        EmailUtil.sendEmail(
            customer.getEmail(),
            "Verify Your Email Address",
            emailBody
        );

        return savedCustomer;
    }

    public Customer verifyEmail(String token) {
        Customer customer = customerRepository.findByVerificationToken(token);
        if (customer == null) {
            throw new RuntimeException("Invalid verification token");
        }

        customer.setVerified(true);
        customer.setVerificationToken(null); // Clear the token after verification
        return customerRepository.save(customer);
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer login(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null && passwordEncoder.matches(password, customer.getPassword())) {
            if (!customer.isVerified()) {
                throw new RuntimeException("Please verify your email before logging in");
            }
            return customer;
        }
        return null;
    }
}
