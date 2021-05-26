package com.example.trackthesattelite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    Button polyButton, graphButton;
    EditText newTemp;
    TextView newDeviant;
    Resources res;
    Intent intent;
    Animation finishAct, startAct;
    Lagrange lagrange = new Lagrange();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.activity_main);
        res = getResources();
        graphButton = findViewById(R.id.graphButton);
        polyButton = findViewById(R.id.polyButton);
        newTemp = findViewById(R.id.newTemp);
        newDeviant = findViewById(R.id.newDeviant);

        finishAct = AnimationUtils.loadAnimation(this, R.anim.to_from);
        finishAct.setAnimationListener(goNext);
        startAct = AnimationUtils.loadAnimation(this, R.anim.from_to);

        polyButton.startAnimation(startAct);
        graphButton.startAnimation(startAct);
        newTemp.startAnimation(startAct);
        newDeviant.startAnimation(startAct);

        intent = new Intent(MainActivity.this, GraphActivity.class);
        newTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTemp.setBackgroundColor(Color.TRANSPARENT);
            }
        });
        polyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(newTemp.getText().toString()) || ".".equals(newTemp.getText().toString())) {
                    newTemp.setBackgroundColor(Color.RED);
                    newDeviant.setText("Необходимо ввести значение температуры!");
                    newDeviant.setTextColor(Color.RED);
                }
                else {
                    newTemp.setBackgroundColor(Color.TRANSPARENT);
                    newDeviant.setTextColor(Color.parseColor("#FF8BC34A"));
                    double deviation = lagrange.polynomial(res, Double.parseDouble(newTemp.getText().toString()));
                    newDeviant.setText(R.string.newDeviation);
                    newDeviant.append(" " + String.valueOf(deviation));
                }
            }
        });
        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                polyButton.startAnimation(finishAct);
                graphButton.startAnimation(finishAct);
                newTemp.startAnimation(finishAct);
                newDeviant.startAnimation(finishAct);
            }
        });
    }
    Animation.AnimationListener goNext = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            polyButton.setVisibility(View.INVISIBLE);
            graphButton.setVisibility(View.INVISIBLE);
            newTemp.setVisibility(View.INVISIBLE);
            newDeviant.setVisibility(View.INVISIBLE);
            MainActivity.this.finish();
            startActivity(intent);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        polyButton.startAnimation(startAct);
        graphButton.startAnimation(startAct);
        newTemp.startAnimation(startAct);
        newDeviant.startAnimation(startAct);
    }
}