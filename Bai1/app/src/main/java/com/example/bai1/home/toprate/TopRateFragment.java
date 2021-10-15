package com.example.bai1.home.toprate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;


public class TopRateFragment extends Fragment {
    public static TopRateFragment newInstance() {
        Bundle args = new Bundle();
        TopRateFragment fragment = new TopRateFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
