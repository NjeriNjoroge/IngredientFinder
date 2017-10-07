package com.example.gnjoroge.ingredientfinder.ui;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gnjoroge.ingredientfinder.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

   // @Bind(R.id.viewPager) ViewPager mViewPager;
    @Bind(R.id.searchRecipe) Button mSearchRecipe;
    @Bind(R.id.discussionForum) Button mDiscuss;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSearchRecipe.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, RecipeDisplay.class);
                startActivity(intent);
            }

        });

        mDiscuss.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, DiscussionForum.class);
                startActivity(intent);
            }
        });

    }
}

