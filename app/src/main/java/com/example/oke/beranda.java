package com.example.oke;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTabHost;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class beranda extends Fragment {
    private FragmentTabHost mTabHost;
    public beranda() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);

        mTabHost = (FragmentTabHost)view.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(),getChildFragmentManager(),R.id.realtabcontent);
        mTabHost.addTab(mTabHost.newTabSpec("play").setIndicator("playing now"),play.class,null);
        mTabHost.addTab(mTabHost.newTabSpec("coming").setIndicator("Coming soon"),coming.class,null);
        return view;
    }


}
