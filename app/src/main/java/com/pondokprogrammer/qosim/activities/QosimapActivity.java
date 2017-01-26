package com.pondokprogrammer.qosim.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pondokprogrammer.qosim.R;
import com.pondokprogrammer.qosim.models.QosimapModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class QosimapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int[] drawables = {
            R.drawable.qosimap_marker_red,
            R.drawable.qosimap_marker_green,
            R.drawable.qosimap_marker_yellow,
            R.drawable.qosimap_marker_orange,
            R.drawable.qosimap_marker_green,
            R.drawable.qosimap_marker_red,
            R.drawable.qosimap_marker_orange,
            R.drawable.qosimap_marker_yellow
    };
    private List<QosimapModel> data;

    class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        MyInfoWindowAdapter() {
            myContentsView = getLayoutInflater().inflate(R.layout.windowlayout, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = ((TextView) myContentsView.findViewById(R.id.tvRestoName));
            String substrTitle=marker.getTitle().toString();
            tvTitle.setText(substrTitle.substring(0, 12)+"...");
            TextView tvSnippet = ((TextView) myContentsView.findViewById(R.id.tvRestoAddess));
            tvSnippet.setText(marker.getSnippet());
            ImageView ivPrev = ((ImageView) myContentsView.findViewById(R.id.img_resto));

            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/century_gothic.ttf");
            tvSnippet.setTypeface(custom_font);

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qosimap);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarOnMap);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_white_24dp);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://pondokprogrammer.com/qosim/qosimap.json";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Gson gson = new Gson();
                            Type collectionType = new TypeToken<ArrayList<QosimapModel>>() {
                            }.getType();
                            data = gson.fromJson(response.toString(), collectionType);

                            for (int newlang = 0; newlang < data.size(); newlang++) {
                                double latToReal = Double.parseDouble(data.get(newlang).latitude.toString());
                                double longToReal = Double.parseDouble(data.get(newlang).longitude.toString());
                                LatLng markerLocation = new LatLng(latToReal, longToReal);
                                mMap.addMarker(new MarkerOptions()
                                                .position(markerLocation)
                                                .title(data.get(newlang).name.toString()).icon(BitmapDescriptorFactory
                                                        .fromResource(drawables[newlang]))
                                                .snippet(data.get(newlang).address.toString())
                                );
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(markerLocation));
                            }
                            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                                @Override
                                public void onInfoWindowClick(Marker marker) {
                                    // TODO Auto-generated method stub
                                    Intent goTo = new Intent(getApplicationContext(), QosimapDetailActivity.class);
                                    goTo.putExtra("markerID", marker.getId());
                                    goTo.putExtra("markerTitle", marker.getTitle());
                                    goTo.putExtra("markerAdress", marker.getSnippet());
                                    startActivity(goTo);
                                }
                            });
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-6.241364, 106.816768), 16.0f));
                            mMap.setInfoWindowAdapter(new MyInfoWindowAdapter());

                        } catch (Exception e) {
                            Log.d("Error ", e.toString());
                            Toast.makeText(getApplicationContext(), "Failed Load data", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR", "error => " + error.toString());
                        Toast.makeText(getApplicationContext(), "Failed : Please check your connection", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }
        );
        queue.add(postRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_map_qosimap, menu);
        // Action View
        return true;

    }
}