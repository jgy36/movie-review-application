package com.jgy36.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository; // inject repository

    public List<Movie> allMovies() { // method will return a list of (class) Movie
        return movieRepository.findAll(); // call findAll method from repository
    }
}

