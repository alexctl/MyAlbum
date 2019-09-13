package fr.fr.alex.myalbum.Util;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.fr.alex.myalbum.Activity.MainActivity;
import fr.fr.alex.myalbum.Constant.Constant;
import fr.fr.alex.myalbum.Database.Albums.AlbumTable;

public class RequestApi {
     private Context mContext;
     private String mUrl;
     private RequestQueue mQueue;

    public void RequestAPI(Context context, String url){
        mContext = context;
        mUrl = url;
        mQueue = Volley.newRequestQueue(mContext);
        sendrequest();
    }

    private void sendrequest() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mUrl, null,
                new com.android.volley.Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);

                                AlbumTable albumTable = new AlbumTable(Integer.parseInt(jsonObject.getString("id")),
                                        jsonObject.getString("title"),jsonObject.getString("url"),
                                        jsonObject.getString("thumbnailUrl"), Integer.parseInt(jsonObject.getString("albumId")));
                                Constant.mAlbumViewModel.insert(albumTable);

                                Log.e("resultApi", jsonObject.toString());
                            }
                            MainActivity mainActivity = new MainActivity();
                            mainActivity.getDataRC();
                        }catch(JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}
