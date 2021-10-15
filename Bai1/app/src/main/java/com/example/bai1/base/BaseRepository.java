package com.example.bai1.base;

import android.app.Application;

import com.example.bai1.network.MovieAPI;
import com.example.bai1.network.NetworkController;

public class BaseRepository {
    protected MovieAPI movieAPI;

    public BaseRepository(Application application) {
        movieAPI = NetworkController.getMovieAPI(application);
    }
}
