package com.pondokprogrammer.qosim.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pondokprogrammer.qosim.MainActivity;
import com.pondokprogrammer.qosim.R;
import com.pondokprogrammer.qosim.activities.QosimapActivity;
import com.pondokprogrammer.qosim.activities.QosimedAddActivity;
import com.pondokprogrammer.qosim.adapters.QosimedAdapter;
import com.pondokprogrammer.qosim.models.QosimedModel;

import java.util.ArrayList;

/**
 * Created by muhammad on 08/04/16.
 */
public class MyQosimedFragment extends Fragment {

    private ArrayList<QosimedModel> dataQosimed;
    
    public MyQosimedFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataQosimed = new ArrayList<>();
        dataQosimed.add(new QosimedModel(R.drawable.sosmed_picture_1,"Ummi Hanni","@ummihanni",R.drawable.desserts,"Puding adalah nama untuk berbagai hidangan penutup yang umumnya dibuat dari bahan-bahan yang direbus, dikukus, atau dipanggang. Istilah puding juga dipakai untuk berbagai jenis pai."));
        dataQosimed.add(new QosimedModel(R.drawable.sosmed_picture_2,"Khalid Basalamah","@khalidbasalamah",R.drawable.kabah,"Assalamualaikum. Temen2 pecinta Baitullah kita kumpul yuk di Cimahi tepatnya di Gedung Rabbani Cimahi tgl 5 Mei 2016."));
        dataQosimed.add(new QosimedModel(R.drawable.sosmed_picture_3,"Nicky Valentino","@nickyvalen",R.drawable.alaqsa,"Masjid Al-Aqsa, juga ditulis Al-Aqsha adalah salah satu tempat suci agama Islam yang menjadi bagian dari kompleks bangunan suci di Kota Lama Yerusalem."));
        dataQosimed.add(new QosimedModel(R.drawable.fahmiazian,"Fahmi A.Zain","@fahmiazain",R.drawable.muslimjepang,"Muslim Indonesia banyak berperan mendukung aspek wisata halal di Jepang. Dari segi jumlah wisatawan saja, pada 2014 lalu Indonesia menyumbang sebanyak 150 ribu wisatawan ke Jepang. Sebagian besar wisatawan Muslim."));
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myqosimed, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView lvQosimed = (ListView) getActivity().findViewById(R.id.lvQosimedLetest);
        lvQosimed.setVerticalScrollBarEnabled(true);
        QosimedAdapter adapter = new QosimedAdapter(getContext(), dataQosimed);
        lvQosimed.setAdapter(adapter);

        ((MainActivity) getActivity()).setActionBarTitle("My Qosimed");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.qosimed_action_plus:
                Intent onint = new Intent(getActivity(),QosimedAddActivity.class);
                startActivity(onint);
                break;
            case R.id.qosimed_action_location:
                Intent qosimap = new Intent(getActivity(),QosimapActivity.class);
                startActivity(qosimap);
                break;
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_my_qosimed, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
