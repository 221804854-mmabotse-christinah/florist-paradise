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
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Review;
import za.ac.cput.factory.ReviewFactory;
import za.ac.cput.service.ReviewService;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    private Review review;
    private Set<Review> reviewSet;

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

        review = ReviewFactory.createProductReview(product, customer, 3, "favourite flower", LocalDate.now());

        reviewSet = new HashSet<>();
        reviewSet.add(review);
    }

    @Test
    @Order(1)
    void testCreate() {
        when(reviewService.create(any(Review.class))).thenReturn(review);

        ResponseEntity<Review> response = reviewController.createReview(review);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(review, response.getBody());
        verify(reviewService, times(1)).create(review);
        System.out.println("Created Review: " + response.getBody());
    }

    @Test
    @Order(2)
    void testRead() {
        when(reviewService.read(anyLong())).thenReturn(review);

        ResponseEntity<Review> response = reviewController.readReview(review.getReviewId());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(review, response.getBody());
        verify(reviewService, times(1)).read(review.getReviewId());
        System.out.println("Read Review: " + response.getBody());
    }

    @Test
    @Order(3)
    void testUpdate() {
        Review updatedReview = new Review.Builder().copy(review)
                .setComment("Updated comment").build();

        when(reviewService.update(any(Review.class))).thenReturn(updatedReview);

        ResponseEntity<Review> response = reviewController.updateReview(updatedReview);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedReview, response.getBody());
        verify(reviewService, times(1)).update(updatedReview);
        System.out.println("Updated Review: " + response.getBody());
    }

    @Test
    @Order(4)
    void testDelete() {
        doNothing().when(reviewService).delete(anyLong());

        ResponseEntity<Void> response = reviewController.deleteReview(review.getReviewId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(reviewService, times(1)).delete(review.getReviewId());
        System.out.println("Deleted Review Successfully");
    }

    @Test
    @Order(5)
    void testGetAll() {
        when(reviewService.getall()).thenReturn(reviewSet);

        ResponseEntity<Set<Review>> response = reviewController.getAllReviews();
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        assertEquals(1, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(reviewService, times(1)).getall();
        System.out.println("All Reviews: " + response.getBody());
    }
}
