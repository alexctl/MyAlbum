package fr.fr.alex.myalbum.Database.Albums;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class AlbumViewModel extends AndroidViewModel {
    private AlbumRepository mRepository;
    private LiveData<List<AlbumTable>> mAllData;



    public AlbumViewModel (Application application) {
        super(application);
        mRepository = new AlbumRepository(application);
        mAllData = mRepository.getAllData();

    }

    public LiveData<List<AlbumTable>> getData() { return mAllData; }
    public void insert(AlbumTable albumTable) { mRepository.insert(albumTable); }
}
