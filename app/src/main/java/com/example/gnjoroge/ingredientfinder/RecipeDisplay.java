package com.example.gnjoroge.ingredientfinder;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecipeDisplay extends AppCompatActivity {

    @Bind(R.id.recipesView) ListView mRecipesView;

    public ArrayList<Recipe> mRecipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_display);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String recipe = intent.getStringExtra("recipe");
        findRecipe(recipe);
    }

    //returning response after user enters a search word
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        ButterKnife.bind(this);

        final MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
//
                findRecipe(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


  //accessing the API
    private void findRecipe(String recipe) {

        final RecipePuppyService recipePuppyService = new RecipePuppyService();
        recipePuppyService.findRecipe(recipe, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {

               mRecipes = recipePuppyService .processResults(response);
                RecipeDisplay.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        String[] recipeResults = new String[mRecipes.size()];
                        for(int i = 0; i < recipeResults.length; i++) {
                            recipeResults[i] = mRecipes.get(i).getTitle();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(RecipeDisplay.this, android.R.layout.simple_list_item_1, recipeResults);
                        mRecipesView.setAdapter(adapter);

                    }
                });

            }
        });
    }
}
