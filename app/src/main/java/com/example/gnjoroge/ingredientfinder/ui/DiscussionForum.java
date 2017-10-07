package com.example.gnjoroge.ingredientfinder.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gnjoroge.ingredientfinder.Constants;
import com.example.gnjoroge.ingredientfinder.R;
import com.example.gnjoroge.ingredientfinder.adapters.DiscussionForumAdapter;
import com.example.gnjoroge.ingredientfinder.model.Post;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DiscussionForum extends AppCompatActivity {

    //displaying the questions
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    //getting the questions
    @Bind(R.id.inputAuthor) EditText mInputAuthor;
    @Bind(R.id.inputTitle) EditText mInputTitle;
    @Bind(R.id.inputBody) EditText mInputBody;
    @Bind(R.id.submitPost) Button mSubmitPost;

    //calling the PostDetailAdapter created
    private DiscussionForumAdapter mAdapter;


    ArrayList<Post> mPosts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_forum);
        ButterKnife.bind(this);

        //accessing the adapter created
        mAdapter = new DiscussionForumAdapter(getApplicationContext(), mPosts);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DiscussionForum.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mSubmitPost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String title = mInputTitle.getText().toString();
                //validating text fields are not empty
                if(TextUtils.isEmpty(title)) {
                    mInputTitle.setError("Title cannot be left empty");
                    return;
                }
                mInputTitle.setText("");

                String author = mInputAuthor.getText().toString();
                //validating text fields are not empty
                if(TextUtils.isEmpty(author)) {
                    mInputAuthor.setError("Author cannot be left empty");
                    return;
                }
                mInputAuthor.setText("");

                String body = mInputBody.getText().toString();
                //validating text fields are not empty
                if(TextUtils.isEmpty(body)) {
                    mInputBody.setError("Question body cannot be left empty");
                    return;
                }
                mInputBody.setText("");

                String newPost = title + author + body;

                Post post = new Post(title, author,body);
                mAdapter.addPost(post);

                if(view == mSubmitPost) {
                    DatabaseReference postRef = FirebaseDatabase
                            .getInstance()
                            .getReference(Constants.FIREBASE_CHILD_POSTS);
                    postRef.push().setValue(mPosts);
                }

            }
        });




    }



}
