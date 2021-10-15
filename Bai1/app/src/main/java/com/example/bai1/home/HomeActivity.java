package com.example.bai1.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.bai1.R;
import com.example.bai1.home.adapter.ViewPagerAdapter;
import com.example.bai1.home.nowplaying.NowPlayingFragment;
import com.example.bai1.home.popular.PopularFragment;
import com.example.bai1.home.toprate.TopRateFragment;
import com.example.bai1.home.upcoming.UpComingFragment;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter mViewPagerAdapter;
    private UpComingFragment mUpComingFragment;
    private TopRateFragment mTopRateFragment;
    private PopularFragment mPopularFragment;
    private NowPlayingFragment mNowPlayingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        addTabs(mViewPager);
        tabLayout.setupWithViewPager(mViewPager);
        initActions();
    }

    private void initView() {
        mViewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_home);
    }

    private void addTabs(ViewPager viewPager) {
        mNowPlayingFragment = NowPlayingFragment.newInstance();
        mPopularFragment = PopularFragment.newInstance();
        mTopRateFragment = TopRateFragment.newInstance();
        mUpComingFragment = UpComingFragment.newInstance();
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(mPopularFragment);
        mViewPagerAdapter.addFragment(mTopRateFragment);
        mViewPagerAdapter.addFragment(mUpComingFragment);
        mViewPagerAdapter.addFragment(mNowPlayingFragment);
        viewPager.setAdapter(mViewPagerAdapter);
    }

    private void initActions(){
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mPopularFragment.observeViewModel();
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}