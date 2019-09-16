package com.example.oke.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTabHost;

import android.os.Bundle;
import android.view.View;

import com.example.oke.R;
import com.example.oke.fragment.coming;
import com.example.oke.fragment.play;

public class icash extends AppCompatActivity {
    private FragmentTabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icash);
//
//        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
//        mTabHost.setup(getActivity(),getChildFragmentManager(),R.id.realtabcontent);
//        mTabHost.addTab(mTabHost.newTabSpec("play").setIndicator("playing now"), play.class,null);
//        mTabHost.addTab(mTabHost.newTabSpec("coming").setIndicator("Coming soon"), coming.class,null);
    }


}
