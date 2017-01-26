package com.pondokprogrammer.qosim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pondokprogrammer.qosim.R;
import com.pondokprogrammer.qosim.models.QosimedModel;

import java.util.ArrayList;

public class QosimedAdapter extends BaseAdapter{

    private ArrayList<QosimedModel> data;
    private LayoutInflater inflater;
    private Context context;

    public QosimedAdapter(Context context, ArrayList<QosimedModel> data) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.list_view_layout, null);

        ImageView ivPricture = (ImageView) convertView.findViewById(R.id.sosmed_picture);
        TextView tvFullname = (TextView) convertView.findViewById(R.id.sosmed_fullname);
        TextView tvUsername = (TextView) convertView.findViewById(R.id.sosmed_username);
        ImageView ivImgPost = (ImageView) convertView.findViewById(R.id.sosmed_img_post);
        TextView tvDescriptons = (TextView) convertView.findViewById(R.id.sosmed_status);

        ivPricture.setImageResource(data.get(position).getImgprofile());
        tvFullname.setText(data.get(position).getFullname());
        tvUsername.setText(data.get(position).getUsername());
        ivImgPost.setImageResource(data.get(position).getImgpost());
        tvDescriptons.setText(data.get(position).getDescriptions());


        return convertView;
    }
}