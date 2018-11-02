package lu.movielibrary.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import lu.movielibrary.entity.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long>{	
	@Transactional
    @Modifying
    @Query("update Movie m set m.title = ?1, m.director = ?2, m.releaseDate = ?3, m.type = ?4 where m.id = ?5")
	void updateMovie(String title, String director, Date releaseDate, String type, Long id);
	
	Movie findByTitle(String title);
	Movie findById(Long id);
}
