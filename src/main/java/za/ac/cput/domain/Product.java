package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long productId;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "flowershop_id", nullable = false)
    private long flowershopId;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "quantity")
    private int quantity;

    //This should be protected , @Mabotse Something is wrong with your review class , you're building a review with an empty product and customer
    public Product() {
    }
    private Product(Builder builder) {
        this.productId = builder.productId;
        this.productName = builder.productName;
        this.productDescription = builder.productDescription;
        this.flowershopId = builder.flowershopId;
        this.price = builder.price;
        this.imageUrl = builder.imageUrl;
        this.quantity = builder.quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public long getFlowershopId() {
        return flowershopId;
    }

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) && flowershopId == product.flowershopId && Double.compare( price, product.price) == 0 && Objects.equals(productName, product.productName) && Objects.equals(productDescription, product.productDescription) && Objects.equals(imageUrl, product.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productDescription, flowershopId, price, imageUrl, quantity);
    }
    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", flowershopId=" + flowershopId +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public static class Builder {
        private Long productId;
        private String productName;
        private String productDescription;
        private long flowershopId;
        private double price;
        private String imageUrl;
        private int quantity;

        public Builder setProductId(Long productId) {
            this.productId = productId;
            return this;
        }

        public Builder setProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder setProductDescription(String productDescription) {
            this.productDescription = productDescription;
            return this;
        }

        public Builder setFlowershopId(long flowershopId) {
            this.flowershopId = flowershopId;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder copy(Product products) {
            this.productId = products.productId;
            this.productName = products.productName;
            this.productDescription = products.productDescription;
            this.flowershopId = products.flowershopId;
            this.price = products.price;
            this.imageUrl = products.imageUrl;
            this.quantity = products.quantity;
            return this;

        }

        public Product build() {
            return new Product(this);
        }

    }


}