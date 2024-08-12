package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Product;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.service.ProductService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private Product product;
    private Set<Product> productSet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        product = ProductFactory.createProduct(
                "Sunflower",
                "The common sunflower is a species of large annual forb of the daisy family Asteraceae.",
                2L,
                19.55,
                "https://cdn.prod.website-files.com/628ee8cd8f04ca5405cebd16/652e201aee89d21a4173b839_Growing%20Sunflower-banner.jpg",
                2
        );

        productSet = new HashSet<>();
        productSet.add(product);
    }

    @Test
    @Order(1)
    void testCreateProduct() {
        when(productService.create(any(Product.class))).thenReturn(product);

        ResponseEntity<Product> response = productController.createProduct(product);
        assertNotNull(response.getBody());
        assertEquals(product, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(productService, times(1)).create(product);
        System.out.println("Created Product: " + response.getBody());
    }

    @Test
    @Order(2)
    void testReadProduct() {
        when(productService.read(anyLong())).thenReturn(product);

        ResponseEntity<Product> response = productController.readProduct(product.getProductId());
        assertNotNull(response.getBody());
        assertEquals(product, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productService, times(1)).read(product.getProductId());
        System.out.println("Read Product: " + response.getBody());
    }

    @Test
    @Order(3)
    void testUpdateProduct() {
        Product updatedProduct = new Product.Builder()
                .copy(product)
                .setPrice(3.0)
                .build();

        when(productService.update(any(Product.class))).thenReturn(updatedProduct);

        ResponseEntity<Product> response = productController.updateProduct(updatedProduct);
        assertNotNull(response.getBody());
        assertEquals(updatedProduct, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productService, times(1)).update(updatedProduct);
        System.out.println("Updated Product: " + response.getBody());
    }

    @Test
    @Order(4)
    void testDeleteProduct() {
        doNothing().when(productService).delete(anyLong());

        ResponseEntity<Void> response = productController.deleteProduct(product.getProductId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).delete(product.getProductId());
        System.out.println("Deleted Product Successfully");
    }

    @Test
    @Order(5)
    void testGetAllProducts() {
        when(productService.getall()).thenReturn(productSet);

        ResponseEntity<Set<Product>> response = productController.getAllProducts();
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        assertEquals(1, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productService, times(1)).getall();
        System.out.println("All Products: " + response.getBody());
    }
}
