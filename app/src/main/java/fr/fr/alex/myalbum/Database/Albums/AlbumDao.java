package fr.fr.alex.myalbum.Database.Albums;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AlbumTable albumTable);

    @Query("SELECT * FROM Album")
    LiveData<List<AlbumTable>> getAllData();
}
