package com.aimtechltd.ieltspointsenumerator.fragment;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aimtechltd.ieltspointsenumerator.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class OverallBandScoreFragment
        extends Fragment
        implements View.OnClickListener {

    private TextView overallBand;

    private EditText listeningScoreOv,
            readingScoreOv,
            writingScoreOv,
            speakingScoreOv;

    private Button calculateOverall;

    private Context context;

    private AdView mAdView;


    public OverallBandScoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater
                .inflate(R.layout.fragment_overall_band_score,
                        container,
                        false);

        initializeViews(view);

        setupAds();

        calculateOverall.setOnClickListener(this);

        return view;
    }

    private void initializeViews(View v) {

        overallBand = v.findViewById(R.id.overall_band);

        listeningScoreOv = v.findViewById(R.id.listening_score_ov);
        readingScoreOv = v.findViewById(R.id.reading_score_ov);
        writingScoreOv = v.findViewById(R.id.writing_score_ov);
        speakingScoreOv = v.findViewById(R.id.speaking_score_ov);

        calculateOverall = v.findViewById(R.id.calculate_overall);

        context = getContext();

        mAdView = v.findViewById(R.id.adView3);


    }

    private void setupAds() {

        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = new AdView(context);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-7291069729871905/2778776279");

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.calculate_overall) {
            calculateOverallScore();
        }

    }


    private void calculateOverallScore() {

        String text1 = listeningScoreOv
                .getText()
                .toString()
                .trim();
        String text2 = readingScoreOv
                .getText()
                .toString()
                .trim();

        String text3 = writingScoreOv
                .getText()
                .toString()
                .trim();

        String text4 = speakingScoreOv
                .getText()
                .toString()
                .trim();


        if (TextUtils.isEmpty(text1)
                || TextUtils.isEmpty(text2)
                || TextUtils.isEmpty(text3)
                || TextUtils.isEmpty(text4)) {
            Toast.makeText(context,
                    "Any empty value is not acceptable",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        double listeningScore = Double
                .parseDouble(text1);

        double readingScore = Double
                .parseDouble(text2);

        double writingScore = Double
                .parseDouble(text3);

        double speakingScore = Double
                .parseDouble(text4);

        if (listeningScore > 9 ||
                readingScore > 9 ||
                writingScore > 9 ||
                speakingScore > 9) {
            Toast.makeText(context,
                    "Values must be in between 0 and 9",
                    Toast.LENGTH_SHORT).show();
            listeningScoreOv.setText("");
            readingScoreOv.setText("");
            writingScoreOv.setText("");
            speakingScoreOv.setText("");
            return;
        }

        double result = (listeningScore
                + readingScore
                + writingScore
                + speakingScore) / 4.0;

        int result2 = (int) result;

        double dif = result - result2;

        result -= dif;

        if (dif >= 0.0 && dif <= 0.24) {
            dif = 0.0;
        } else if (dif >= 0.25 && dif <= 0.74) {
            dif = 0.5;
        } else if (dif >= 0.75 && dif <= 1.0) {
            dif = 1.0;
        }

        result += dif;

        overallBand.setText("Your band score is: " + result);

        showDialog(result);

    }

    private void showDialog(double result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Congratulations");
        builder.setMessage("Your band score is: " + result);

        builder.setCancelable(false);

        builder.setPositiveButton("Rate us!!!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);

                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
                }
            }
        });

        builder.setNegativeButton("No, cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();

    }


}
