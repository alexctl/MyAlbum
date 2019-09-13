package fr.fr.alex.myalbum.Database.Albums;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import fr.fr.alex.myalbum.Database.Database;

public class AlbumRepository {
    private AlbumDao mAlbumDao;
    private LiveData<List<AlbumTable>> mAllData;

    AlbumRepository(Application application){
        Database db = Database.getDatabase(application);
        mAlbumDao = db.albumDao();
        mAllData = mAlbumDao.getAllData();
    }

    LiveData<List<AlbumTable>> getAllData(){ return mAllData; }

    public void insert (AlbumTable albumTable){
        new insertAsyncTask(mAlbumDao).execute(albumTable);
    }
    private static class insertAsyncTask extends AsyncTask<AlbumTable, Void, Void> {
        private AlbumDao mAsyncTaskDao;

        insertAsyncTask(AlbumDao albumDao){
            mAsyncTaskDao = albumDao;
        }

        @Override
        protected Void doInBackground(final AlbumTable... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
