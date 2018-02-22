package com.memor.thinkers.jilani.hotelapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Near_Me extends Fragment {

    ViewPager viewPager;
    private List<Data> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DataAdapter mAdapter;


    public Near_Me() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View c=(View) inflater.inflate(R.layout.fragment_near__me, container, false);
        viewPager=(ViewPager) c.findViewById(R.id.nearmeviewpager);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(0,0,800,0);
        viewPager.setPageMargin(50);

        recyclerView = (RecyclerView) c.findViewById(R.id.recyclerview);

        mAdapter = new DataAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();

        Near_Me_Viewpageradapter viewpageradapter=new Near_Me_Viewpageradapter(getContext());
        viewPager.setAdapter(viewpageradapter);
        return c;
    }

    private void prepareMovieData() {
        Data movie = new Data("Mad Max: Fury Road", "Action & Adventure");
        movieList.add(movie);

        Data movie1 = new Data("Mad Max: Fury Road", "Action & Adventure");
        movieList.add(movie1);

        Data movie2 = new Data("Mad Max: Fury Road", "Action & Adventure");
        movieList.add(movie2);

        Data movie3 = new Data("Mad Max: Fury Road", "Action & Adventure");
        movieList.add(movie3);

        Data movie4 = new Data("Mad Max: Fury Road", "Action & Adventure");
        movieList.add(movie4);
    }
}
