package com.example.gnjoroge.ingredientfinder;

/**
 * Created by gnjoroge on 10/3/17.
 */

public class Recipe {


  //fields in the Recipe Puppy API
    private String title;
    private String href;
    private String ingredients;
    private String thumbnail;

    public Recipe (String title, String href, String ingredients, String thumbnail) {

        this.title = title;
        this.href = href;
        this.ingredients = ingredients;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getHref() {
        return href;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
