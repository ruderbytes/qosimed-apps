package com.pondokprogrammer.qosim.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.pondokprogrammer.qosim.MainActivity;
import com.pondokprogrammer.qosim.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

/**
 * Created by muhammad on 11/04/16.
 */
public class CalendarFragment extends Fragment{
    MaterialCalendarView calendarView;
    int selectedMonth;
    public CalendarFragment() {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_calendar_kajian, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        selectedMonth=4-1;
        calendarView = (MaterialCalendarView) getActivity().findViewById(R.id.calendarView);
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {

                calendarView.setDateSelected(CalendarDay.from(2016, selectedMonth, 17), true);
                calendarView.setDateSelected(CalendarDay.from(2016, selectedMonth, 29),true);

            }
        });
        calendarView.setDateSelected(CalendarDay.from(2016, selectedMonth, 17), true);
        calendarView.setDateSelected(CalendarDay.from(2016, selectedMonth, 29),true);

        ((MainActivity) getActivity()).setActionBarTitle("Calendar & Info Kajian");
    }

}
