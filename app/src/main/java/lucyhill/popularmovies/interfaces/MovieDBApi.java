package lucyhill.popularmovies.interfaces;

import lucyhill.popularmovies.objects.PaginatedMovieList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDBApi {
    public static final String BASE_API = "/3/movie";

    @GET(BASE_API+"/popular")
    Call<PaginatedMovieList> loadPopularMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET(BASE_API+"/top_rated")
    Call<PaginatedMovieList> loadTopRatedMovies(@Query("api_key") String apiKey, @Query("page") int page);
}
