package URaphael.raphy.controller;

import URaphael.raphy.model.Purchased;
import URaphael.raphy.service.PurchasedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/checkout")
@CrossOrigin(origins = "*")
public class PurchasedController {
    @Autowired
    private PurchasedService purchasedService;

    @PostMapping("/{customerId}")
    public ResponseEntity<List<Purchased>> checkout(@PathVariable Long customerId) {
        return ResponseEntity.ok(purchasedService.checkout(customerId));
    }

    @GetMapping
    public ResponseEntity<List<Purchased>> getAllPurchases() {
        return ResponseEntity.ok(purchasedService.getAllPurchases());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Purchased>> getCustomerPurchases(@PathVariable Long customerId) {
        return ResponseEntity.ok(purchasedService.getCustomerPurchases(customerId));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Purchased>> getPurchasesByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return ResponseEntity.ok(purchasedService.getPurchasesByDateRange(startDate, endDate));
    }
}
