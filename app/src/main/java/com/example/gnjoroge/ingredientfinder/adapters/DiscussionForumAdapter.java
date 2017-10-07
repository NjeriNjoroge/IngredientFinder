package com.example.gnjoroge.ingredientfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gnjoroge.ingredientfinder.R;
import com.example.gnjoroge.ingredientfinder.model.Post;
import com.example.gnjoroge.ingredientfinder.ui.DiscussionForum;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gnjoroge on 10/8/17.
 */

public class DiscussionForumAdapter extends RecyclerView.Adapter<DiscussionForumAdapter.DiscussionForumViewHolder> {

    private static final String TAG = "DiscussionForumAdapter";
    private ArrayList<Post> mPosts = new ArrayList<>();
    private Context mContext;

    public DiscussionForumAdapter(Context context, ArrayList<Post> posts) {
        mContext = context;
        mPosts = posts;
    }

    public void addPost(Post post) {

        mPosts.add(post);
    }

    @Override
    public DiscussionForumAdapter.DiscussionForumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        inflater.from(parent.getContext()).inflate(R.layout.post_forum, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_forum, parent, false);

        DiscussionForumViewHolder viewHolder = new DiscussionForumViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DiscussionForumAdapter.DiscussionForumViewHolder holder, int position) {

        holder.bindPost(mPosts.get(position));
    }

    public int getItemCount() {

        return mPosts.size();
    }

    public class DiscussionForumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.title) TextView mTitle;
        @Bind(R.id.body) TextView mBody;
        @Bind(R.id.author) TextView mAuthor;

        private Context mContext;

        public DiscussionForumViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, DiscussionForum.class);
            intent.putExtra("position", itemPosition);
//            intent.putExtra("posts", Parcel.wrap(mPosts));
            mContext.startActivity(intent);

        }

        public void bindPost(Post post) {

            mTitle.setText(post.getTitle());
            mBody.setText(post.getBody());
            mAuthor.setText(post.getAuthor());


        }

    }

}
