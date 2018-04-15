package com.example.eltimmy.oneway2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by eltimmy on 3/16/2018.
 */

public class DataParser {

    private HashMap<String,String> getDuration(JSONArray googleDirectionsJson) {
        HashMap<String,String> googleDirectionsMap=new HashMap<String, String>();
        String Distance="";
        String Duration="";

        Log.d("1:2:",googleDirectionsJson.toString());
        try {
            Distance=googleDirectionsJson.getJSONObject(0).getJSONObject("distance").getString("text");
            Duration=googleDirectionsJson.getJSONObject(0).getJSONObject("duration").getString("text");

            googleDirectionsMap.put("duration",Duration);
            googleDirectionsMap.put("distance",Distance);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return googleDirectionsMap;
    }



    public String[] parseDirections(String jsonData,int index) {
        JSONArray jsonArray=null;
        JSONObject jsonObject;

        try {

           /* jsonObject=new JSONObject(jsonData);
            jsonArray=jsonObject.getJSONArray("routes")
                    .getJSONObject(0).getJSONArray("legs")
                    .getJSONObject(0).getJSONArray("steps");

*/
            jsonObject=new JSONObject(jsonData);
            jsonArray=jsonObject.getJSONArray("routes")
                    .getJSONObject(0).getJSONArray("legs")
                    .getJSONObject(index).getJSONArray("steps");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    return getpaths(jsonArray);
    }

    String[] getpaths(JSONArray googleStepsJson)
    {
        int count=googleStepsJson.length();
        String[] polylines=new String[count];
        for (int i=0;i<count;i++)
        {
            try {
                polylines[i]=getpath(googleStepsJson.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    return polylines;
    }



    String getpath(JSONObject googlePathJson)
    {
        String polyline="";
        try {

            polyline=googlePathJson.getJSONObject("polyline").getString("points");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return polyline;
    }


}
