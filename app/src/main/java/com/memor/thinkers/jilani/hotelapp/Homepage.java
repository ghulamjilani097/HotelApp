package com.memor.thinkers.jilani.hotelapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
        import java.util.List;

public class Homepage extends AppCompatActivity
{
    private android.support.v7.widget.Toolbar toolbar;
    private TabLayout tabLayout;
    ViewPager viewPager;
    TextView location1;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        location1= (TextView) findViewById(R.id.location);
        locationManager= (LocationManager) getSystemService(LOCATION_SERVICE);

        toolbar=(android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.beginFakeDrag();
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED &&ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION},0);         //string array for multiple permissions.
            return;                 //if allow return true, if deny return false.
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener(){
            @Override
            public void onLocationChanged(Location location)
            {
                double latitude=location.getLatitude();
                double longitude=location.getLongitude();

                //now convert latitude and longitude into address.
                Geocoder geocoder=new Geocoder(Homepage.this);
                try{
                    List<Address> address=geocoder.getFromLocation(latitude,longitude,1);
                    String pinCode=address.get(0).getPostalCode();
                    String fulladdress=address.get(0).getAddressLine(0);

                    location1.append(fulladdress+", "+pinCode);
                    Log.e("Location: ",(fulladdress+" "+pinCode));
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle){}

            @Override
            public void onProviderEnabled(String s){}

            @Override
            public void onProviderDisabled(String s) {}
        });
    }

    private void setupTabIcons()
    {
        tabLayout.getTabAt(0).setIcon(R.drawable.location).setText("Near Me");
        tabLayout.getTabAt(1).setIcon(R.drawable.explore).setText("Explore");
        tabLayout.getTabAt(2).setIcon(R.drawable.cart).setText("Cart");
        tabLayout.getTabAt(3).setIcon(R.drawable.account).setText("Account");
        tabLayout.getTabAt(4).setIcon(R.drawable.favorite).setText("Favorite");

    }
    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Near_Me(),"");
        adapter.addFrag(new Explore(),"");
        adapter.addFrag(new Cart(),"");
        adapter.addFrag(new Account(),"");
        adapter.addFrag(new Favorite(),"");
        viewPager.setAdapter(adapter);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter
    {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position)
        {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
    }
}