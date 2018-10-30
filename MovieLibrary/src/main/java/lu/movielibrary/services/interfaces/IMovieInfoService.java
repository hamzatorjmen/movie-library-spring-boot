package lu.movielibrary.services.interfaces;

import lu.movielibrary.entity.Movie;

public interface IMovieInfoService {
	public void addMovie(Movie movie);
	public void updateMovie(Movie movie, String title);
	public Movie getMovieByName(String title);
	public void deleteMovie(Movie movie);
}
