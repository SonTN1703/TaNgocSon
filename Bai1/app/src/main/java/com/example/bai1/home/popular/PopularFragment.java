package com.example.bai1.home.popular;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.bai1.home.nowplaying.NowPlayingFragment;

public class PopularFragment extends Fragment {
    public static PopularFragment newInstance() {
        Bundle args = new Bundle();
        PopularFragment fragment = new PopularFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
