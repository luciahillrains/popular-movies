package lucyhill.popularmovies.controllers;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import lucyhill.popularmovies.MainActivity;
import lucyhill.popularmovies.PopularMoviesProperties;
import lucyhill.popularmovies.interfaces.MovieDBApi;
import lucyhill.popularmovies.objects.PaginatedMovieList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDBController implements Callback<PaginatedMovieList> {
    public static final String BASE_URL = "http://api.themoviedb.org";
    Retrofit retrofit;
    MovieDBApi api;
    String apiKey;

    MainActivity activity;

    public MovieDBController(Context context, MainActivity mainActivity) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        api = retrofit.create(MovieDBApi.class);
        apiKey = PopularMoviesProperties.getInstance(context).getMoviedbApiKey();
        activity = mainActivity;
    }


    public void getTopRatedMovies(int page) {
        api.loadTopRatedMovies(apiKey, page).enqueue(this);
    }

    public void getPopularMovies(int page) {
        api.loadPopularMovies(apiKey, page).enqueue(this);
    }


    @Override
    public void onResponse(Call<PaginatedMovieList> call, Response<PaginatedMovieList> response) {
        if(response.isSuccessful()) {
            PaginatedMovieList paginatedMovieList = response.body();
            activity.displayPopularMovie(paginatedMovieList);
        } else {
            try {
                Log.e("MovieDBController", response.errorBody().string());
            }
            catch (IOException io) {
                Log.e("MovieDBController", "an error occured while trying to find out details of previous error:"+io.getLocalizedMessage());
            }
        }
    }

    @Override
    public void onFailure(Call<PaginatedMovieList> call, Throwable t) {
        Log.e("MovieDBController", t.getLocalizedMessage());
        Log.e("MovieDBController", t.getStackTrace().toString());
    }
}
