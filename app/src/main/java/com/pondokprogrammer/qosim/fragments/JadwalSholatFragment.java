package com.pondokprogrammer.qosim.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.pondokprogrammer.qosim.MainActivity;
import com.pondokprogrammer.qosim.R;
import com.pondokprogrammer.qosim.adapters.JadwalSholatAdapter;
import com.pondokprogrammer.qosim.models.JadwalSholatModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by muhammad on 16/04/16.
 */
public class JadwalSholatFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jadwal_sholat,container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPrayerTime();
        final TextView txtcdn= (TextView) getActivity().findViewById(R.id.count_down);

        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        int startHour=(hour*60)*60*1000;
        int startMinute=(minute*60)*1000;
        int startSecond=second*1000;

        int endHour = (14*60)*60*1000;
        int endMinute = (57*60)*1000;
        int endSecond = 00*1000;

        int startMillisec=startHour+startMinute+startSecond;
        int endMillisec=endHour+endMinute+endSecond;
        long countDown=endMillisec-startMillisec;



        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm");
        String strDate = mdformat.format(calendar.getTime());
        display(strDate);


        //06:19:00 start
        //11:36:00 end
        //5:17:00 range = 19020000
        //1:16;39 = 4600000
        //1:16;39 = 4599000
        //1020

        new CountDownTimer(countDown,1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                txtcdn.setText(hms);
            }

            public void onFinish() {
//                txtc.setText("done!");
            }
        }.start();

        ((MainActivity) getActivity()).setActionBarTitle("JADWAL SHALAT");

    }

    private void display(String num) {
        final TextView txtc= (TextView) getActivity().findViewById(R.id.jam_now);
        txtc.setText(num);
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void getPrayerTime() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://muslimsalat.com/daily.json?key=8e9fa83064771b1c43037d40cc674bf6";

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // response
                        String jsonResult = response.toString();

                        ArrayList<JadwalSholatModel> items = null;
                        try{
                            Gson gson = new GsonBuilder().create();
                            ParsingResult dataResult = gson.fromJson(jsonResult, ParsingResult.class);
                            JsonObject jsonObj = gson.fromJson(jsonResult, JsonObject.class);
                            String title = jsonObj.get("title").getAsString();
                            String city = jsonObj.get("city").getAsString();
                            String state = jsonObj.get("state").getAsString();
                            String country = jsonObj.get("country").getAsString();
                            String title2= jsonObj.get("qibla_direction").getAsString();
                            items = dataResult.getItems();
                            ListView itemsList = (ListView) getActivity().findViewById(R.id.jadwal_sholat_listview);
                            JadwalSholatAdapter adapter = new JadwalSholatAdapter(getContext(),items);
                            itemsList.setAdapter(adapter);
                            itemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                    RelativeLayout rl = (RelativeLayout)view.getParent();
//                                    TextView tv = (TextView)rl.findViewById(R.id.alarm1);
//                                    String text = tv.getText().toString();
//                                    Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }catch (Exception e){
                            Log.d("Exception", e.toString());
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("ERROR", "error => " + error.toString());
                    }
                }
        );
        queue.add(postRequest);

    }
    private class ParsingResult {

        public ArrayList<JadwalSholatModel> items;
        public ArrayList<JadwalSholatModel> getItems() {
            return items;
        }
    }

}
