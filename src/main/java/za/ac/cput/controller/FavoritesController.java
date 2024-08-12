package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Favorites;
import za.ac.cput.service.FavoritesService;

import java.util.Set;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesController {

    private final FavoritesService favoritesService;

    @Autowired
    public FavoritesController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @PostMapping("/create")
    public ResponseEntity<Favorites> createFavorites(@RequestBody Favorites favorites) {
        Favorites createdFavorites = favoritesService.create(favorites);
        return new ResponseEntity<>(createdFavorites, HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Favorites> readFavorites(@PathVariable Long id) {
        Favorites favorites = favoritesService.read(id);
        return favorites != null ? new ResponseEntity<>(favorites, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<Favorites> updateFavorites(@RequestBody Favorites favorites) {
        Favorites updatedFavorites = favoritesService.update(favorites);
        return updatedFavorites != null ? new ResponseEntity<>(updatedFavorites, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFavorites(@PathVariable Long id) {
        favoritesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getall")
    public ResponseEntity<Set<Favorites>> getAllFavorites() {
        Set<Favorites> favoritesSet = favoritesService.getall();
        return new ResponseEntity<>(favoritesSet, HttpStatus.OK);
    }
}
