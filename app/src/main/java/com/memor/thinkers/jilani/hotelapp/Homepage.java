package com.memor.thinkers.jilani.hotelapp;

import android.support.design.widget.TabLayout;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import java.util.ArrayList;
        import java.util.List;

public class Homepage extends AppCompatActivity
{
    private android.support.v7.widget.Toolbar toolbar;
    private TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=(android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.beginFakeDrag();
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons()
    {
        tabLayout.getTabAt(0).setIcon(R.drawable.location).setText("Near Me");
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_launcher_background).setText("Explore");
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_launcher_background).setText("Cart");
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_launcher_background).setText("Account");
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_launcher_background).setText("Favorite");

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