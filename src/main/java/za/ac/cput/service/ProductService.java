package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Product;
import za.ac.cput.repository.ProductRepository;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class ProductService implements IProductService {
    private ProductRepository repository;
    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Product> getall() {
        return repository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }

    @Override
    public Product read(Long productId) {
        return this.repository.findByProductId(productId);
    }

    @Override
    public Product update(Product product) {
        return repository.save(product);
    }

    @Override
    public void delete(Long productId) {repository.deleteById(productId);}
}
