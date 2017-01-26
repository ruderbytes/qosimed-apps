package com.pondokprogrammer.qosim.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pondokprogrammer.qosim.fragments.MyQosimedFragment;

/**
 * Created by muhammad on 09/04/16.
 */
public class LatestsTabAdapter extends FragmentPagerAdapter {

    private String[] title_tab = {"LATESTS","FAVORITES", "ALL"};

    public LatestsTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();

        switch (position){
            case 0:
                fragment = new MyQosimedFragment();
                break;
            case 1:
//                fragment = new FragmentFindTaxi();
                break;
            case 2:
//                fragment = new FragmentFamilyMode();
                break;

        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title_tab[position];
    }
}