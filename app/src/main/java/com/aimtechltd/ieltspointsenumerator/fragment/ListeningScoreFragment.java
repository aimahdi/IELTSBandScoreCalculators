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
public class ListeningScoreFragment
        extends Fragment
        implements View.OnClickListener {

    private TextView listeningBand;

    private EditText listeningScore;

    private Button calculateListening;

    private Context context;

    private AdView mAdView;


    public ListeningScoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater
                .inflate(R.layout.fragment_listening_score,
                        container,
                        false);

        initializeViews(view);

        setupAds();

        calculateListening.setOnClickListener(this);

        return view;
    }


    private void initializeViews(View v) {

        listeningBand = v.findViewById(R.id.listening_band);

        listeningScore = v.findViewById(R.id.listening_score);

        calculateListening = v.findViewById(R.id.calculate_listening);

        context = getContext();

        mAdView = v.findViewById(R.id.adView1);
    }

    private void setupAds() {

        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = new AdView(context);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-7291069729871905/5302612233");

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.calculate_listening) {
            calculateListeningScore();
        }

    }

    private void calculateListeningScore() {

        String text = listeningScore
                .getText()
                .toString()
                .trim();

        if (TextUtils.isEmpty(text)) {
            Toast.makeText(context,
                    "Please enter any value",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        int score = Integer
                .parseInt(text);

        double result = 0.0;
        if (score < 0 || score > 40) {
            Toast.makeText(context,
                    "Please enter any value between 0 and 40 inclusive",
                    Toast.LENGTH_SHORT).show();
            listeningScore.setText("");
            return;
        } else if (score >= 39 && score <= 40) {
            result = 9.0;
        } else if (score >= 37 && score <= 38) {
            result = 8.5;
        } else if (score >= 35 && score <= 36) {
            result = 8.0;
        } else if (score >= 33 && score <= 34) {
            result = 7.5;
        } else if (score >= 30 && score <= 32) {
            result = 7.0;
        } else if (score >= 27 && score <= 29) {
            result = 6.5;
        } else if (score >= 23 && score <= 26) {
            result = 6.0;
        } else if (score >= 19 && score <= 22) {
            result = 5.5;
        } else if (score >= 15 && score <= 18) {
            result = 5.0;
        } else if (score >= 13 && score <= 14) {
            result = 4.5;
        } else if (score >= 10 && score <= 12) {
            result = 4.0;
        }

        listeningBand.setText("Your band score is: " + result);

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
