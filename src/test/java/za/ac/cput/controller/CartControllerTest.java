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
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Product;
import za.ac.cput.factory.CartFactory;
import za.ac.cput.service.CartService;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    private Cart cart;
    private Set<Cart> cartSet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Customer customer = new Customer.Builder()
                .setCustomerId(1L)
                .setName("John")
                .setLastName("Doe")
                .setUsername("johndoe@gmail.com")
                .build();

        Product product = new Product.Builder()
                .setProductId(1L)
                .setProductName("Rose")
                .setProductDescription("Red Rose")
                .setPrice(2.5)
                .setQuantity(100)
                .setImageUrl("http://example.com/rose.jpg")
                .build();

        cart = CartFactory.createCart(customer, product,3, 2, 5, LocalDate.now());

        cartSet = new HashSet<>();
        cartSet.add(cart);
    }

    @Test
    @Order(1)
    void testCreate() {
        when(cartService.create(any(Cart.class))).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.createCart(cart);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cart, response.getBody());
        verify(cartService, times(1)).create(cart);
        System.out.println("Created Cart: " + response.getBody());
    }

    @Test
    @Order(2)
    void testRead() {
        when(cartService.read(anyLong())).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.readCart(cart.getCartId());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
        verify(cartService, times(1)).read(cart.getCartId());
        System.out.println("Read Cart: " + response.getBody());
    }

    @Test
    @Order(3)
    void testUpdate() {
        Cart updatedCart = new Cart.Builder()
                .copy(cart)
                .setQuantity(3)
                .build();

        when(cartService.update(any(Cart.class))).thenReturn(updatedCart);

        ResponseEntity<Cart> response = cartController.updateCart(updatedCart);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCart, response.getBody());
        verify(cartService, times(1)).update(updatedCart);
        System.out.println("Updated Cart: " + response.getBody());
    }

    @Test
    @Order(4)
    void testDelete() {
        doNothing().when(cartService).delete(anyLong());

        ResponseEntity<Void> response = cartController.deleteCart(cart.getCartId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(cartService, times(1)).delete(cart.getCartId());
        System.out.println("Deleted Cart Successfully");
    }

    @Test
    @Order(5)
    void testGetAll() {
        when(cartService.getall()).thenReturn(cartSet);

        ResponseEntity<Set<Cart>> response = cartController.getAllCarts();
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        assertEquals(1, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cartService, times(1)).getall();
        System.out.println("All Carts: " + response.getBody());
    }
}
