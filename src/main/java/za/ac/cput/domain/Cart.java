package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private long cartId;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "subtotal", nullable = false)
    private double subtotal;
    @Column(name = "created_Date", nullable = false)
    private LocalDate createdDate;

    protected Cart() {
    }

    private Cart(Builder builder) {
        this.cartId = builder.cartId;
        this.customer = builder.customer;
        this.product = builder.product;
        this.price = builder.price;
        this.quantity = builder.quantity;
        this.subtotal = builder.subtotal;
        this.createdDate = builder.createdDate;
    }

    //Getters
    public long getCartId() {
        return cartId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(cartId, cart.cartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId);
    }

    @Override
    public String toString() {
        return "Cart \n" +
                "cart ID = " + cartId + '\n'+
                "customer ID = " + customer.getCustomerId() + '\n'+
                "product ID = " + product.getProductId() + '\n'+
                "price = " + price + '\n'+
                "quantity = " + quantity + '\n'+
                "subtotal = " + subtotal + '\n'+
                "createdDate = " + createdDate + '\n';
    }

    public static class Builder {
        private long cartId;
        private Customer customer;
        private Product product;
        private double price;
        private int quantity;
        private double subtotal;
        private LocalDate createdDate;

        public Builder setCartId(long cartId) {
            this.cartId = cartId;
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

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setSubtotal(double subtotal) {
            this.subtotal = subtotal;
            return this;
        }

        public Builder setCreatedDate(LocalDate createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder copy(Cart cart) {
            this.cartId = cart.cartId;
            this.customer = cart.customer;
            this.product = cart.product;
            this.price = cart.price;
            this.quantity = cart.quantity;
            this.subtotal = cart.subtotal;
            this.createdDate = cart.createdDate;
            return this;
        }
        public  Cart build(){
            return  new Cart(this);
        }
    }
}
