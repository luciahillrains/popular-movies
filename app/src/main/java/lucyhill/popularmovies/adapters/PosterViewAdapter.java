package lucyhill.popularmovies.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;


import lucyhill.popularmovies.R;
import lucyhill.popularmovies.interfaces.PosterViewClickListener;
import lucyhill.popularmovies.objects.PaginatedMovieList;

public class PosterViewAdapter extends RecyclerView.Adapter<PosterViewAdapter.ViewHolder>  {
    public static final String IMAGES_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private PaginatedMovieList mDataSet;
    private final PosterViewClickListener mListener;
    public PosterViewAdapter (PaginatedMovieList data, PosterViewClickListener clickListener) {
        mDataSet = data;
        mListener = clickListener;
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

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public RelativeLayout view;
        public ViewHolder(RelativeLayout v) {
            super(v);
            view = v;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("ViewHolder", "click invoked");
            int number =  getAdapterPosition();
            mListener.posterViewClick();
        }
    }
}
