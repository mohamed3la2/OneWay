package com.example.eltimmy.oneway2;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
        , LocationListener {

    private GoogleMap mMap;

    private FusedLocationProviderApi locationProviderApi = LocationServices.FusedLocationApi;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;

    private LatLng lng,lng1,lng2,lng3;
    public static final int MINUTE_PER_SECOND = 1000;
    public static final int MINUTE = 5 * MINUTE_PER_SECOND;
    private Marker marker=null;
    private TextView textView;


    private Object dataTransfer[];
    private String url;
    private int waypoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

             /* googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        locationRequest = new LocationRequest();

        locationRequest.setInterval(MINUTE);
        locationRequest.setFastestInterval(1 * MINUTE_PER_SECOND);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
*/
    }


    private String getDirctionsUrl() {
        StringBuilder googleDirectionsUrl=new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?");
        googleDirectionsUrl.append("origin="+lng1.latitude+","+lng1.longitude);
        googleDirectionsUrl.append("&destination="+lng.latitude+","+lng.longitude);
        googleDirectionsUrl.append("&waypoints=optimize:true|"
                +lng2.latitude+","+lng2.longitude+"|"
                +lng3.latitude+","+lng3.longitude);
        googleDirectionsUrl.append("&key=AIzaSyBlAFd3r2H62WgQNnmDMTMmiS7F9I-va5Q");

        return googleDirectionsUrl.toString();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        lng=new LatLng(30.6208678,32.2692151);
        lng1=new LatLng(31.2468149,32.3195051);
        lng2=new LatLng(31.2438146,32.3165811);
        lng3=new LatLng(31.241462, 32.316670);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(lng1));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lng1,12));

        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(lng).title("Marker 0"));
        mMap.addMarker(new MarkerOptions().position(lng1).title("Marker 1"));
        mMap.addMarker(new MarkerOptions().position(lng2).title("Marker 2"));
        mMap.addMarker(new MarkerOptions().position(lng3).title("Marker 3"));

        dataTransfer= new Object[4];
        url=getDirctionsUrl();
        GetDirectionsData getDirctionsData=new GetDirectionsData();
        waypoints=3;
        dataTransfer[0]=mMap;
        dataTransfer[1]=url;
        dataTransfer[2]=textView;
        dataTransfer[3]=waypoints;
        getDirctionsData.execute(dataTransfer);

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
  /*      if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    */}

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
/* if (marker!=null)
        {
            marker.remove();
        }

        //latLng= new LatLng(location.getLatitude(),location.getLongitude());
        //marker=mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
*/
    }


    /*@Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (googleApiClient.isConnected())
        {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);
    }
*/}
