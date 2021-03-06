package lu.movielibrary.controller.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lu.movielibrary.entity.Movie;
import lu.movielibrary.services.interfaces.IMovieInfoService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/library")
public class MovieController {
	
	@Autowired
	private IMovieInfoService movieInfoService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//@RequestMapping(value = "/movie", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping("/movie")
	public ResponseEntity<Void> addMovie(@RequestBody Movie movie) {
		movieInfoService.addMovie(movie);
		logger.info("Un film a été crée dans la base");
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	//@RequestMapping(value = "/movie", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping("/movie")
	public ResponseEntity<List<Movie>> getAllMovies(){
		List<Movie> movies = movieInfoService.getAllMovies();
		if (movies.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else {
			logger.info("Getting list of movies");
			return new ResponseEntity<>(movies, HttpStatus.OK);
		}
	}
	//@RequestMapping(value = "/movie/{title}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping("/movie/{title}")
	public ResponseEntity<Movie> getMovieByTitle(@PathVariable("title") String title){
		Movie movie = movieInfoService.getMovieByName(title) ;
		if (movie == null) {
			logger.info("L'objet film dont le titre : " + title + " est introuvable dans la base de donnée");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(movie, HttpStatus.FOUND);
	}
	@GetMapping("/movie/id/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id){
		Movie movie = movieInfoService.getMovieById(id) ;
		if (movie == null) {
			logger.info("L'objet film dont l'identifiant : " + id + " est introuvable dans la base de donnée");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info("L'objet film dont l'identifiant : " + id);
		return new ResponseEntity<>(movie, HttpStatus.OK);
	}
	//@RequestMapping(value = "/movie/{title}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping("/movie/{id}")
	public ResponseEntity<Void> updateMovie(@RequestBody Movie movie, @PathVariable("id") Long id) {
		
		Movie movie2 = movieInfoService.getMovieById(id);
		logger.info("recuperation du film...");
		movie2.setTitle(movie.getTitle());
		movie2.setDirector(movie.getDirector());
		movie2.setReleaseDate(movie.getReleaseDate());
		movie2.setType(movie.getType());
		movieInfoService.updateMovie(movie2, movie2.getId());
		logger.info("Le film dont le titre : " + id + " a été mise à jour");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	//@RequestMapping(value = "/movie", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping("/movie")
	public ResponseEntity<Void> deleteMovie(@RequestBody Movie movie){
		Movie movie2 = movieInfoService.getMovieByName(movie.getTitle());
		movieInfoService.deleteMovie(movie2);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/movie/{id}")
	public ResponseEntity<Void> deleteMovieById(@PathVariable Long id){
		Movie movie2 = movieInfoService.getMovieById(id);
		movieInfoService.deleteMovie(movie2);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
