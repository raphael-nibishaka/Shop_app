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
            "<!DOCTYPE html>" +
            "<html>" +
            "<head>" +
            "    <style>" +
            "        body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }" +
            "        .container { max-width: 600px; margin: 0 auto; padding: 20px; }" +
            "        .header { background-color: #4CAF50; color: white; padding: 20px; text-align: center; border-radius: 5px 5px 0 0; }" +
            "        .content { background-color: #f9f9f9; padding: 20px; border-radius: 0 0 5px 5px; }" +
            "        .button { display: inline-block; background-color: #4CAF50; color: white; padding: 12px 24px; text-decoration: none; border-radius: 4px; margin: 20px 0; }" +
            "        .footer { text-align: center; margin-top: 20px; font-size: 12px; color: #666; }" +
            "    </style>" +
            "</head>" +
            "<body>" +
            "    <div class='container'>" +
            "        <div class='header'>" +
            "            <h2>Welcome to Our Service!</h2>" +
            "        </div>" +
            "        <div class='content'>" +
            "            <p>Dear %s,</p>" +
            "            <p>Thank you for registering with us. To complete your registration and verify your email address, please click the button below:</p>" +
            "            <p style='text-align: center;'>" +
            "                <a href='%s' class='button'>Verify Email Address</a>" +
            "            </p>" +
            "            <p>If the button above doesn't work, you can also copy and paste this link into your browser:</p>" +
            "            <p style='word-break: break-all;'>%s</p>" +
            "            <p>This verification link will expire in 24 hours.</p>" +
            "            <p>If you did not register for this service, please ignore this email.</p>" +
            "        </div>" +
            "        <div class='footer'>" +
            "            <p>This is an automated message, please do not reply to this email.</p>" +
            "        </div>" +
            "    </div>" +
            "</body>" +
            "</html>",
            customer.getFirstName(),
            verificationLink,
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
