package com.pondokprogrammer.qosim.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pondokprogrammer.qosim.R;
import com.pondokprogrammer.qosim.adapters.LatestsTabAdapter;

/**
 * Created by muhammad on 09/04/16.
 */
public class LatestsTab extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_qasimed_latests, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tabLayout = (TabLayout)getActivity().findViewById(R.id.tablayout);
        viewPager = (ViewPager)getActivity().findViewById(R.id.viewpager);
        viewPager.setAdapter(new LatestsTabAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }
}
