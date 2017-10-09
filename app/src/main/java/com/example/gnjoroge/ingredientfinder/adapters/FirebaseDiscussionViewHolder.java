package com.example.gnjoroge.ingredientfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.gnjoroge.ingredientfinder.Constants;
import com.example.gnjoroge.ingredientfinder.R;
import com.example.gnjoroge.ingredientfinder.model.Post;
import com.example.gnjoroge.ingredientfinder.ui.DiscussionForum;
import com.example.gnjoroge.ingredientfinder.ui.PostQuestionsList;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by gnjoroge on 10/8/17.
 */

public class FirebaseDiscussionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseDiscussionViewHolder(View itemView) {

        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindPost(Post post) {
        TextView postTitle = (TextView) mView.findViewById(R.id.title);
        TextView postAuthor = (TextView) mView.findViewById(R.id.author);
        TextView postBody = (TextView) mView.findViewById(R.id.body);

        postAuthor.setText(post.getAuthor());
        postBody.setText(post.getBody());
        postTitle.setText(post.getTitle());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Post> posts = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_POSTS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    posts.add(snapshot.getValue(Post.class));
                }

                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, PostQuestionsList.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("posts", Parcels.wrap(posts));
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
