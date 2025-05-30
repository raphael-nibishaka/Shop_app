package URaphael.raphy.service;

import URaphael.raphy.model.Quantity;
import URaphael.raphy.repository.QuantityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class QuantityService {
    @Autowired
    private QuantityRepository quantityRepository;

    public Quantity addQuantity(String productCode, int quantity) {
        Quantity q = new Quantity();
        q.setProductCode(productCode);
        q.setQuantity(quantity);
        q.setOperation("add");
        q.setDate(LocalDate.now());
        return quantityRepository.save(q);
    }

    public Quantity removeQuantity(String productCode, int quantity) {
        Quantity q = new Quantity();
        q.setProductCode(productCode);
        q.setQuantity(quantity);
        q.setOperation("remove");
        q.setDate(LocalDate.now());
        return quantityRepository.save(q);
    }
}
