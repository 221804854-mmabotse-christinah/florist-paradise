package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Product;
import za.ac.cput.utility.Helper;

import java.time.LocalDate;

public class CartFactory {

    public static Cart createCart(Customer customer,Product product, double price, int quantity, double subtotal, LocalDate createdDate) {
        if (product == null || customer == null || price == 0 || createdDate == null) {
            return null;
        }
        long cartId = Helper.generateUniqueID();
        return new Cart.Builder()
                .setCartId(cartId)
                .setCustomer(customer)
                .setProduct(product)
                .setPrice(price)
                .setQuantity(quantity)
                .setSubtotal(subtotal)
                .setCreatedDate(createdDate)
                .build();
    }
}
