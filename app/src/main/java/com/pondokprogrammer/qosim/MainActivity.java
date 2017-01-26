package com.pondokprogrammer.qosim;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.pondokprogrammer.qosim.activities.QosimapActivity;
import com.pondokprogrammer.qosim.adapters.MenuDrawerAdapter;
import com.pondokprogrammer.qosim.fragments.CalendarFragment;
import com.pondokprogrammer.qosim.fragments.HomeFragment;
import com.pondokprogrammer.qosim.fragments.JadwalSholatFragment;
import com.pondokprogrammer.qosim.fragments.ProfileFragment;
import com.pondokprogrammer.qosim.fragments.SocialConnectionsFragment;
import com.pondokprogrammer.qosim.models.MenuDrawerModel;
import com.pondokprogrammer.qosim.tabs.LatestsTab;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navLayout;
    private Fragment homeFragment, messagesFragment, checkMapFragment, qosimapFragment, infaqFragment,
                    taggedFragment, infokajian, jadwalSholat, qiblatFragment,settingsFragment;
    private ListView lv;
    private Context context;
    private ArrayList<MenuDrawerModel> dataOndrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;

        dataOndrawer = new ArrayList<>();
        dataOndrawer.add(new MenuDrawerModel("Profile",R.drawable.menu_friend));
        dataOndrawer.add(new MenuDrawerModel("Message",R.drawable.menu_message));
//        dataOndrawer.add(new MenuDrawerModel("Check Map",R.drawable.menu_map));
        dataOndrawer.add(new MenuDrawerModel("Qosimed",R.drawable.menu_penanda));
        dataOndrawer.add(new MenuDrawerModel("Infaq",R.drawable.menu_donate));
//        dataOndrawer.add(new MenuDrawerModel("Flagged",R.drawable.menu_flag));
        dataOndrawer.add(new MenuDrawerModel("Sholat",R.drawable.view_agenda));
        dataOndrawer.add(new MenuDrawerModel("Info Kajian", R.drawable.calendar));
        dataOndrawer.add(new MenuDrawerModel("Kiblat", R.drawable.menu_compass));
        dataOndrawer.add(new MenuDrawerModel("Settings", R.drawable.menu_setting));
        dataOndrawer.add(new MenuDrawerModel("Logout", R.drawable.menu_shutdown));

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.bg_custom_default));
        }

        homeFragment = new HomeFragment();
        messagesFragment = new ProfileFragment();
        settingsFragment = new SocialConnectionsFragment();
//        checkMapFragment = new MyQosimedFragment();
        jadwalSholat = new JadwalSholatFragment();
        checkMapFragment = new LatestsTab();
        qiblatFragment = new CalendarFragment();
        jadwalSholat = new JadwalSholatFragment();
        infaqFragment = new HomeFragment();

        toolbar =(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        drawerLayout =(DrawerLayout)findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer){

            @Override
            public void onDrawerSlide(View drawerView, float offset)
            {
                super.onDrawerSlide(drawerView, 0);
                View container = findViewById(R.id.allElement);
                container.setTranslationX(offset * drawerView.getWidth());
            }
        };
        toggle.setHomeAsUpIndicator(R.drawable.ico_menu);
        drawerLayout.setScrimColor(getResources().getColor(android.R.color.transparent));
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navLayout = (NavigationView)findViewById(R.id.navigationView);

        try{
            View header = LayoutInflater.from(this).inflate(R.layout.drawer_header, null);
            navLayout.addHeaderView(header);

            lv = (ListView) header.findViewById(R.id.left_drawer);

            MenuDrawerAdapter adapter = new MenuDrawerAdapter(this, dataOndrawer);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    view.setSelected(true);
                    switch (position){
                        case 0:
                            setFragment(messagesFragment);
                            break;
                        case 1:
                            setFragment(homeFragment);
                            //not yet
                            break;
//                        case 2:
//                            goToActivity(QosimapActivity.class, false);
//                            drawerLayout.closeDrawer(GravityCompat.START);
//                            break;
                        case 2:
                            setFragment(checkMapFragment);
                            break;
//                        case 4:
////                            setFragment(infaqFragment);
//                            setFragment(homeFragment);
//                            //not yet
//                            break;
                        case 3:
                            setFragment(homeFragment);
                            //not yet
                            break;
                        case 4:
                            setFragment(jadwalSholat);
                            break;
                        case 5:
                            setFragment(qiblatFragment);
                            break;
                        case 6:
//                            setFragment(qiblatFragment);
                            setFragment(homeFragment);
                            //not yet
                            break;
                        case 7:
                            setFragment(settingsFragment);
                            break;
                        case 8:
                            goToActivity(LoginActivity.class, true);
                            break;
                    }
                }
            });

            setListViewHeightBasedOnItems(lv);
            adapter.notifyDataSetChanged();
            setListViewHeightBasedOnItems(lv);

        }catch (Exception e){
            Log.d("Error exception home", Log.getStackTraceString(e));
        }

        navLayout.setNavigationItemSelectedListener(this);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_drawer_followers:
                setFragment(homeFragment);
                break;
            case R.id.menu_drawer_messages:
                setFragment(messagesFragment);
                break;
            case R.id.menu_drawer_checkmap:
                setFragment(checkMapFragment);
                break;
            case R.id.menu_drawer_qosimap:
                goToActivity(QosimapActivity.class, false);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu_drawer_infaq:
                setFragment(infaqFragment);
                break;
            case R.id.menu_drawer_tagged:
                setFragment(taggedFragment);
                break;
            case R.id.menu_drawer_qiblat:
                setFragment(qiblatFragment);
                break;
            case R.id.menu_drawer_settings:
                setFragment(settingsFragment);
                break;
            case R.id.menu_drawer_logout:
                goToActivity(LoginActivity.class, true);
                break;
        }
        return false;
    }
    public void setFragment(Fragment fr){
        getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,fr).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void goToActivity(Class activity, boolean isfinish){
        Intent goTo=new Intent(this,activity);
        startActivity(goTo);
        if (isfinish==true){
            finish();
        }
    }
}
