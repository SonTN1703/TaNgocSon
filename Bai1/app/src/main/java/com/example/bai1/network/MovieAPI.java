package com.example.bai1.network;

import com.example.bai1.detailfilm.models.DetailFilm;
import com.example.bai1.detailfilm.models.VideoResponse;
import com.example.bai1.home.models.ResultResponse;
import com.example.bai1.home.models.Results;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {
    @GET("movie/popular")
    Single<ResultResponse> getPopularFilmResponse(@Query("api_key") String apiKey,
                                                  @Query("page") int page);

    @GET("movie/top_rated")
    Single<ResultResponse> getTopRatedFilmRespone(@Query("api_key") String apiKey,
                                                  @Query("page") int page);
    @GET("movie/upcoming")
    Single<ResultResponse> getUpcomingFilmRespone(@Query("api_key") String apiKey,
                                                  @Query("page") int page);
    @GET("movie/now_playing")
    Single<ResultResponse> getNowPlayingFilmRespone(@Query("api_key") String apiKey,
                                                    @Query("page") int page);

    @GET("movie/{movie_id}")
    Observable<DetailFilm> getDetailMovies(@Path("movie_id") String id,
                                           @Query("api_key") String apiKey);
    @GET("movie/{movie_id}/videos")
    Observable<VideoResponse> getDetailVideoTrailer(@Path("movie_id") String id,
                                                    @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/similar")
    Observable<Results> getSimilarVideoTrailer(@Path("movie_id") String id,
                                               @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/recommendations")
    Observable<Results> getRecommendVideoTrailer(@Path("movie_id") String id,
                                                 @Query("api_key") String apiKey);

    @GET("search/movie")
    Observable<ResultResponse> getSearchMovies(@Query("api_key") String apiKey,
                                               @Query("query") String query);

}
