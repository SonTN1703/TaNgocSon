package com.example.bai1.network;

import android.content.Context;
import android.provider.SyncStateContract;

import com.example.bai1.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkController {
    private static volatile MovieAPI movieAPI;

    public static MovieAPI getMovieAPI(Context context) {
        if (movieAPI == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            movieAPI = retrofit.create(MovieAPI.class);
        }
        return movieAPI;
    }
}
