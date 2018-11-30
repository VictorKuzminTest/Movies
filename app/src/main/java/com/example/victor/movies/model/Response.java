package com.example.victor.movies.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response {

	@SerializedName("films")
	private List<FilmsItem> films;

	public void setFilms(List<FilmsItem> films){
		this.films = films;
	}

	public List<FilmsItem> getFilms(){
		return films;
	}

	@Override
 	public String toString() {
		return 
			"Response{" + 
			"films = '" + films + '\'' + 
			"}";
		}
}