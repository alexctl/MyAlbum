package fr.fr.alex.myalbum.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.fr.alex.myalbum.Model.AlbumModel;
import fr.fr.alex.myalbum.R;

public class AlbumAdapterRC extends RecyclerView.Adapter<AlbumAdapterRC.ViewHolder>{

    private List<AlbumModel> albumModels;
    private Context context;

    public AlbumAdapterRC(List<AlbumModel> albumModels, Context context){
        this.albumModels = albumModels;
        this.context = context;

    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView album_name;
        public ImageView album_picture;

        public ViewHolder(View itemView) {
            super(itemView);
            album_name      = itemView.findViewById(R.id.album_title);
            album_picture   = itemView.findViewById(R.id.album_picture);
        }
    }


    @Override
    public AlbumAdapterRC.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_cell,parent,false);
        return new AlbumAdapterRC.ViewHolder(vh);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final AlbumModel albumModel = albumModels.get(position);

        holder.album_name.setText(albumModel.getAlbum_titre());

        if(!albumModel.getAlbum_image_url().equals("")
                && !albumModel.getAlbum_image_url().equals("null")
                ){
            Log.e("test image ", albumModel.getAlbum_image_url());
            Picasso.with(context)
                    .load(albumModel.getAlbum_image_url())
                    .into(holder.album_picture);
        }

    }

    @Override
    public int getItemCount() {
        return albumModels.size();
    }
}
