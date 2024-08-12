package za.ac.cput.service;

import za.ac.cput.domain.Product;

import java.util.Set;

public interface IProductService {
    Set<Product> getall();

    Product create(Product product);

    Product read(Long productId);

    Product update(Product product);

    void delete(Long productId);
}
