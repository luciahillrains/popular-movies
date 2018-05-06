package lucyhill.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import lucyhill.popularmovies.controllers.MovieDBController;
import lucyhill.popularmovies.objects.Movie;
import lucyhill.popularmovies.objects.PaginatedMovieList;

public class MainActivity extends AppCompatActivity {
    public static final int PAGE_REQUESTED = 1;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txt_temp);
        textView.setText("Loading");

        new MovieDBController(this, this).getPopularMovies(1);


    }

    public void displayPopularMovie(PaginatedMovieList movieList) {
        Movie movie = movieList.getResults().get(0);
        textView.setText(movie.getTitle());
    }
}
