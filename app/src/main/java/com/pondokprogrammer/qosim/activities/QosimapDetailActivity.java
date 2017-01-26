package com.pondokprogrammer.qosim.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.pondokprogrammer.qosim.R;

public class QosimapDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qosimap_detail);
//        TextView tvMarkerID = (TextView) findViewById(R.id.id_marker);
        TextView tvMarkerTitle = (TextView) findViewById(R.id.marker_name);
        TextView tvMarkerAdress = (TextView) findViewById(R.id.marker_address);
        Bundle mapDetailInfo = getIntent().getExtras();
        if(mapDetailInfo != null) {
            String markerID=mapDetailInfo.getString("markerID");
            String markerTitle=mapDetailInfo.getString("markerTitle");
            String markerAdress=mapDetailInfo.getString("markerAdress");
            tvMarkerTitle.setText(markerTitle);
            tvMarkerAdress.setText(markerAdress);
//            tvMarkerID.setText(markerTitle);
        }
    }
}
