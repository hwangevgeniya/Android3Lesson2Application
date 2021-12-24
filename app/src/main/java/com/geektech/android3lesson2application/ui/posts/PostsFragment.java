package com.geektech.android3lesson2application.ui.posts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.geektech.android3lesson2application.App;
import com.geektech.android3lesson2application.R;
import com.geektech.android3lesson2application.databinding.FragmentPostsBinding;
import com.geektech.android3lesson2application.models.Names;
import com.geektech.android3lesson2application.models.Post;
import com.geektech.android3lesson2application.utils.constants.Keys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsFragment extends Fragment implements Callback<List<Post>> {

    private FragmentPostsBinding binding;
    private PostsAdapter adapter;
    private NavHostFragment navHostFragment;
    private NavController navController;
    public boolean isListener = false;
    private int pos;

    private List<Post> list = new ArrayList();

    Names classNames = new Names();
    ArrayList<Names> names = new ArrayList<>();

    public PostsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new PostsAdapter();
        navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPostsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recycler.setAdapter(adapter);
        App.api.getPosts().enqueue(this);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_postsFragment_to_formFragment);
            }
        });

        //Integer hashMapUserID = adapter.getItem(pos).getUserId();
        //String hashmapNames = adapter.rename();
        //setNames();
        //createHashmap();
        onItemClick();
    }

    private void createHashmap() {
        Map<Integer, String> names = new HashMap<Integer, String>();
        names.put(adapter.getItem(pos).getUserId(), classNames.getNames());

    }


    private void setNames() {

        names.add(0,classNames.setNames("Qoqo"));
        names.add(1,classNames.setNames("Popo"));
        names.add(2,classNames.setNames("Gogo"));
        names.add(3,classNames.setNames("Koko"));
        names.add(4,classNames.setNames("Lolo"));
        names.add(5,classNames.setNames("Momo"));
        names.add(6,classNames.setNames("Nono"));
        names.add(7,classNames.setNames("Bobo"));
        names.add(8,classNames.setNames("Roro"));
    }

    private void onItemClickLongClick(int position) {
        new AlertDialog.Builder(requireActivity())

                .setMessage("Удалить данные?")
                .setNegativeButton("Нет", null)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Post post = adapter.getItem(position);
                        App.api.deletePost(post.getId()).enqueue(new Callback<Post>() {
                            @Override
                            public void onResponse(Call<Post> call, Response<Post> response) {
                                adapter.deletePost(position);
                            }

                            @Override
                            public void onFailure(Call<Post> call, Throwable t) {

                            }
                        });
                    }
                })
                .show();
    }

    private void onItemClick() {
        adapter.setListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
/*
                isListener = true;
                pos = position;
                //Bundle bundle = new Bundle();
                //Post post = adapter.getItem(position);
               // bundle.putAll(bundle);
                navController.navigate(R.id.formFragment);
                //navController.navigate(R.id.action_postsFragment_to_formFragment);
               // bundle.getParcelable("post");
*/

                Bundle bundle = new Bundle();
                pos = position;
                Post postToUpdate = adapter.getItem(position);
                bundle.putSerializable(Keys.UPDATE, postToUpdate);
                navController.navigate(R.id.formFragment, bundle);
            }

            @Override
            public void onLongClick(int position) {
                onItemClickLongClick(position);
            }
        });
    }

    @Override
    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
        if (response.isSuccessful() && response.body() != null) {
            list = response.body();
            adapter.setPosts(response.body());
        }

/*
        if (isListener == true){
            Post post = (Post) getArguments().getSerializable("post");
            adapter.updatePost(pos);
            isListener = false;
        }
*/
    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable t) {

    }


}