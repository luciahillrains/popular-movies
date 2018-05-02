package lucyhill.popularmovies;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.Properties;

public class PopularMoviesProperties {
    private static PopularMoviesProperties instance;
    private String moviedbApiKey = "not set";

    public static PopularMoviesProperties getInstance(Context context) {
        if(instance == null) {
            instance = new PopularMoviesProperties(context);
        }
        return instance;
    }
    private PopularMoviesProperties(Context context) {
        getApiKeyFromPropertiesFile(context);
    }

    private void getApiKeyFromPropertiesFile(Context context) {
        try {
            Properties properties = new Properties();
            properties.load(context.getAssets().open("api.properties"));
            moviedbApiKey = properties.getProperty("moviedb.api.key");
        } catch(IOException e) {
            Log.e("PopularMoviesProperties","IO Exception occurted getting API Key");
            Log.e("PopularMoviesProperties",e.getStackTrace().toString());
        }
    }

    public String getMoviedbApiKey() {
        return moviedbApiKey;
    }

    public void setMoviedbApiKey(String moviedbApiKey) {
        this.moviedbApiKey = moviedbApiKey;
    }
}
