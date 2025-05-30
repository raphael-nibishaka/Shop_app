package URaphael.raphy.service;

import URaphael.raphy.model.ShoppingCartItem;
import URaphael.raphy.model.Purchased;
import URaphael.raphy.repository.ShoppingCartItemRepository;
import URaphael.raphy.repository.PurchasedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PurchasedService {
    @Autowired
    private ShoppingCartItemRepository cartRepository;

    @Autowired
    private PurchasedRepository purchasedRepository;

    @Transactional
    public List<Purchased> checkout(Long customerId) {
        List<ShoppingCartItem> cartItems = cartRepository.findByCustomerId(customerId);

        for (ShoppingCartItem cart : cartItems) {
            Purchased purchased = new Purchased();
            purchased.setProductCode(cart.getProductCode());
            purchased.setQuantity(cart.getQuantity());
            purchased.setCustomerId(customerId);
            purchased.setDate(LocalDate.now());
            purchasedRepository.save(purchased);
        }

        // Clear the cart after successful purchase
        cartRepository.deleteAll(cartItems);

        return purchasedRepository.findByCustomerId(customerId);
    }

    public List<Purchased> getAllPurchases() {
        return purchasedRepository.findAll();
    }

    public List<Purchased> getCustomerPurchases(Long customerId) {
        return purchasedRepository.findByCustomerId(customerId);
    }

    public List<Purchased> getPurchasesByDateRange(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return purchasedRepository.findByDateBetween(start, end);
    }
}
