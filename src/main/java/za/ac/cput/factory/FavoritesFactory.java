package za.ac.cput.factory;

import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Favorites;
import za.ac.cput.domain.Product;
import za.ac.cput.utility.Helper;

public class FavoritesFactory {
    public static Favorites createFavorites(Product product, Customer customer) {
        if ((customer == null) || (product == null)) {
            return null;
        }
        long favoriteId = Helper.generateUniqueID();
        return new Favorites.Builder()
                .setFavoriteId(favoriteId)
                .setProduct(product)
                .setCustomer(customer)
                .build();
    }
}
