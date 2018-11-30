package com.example.victor.movies.model;

import com.google.gson.annotations.SerializedName;

public class FilmsItem{

	public static final int TYPE_MOVIE = 0;
	public static final int TYPE_YEAR = 1;

	private int type;

	@SerializedName("year")
	private int year;

	@SerializedName("image_url")
	private String imageUrl;

	@SerializedName("name")
	private String name;

	@SerializedName("rating")
	private double rating;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private int id;

	@SerializedName("localized_name")
	private String localizedName;

	public void setYear(int year){
		this.year = year;
	}

	public int getYear(){
		return year;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setRating(double rating){
		this.rating = rating;
	}

	public double getRating(){
		return rating;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setLocalizedName(String localizedName){
		this.localizedName = localizedName;
	}

	public String getLocalizedName(){
		return localizedName;
	}

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
 	public String toString(){
		return 
			"FilmsItem{" + 
			"year = '" + year + '\'' + 
			",image_url = '" + imageUrl + '\'' + 
			",name = '" + name + '\'' + 
			",rating = '" + rating + '\'' + 
			",description = '" + description + '\'' + 
			",id = '" + id + '\'' + 
			",localized_name = '" + localizedName + '\'' + 
			"}";
		}
}