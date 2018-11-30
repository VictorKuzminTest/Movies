package com.example.victor.movies.views;

import com.example.victor.movies.model.FilmsItem;
import com.example.victor.movies.model.Response;

public interface ContractView {

    void showToast(String s);

    void displayMovies(Response response);

    void displayError(String s);

    void showFragment(int id, FilmsItem item);

    void setFilm(FilmsItem film);
}
