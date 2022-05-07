package com.example.spotty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataParser
{
    private HashMap<String, String> getSingleNearbyPlace(JSONObject googlePlaceJSON)
    {

        HashMap<String, String>googlePlaceMap=new HashMap<>();
        String NameOfPlace="-NA-";
        String vicinity= "-NA-";
        String latitude="";
        String longitude="";
        String reference="";

        try
        {
            if(!googlePlaceJSON.isNull(("name")))
            {
                NameOfPlace = googlePlaceJSON.getString("name");
            }
            if(!googlePlaceJSON.isNull(("vicinity")))
            {
                vicinity = googlePlaceJSON.getString("vicinity");
            }
            latitude=googlePlaceJSON.getJSONObject("geometry").getJSONObject("location").getString("latitude");
            longitude=googlePlaceJSON.getJSONObject("geometry").getJSONObject("location").getString("longitude");
            reference = googlePlaceJSON.getString("reference");

            googlePlaceMap.put("place_name", NameOfPlace);
            googlePlaceMap.put("vicinity", vicinity);
            googlePlaceMap.put("latitude", latitude);
            googlePlaceMap.put("longitude", longitude);
            googlePlaceMap.put("refernce", reference);


        }catch (JSONException e){
            e.printStackTrace();
        }
        return googlePlaceMap;
    }
        private List<HashMap<String, String>>getAllNearbyPlaces(JSONArray jsonArray)
        {
            int counter=jsonArray.length();

            List<HashMap<String, String>>NearbyPlaceList=new ArrayList<>();

            HashMap<String, String>NearbyPlaceMap=null;
            for(int i=0;i<counter;i++) {
                try {
                    NearbyPlaceMap = getSingleNearbyPlace((JSONObject) jsonArray.get(i));
                    NearbyPlaceList.add(NearbyPlaceMap);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return NearbyPlaceList;
        }

        public List<HashMap<String, String>>parse(String jSONdata){
            {
                JSONArray jsonArray=null;
                JSONObject jsonObject;


                try{
                    jsonObject=new JSONObject(jSONdata);
                    jsonArray=jsonObject.getJSONArray("result");
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
                return getAllNearbyPlaces(jsonArray);
            }

    }
}
