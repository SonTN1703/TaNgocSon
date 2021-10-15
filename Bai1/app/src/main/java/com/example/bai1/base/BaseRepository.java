package com.example.bai1.base;

import android.app.Application;

import com.example.bai1.network.MovieAPI;
import com.example.bai1.network.NetworkController;

import io.reactivex.disposables.CompositeDisposable;

public class BaseRepository {
    protected MovieAPI movieAPI;
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    public BaseRepository(Application application) {
        movieAPI = NetworkController.getMovieAPI(application);
    }
}
