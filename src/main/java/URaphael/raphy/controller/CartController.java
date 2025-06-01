package URaphael.raphy.controller;

import URaphael.raphy.model.ShoppingCartItem;
import URaphael.raphy.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/{customerId}")
    public ResponseEntity<ShoppingCartItem> addToCart(
            @PathVariable Long customerId,
            @RequestParam String productCode,
            @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.addToCart(customerId, productCode, quantity));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<ShoppingCartItem>> getCustomerCart(@PathVariable Long customerId) {
        return ResponseEntity.ok(cartService.getCustomerCart(customerId));
    }
}
