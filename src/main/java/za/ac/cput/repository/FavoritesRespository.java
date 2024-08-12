package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Favorites;

public interface FavoritesRespository extends JpaRepository<Favorites, Long> {

    Favorites findByFavoriteId(Long favoriteId);
}
