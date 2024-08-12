package za.ac.cput.domain;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Long favoriteId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    protected Favorites() {
    }

    public Favorites(Builder builder) {
        this.favoriteId = builder.favoriteId;
        this.customer = builder.customer;
        this.product = builder.product;
    }

    //Getters
    public long getFavoriteId() {return favoriteId;}
    public Customer getCustomer() {
        return customer;
    }
    public Product getProduct() {
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorites favorites = (Favorites) o;
        return Objects.equals(customer, favorites.customer) && Objects.equals(product, favorites.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, product);
    }

    @Override
    public String toString() {
        return "Favorites{" +
                "favoriteId=" + favoriteId +
                "customer=" + customer +
                ", product=" + product +
                '}';
    }

    public static class Builder {
        private Long favoriteId;
        private Customer customer;
        private Product product;

        //setters
        public Builder setFavoriteId(Long favoriteId) {
            this.favoriteId = favoriteId;
            return this;
        }
        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }
        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder copy(Favorites favorites) {
            this.favoriteId = favorites.favoriteId;
            this.customer = favorites.customer;
            this.product = favorites.product;
            return this;
        }

        public Favorites build() {
            return new Favorites(this);
        }
    }

}
