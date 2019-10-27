package com.example.oke.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.oke.pemasukkan;
import com.example.oke.pengeluaran;

public class ad_tab extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    public ad_tab(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new pemasukkan();
            case 1:
                return new pengeluaran();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
