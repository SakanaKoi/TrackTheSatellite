package com.example.trackthesattelite;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {
    Button tempButton, deviationsButton, lagrangeButton;
    TempGraph tempGraph;
    DeviationsGraph deviationsGraph;
    LagrangeGraph lagrangeGraph;
    FragmentManager fragmentManager;
    Animation finishAct, startAct;
    FrameLayout graphContainer;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_graph);
        tempButton = findViewById(R.id.tempButton);
        deviationsButton = findViewById(R.id.deviationsButton);
        lagrangeButton = findViewById(R.id.lagrangeButton);
        graphContainer = findViewById(R.id.container);

        finishAct = AnimationUtils.loadAnimation(this, R.anim.to_from);
        finishAct.setAnimationListener(goNext);
        startAct = AnimationUtils.loadAnimation(this, R.anim.from_to);
        tempButton.startAnimation(startAct);
        deviationsButton.startAnimation(startAct);
        lagrangeButton.startAnimation(startAct);
        graphContainer.startAnimation(startAct);

        intent = new Intent(GraphActivity.this, MainActivity.class);
        tempGraph = new TempGraph();
        deviationsGraph = new DeviationsGraph();
        lagrangeGraph = new LagrangeGraph();
        fragmentManager = getSupportFragmentManager();

        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.container, tempGraph);
                ft.commit();
            }
        });
        deviationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.container, deviationsGraph);
                ft.commit();
            }
        });
        lagrangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.container, lagrangeGraph);
                ft.commit();
            }
        });
    }
    Animation.AnimationListener goNext = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            tempButton.setVisibility(View.INVISIBLE);
            deviationsButton.setVisibility(View.INVISIBLE);
            lagrangeButton.setVisibility(View.INVISIBLE);
            graphContainer.setVisibility(View.INVISIBLE);
            GraphActivity.this.finish();
            startActivity(intent);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    @Override
    public void onBackPressed() {
        tempButton.startAnimation(finishAct);
        deviationsButton.startAnimation(finishAct);
        lagrangeButton.startAnimation(finishAct);
        graphContainer.startAnimation(finishAct);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tempButton.startAnimation(startAct);
        deviationsButton.startAnimation(startAct);
        lagrangeButton.startAnimation(startAct);
        graphContainer.startAnimation(startAct);
    }
}