package com.example.realestate.wantedscreen;

import android.Manifest;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.ConfirmAddress;
import com.example.realestate.Getsellerimages.RecyclerAdaptor;
import com.example.realestate.HomeNav;
import com.example.realestate.R;
import com.example.realestate.databinding.ActivityGoogleMapBinding;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WantedScreenReview extends AppCompatActivity implements OnMapReadyCallback,ConfirmAddress.OnInputListener {
    RecyclerView recyclerView;
    RecyclerAdaptor adaptor;
    private DatabaseReference mDatabaseUsers;
    private FirebaseAuth mAuth;
    Button btnpriceshow;
    private GoogleMap mMap;
    private ActivityGoogleMapBinding binding;
    private final static int PLACE_PICKER_REQUEST = 999;
    private final static int LOCATION_REQUEST_CODE = 23;
    String MapAddress;
    double MapLat, MapLong;
    SupportMapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wanted_screen_review);
        Intent intent = getIntent();
        btnpriceshow = findViewById(R.id.priceshowbutton);
        String title = intent.getStringExtra("title");
        String des = intent.getStringExtra("des");
        String date = intent.getStringExtra("dateavalible");
        String area = intent.getStringExtra("Area");
        String dimention = intent.getStringExtra("dim");
        String minrent = intent.getStringExtra("minrent");
        String maxrent = intent.getStringExtra("maxrent");
        String sellRentchose = intent.getStringExtra("sellorRent");
        String totalbedrooms = intent.getStringExtra("bedrooms");
        String totalbathrooms = intent.getStringExtra("bathrooms");
        Bundle bundle = getIntent().getExtras();
//        ArrayList listimages = bundle.getParcelable("listimage");
        ArrayList<String> listimages = (ArrayList<String>) getIntent().getSerializableExtra("listimage");
        String property_type = intent.getStringExtra("property_type");
        btnpriceshow.setText(minrent + "-" + maxrent + "/MONTH");
//        recyclerView = findViewById(R.id.recyclerView);
//        adaptor = new RecyclerAdaptor(listimages);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL,false));
//        recyclerView.setAdapter(adaptor);
        // Declares map fragment.
//        SupportMapFragment eventMapFragment = (SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.map);
//        eventMapFragment.getMapAsync(this);

        mapFragment = (SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_REQUEST_CODE);
            mapFragment.getMapAsync(this);
            Toast.makeText(this, "acess request", Toast.LENGTH_SHORT).show();

        }

        btnpriceshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData(title, des, date, area, dimention, minrent, maxrent, MapAddress, MapLat, MapLong, sellRentchose, totalbedrooms, totalbathrooms);
            }
        });
        mAuth = FirebaseAuth.getInstance();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference();
    }

    public void backButton(View view) {
        finish();
    }

    private void saveData(String title, String des, String date, String area, String dimention, String minrent, String maxrent, String mapadres, double lat, double log, String purchaserent, String bedrom, String bthrom) {
        DatabaseReference mNewRef = mDatabaseUsers.child("wanted").child(mAuth.getCurrentUser().getUid()).push();

        mNewRef.child("title").setValue(title);
        mNewRef.child("des").setValue(des);
        mNewRef.child("date").setValue(date);
        mNewRef.child("area").setValue(area);
        mNewRef.child("dimention").setValue(dimention);
        mNewRef.child("minrent").setValue(Integer.parseInt(minrent));
        mNewRef.child("maxrent").setValue(Integer.parseInt(maxrent));
        mNewRef.child("mapaddres").setValue(mapadres);
        mNewRef.child("latitude").setValue(lat);
        mNewRef.child("longitut").setValue(log);
        mNewRef.child("bedrooms").setValue(bedrom);
        mNewRef.child("bathrooms").setValue(bthrom);
        mNewRef.child("need").setValue(purchaserent);
        Intent intent = new Intent(this, HomeNav.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
    }
        @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap=googleMap;
        LatLng latLng=new LatLng(33.6844,73.0479);
        MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("Islamabad").snippet("Capital");
        mMap.addMarker(markerOptions);
        CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(latLng,16);
        mMap.animateCamera(cameraUpdate);
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                        LOCATION_REQUEST_CODE);
                mapFragment.getMapAsync(this);
                Toast.makeText(this, "acess request", Toast.LENGTH_SHORT).show();

            }
            mapFragment.getMapAsync(WantedScreenReview.this);
        }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                                    this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
                        mapFragment.getMapAsync(this);
                        return;
                    }
                    mMap.setMyLocationEnabled(true);
                    mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                        @Override
                        public void onMyLocationChange(Location location) {
                            mapFragment.getMapAsync(WantedScreenReview.this);
                            LatLng ltlng = new LatLng(location.getLatitude(), location.getLongitude());
                            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                                    ltlng, 16f);
                            mMap.animateCamera(cameraUpdate);
                        }
                    });
                    Location location = mMap.getMyLocation();

                    mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                        @Override
                        public void onMapClick(LatLng latLng) {
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(latLng);

                            markerOptions.title(getAddress(latLng));
                            mMap.clear();
                            CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                                    latLng, 16);
                            mMap.animateCamera(location);
                            mMap.addMarker(markerOptions);
                        }
                    });


                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        mapFragment = (SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.map);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_REQUEST_CODE);
            mapFragment.getMapAsync(this);
            Toast.makeText(this, "acess request", Toast.LENGTH_SHORT).show();

        }

    }

    private String getAddress(LatLng latLng){

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {

                ft.remove(prev);
            }
            ft.addToBackStack(null);
            DialogFragment dialogFragment = new ConfirmAddress();
            Bundle args = new Bundle();
            args.putDouble("lat", latLng.latitude);
            args.putDouble("long", latLng.longitude);
            args.putString("address", address);
            dialogFragment.setArguments(args);
            dialogFragment.show(ft, "dialog");
            return address;
        } catch (IOException e) {
            e.printStackTrace();
            return "No Address Found";

        }


    }

    @Override
    public void sendInput(String mapAddress,double madLat,double mapLong) {
        MapAddress=mapAddress;
        MapLong=mapLong;
        MapLat=madLat;
    }
}