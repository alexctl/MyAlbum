package fr.fr.alex.myalbum.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.fr.alex.myalbum.Adapter.AlbumAdapterRC;
import fr.fr.alex.myalbum.Constant.Constant;
import fr.fr.alex.myalbum.Database.Albums.AlbumTable;
import fr.fr.alex.myalbum.Database.Albums.AlbumViewModel;
import fr.fr.alex.myalbum.Model.AlbumModel;
import fr.fr.alex.myalbum.R;
import fr.fr.alex.myalbum.Util.RequestApi;

public class MainActivity extends AppCompatActivity {
    Boolean isConnected;
    private List<AlbumTable> albumList;
    private RecyclerView mAlbumRecycler;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAlbumRecycler = findViewById(R.id.main_rc_album);
        mAlbumRecycler.setLayoutManager(new LinearLayoutManager(this));

        Constant.mRcAlbum = mAlbumRecycler;
        mAlbumRecycler.setAdapter(adapter);


        AlbumViewModel albumViewModel = ViewModelProviders.of(this).get(AlbumViewModel.class);
        Constant.mAlbumViewModel= albumViewModel;

        Constant.mAlbumViewModel.getData().observe(this, new Observer<List<AlbumTable>>() {
            @Override
            public void onChanged(@Nullable List<AlbumTable> albumTables) {
                Constant.mBddValues = albumTables;
                if(albumTables != null && albumTables.size()>0){
                    getDataRC();
                }
                else{
                    checkForData();
                }
            }
        });
    }

    private void checkForData() {
        isConnected = checkInternetConnection();
        if (isConnected){
            RequestApi requestApi = new RequestApi();
            requestApi.RequestAPI(getApplicationContext(),"https://static.leboncoin.fr/img/shared/technical-test.json");
        } else{
            AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
            alert.setTitle(R.string.splash_alertDialog_Titre);
            alert.setMessage(R.string.splash_alertDialog_Message);

            alert.setPositiveButton(R.string.splash_alertDialog_Button_Valide, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    /** Reload **/
                }
            });
            alert.show();
        }
    }


    private boolean checkInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void getDataRC(){
        List<AlbumModel> albumModels = new ArrayList<>();
        for(AlbumTable albumTable : Constant.mBddValues){
            AlbumModel model = new AlbumModel(albumTable.title,albumTable.url);
            albumModels.add(model);
        }
        adapter = new AlbumAdapterRC(albumModels, getApplicationContext());
        Constant.mRcAlbum.setAdapter(adapter);
    }
}
