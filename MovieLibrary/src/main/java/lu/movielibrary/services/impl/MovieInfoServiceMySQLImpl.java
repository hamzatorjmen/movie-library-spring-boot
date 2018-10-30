package lu.movielibrary.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lu.movielibrary.entity.Movie;
import lu.movielibrary.repository.MovieRepository;
import lu.movielibrary.services.interfaces.IMovieInfoService;

@Component
public class MovieInfoServiceMySQLImpl implements IMovieInfoService{

	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public void addMovie(Movie movie) {
		movieRepository.save(movie);
	}

	@Override
	public void updateMovie(Movie movie, String title) {
		movieRepository.updateMovie(movie.getTitle(), movie.getDirector(), movie.getReleaseDate(), movie.getType());
	}

	@Override
	public Movie getMovieByName(String title) {
		return null;
	}

	@Override
	public void deleteMovie(Movie movie) {
		// TODO Auto-generated method stub
		
	}

}
