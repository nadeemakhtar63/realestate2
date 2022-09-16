package com.example.realestate.sellerscreen;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.Getsellerimages.RecyclerAdaptor;
import com.example.realestate.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class previopropertyselladd extends AppCompatActivity  implements OnMapReadyCallback {
    RecyclerView recyclerView;
    RecyclerAdaptor adaptor;
    private DatabaseReference mDatabaseUsers;
    private FirebaseAuth mAuth;
    Button btnpriceshow;
    Marker now;
    private GoogleMap mMap;
    int val;
    //    private ActivityGoogleMapBinding binding;
    private final static int PLACE_PICKER_REQUEST = 999;
    private final static int LOCATION_REQUEST_CODE = 23;
    String MapAddress;
    double MapLat, MapLong;
    private Uri filePath;
    StorageReference storageReference;
    List<String> linkImages = new ArrayList<>();
    private final int PICK_IMAGE_REQUEST = 71;
    ArrayList<Uri> listimages;
    ProgressDialog progressDialog;
    String propertyYype;
    SupportMapFragment mapFragment;
    private Location mCurrentLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previopropertyselladd);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.premap);
        mapFragment.getMapAsync(this);
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
        propertyYype = intent.getStringExtra("property_type");
        storageReference = FirebaseStorage.getInstance().getReference();
        Bundle bundle = getIntent().getExtras();
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Wait..Data Uploading...");

//        ArrayList listimages = bundle.getParcelable("listimage");
        listimages = (ArrayList<Uri>) getIntent().getSerializableExtra("listimage");

        String property_type = intent.getStringExtra("property_type");
//        btnpriceshow.setText("Rs"+rent+"/month");
        recyclerView = findViewById(R.id.recyclerView);
        adaptor = new RecyclerAdaptor(listimages);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adaptor);
        btnpriceshow.setText(minrent + "-" + maxrent + "/MONTH");
        btnpriceshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImageFileToFirebaseStorage(title, des, date, area, dimention, minrent, maxrent, MapAddress, MapLat, MapLong, listimages, totalbedrooms, totalbathrooms, sellRentchose);
            }
        });
        mAuth = FirebaseAuth.getInstance();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference();
    }

    public void btncack(View view) {
        finish();
    }

    private void saveData(String title, String des, String date, String area, String dimention, String minrent, String maxrent, String mapadres, double lat, double log, ArrayList<Uri> listimages, String bed, String toilts, String sell_ya_rent) {

        if (listimages.size() == linkImages.size()) {

            DatabaseReference mNewRef = mDatabaseUsers.child("sell").child(mAuth.getCurrentUser().getUid()).push();
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
            mNewRef.child("property_Type").setValue(propertyYype);
            mNewRef.child("bedrooms").setValue(bed);
            mNewRef.child("bathrooms").setValue(toilts);
            mNewRef.child("need").setValue(sell_ya_rent);
            for (int i = 0; i < linkImages.size(); i++) {
                mNewRef.child("imagelist").push().child("url").setValue(linkImages.get(i));
            }
//            Intent intent = new Intent(this, HomeNav.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }

    public void UploadImageFileToFirebaseStorage(String title, String des, String date, String area, String dimention, String minrent, String maxrent, String MapAddress, double MapLat, double MapLong, ArrayList<Uri> listimage, String tbed, String tbath, String sellrentc) {
        progressDialog.show();
        for (int i = 0; i < listimage.size(); i++) {
            // Checking whether FilePathUri Is empty or not.
            if (listimage.get(i) != null) {
                String Storage_Path = "All_Image_Uploads/";
                // Setting progressDialog Title.
//            progressDialog.setTitle("Image is Uploading...");

                // Showing progressDialog.
//            progressDialog.show();

                // Creating second StorageReference.
                StorageReference storageReference2nd = storageReference.child(Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(listimage.get(i)));
                // Adding addOnSuccessListener to second StorageReference.
                storageReference2nd.putFile(listimage.get(i))
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                storageReference2nd.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        Uri downloadUrl = uri;
                                        linkImages.add(downloadUrl.toString());
                                        saveData(title, des, date, area, dimention, minrent, maxrent, MapAddress, MapLat, MapLong, listimages, tbed, tbath, sellrentc);
                                    }
                                });
                            }

                        })
                        // If something goes wrong .
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {

                                // Hiding the progressDialog.

                                progressDialog.dismiss();
                                // Showing exception erro message.
                                Toast.makeText(previopropertyselladd.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        })

                        // On progress change upload time.
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                                // Setting progressDialog Title.


                            }
                        });

//                    if (i == listimage.size()-1) {
//                        Toast.makeText(previopropertyselladd.this, "" + i, Toast.LENGTH_SHORT).show();
//
//                    }

            } else {

                Toast.makeText(previopropertyselladd.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

            }

        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng latLng = new LatLng(33.6844, 73.0479);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Islamabad").snippet("Capital");
        mMap.addMarker(markerOptions);
//        onLocationChanged(markerOptions);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
        mMap.animateCamera(cameraUpdate);
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(@NonNull LatLng latLng) {
//
//            }
//        });
    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//        super.onPointerCaptureChanged(hasCapture);
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                        mapFragment.getMapAsync(this);
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    mMap.setMyLocationEnabled(true);
                    mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                        @Override
                        public void onMyLocationChange(Location location) {
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
                            mapFragment.getMapAsync(previopropertyselladd.this);
                            //markerOptions.title(getAddress(latLng));
                            mMap.clear();
                            CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                                    latLng, 15);
                            mMap.animateCamera(location);
                            mMap.addMarker(markerOptions);
                        }
                    });
                } else {
                    mapFragment.getMapAsync(previopropertyselladd.this);
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

//
//
//
//    private String getAddress(LatLng latLng){
//
//        Geocoder geocoder;
//        List<Address> addresses;
//        geocoder = new Geocoder(this, Locale.getDefault());
//
//        try {
//            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//            String city = addresses.get(0).getLocality();
//            String state = addresses.get(0).getAdminArea();
//            String country = addresses.get(0).getCountryName();
//            String postalCode = addresses.get(0).getPostalCode();
//            String knownName = addresses.get(0).getFeatureName();
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            Fragment prev = getFragmentManager().findFragmentByTag("dialog");
//            if (prev != null) {
//
//                ft.remove(prev);
//            }
//            ft.addToBackStack(null);
//            DialogFragment dialogFragment = new ConfirmAddress();
//            Bundle args = new Bundle();
//            args.putDouble("lat", latLng.latitude);
//            args.putDouble("long", latLng.longitude);
//            args.putString("address", address);
//            dialogFragment.setArguments(args);
//            dialogFragment.show(ft, "dialog");
//            return address;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "No Address Found";
//
//        }
//
//
//    }
//
//    @Override
//    public void sendInput(String mapAddress,double madLat,double mapLong) {
//        MapAddress=mapAddress;
//        MapLong=mapLong;
//        MapLat=madLat;
//    }
}