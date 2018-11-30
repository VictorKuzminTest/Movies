package com.example.victor.movies.presenter;

import com.example.victor.movies.model.FilmsItem;

public interface Presenter {

    void getMovies();

    void showFragment(int id, FilmsItem item);
}
