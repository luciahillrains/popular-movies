package lucyhill.popularmovies;

public class SortingSingleton {
    private static SortingSingleton INSTANCE;
    private boolean mShowTopRatedMovies;

    private SortingSingleton() { }

    public static SortingSingleton getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new SortingSingleton();
        }
        return INSTANCE;
    }

    public boolean isShowTopRatedMovies() {
        return mShowTopRatedMovies;
    }

    public void setShowTopRatedMovies(boolean mShowTopRatedMovies) {
        this.mShowTopRatedMovies = mShowTopRatedMovies;
    }
}
