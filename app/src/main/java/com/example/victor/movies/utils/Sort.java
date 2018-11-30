package com.example.victor.movies.utils;

import com.example.victor.movies.model.FilmsItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {

    public static void sort(List<FilmsItem> films){
        Collections.sort(films, new Comparator<FilmsItem>() {
            @Override
            public int compare(FilmsItem film1, FilmsItem film2) {
                //first we sort movies in ascending order by their year of release
                if (film1.getYear() != film2.getYear()) {
                    return film1.getYear() - film2.getYear();
                } else {
                    //for the second sort we use negative to sort ratings in descending order
                    return (int)(-(film1.getRating() - film2.getRating()));
                }
            }
        });
    }
}
