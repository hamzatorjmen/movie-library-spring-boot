package lu.movielibrary.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lu.movielibrary.entity.Movie;
import lu.movielibrary.repository.MovieRepository;
import lu.movielibrary.services.interfaces.IMovieInfoService;

@Component
public class MovieInfoServiceMySQLImpl implements IMovieInfoService{

	@Autowired
	private MovieRepository movieRepository;
	
	List<Movie> movies ;
	
	@Override
	public void addMovie(Movie movie) {
		movieRepository.save(movie);
	}

	@Override
	public void updateMovie(Movie movie, Long id) {
		movieRepository.updateMovie(movie.getTitle(), movie.getDirector(), movie.getReleaseDate(), movie.getType(), id);
	}

	@Override
	public Movie getMovieByName(String title) {
		return movieRepository.findByTitle(title);
	}

	@Override
	public void deleteMovie(Movie movie) {
		movieRepository.delete(movie);
	}

	@Override
	public List<Movie> getAllMovies() {
		movies = new ArrayList<>();
		movieRepository.findAll().forEach(movies::add);
		return movies;
	}

}
