package com.example.bai1.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.bai1.base.BaseViewModel;
import com.example.bai1.home.models.ResultResponse;
import com.example.bai1.home.repo.HomeRepository;

public class HomeViewModel extends BaseViewModel {
    private HomeRepository mHomeRepository;
    private MutableLiveData<ResultResponse> mNowPlayingMutableLiveData;
    private MutableLiveData<ResultResponse> mPopularMutableLiveData;
    private MutableLiveData<ResultResponse> mTopRateMutableLiveData;
    private MutableLiveData<ResultResponse> mUpcomingMutableLiveData;
    public HomeViewModel(@NonNull Application application) {
        super(application);
        mHomeRepository = new HomeRepository(application);
    }
    public MutableLiveData<ResultResponse> getNowPlayingMutableLiveData() {
        if (mNowPlayingMutableLiveData == null)
            return mNowPlayingMutableLiveData = mHomeRepository.getNowPlayingLiveData();
        return mNowPlayingMutableLiveData;
    }

    public MutableLiveData<ResultResponse> getPopularMutableLiveData() {
        if (mPopularMutableLiveData == null)
            return mPopularMutableLiveData = mHomeRepository.getPopularLiveData();
        return mPopularMutableLiveData;
    }

    public MutableLiveData<ResultResponse> getTopRateMutableLiveData() {
        if (mTopRateMutableLiveData == null)
            return mTopRateMutableLiveData = mHomeRepository.getTopRateLiveData();
        return mTopRateMutableLiveData;
    }

    public MutableLiveData<ResultResponse> getUpcomingMutableLiveData() {
        if (mUpcomingMutableLiveData == null)
            return mUpcomingMutableLiveData = mHomeRepository.getUpcomingLiveData();
        return mUpcomingMutableLiveData;
    }

    public void fetchPopularMovies(String apiKey ,int page) {
        mHomeRepository.fetchPopularMovies(apiKey,page);
    }

    public void fetchTopRateMovies(String apiKey ,int page) {
        mHomeRepository.fetchTopRateMovies(apiKey,page);
    }

    public void fetchNowPlayingMovies(String apiKey ,int page) {
        mHomeRepository.fetchNowPlayingMovies(apiKey,page);
    }

    public void fetchUpcomingMovies(String apiKey ,int page) { mHomeRepository.fetchUpcomingMovies(apiKey,page); }
}
