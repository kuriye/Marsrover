package com.mustart.marspictures;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Bou's Laptop on 30/05/2018.
 */

public class NasaApiManager {

    private RequestQueue queue;
    private NasaApiListener listener;
    private ArrayList<Picture> pictures;

    public NasaApiManager(Context context, NasaApiListener listener) {
        this.queue = Volley.newRequestQueue(context);
        this.listener = listener;
        this.pictures = new ArrayList<>();
    }

    public void getPictures(){

        String url = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=4WKcgHtFzcv8s7IM9zfv1rhzCwMySeUVpSrZzzS1";
        JsonObjectRequest request = new JsonObjectRequest(

                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("nasa", response.toString());

                        try {
                            JSONArray images = response.getJSONArray("photos");
                            for( int idx = 0; idx < images.length(); idx++) {
                                //String id = response.getJSONObject(idx).getString("id");
                                int id = images.getJSONObject(idx).getInt("id");
                                String imageUrl = images.getJSONObject(idx).getString("img_src");
                                String camera = images.getJSONObject(idx).getJSONObject("camera").getString("name");
                                String fullName = images.getJSONObject(idx).getJSONObject("camera").getString("full_name");

                                Picture picture = new Picture(id, imageUrl, camera, fullName);
                                System.out.println(picture);
                                //pictures.add(picture);
                                listener.onPictureAvailable(picture);

                                Log.d("VOLLEY_TAG", picture.toString());
                            }
                           // listener.onPictureAvailable(pictures);
                        } catch (JSONException e) {
                            Log.d("API", e.getLocalizedMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("nasa", error.toString());
                        listener.onPictureError(error.toString());
                    }
                }
        );
        request.setRetryPolicy(new DefaultRetryPolicy(5000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        this.queue.add(request);
    }
}
