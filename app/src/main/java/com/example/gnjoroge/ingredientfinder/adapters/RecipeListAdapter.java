package com.example.gnjoroge.ingredientfinder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gnjoroge.ingredientfinder.R;
import com.example.gnjoroge.ingredientfinder.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gnjoroge on 10/3/17.
 */

//this adapter displays the recipe image, ingredients, and title in the recipeDisplay Activity

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {


    private static final String TAG = "RecipeListAdapter";
    private ArrayList<Recipe> mRecipes = new ArrayList<>();
    private Context mContext;

    public RecipeListAdapter(Context context, ArrayList<Recipe> recipes) {
        mContext = context;
        mRecipes = recipes;
    }

    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_display_list, parent, false);

        RecipeViewHolder viewHolder = new RecipeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeListAdapter.RecipeViewHolder holder, int position) {

        holder.bindRecipe(mRecipes.get(position));
    }

    @Override
    public int getItemCount() {

        return mRecipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.foodImageView) ImageView mFoodImageView ;
        @Bind(R.id.titleView) TextView mTtileView;
        @Bind(R.id.ingredientView) TextView mIngredientView;


        private Context mContext;

    public RecipeViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindRecipe (Recipe recipe) {

            mIngredientView.setText(recipe.getIngredients());
            mTtileView.setText(recipe.getTitle());


            if(recipe.getThumbnail().isEmpty()) {
                //loads the image
                Picasso.with(mContext)
                        .load(R.drawable.logo)
                        .placeholder(R.drawable.logo)
                        .resize(130,100)
                        .centerCrop()
                        .into(mFoodImageView);
            } else {

                Picasso.with(mContext)
                        .load(recipe.getThumbnail())
                        .resize(130,100)
                        .centerCrop()
                        .into(mFoodImageView);
            }

        }

    }
}
