package com.aimtechltd.ieltspointsenumerator;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView listeningBand,
            readingBand,
            overallBand;

    private EditText listeningScore,
            readingScore,
            listeningScoreOv,
            readingScoreOv,
            writingScoreOv,
            speakingScoreOv;

    private RadioGroup readingType;


    private Button calculateListening,
            calculateReading,
            calculateOverall;

    private Context context;

    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544/6300978111");

        adView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);

        calculateListening.setOnClickListener(this);
        calculateReading.setOnClickListener(this);
        calculateOverall.setOnClickListener(this);
    }

    private void initializeViews() {

        listeningBand = findViewById(R.id.listening_band);
        readingBand = findViewById(R.id.reading_band);
        overallBand = findViewById(R.id.overall_band);

        listeningScore = findViewById(R.id.listening_score);
        readingScore = findViewById(R.id.reading_score);
        listeningScoreOv = findViewById(R.id.listening_score_ov);
        readingScoreOv = findViewById(R.id.reading_score_ov);
        writingScoreOv = findViewById(R.id.writing_score_ov);
        speakingScoreOv = findViewById(R.id.speaking_score_ov);

        readingType = findViewById(R.id.reading_type);

        calculateListening = findViewById(R.id.calculate_listening);
        calculateReading = findViewById(R.id.calculate_reading);
        calculateOverall = findViewById(R.id.calculate_overall);

        context = getApplicationContext();


        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-7291069729871905/1035813175");
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.calculate_listening) {
            calculateListeningScore();
        } else if (id == R.id.calculate_reading) {
            int type = readingType.getCheckedRadioButtonId();

            if (type == R.id.academic) {
                calculateAcademicReadingScore();
            } else if (type == R.id.general) {
                calculateGeneralReadingScore();
            }


        } else if (id == R.id.calculate_overall) {
            calculateOverallScore();
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

    }

    private void calculateAcademicReadingScore() {


        String text = readingScore
                .getText()
                .toString()
                .trim();

        if (TextUtils.isEmpty(text)) {
            Toast.makeText(context,
                    "Please enter any value",
                    Toast.LENGTH_SHORT).show();

            readingScore.setText("");

            return;
        }
        int score = Integer
                .parseInt(text);

        double result = 0.0;

        if (score < 0 || score > 40) {
            Toast.makeText(context,
                    "Please enter any value between 0 and 40 inclusive",
                    Toast.LENGTH_SHORT).show();

            readingScore.setText("");
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
        } else if (score >= 8 && score <= 9) {
            result = 3.5;
        } else if (score >= 6 && score <= 7) {
            result = 3.0;
        } else if (score >= 4 && score <= 5) {
            result = 2.5;
        }

        readingBand.setText("Your band score is: " + result);
    }

    private void calculateGeneralReadingScore() {

        String text = readingScore
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

            readingScore.setText("");
        } else if (score == 40) {
            result = 9.0;
        } else if (score == 39) {
            result = 8.5;
        } else if (score >= 37 && score <= 38) {
            result = 8.0;
        } else if (score == 36) {
            result = 7.5;
        } else if (score >= 34 && score <= 35) {
            result = 7.0;
        } else if (score >= 32 && score <= 33) {
            result = 6.5;
        } else if (score >= 30 && score <= 31) {
            result = 6.0;
        } else if (score >= 27 && score <= 29) {
            result = 5.5;
        } else if (score >= 23 && score <= 26) {
            result = 5.0;
        } else if (score >= 19 && score <= 22) {
            result = 4.5;
        } else if (score >= 15 && score <= 18) {
            result = 4.0;
        } else if (score >= 12 && score <= 14) {
            result = 3.5;
        } else if (score >= 9 && score <= 11) {
            result = 3.0;
        } else if (score >= 6 && score <= 8) {
            result = 2.5;
        }

        readingBand.setText("Your band score is: " + result);
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

    }
}
