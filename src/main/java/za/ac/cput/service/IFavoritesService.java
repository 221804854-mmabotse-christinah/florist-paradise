package za.ac.cput.service;

import za.ac.cput.domain.Favorites;

import java.util.Set;

public interface IFavoritesService {
    Set<Favorites> getall();

    Favorites create(Favorites favorites);

    Favorites read(Long favoriteId);

    Favorites update(Favorites favorites);

    void delete(Long favoriteId);
}
