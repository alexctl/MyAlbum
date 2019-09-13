package fr.fr.alex.myalbum.Constant;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import fr.fr.alex.myalbum.Database.Albums.AlbumTable;
import fr.fr.alex.myalbum.Database.Albums.AlbumViewModel;

public class Constant extends AppCompatActivity {
    public static AlbumViewModel mAlbumViewModel;
    public static List<AlbumTable> mBddValues;
    public static RecyclerView mRcAlbum;
}
