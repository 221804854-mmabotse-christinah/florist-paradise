package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Cart;
import za.ac.cput.service.CartService;

import java.util.Set;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/create")
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        Cart newCart = cartService.create(cart);
        return new ResponseEntity<>(newCart, HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Cart> readCart(@PathVariable Long id) {
        Cart cart = cartService.read(id);
        return cart != null ? new ResponseEntity<>(cart, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
        Cart updatedCart = cartService.update(cart);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getall")
    public ResponseEntity<Set<Cart>> getAllCarts() {
        Set<Cart> carts = cartService.getall();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }
}
