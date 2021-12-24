package com.geektech.android3lesson2application.ui.posts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.android3lesson2application.databinding.ItemPostBinding;
import com.geektech.android3lesson2application.models.Post;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder>{

    private List<Post> posts = new ArrayList<>();
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void getPost() {
        this.posts = posts;
    }

    public Post getItem(int position) {
        return posts.get(position);
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    public void deletePost(int position){
        this.posts.remove(position);
        notifyItemRemoved(position);
    }

    public void updatePost(int position) {
        posts.get(position);
        this.posts = posts;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding binding = ItemPostBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new PostsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        holder.onBind(posts.get(position));

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }



    public void rename(List<Post> posts) {

        this.posts = posts;
    }

    protected class PostsViewHolder extends RecyclerView.ViewHolder {

        private  ItemPostBinding binding;
        public PostsViewHolder(@NonNull ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Post post) {
            binding.tvUserId.setText(String.valueOf(post.getUserId()));
            binding.tvTitle.setText(post.getTitle());
            binding.tvContent.setText(post.getContent());

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(getAdapterPosition());


                }
            });

            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onLongClick(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
