package com.example.spotty;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


//This class defines marker options for the nearby places
public class getNearbyPlaces extends AsyncTask<Object, String, String>
{
    private String googleplaceData, url;
    private GoogleMap mMap;


    @Override
    protected String doInBackground(Object... objects) {
        mMap=(GoogleMap) objects[0];
        url=(String)objects[1];

        downloadUrl DownloadUrl=new downloadUrl();
        try {
            googleplaceData = downloadUrl.ReadTheURL(url);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return googleplaceData;
    }

    @Override
    protected void onPostExecute(String s)
    {
        List<HashMap<String, String>>nearbyPlaceList=null;
        DataParser dataParser=new DataParser();
        nearbyPlaceList=dataParser.parse(s);

        DisplayNearbyPlaces(nearbyPlaceList);
    }

    private void DisplayNearbyPlaces(List<HashMap<String, String>>nearbyPlaceList)
    {
        for(int i=0; i<nearbyPlaceList.size(); i++)
        {
            MarkerOptions markerOptions=new MarkerOptions();

            HashMap<String, String>googleNearbyPlace=nearbyPlaceList.get(i);
            String nameOfPlace=googleNearbyPlace.get("place_name");
            String vicinity=googleNearbyPlace.get("vicinity");
            double latitude=Double.parseDouble(googleNearbyPlace.get("latitude"));
            double longitude=Double.parseDouble(googleNearbyPlace.get("longitude"));

            LatLng latLng = new LatLng(latitude,longitude);


            markerOptions.position(latLng);
            markerOptions.title(nameOfPlace + " : " + vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10));

        }
    }

}
