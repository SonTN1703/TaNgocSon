package com.example.bai1.home.nowplaying;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bai1.Constants;
import com.example.bai1.R;
import com.example.bai1.databinding.FragmentNowPlayingBinding;
import com.example.bai1.databinding.FragmentTopRateBinding;
import com.example.bai1.home.HomeViewModel;
import com.example.bai1.home.MovieItemListener;
import com.example.bai1.home.adapter.MoviesAdapter;
import com.example.bai1.home.models.Results;
import com.example.bai1.home.toprate.TopRateFragment;

import java.util.ArrayList;
import java.util.List;

public class NowPlayingFragment extends Fragment implements MovieItemListener {
    private FragmentNowPlayingBinding binding;
    private View mView;
    private HomeViewModel homeViewModel;
    private List<Results> movieList = new ArrayList<>();
    private RecyclerView rcvMovies;
    private MoviesAdapter moviesAdapter;

    public static NowPlayingFragment newInstance() {
        Bundle args = new Bundle();
        NowPlayingFragment fragment = new NowPlayingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNowPlayingBinding.inflate(inflater, container, false);
        homeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        mView = binding.getRoot();
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        observeViewModel();
        initRecyclerView();
    }

    public void observeViewModel() {
        homeViewModel.fetchNowPlayingMovies(Constants.API_KEY, 1);
        homeViewModel.getNowPlayingMutableLiveData().observe(requireActivity(), resultResponse -> {
            if (resultResponse.getResults() != null && resultResponse.getResults().size() > 0) {
                movieList.addAll(resultResponse.getResults());
                moviesAdapter.notifyDataSetChanged();
            }
        });
    }

    public void initView() {
        rcvMovies = mView.findViewById(R.id.rcv_now_loading);
    }

    public void initRecyclerView() {
        moviesAdapter = new MoviesAdapter(movieList, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false);
        rcvMovies.setLayoutManager(gridLayoutManager);
        rcvMovies.setAdapter(moviesAdapter);
    }

    @Override
    public void onItemMovieSelected(Results movie, int position) {

    }
}
