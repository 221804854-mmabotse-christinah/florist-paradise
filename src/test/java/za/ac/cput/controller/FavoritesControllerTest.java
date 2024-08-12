package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Favorites;
import za.ac.cput.domain.Product;
import za.ac.cput.factory.FavoritesFactory;
import za.ac.cput.service.FavoritesService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FavoritesControllerTest {

    @Mock
    private FavoritesService favoritesService;

    @InjectMocks
    private FavoritesController favoritesController;

    private Favorites favorites;
    private Set<Favorites> favoritesSet;

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

        favorites = FavoritesFactory.createFavorites(product, customer);

        favoritesSet = new HashSet<>();
        favoritesSet.add(favorites);
    }

    @Test
    @Order(1)
    void testCreateFavorites() {
        when(favoritesService.create(any(Favorites.class))).thenReturn(favorites);

        ResponseEntity<Favorites> response = favoritesController.createFavorites(favorites);
        assertNotNull(response.getBody());
        assertEquals(favorites, response.getBody());
        verify(favoritesService, times(1)).create(favorites);
        System.out.println("Created Favorites: " + response.getBody());
    }

    @Test
    @Order(2)
    void testReadFavorites() {
        when(favoritesService.read(anyLong())).thenReturn(favorites);

        ResponseEntity<Favorites> response = favoritesController.readFavorites(favorites.getFavoriteId());
        assertNotNull(response.getBody());
        assertEquals(favorites, response.getBody());
        verify(favoritesService, times(1)).read(favorites.getFavoriteId());
        System.out.println("Read Favorites: " + response.getBody());
    }

    @Test
    @Order(3)
    void testUpdateFavorites() {
        Favorites updatedFavorites = new Favorites.Builder()
                .copy(favorites)
                .build();

        when(favoritesService.update(any(Favorites.class))).thenReturn(updatedFavorites);

        ResponseEntity<Favorites> response = favoritesController.updateFavorites(updatedFavorites);
        assertNotNull(response.getBody());
        assertEquals(updatedFavorites, response.getBody());
        verify(favoritesService, times(1)).update(updatedFavorites);
        System.out.println("Updated Favorites: " + response.getBody());
    }

    @Test
    @Order(4)
    void testDeleteFavorites() {
        doNothing().when(favoritesService).delete(anyLong());

        ResponseEntity<Void> response = favoritesController.deleteFavorites(favorites.getFavoriteId());
        assertEquals(204, response.getStatusCodeValue());
        verify(favoritesService, times(1)).delete(favorites.getFavoriteId());
        System.out.println("Deleted Favorites Successfully");
    }

    @Test
    @Order(5)
    void testGetAllFavorites() {
        when(favoritesService.getall()).thenReturn(favoritesSet);

        ResponseEntity<Set<Favorites>> response = favoritesController.getAllFavorites();
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        assertEquals(1, response.getBody().size());
        verify(favoritesService, times(1)).getall();
        System.out.println("All Favorites: " + response.getBody());
    }
}
