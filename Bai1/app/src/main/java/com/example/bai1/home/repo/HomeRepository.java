package com.example.bai1.home.repo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.bai1.base.BaseRepository;
import com.example.bai1.home.models.ResultResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;

public class HomeRepository extends BaseRepository {

    public HomeRepository(Application application) {
        super(application);
    }

    private MutableLiveData<ResultResponse> mNowPlayingLiveData = new MutableLiveData<>();
    private MutableLiveData<ResultResponse> mPopularLiveData = new MutableLiveData<>();
    private MutableLiveData<ResultResponse> mTopRateLiveData = new MutableLiveData<>();
    private MutableLiveData<ResultResponse> mUpcomingLiveData = new MutableLiveData<>();

    public void fetchPopularMovies(String apiKey, int page) {
        compositeDisposable.add(movieAPI.getPopularFilmResponse(apiKey, page)
                .timeout(30000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(resultResponse -> {
                    if (resultResponse.getResults() != null && resultResponse.getResults().size() > 0) {
                        mPopularLiveData.postValue(resultResponse);
                    }
                }, throwable -> {
                    Log.e("TAG", "fetchMovieResponse: " + throwable.getMessage());
                }));

    }

    public void fetchTopRateMovies(String apiKey, int page) {
        compositeDisposable.add(movieAPI.getTopRatedFilmRespone(apiKey, page)
                .timeout(30000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(resultResponse -> {
                    if (resultResponse.getResults() != null && resultResponse.getResults().size() > 0) {
                        mTopRateLiveData.postValue(resultResponse);
                    }
                }, throwable -> {
                    Log.e("TAG", "fetchMovieResponse: " + throwable.getMessage());
                }));
    }

    public void fetchUpcomingMovies(String apiKey, int page) {
        compositeDisposable.add(movieAPI.getUpcomingFilmRespone(apiKey, page)
                .timeout(30000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(resultResponse -> {
                    if (resultResponse.getResults() != null && resultResponse.getResults().size() > 0) {
                        mUpcomingLiveData.postValue(resultResponse);
                    }
                }, throwable -> {
                    Log.e("TAG", "fetchMovieResponse: " + throwable.getMessage());
                }));
    }

    public void fetchNowPlayingMovies(String apiKey, int page) {
        compositeDisposable.add(movieAPI.getNowPlayingFilmRespone(apiKey, page)
                .timeout(30000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(resultResponse -> {
                    if (resultResponse.getResults() != null && resultResponse.getResults().size() > 0) {
                        mNowPlayingLiveData.postValue(resultResponse);
                    }
                }, throwable -> {
                    Log.e("TAG", "fetchMovieResponse: " + throwable.getMessage());
                }));

    }

    public MutableLiveData<ResultResponse> getNowPlayingLiveData() {
        return mNowPlayingLiveData;
    }

    public MutableLiveData<ResultResponse> getPopularLiveData() {
        return mPopularLiveData;
    }

    public MutableLiveData<ResultResponse> getTopRateLiveData() {
        return mTopRateLiveData;
    }

    public MutableLiveData<ResultResponse> getUpcomingLiveData() {
        return mUpcomingLiveData;
    }
}
