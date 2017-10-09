//package com.example.gnjoroge.ingredientfinder.ui;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//
//import com.example.gnjoroge.ingredientfinder.Constants;
//import com.example.gnjoroge.ingredientfinder.R;
//import com.example.gnjoroge.ingredientfinder.adapters.FirebaseDiscussionViewHolder;
//import com.example.gnjoroge.ingredientfinder.model.Post;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//
//public class PostedQuestionsList extends AppCompatActivity {

//    private DatabaseReference mPostReference;
//    private FirebaseRecyclerAdapter mFirebaseAdapter;
//
//    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_discussion_forum);
//        ButterKnife.bind(this);
//
//        mPostReference = FirebaseDatabase.getInstance()
//                .getReference(Constants.FIREBASE_CHILD_POSTS);
//        setUpFirebaseAdapter();
//    }
//
//    private void setUpFirebaseAdapter() {
//
//        mFirebaseAdapter = new FirebaseRecyclerAdapter <Post, FirebaseDiscussionViewHolder> (Post.class, R.layout.post_forum, FirebaseDiscussionViewHolder.class, mPostReference) {
//
//            @Override
//            protected void populateViewHolder(FirebaseDiscussionViewHolder viewHolder, Post model, int position) {
//
//                viewHolder.bindPost(model);
//            }
//        };
//
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mFirebaseAdapter);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mFirebaseAdapter.cleanup();
//    }
//}
