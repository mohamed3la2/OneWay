package com.example.eltimmy.oneway2;

import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.io.IOException;

/**
 * Created by eltimmy on 3/16/2018.
 */



public class GetDirectionsData extends AsyncTask<Object,String,String> {

    GoogleMap mMap;
    String url;
    String getDirectionsData;
    int waypoints;

    String distance, duration;

    TextView textView;

    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap) objects[0];
        url = (String) objects[1];
        textView = (TextView) objects[2];
        waypoints=(int)objects[3];


        DownloadUrl downloadUrl = new DownloadUrl();
        try {
            getDirectionsData = downloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getDirectionsData;
    }

    @Override
    protected void onPostExecute(String s) {
        for(int i=0;i<waypoints;i++)
        {
        String[] directionsList = null;
        DataParser dataParser = new DataParser();
        directionsList = dataParser.parseDirections(s,i);
        displayDirectionList(directionsList);
        }
    }

    private void displayDirectionList(String[] directionsList) {

        int count = directionsList.length;
        for (int i = 0; i < count; i++) {
            PolylineOptions options = new PolylineOptions();
            options.color(Color.BLUE);
            options.width(10);
            options.addAll(PolyUtil.decode(directionsList[i]));

            mMap.addPolyline(options);
        }
    }
}