package lucyhill.popularmovies.adapters;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;


import lucyhill.popularmovies.R;
import lucyhill.popularmovies.objects.PaginatedMovieList;

public class PosterViewAdapter extends RecyclerView.Adapter<PosterViewAdapter.ViewHolder>  {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout view;
        public ViewHolder(RelativeLayout v) {
            super(v);
            view = v;
        }
    }
    public static final String IMAGES_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private PaginatedMovieList mDataSet;

    public PosterViewAdapter (PaginatedMovieList data) {
        mDataSet = data;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String posterImage = mDataSet.getResults().get(position).getPosterPath();
        Log.d("PosterViewAdapter", "poster view path is "+posterImage );
        ImageView imageView = holder.view.findViewById(R.id.movie_poster);
        Picasso.get().load(IMAGES_BASE_URL+posterImage).into(imageView);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RelativeLayout view = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.poster_view_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mDataSet.getResults().size();
    }
}
