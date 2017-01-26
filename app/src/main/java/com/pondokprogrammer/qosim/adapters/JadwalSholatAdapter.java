package com.pondokprogrammer.qosim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.pondokprogrammer.qosim.R;
import com.pondokprogrammer.qosim.models.JadwalSholatModel;

import java.util.List;

/**
 * Created by muhammad on 23/04/16.
 */
public class JadwalSholatAdapter extends BaseAdapter implements View.OnClickListener {


    private Context context;
    private LayoutInflater inflanteri;
    private List<JadwalSholatModel> data;
    private View alarm_imsak,alarm_subuh,alarm_dzuhur,alarm_ashar,alarm_magrib,alarm_isya;
    public JadwalSholatAdapter(Context context, List<JadwalSholatModel> data){
        inflanteri = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView==null){
            convertView = inflanteri.inflate(R.layout.fragment_jadwal_sholat_items,null);
            vh = new ViewHolder();
            vh.fajr = (TextView) convertView.findViewById(R.id.subuh);
            vh.shurooq = (TextView) convertView.findViewById(R.id.imsak);
            vh.dhuhr = (TextView) convertView.findViewById(R.id.dzuhur);
            vh.asr = (TextView) convertView.findViewById(R.id.ashar);
            vh.maghrib = (TextView) convertView.findViewById(R.id.magrib);
            vh.isha = (TextView) convertView.findViewById(R.id.isya);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        alarm_imsak = convertView.findViewById(R.id.alarm1);
        alarm_subuh = convertView.findViewById(R.id.alarm2);
        alarm_dzuhur = convertView.findViewById(R.id.alarm3);
        alarm_ashar = convertView.findViewById(R.id.alarm4);
        alarm_magrib = convertView.findViewById(R.id.alarm5);
        alarm_isya = convertView.findViewById(R.id.alarm6);
        alarm_imsak.setTag(position);
        alarm_subuh.setTag(position);
        alarm_dzuhur.setTag(position);
        alarm_ashar.setTag(position);
        alarm_magrib.setTag(position);
        alarm_imsak.setTag(position);
        alarm_imsak.setOnClickListener(this);
        alarm_subuh.setOnClickListener(this);
        alarm_dzuhur.setOnClickListener(this);
        alarm_ashar.setOnClickListener(this);
        alarm_magrib.setOnClickListener(this);
        alarm_isya.setOnClickListener(this);

//        Typeface face= Typeface.createFromAsset(context.getAssets(), "fonts/digital.otf");
//        vh.fajr.setTypeface(face);
//        vh.shurooq.setTypeface(face);
//        vh.dhuhr.setTypeface(face);
//        vh.asr.setTypeface(face);
//        vh.maghrib.setTypeface(face);
//        vh.isha.setTypeface(face);
        vh.fajr.setText(data.get(position).fajr);
        vh.shurooq.setText(data.get(position).shurooq);
        vh.dhuhr.setText(data.get(position).dhuhr);
        vh.asr.setText(data.get(position).asr);
        vh.maghrib.setText(data.get(position).maghrib);
        vh.isha.setText(data.get(position).isha);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.alarm1:
                Toast.makeText(context, "Left Accessory " + v.getTag(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.alarm2:
                Toast.makeText(context, "Right Accessory "+v.getTag(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.alarm3:
                Toast.makeText(context, "Right Accessory "+v.getTag(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.alarm4:
                Toast.makeText(context, "Right Accessory "+v.getTag(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.alarm5:
                Toast.makeText(context, "Right Accessory "+v.getTag(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.alarm6:
                Toast.makeText(context, "Right Accessory "+v.getTag(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private class ViewHolder{
        TextView fajr,shurooq,dhuhr,asr,maghrib,isha;
    }
}
