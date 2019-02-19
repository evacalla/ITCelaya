package com.itcelaya.village.Controller;

import com.itcelaya.village.Exception.ResourceNotFoundException;
import com.itcelaya.village.Model.Movie;
import com.itcelaya.village.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class Movies {

    @Autowired
    public MovieRepository movieRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Movie> getAllMovie() {
        return movieRepository.findAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Movie createMovie(@Valid @RequestBody Movie newMovie) {
        return movieRepository.save(newMovie);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Movie getMovie (@PathVariable("id") Long id) {
        return  movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Movie updateMovie(@PathVariable("id") Long id, @Valid @RequestBody Movie editMovie) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
        movie.description = editMovie.description;
        movie.duration = editMovie.duration;

        Movie updateMovie = movieRepository.save(movie);

        return updateMovie;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deteleMovie(@PathVariable("id") Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));

        movieRepository.delete(movie);

        return ResponseEntity.ok().build();
    }
}
