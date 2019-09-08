package com.example.oke;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTabHost;

import com.example.oke.R;
import com.example.oke.play;

public class coming extends Fragment {

  public coming() {
    // Required empty public constructor
  }
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_coming, container, false);

    return view;
  }


}