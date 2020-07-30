package com.example.ecostyle;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class MapActivity extends AppCompatActivity {
    // Initialize Variables
    Spinner spType;
    Button btFind;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    FusedLocationProviderClient fusedLocationProviderClient;
    double currentLat = 0, currentLong = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Assigning Variables
        spType = findViewById(R.id.sp_type);
        btFind = findViewById(R.id.bt_find);
        supportMapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.google_map);

        // Initialize Array of place type
        final String[] placeTypeList = {"atm", "bank", "hospital", "movie_theater"};

        // Initialize Array of place name
        String[] placeNameList = {"ATM", "Bank", "Hospital", "Movie Theater"};

        // Set adapter on Spinner
        spType.setAdapter(new ArrayAdapter<>(MapActivity.this, android.R.layout.simple_spinner_dropdown_item, placeNameList));

        // Initialize fused location provider client
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Check Permission
        if(ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            // When permission granted
            // Call Method
            getCurrentLocation();
        }
        else{
            // When permission is denied
            // Request permission
            ActivityCompat.requestPermissions(MapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        btFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Selected of Spinner
                int i = spType.getSelectedItemPosition();
                // Initialize url
                String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" + // url
                "?location=" + currentLat + "," + currentLong + // location, latitude, and longitude
                "&radius=5000" + // near by radius
                "&types=" + placeTypeList[i] + // place type
                "&sensor=true" + getResources().getString(R.string.google_map_key); // google map key

                // Execute place task method to download json data
                new PlaceTask().execute(url);
            }
        });
    }

    private void getCurrentLocation()
    {
        // Initialized task location
        Task<Location> task =fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // When successful
                if(location != null)
                {
                    // When location is not null
                    // Get current Latitude
                    currentLat = location.getLatitude();
                    // Get current Longitude
                    currentLong = location.getLongitude();
                    // Sync Map
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            // When map is ready
                            map = googleMap;
                            // Zoom current location on map
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(currentLat, currentLong),10
                            ));
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 44)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                // When permission granted
                // Call Method
                getCurrentLocation();
            }
        }
    }

    private class PlaceTask extends AsyncTask<String, Integer,String>{
        @Override
        protected String doInBackground(String... strings) {
            String data = null;
            try {
                // Initialize data
                data = downloadUrl(strings[0]);
                return null;
            } catch (IOException e){
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            // Execute parser task
            new ParserTask().execute(s);
        }

    }

    private String downloadUrl(String string) throws IOException {
        // Initialize url
        URL url = new URL(string);
        // Initialize connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Connect connection
        connection.connect();
        // Initialize input stream
        InputStream stream = connection.getInputStream();
        // Initialize buffer reader
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        // Initialize string builder
        StringBuilder builder = new StringBuilder();
        // Initialize string variable
        String line = "";
        // Use while loop
        while ((line = reader.readLine()) != null){
            // Append line
            builder.append(line);
        }
        // Get append data
        String data = builder.toString();
        // Close reader
        reader.close();
        // Return data
        return data;
    }

    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>> {
        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {
            // Create json parser class
            JsonParser jsonParser = new JsonParser();
            // Initialize hash map list
            List<HashMap<String, String>> mapList = null;
            JSONObject object = null;
            try {
                // Initialize json object
                object = new JSONObject(strings[0]);
                // Parse json object
                mapList = jsonParser.parseResult(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Return map list
            return mapList;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> hashMaps) {
            // Clear Map
            map.clear();
            // Use for loop
            for (int i=0; i<hashMaps.size(); i++){
                // Initialize hash map
                HashMap<String, String> hashMapList = hashMaps.get(i);
                // Get latitude
                double lat = Double.parseDouble(hashMapList.get("lat"));
                // Get longitude
                double lng = Double.parseDouble(hashMapList.get("lng"));
                // Get name
                String name = hashMapList.get("name");
                // Concat lat and long
                LatLng latLng = new LatLng(lat, lng);
                // Initialize marker options
                MarkerOptions options = new MarkerOptions();
                // Set position
                options.position(latLng);
                // Set title
                options.title(name);
                // Add marker on map
                map.addMarker(options);
            }
        }
    }

}
