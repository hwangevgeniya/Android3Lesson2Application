package com.geektech.android3lesson2application.ui.form;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;

import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.geektech.android3lesson2application.App;
import com.geektech.android3lesson2application.R;
import com.geektech.android3lesson2application.databinding.FragmentFormBinding;
import com.geektech.android3lesson2application.models.Post;
import com.geektech.android3lesson2application.ui.posts.PostsAdapter;
import com.geektech.android3lesson2application.utils.constants.Keys;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FormFragment extends Fragment {

    private FragmentFormBinding binding;
    private static final int USERID = 8;
    private static final int GROUPID = 36;
    private NavHostFragment navHostFragment;
    private NavController navController;
    //    private PostsAdapter adapter;
    public boolean isListener;
    Post post;


    public FormFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFormBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            post = (Post) getArguments().getSerializable(Keys.UPDATE);
            binding.etContent.setText(post.getContent());
            binding.etTitle.setText(post.getTitle());
        }

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.etTitle.getText().toString();
                String content = binding.etContent.getText().toString();


//                if (isListener == true) {
                if (getArguments() != null) {
                    post.setTitle(title);
                    post.setContent(content);

                  /*  Bundle bundle = new Bundle();
                    Intent intent = new Intent();
                    bundle.putSerializable(Keys.POST, post);
                    intent.putExtras(bundle);
                    navController.navigate(R.id.postsFragment, bundle);
*/
//                    App.api.updatePost(post.getId(), post.getUserId(), post.getTitle(), post.getContent(), post.getGroupId())
                    App.api.updatePost(post.getId(), post)
                            .enqueue(new Callback<Post>() {
                                @Override
                                public void onResponse(Call<Post> call, Response<Post> response) {
                                    if (response.isSuccessful()) navController.popBackStack();
                                }

                                @Override
                                public void onFailure(Call<Post> call, Throwable t) {

                                }
                            });


                } else {
                    post = new Post(
                            USERID,
                            GROUPID,
                            title,
                            content
                    );
                    App.api.createPost(post).enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            if (response.isSuccessful()) {
                                navController.popBackStack();
                            }
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {

                        }

                    });

                }
            }
        });
    }
}