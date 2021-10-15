package com.example.bai1.home.nowplaying;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class NowPlayingFragment extends Fragment {
    public static NowPlayingFragment newInstance() {
        Bundle args = new Bundle();
        NowPlayingFragment fragment = new NowPlayingFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
