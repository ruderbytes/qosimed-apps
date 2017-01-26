package com.pondokprogrammer.qosim.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pondokprogrammer.qosim.R;
import com.pondokprogrammer.qosim.models.MenuDrawerModel;

import java.util.ArrayList;

/**
 * Created by muhammad on 20/04/16.
 */
public class MenuDrawerAdapter extends BaseAdapter {

    private ArrayList<MenuDrawerModel> data;
    private LayoutInflater inflater;
    private Context context;

    public MenuDrawerAdapter(Context context, ArrayList<MenuDrawerModel> data) {
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

        convertView = inflater.inflate(R.layout.menu_drawer_listview, null);

        ImageView menuImg = (ImageView) convertView.findViewById(R.id.menuimg);
        TextView menutitle = (TextView) convertView.findViewById(R.id.menutitle);

        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/century_gothic.ttf");
        menutitle.setTypeface(custom_font);

        menutitle.setText(data.get(position).getMenutitle());
        menuImg.setImageResource(data.get(position).getMenuimg());
        return convertView;
    }
}
