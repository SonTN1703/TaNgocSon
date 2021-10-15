package com.example.bai1.home.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bai1.BR;
import com.example.bai1.R;
import com.example.bai1.base.BaseViewHolderBinding;
import com.example.bai1.databinding.ItemMovieBinding;
import com.example.bai1.home.MovieItemListener;
import com.example.bai1.home.models.Results;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<BaseViewHolderBinding> {
    private List<Results> listMovie;
    private MovieItemListener movieItemListener;

    public MoviesAdapter(List<Results> listMovie, MovieItemListener movieItemListener) {
        this.listMovie = listMovie;
        this.movieItemListener = movieItemListener;
    }

    @NonNull
    @Override
    public BaseViewHolderBinding onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMovieBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_movie, parent,false);
        return new MoviesHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolderBinding holder, int position) {
        if (holder instanceof MoviesHolder) {
            MoviesHolder moviesHolder = (MoviesHolder) holder;
            if (listMovie != null && listMovie.size() > 0) {
                moviesHolder.getBinding().setVariable(BR.movieItem, listMovie.get(position));
            }
        }
    }

    @Override
    public int getItemCount() {
        if (listMovie != null)
            return listMovie.size();
        return 0;
    }

    public class MoviesHolder extends BaseViewHolderBinding<ItemMovieBinding> {

        public MoviesHolder(@NonNull ItemMovieBinding itemView) {
            super(itemView);
            itemView.setVariable(BR.movieItemListener, movieItemListener);
        }
    }
}
