package com.example.victor.movies.network;

import com.example.victor.movies.model.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesInterface {

    @GET("films.json")
    Observable<Response> getMovies();
}
