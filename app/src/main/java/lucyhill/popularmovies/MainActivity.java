package lucyhill.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txt_temp);
        String apiKey = getMovieDBAPIKey();

        textView.setText(apiKey);

    }

    private String getMovieDBAPIKey() {
        PopularMoviesProperties props = PopularMoviesProperties.getInstance(this);
        return props.getMoviedbApiKey();
    }
}
