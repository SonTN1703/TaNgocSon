package com.example.bai1.home.upcoming;

import android.os.Bundle;

import androidx.fragment.app.Fragment;


public class UpComingFragment extends Fragment {
    public static UpComingFragment newInstance() {
        Bundle args = new Bundle();
        UpComingFragment fragment = new UpComingFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
