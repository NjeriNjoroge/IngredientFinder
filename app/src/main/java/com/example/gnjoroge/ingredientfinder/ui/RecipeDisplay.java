package com.example.gnjoroge.ingredientfinder.ui;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.gnjoroge.ingredientfinder.R;
import com.example.gnjoroge.ingredientfinder.model.Recipe;
import com.example.gnjoroge.ingredientfinder.services.RecipePuppyService;
import com.example.gnjoroge.ingredientfinder.adapters.RecipeListAdapter;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecipeDisplay extends AppCompatActivity {

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    //calling the RecipeListAdapter created
    private RecipeListAdapter mAdapter;

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

                        //using the created adapter and recycler view to display the recipes
                        mAdapter = new RecipeListAdapter(getApplicationContext(), mRecipes);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecipeDisplay.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                    }
                });

            }
        });
    }
}
