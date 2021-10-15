package com.example.bai1.home.popular;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bai1.Constants;
import com.example.bai1.R;
import com.example.bai1.databinding.FragmentPopularBinding;
import com.example.bai1.home.HomeViewModel;
import com.example.bai1.home.MovieItemListener;
import com.example.bai1.home.adapter.MoviesAdapter;
import com.example.bai1.home.models.Results;

import java.util.List;


public class PopularFragment extends Fragment implements MovieItemListener {
    private FragmentPopularBinding binding;
    private View mView;
    private HomeViewModel homeViewModel;
    private List<Results> movieList;
    private RecyclerView rcvMovies;
    private MoviesAdapter moviesAdapter;

    public static PopularFragment newInstance() {
        Bundle args = new Bundle();
        PopularFragment fragment = new PopularFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPopularBinding.inflate(inflater, container, false);
        homeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        mView = binding.getRoot();
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    public void observeViewModel() {
        homeViewModel.fetchPopularMovies(Constants.API_KEY, 1);
        homeViewModel.getPopularMutableLiveData().observe(this, resultResponse -> {
            if (resultResponse.getResults() != null && resultResponse.getResults().size() > 0) {
                movieList.addAll(resultResponse.getResults());
            }
        });
    }

    public void initView() {
        rcvMovies = mView.findViewById(R.id.rcv_popular);
    }

    public void initRecyclerView() {
        moviesAdapter = new MoviesAdapter(movieList, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false);
        rcvMovies.setLayoutManager(gridLayoutManager);
        rcvMovies.setItemAnimator(new DefaultItemAnimator());
        rcvMovies.setAdapter(moviesAdapter);
    }

    @Override
    public void onItemMovieSelected(Results movie, int position) {

    }
}
