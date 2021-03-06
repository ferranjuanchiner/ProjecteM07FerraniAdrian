package cat.copernic.ferranjuan.projectem07ferraniadrian;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AlbumesAdapter extends RecyclerView.Adapter<AlbumesAdapter.ViewHolder> {
    private ArrayList<Album> mAlbumesData;
    private Context mContext;
    public AlbumesAdapter(Context context, ArrayList<Album> albumesData) {
        this.mAlbumesData = albumesData;
        this.mContext = context;
    }
    @Override
    public AlbumesAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }
    @Override
    public void onBindViewHolder(AlbumesAdapter.ViewHolder holder,
                                 int position) {
        // Get current sport.
        Album currentAlbum = mAlbumesData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentAlbum);
    }
    @Override
    public int getItemCount() {
        return mAlbumesData.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Member Variables for the TextViews
        private TextView mTitleText;
        private ImageView mSportsImage;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            //Initialize the views
            mTitleText = itemView.findViewById(R.id.title);
            mSportsImage = itemView.findViewById(R.id.album_images);

            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
        }

        void bindTo(Album currentAlbum){
            // Populate the textviews with data.
            mTitleText.setText(currentAlbum.getNom());
            Glide.with(mContext).load(currentAlbum.getImg()).into(mSportsImage);

        }


        @Override
        public void onClick(View v) {
            Album currentAlbum = mAlbumesData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("title", currentAlbum.getNom());
            detailIntent.putExtra("image_resource",
                    currentAlbum.getImg());
            detailIntent.putExtra("cancion_name",
                    currentAlbum.getNomCanço());
            detailIntent.putExtra("cancion_file",
                    currentAlbum.getCanço());
            mContext.startActivity(detailIntent);
        }
    }
}
