package URaphael.raphy.controller;

import URaphael.raphy.model.Quantity;
import URaphael.raphy.service.QuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quantities")
@CrossOrigin(origins = "*")
public class QuantityController {
    @Autowired
    private QuantityService quantityService;

    @PostMapping("/add")
    public ResponseEntity<Quantity> addQuantity(
            @RequestParam String productCode,
            @RequestParam int quantity) {
        return ResponseEntity.ok(quantityService.addQuantity(productCode, quantity));
    }

    @PostMapping("/remove")
    public ResponseEntity<Quantity> removeQuantity(
            @RequestParam String productCode,
            @RequestParam int quantity) {
        return ResponseEntity.ok(quantityService.removeQuantity(productCode, quantity));
    }
}
