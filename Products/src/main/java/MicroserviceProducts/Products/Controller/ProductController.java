package MicroserviceProducts.Products.Controller;

import MicroserviceProducts.Products.Model.Product;
import MicroserviceProducts.Products.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/all")
    public @ResponseBody Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

@GetMapping("/id/{id}")
public ResponseEntity<Product> getById(@PathVariable Integer id) {
    Optional<Product> product = productRepository.findById(id);
    if (product.isPresent()) {
        return ResponseEntity.ok(product.get());
    } else {
        return ResponseEntity.notFound().build();
    }
}
}