package lucyhill.popularmovies.itemdecorations;


import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class PosterViewDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = 0;
        outRect.right = 0;
        outRect.bottom = 0;
        outRect.top = 0;
    }
}
