package za.ac.cput.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.core.annotation.Order;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Product;

import java.time.LocalDate;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartFactoryTest {

    @Test
    @Order(1)
    void testCreateCart() {
        // Arrange
        Customer customer = new Customer.Builder()
                .setCustomerId(1L)
                .setName("Mabotse")
                .setLastName("Mosima")
                .setUsername("Mosima@gmail.com")
                .build();

        Product product = new Product.Builder()
                .setProductId(2L)
                .setProductDescription("The common sunflower is a species of large annual forb of the daisy family Asteraceae.")
                .setProductName("Sunflowers")
                .setPrice(10.99)
                .setImageUrl("https://cdn.prod.website-files.com/628ee8cd8f04ca5405cebd16/652e201aee89d21a4173b839_Growing%20Sunflower-banner.jpg")
                .build();

        double price = 10.99;
        int quantity = 2;
        double subtotal = 21.98;
        LocalDate createdDate = LocalDate.now();

        // Act
        Cart cart = CartFactory.createCart(customer, product, price, quantity, subtotal, createdDate);

        // Assert
        Assertions.assertNotNull(cart);
        Assertions.assertEquals(customer, cart.getCustomer());
        Assertions.assertEquals(product, cart.getProduct());
        Assertions.assertEquals(price, cart.getPrice());
        Assertions.assertEquals(quantity, cart.getQuantity());
        Assertions.assertEquals(subtotal, cart.getSubtotal());
        Assertions.assertEquals(createdDate, cart.getCreatedDate());
        System.out.println(cart);
    }

    @Test
    @Order(1)
    void createCart() {
        Customer customer = new Customer();
        Product product = new Product();
        double price = 25.99;
        int quantity = 2;
        double subtotal = 51.98;
        LocalDate cartDateB = LocalDate.of(2021, 8, 10);

        Cart cartA = CartFactory.createCart(customer, product, price, quantity, subtotal, cartDateB);

        Assertions.assertNotNull(cartA);
        Assertions.assertEquals(customer, cartA.getCustomer());
        Assertions.assertEquals(product, cartA.getProduct());
        Assertions.assertEquals(price, cartA.getPrice());
        Assertions.assertEquals(quantity, cartA.getQuantity());
        Assertions.assertEquals(subtotal, cartA.getSubtotal());
        Assertions.assertEquals(cartDateB, cartA.getCreatedDate());
        System.out.println(cartA);
    }
}