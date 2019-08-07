package com.aimtechltd.ieltspointsenumerator.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.aimtechltd.ieltspointsenumerator.R;
import com.aimtechltd.ieltspointsenumerator.adapter.TabAdapter;
import com.aimtechltd.ieltspointsenumerator.fragment.ListeningScoreFragment;
import com.aimtechltd.ieltspointsenumerator.fragment.OverallBandScoreFragment;
import com.aimtechltd.ieltspointsenumerator.fragment.ReadingFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    private ViewPager viewPager;

    private TabAdapter tabAdapter;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tabLayout);

        viewPager = findViewById(R.id.viewpager);

        tabAdapter = new TabAdapter(getSupportFragmentManager());

        tabAdapter.addFragment("Listening", new ListeningScoreFragment());
        tabAdapter.addFragment("Reading", new ReadingFragment());
        tabAdapter.addFragment("Overall", new OverallBandScoreFragment());

        viewPager.setAdapter(tabAdapter);

        tabLayout.setupWithViewPager(viewPager);

    }


}
