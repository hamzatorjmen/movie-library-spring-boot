package lu.movielibrary.services.interfaces;

import java.util.List;

import lu.movielibrary.entity.Movie;

public interface IMovieInfoService {
	public void addMovie(Movie movie);
	public void updateMovie(Movie movie, Long id);
	public Movie getMovieByName(String title);
	public Movie getMovieById(Long id);
	public List<Movie> getAllMovies();
	public void deleteMovie(Movie movie);
}
