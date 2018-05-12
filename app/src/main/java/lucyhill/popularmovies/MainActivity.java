package lucyhill.popularmovies;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import lucyhill.popularmovies.adapters.EmptyAdapter;
import lucyhill.popularmovies.adapters.PosterViewAdapter;
import lucyhill.popularmovies.controllers.MovieDBController;
import lucyhill.popularmovies.interfaces.PosterViewClickListener;
import lucyhill.popularmovies.itemdecorations.PosterViewDecoration;
import lucyhill.popularmovies.objects.Movie;
import lucyhill.popularmovies.objects.PaginatedMovieList;

public class MainActivity extends AppCompatActivity implements PosterViewClickListener {
    public static final int PAGE_REQUESTED = 1;
    public static final int COLUMNS_IN_POSTER_VIEW = 3;
    private RecyclerView mPosterView;
    private RecyclerView.Adapter mAdapter;
    private TextView mLoadingText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPosterView = findViewById(R.id.poster_view);
        mPosterView.setAdapter(new EmptyAdapter());
        mPosterView.setLayoutManager(new GridLayoutManager(this, COLUMNS_IN_POSTER_VIEW));
        mPosterView.addItemDecoration(new PosterViewDecoration());
        mLoadingText = findViewById(R.id.loading_text);
        new MovieDBController(this, this).getPopularMovies(PAGE_REQUESTED);


    }

    public void displayPopularMovie(PaginatedMovieList movieList) {
        Log.d("MainActivity", "got movies back from api");
        mLoadingText.setVisibility(View.INVISIBLE);
        mAdapter = new PosterViewAdapter(movieList, this);
        mPosterView.setAdapter(mAdapter);
    }

    @Override
    public void posterViewClick() {
        Log.d("MainActivity", "Houston, we have a click.");
        Toast toast = Toast.makeText(this, "Click!", Toast.LENGTH_LONG);
        toast.show();
    }
}
