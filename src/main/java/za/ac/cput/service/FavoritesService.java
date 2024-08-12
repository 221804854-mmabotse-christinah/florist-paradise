package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.repository.FavoritesRespository;
import za.ac.cput.domain.Favorites;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FavoritesService implements IFavoritesService {
    private FavoritesRespository repository;
    @Autowired
    FavoritesService(FavoritesRespository repository){this.repository = repository;}

    @Override
    public Set<Favorites> getall() {
        return repository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Favorites create(Favorites favorites) {
        return repository.save(favorites);
    }

    @Override
    public Favorites read(Long favoriteId) {
        return repository.findByFavoriteId(favoriteId);
    }

    @Override
    public Favorites update(Favorites favorites) {
        return repository.save(favorites);
    }

    @Override
    public void delete(Long favoriteId) {
        repository.deleteById(favoriteId);
    }
}
