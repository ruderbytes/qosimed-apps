package com.pondokprogrammer.qosim.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pondokprogrammer.qosim.MainActivity;
import com.pondokprogrammer.qosim.R;

/**
 * Created by muhammad on 08/04/16.
 */
public class SocialConnectionsFragment extends Fragment {

    public SocialConnectionsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_social_connections, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).setActionBarTitle("Social Connections");
    }
}
