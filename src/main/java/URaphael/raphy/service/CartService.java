package URaphael.raphy.service;

import URaphael.raphy.model.ShoppingCartItem;
import URaphael.raphy.repository.ShoppingCartItemRepository;
import URaphael.raphy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private ShoppingCartItemRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public ShoppingCartItem addToCart(Long customerId, String productCode, int quantity) {
        // Verify product exists
        productRepository.findById(productCode)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ShoppingCartItem existingCart = cartRepository.findByCustomerIdAndProductCode(customerId, productCode);
        if (existingCart != null) {
            existingCart.setQuantity(existingCart.getQuantity() + quantity);
            return cartRepository.save(existingCart);
        }

        ShoppingCartItem cart = new ShoppingCartItem();
        cart.setCustomerId(customerId);
        cart.setProductCode(productCode);
        cart.setQuantity(quantity);
        return cartRepository.save(cart);
    }

    public List<ShoppingCartItem> getCustomerCart(Long customerId) {
        return cartRepository.findByCustomerId(customerId);
    }
}
