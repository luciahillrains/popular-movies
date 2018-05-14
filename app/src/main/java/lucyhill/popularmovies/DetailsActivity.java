package lucyhill.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lucyhill.popularmovies.adapters.PosterViewAdapter;
import lucyhill.popularmovies.objects.Movie;

public class DetailsActivity extends AppCompatActivity {
    ImageView mPosterView;
    TextView mTitle;
    TextView mOriginalTitle;
    TextView mUserRating;
    TextView mDescription;
    TextView mReleasedOn;
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
        mReleasedOn = findViewById(R.id.txt_released_on);
    }

    private void setDataViews() {
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        DateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormatter = new SimpleDateFormat("MMMM dd, yyyy");
        Picasso.get().load(PosterViewAdapter.IMAGES_BASE_URL + mMovie.getPosterPath()).into(mPosterView);
        mTitle.setText(mMovie.getTitle());
        mOriginalTitle.setText(mMovie.getOriginalTitle());
        mUserRating.setText(decimalFormat.format(mMovie.getVoteAverage()));
        mDescription.setText(mMovie.getOverview());
        try{
            Date releaseDate = dateParser.parse(mMovie.getReleaseDate());
            String formattedReleaseDate = dateFormatter.format(releaseDate);
            mReleasedOn.setText(formattedReleaseDate);
        } catch(ParseException e) {
            Log.e("DetailsActivity", e.getLocalizedMessage());
        }
    }
}
