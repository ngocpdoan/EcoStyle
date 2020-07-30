package com.example.ecostyle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class JsonParser {
    private HashMap<String, String> parseJsonObject(JSONObject object){
        // Initialize Hash Map
        HashMap<String, String> dataList = new HashMap<>();
        try {
            // Get name from object
            String name = object.getString("name");
            // Get latitude from obj
            String latitude = object.getJSONObject("geometry")
                    .getJSONObject("location").getString("lat");
            // Get longitude from obj
            String longitude = object.getJSONObject("geometry")
                    .getJSONObject("location").getString("lng");
            // Put all vals in hash map
            dataList.put("name", name);
            dataList.put("lat", latitude);
            dataList.put("lng", longitude);
        } catch (JSONException e){
            e.printStackTrace();
        }
        // Return hash map
        return dataList;
    }

    private List<HashMap<String, String>> parseJsonArray(JSONArray jsonArray){
        // Initialize hash map list
        List<HashMap<String, String>> dataList = new ArrayList<>();
        for(int i=0; i<jsonArray.length(); i++){
            try{
                // Initialize hash map
                HashMap<String, String> data = parseJsonObject((JSONObject)jsonArray.get(i));
                // Add data in hashmap list
                dataList.add(data);
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        // Return hash map list
        return dataList;
    }

    public List<HashMap<String, String>> parseResult(JSONObject object){
        // Initialize json array
        JSONArray jsonArray = null;
        try {
            //Get result array
            jsonArray = object.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Return array
        return parseJsonArray(jsonArray);

    }

}
