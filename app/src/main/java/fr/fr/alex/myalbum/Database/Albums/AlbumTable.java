package fr.fr.alex.myalbum.Database.Albums;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(tableName = "Album", primaryKeys = {"id"})
public class AlbumTable {

    @NonNull  @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "thumbnailUrl")
    public String thumbnailUrl;

    @ColumnInfo(name = "albumId")
    public int albumId;

    public AlbumTable(int id, String title, String url, String thumbnailUrl, int albumId){
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        this.albumId = albumId;
    }
     @NonNull
    public String getData(){return this.title;}
}
