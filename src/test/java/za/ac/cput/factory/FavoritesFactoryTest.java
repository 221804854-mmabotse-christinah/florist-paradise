package za.ac.cput.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Favorites;
import za.ac.cput.domain.Product;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FavoritesFactoryTest {

    @Test
    void testCreateFavorites() {
        Customer customer = new Customer.Builder()
                .setCustomerId(1L)
                .setName("Mabotse")
                .setLastName("Mosima")
                .setUsername("Mosima@gmail.com")
                .build();

        Product product = new Product.Builder()
                .setProductId(1L)
                .setProductName("The common sunflower is a species of large annual forb of the daisy family Asteraceae.")
                .setProductDescription("Sunflowers")
                .setPrice(10.99)
                .setImageUrl("https://cdn.prod.website-files.com/628ee8cd8f04ca5405cebd16/652e201aee89d21a4173b839_Growing%20Sunflower-banner.jpg")
                .build();

        Favorites favorites = FavoritesFactory.createFavorites(product, customer);

        Assertions.assertNotNull(favorites);
        Assertions.assertEquals(product, favorites.getProduct());
        Assertions.assertEquals(customer, favorites.getCustomer());
        System.out.println(favorites);

    }

    @Test
    void testCreateFavoriteDifferentProducts() {
        // Arrange
        Customer customer = new Customer.Builder()
                .setCustomerId(1L)
                .setName("Mabotse")
                .setLastName("Mosima")
                .setUsername("Mosima@gmail.com")
                .build();

        Product product = new Product.Builder()
                .setProductId(2L)
                .setProductName("A rose is a flower, often with a pleasant smell, which grows on a bush with stems that have sharp points called thorns on them.")
                .setProductDescription("Roses")
                .setPrice(10.99)
                .setImageUrl("https://www.collinsdictionary.com/images/full/rose_277351964.jpg")
                .build();
        Favorites favorites = FavoritesFactory.createFavorites(product, customer);

        // Assert
        Assertions.assertNotNull(favorites);
        Assertions.assertEquals(product, favorites.getProduct());
        Assertions.assertEquals(customer, favorites.getCustomer());
        System.out.println(favorites);

    }
}
