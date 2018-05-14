package lucyhill.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import lucyhill.popularmovies.adapters.PosterViewAdapter;
import lucyhill.popularmovies.objects.Movie;

public class DetailsActivity extends AppCompatActivity {
    ImageView mPosterView;
    TextView mTitle;
    TextView mOriginalTitle;
    TextView mUserRating;
    TextView mDescription;
    Movie mMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent originalIntent = getIntent();
        bindDataViews(originalIntent);
        setDataViews();

    }

    private void bindDataViews(Intent intent) {
        mMovie = intent.getExtras().getParcelable("movie");
        mPosterView = findViewById(R.id.img_poster);
        mTitle = findViewById(R.id.txt_title);
        mOriginalTitle = findViewById(R.id.txt_original_title);
        mUserRating = findViewById(R.id.txt_user_rating);
        mDescription = findViewById(R.id.txt_description);
    }

    private void setDataViews() {
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        Picasso.get().load(PosterViewAdapter.IMAGES_BASE_URL + mMovie.getPosterPath()).into(mPosterView);
        mTitle.setText(mMovie.getTitle());
        mOriginalTitle.setText(mMovie.getOriginalTitle());
        mUserRating.setText(decimalFormat.format(mMovie.getVoteAverage()));
        mDescription.setText(mMovie.getOverview());
    }
}
