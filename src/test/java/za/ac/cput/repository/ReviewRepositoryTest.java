package za.ac.cput.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Review;
import za.ac.cput.factory.ReviewFactory;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    private Review review;

    @BeforeEach
    void setUp() {
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
        reviewRepository.save(review);
    }

    @Test
    @Order(1)
    void testFindByReviewId() {
        Review foundReview = reviewRepository.findByReviewId(review.getReviewId());
        assertNotNull(foundReview);
        assertEquals(review.getReviewId(), foundReview.getReviewId());
        assertEquals(review.getCustomer(), foundReview.getCustomer());
        assertEquals(review.getProduct(), foundReview.getProduct());
        assertEquals(review.getRating(), foundReview.getRating());
        assertEquals(review.getComment(), foundReview.getComment());
        assertEquals(review.getReviewDate(), foundReview.getReviewDate());
    }

    @Test
    @Order(2)
    void testSaveAndFindById() {
        Review newReview = new Review.Builder()
                .setCustomer(review.getCustomer())
                .setProduct(review.getProduct())
                .setRating(5)
                .setComment("Excellent")
                .setReviewDate(LocalDate.now())
                .build();
        reviewRepository.save(newReview);

        Optional<Review> foundReview = reviewRepository.findById(newReview.getReviewId());
        assertTrue(foundReview.isPresent());
        assertEquals(newReview.getReviewId(), foundReview.get().getReviewId());
    }

    @Test
    @Order(3)
    void testDeleteById() {
        reviewRepository.deleteById(review.getReviewId());
        Optional<Review> foundReview = reviewRepository.findById(review.getReviewId());
        assertFalse(foundReview.isPresent());
    }
}
