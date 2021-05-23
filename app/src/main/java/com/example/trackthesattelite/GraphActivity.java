package com.example.trackthesattelite;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        tempButton = findViewById(R.id.tempButton);
        deviationsButton = findViewById(R.id.deviationsButton);
        lagrangeButton = findViewById(R.id.lagrangeButton);

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
}