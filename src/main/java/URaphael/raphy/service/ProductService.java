package URaphael.raphy.service;

import URaphael.raphy.model.Product;
import URaphael.raphy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        if (productRepository.findByProductCode(product.getProductCode()) != null) {
            throw new RuntimeException("Product code already exists");
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStockQuantity(productDetails.getStockQuantity());
        product.setCategory(productDetails.getCategory());
        product.setImageUrl(productDetails.getImageUrl());
        product.setActive(productDetails.isActive());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setActive(false);
        productRepository.save(product);
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product getProductByCode(String productCode) {
        return productRepository.findByProductCode(productCode);
    }

    public List<Product> getAllProducts() {
        return productRepository.findByActive(true);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryAndActive(category, true);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.searchProducts(keyword);
    }

    @Transactional
    public void updateStock(String productCode, int quantity) {
        Product product = productRepository.findByProductCode(productCode);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        int newStock = product.getStockQuantity() - quantity;
        if (newStock < 0) {
            throw new RuntimeException("Insufficient stock");
        }

        product.setStockQuantity(newStock);
        productRepository.save(product);
    }
}
