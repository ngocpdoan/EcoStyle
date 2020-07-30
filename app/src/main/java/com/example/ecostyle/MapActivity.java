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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapActivity extends AppCompatActivity {
    //Initialize Variables
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

        //Assigning Variables
        spType = findViewById(R.id.sp_type);
        btFind = findViewById(R.id.bt_find);
        supportMapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.google_map);

        //Initialize Array of place type
        final String[] placeTypeList = {"atm", "bank", "hospital", "movie_theater"};

        //Initialize Array of place name
        String[] placeNameList = {"ATM", "Bank", "Hospital", "Movie Theater"};

        //Set adapter on Spinner
        spType.setAdapter(new ArrayAdapter<>(MapActivity.this, android.R.layout.simple_spinner_dropdown_item, placeNameList));

        //Initialize fused location provider client
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //Check Permission
        if(ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            //Where permission granted
            //Call Method
            getCurrentLocation();
        }
        else{
            //when permission is denied
            //request permission
            ActivityCompat.requestPermissions(MapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        btFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get Selected of Spinner
                int i = spType.getSelectedItemPosition();
                //Initiallize url
                String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" + //url
                "?location=" + currentLat + "," + currentLong + //location latitude and longitude
                "&radius=5000" + //near by radius
                "&types=" + placeTypeList[i] + // Place type
                "&sensor=true" + getResources().getString(R.string.google_map_key); // google map key

                //Execute place task method to download json data
                new PlaceTask().excute(url);
            }
        });
    }

    private void getCurrentLocation()
    {
        //Initialized task location
        Task<Location> task =fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //when sucuess
                if(location != null)
                {
                    //When location is not null
                    //get current Latitude
                    currentLat = location.getLatitude();
                    //get current Longitude
                    currentLong = location.getLongitude();
                    //Sync Map
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            //When map is ready
                            map = googleMap;
                            //Zoom current location on map
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
                //When permission granted
                //call Method
                getCurrentLocation();
            }
        }
    }

    private class PlaceTask extends AsyncTask<String, Integer,String>{
        @Override
        protected String doInBackground(String... strings) {
            //initialize data
            String data = downloadUrl(strings[0]);
            return null;
        }
    }

    private String downloadUrl(String string) {
    }
}
