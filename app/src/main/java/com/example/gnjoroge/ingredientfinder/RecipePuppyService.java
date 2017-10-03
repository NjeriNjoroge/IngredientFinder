package com.example.gnjoroge.ingredientfinder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by gnjoroge on 10/3/17.
 */

public class RecipePuppyService {

    public static void findRecipe (String recipe, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.PUPPY_BASE_URL)
                .newBuilder();
//        urlBuilder.addQueryParameter(Constants.PUPPY_RECIPE_QUERY, recipe);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Recipe> processResults(Response response) {

        ArrayList<Recipe> recipes = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {

                JSONObject recipePuppyJSON = new JSONObject(jsonData);
                JSONArray recipesJSON = recipePuppyJSON.getJSONArray("results");
                for (int i = 0; i < recipesJSON.length(); i++) {
                    JSONObject foodJSON = recipesJSON.getJSONObject(i);
                    String title = foodJSON.getString("title");
                    String href = foodJSON.getString("href");
                    String ingredients = foodJSON.getString("ingredients");
                    String thumbnail = foodJSON.getString("thumbnail");

                    Recipe recipe = new Recipe(title, href, ingredients, thumbnail);
                    recipes.add(recipe);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
            return recipes;
    }

}
