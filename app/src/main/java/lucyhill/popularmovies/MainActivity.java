package lucyhill.popularmovies;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
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
    private PaginatedMovieList mMovieList;
    private MovieDBController mMovieDBController;
    private Switch mSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPosterView = findViewById(R.id.poster_view);
        mPosterView.setAdapter(new EmptyAdapter());
        mPosterView.setLayoutManager(new GridLayoutManager(this, COLUMNS_IN_POSTER_VIEW));
        mPosterView.addItemDecoration(new PosterViewDecoration());
        mLoadingText = findViewById(R.id.loading_text);
        mSwitch = findViewById(R.id.switch_order);
        mMovieDBController = new MovieDBController(this, this);
        initialMovieLoad();
    }

    public void displayMovies(PaginatedMovieList movieList) {
        Log.d("MainActivity", "got movies back from api");
        mLoadingText.setVisibility(View.INVISIBLE);
        mAdapter = new PosterViewAdapter(movieList, this);
        mMovieList = movieList;
        mPosterView.setAdapter(mAdapter);
    }

    public void initialMovieLoad() {
        boolean showTopRatedFilms = SortingSingleton.getInstance().isShowTopRatedMovies();
        if(showTopRatedFilms) {
            mMovieDBController.getTopRatedMovies(PAGE_REQUESTED);
            mSwitch.setChecked(true);
        } else {
            mMovieDBController.getPopularMovies(PAGE_REQUESTED);
        }
    }

    public void switchMovieOrder(View view) {
        Log.d("MainActivity", "switching view");
        if(mSwitch.isChecked()) {
            mMovieDBController.getTopRatedMovies(PAGE_REQUESTED);
            SortingSingleton.getInstance().setShowTopRatedMovies(true);
        } else {
            mMovieDBController.getPopularMovies(PAGE_REQUESTED);
            SortingSingleton.getInstance().setShowTopRatedMovies(false);
        }
    }

    @Override
    public void posterViewClick(int position) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("movie", mMovieList.getResults().get(position));
        startActivity(intent);
    }
}
