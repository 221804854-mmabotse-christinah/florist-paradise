package za.ac.cput.factory;

import za.ac.cput.domain.Product;
import za.ac.cput.utility.Helper;

public class ProductFactory {
    public static Product createProduct(String productName, String productDescription, Long flowershopId, double price, String imageUrl, Integer quantity) {
        if (Helper.isNullOrEmpty(productName) || Helper.isNullOrEmpty(productDescription) || price <= 0 || Helper.isNullOrEmpty(imageUrl)) {
            return null;
        }
        long productId = Helper.generateUniqueID();
        return new Product.Builder()
                .setProductId(productId)
                .setProductName(productName)
                .setProductDescription(productDescription)
                .setFlowershopId(flowershopId)
                .setPrice(price)
                .setImageUrl(imageUrl)
                .setQuantity(quantity)
                .build();
    }
}
