package za.ac.cput.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Product;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductFactoryTest {

    @Test
    void testCreateProduct() {
        String productName = "Sunflower";
        String productDescription = "The common sunflower is a species of large annual forb of the daisy family Asteraceae.";
        Long flowershopId = 1L;
        double price = 29.99;
        String imageUrl = "https://cdn.prod.website-files.com/628ee8cd8f04ca5405cebd16/652e201aee89d21a4173b839_Growing%20Sunflower-banner.jpg";
        Integer quantity = 5;

        Product product = ProductFactory.createProduct(productName, productDescription,flowershopId, price, imageUrl, quantity);

        Assertions.assertNotNull(product);
        Assertions.assertEquals(productName, product.getProductName());
        Assertions.assertEquals(productDescription, product.getProductDescription());
        Assertions.assertEquals(flowershopId, product.getFlowershopId());
        Assertions.assertEquals(price, product.getPrice());
        Assertions.assertEquals(imageUrl, product.getImageUrl());
        Assertions.assertEquals(quantity, product.getQuantity());

        System.out.println(product);
    }

    @Test
    void testCreateProductWithInvalidInputs() {
        String invalidProductName = "";
        String invalidProductDescription = "";
        Long invalidFlowershopId = 0L;
        double invalidPrice = 0;
        String invalidImageUrl = "";
        Integer invalidQuantity = 0;

        Product product = ProductFactory.createProduct(invalidProductName, invalidProductDescription, invalidFlowershopId, invalidPrice, invalidImageUrl, invalidQuantity);

        Assertions.assertNull(product);
        System.out.println(product);
    }
}
