package com.example.trackthesattelite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    Button polyButton, graphButton;
    EditText newTemp;
    TextView newDeviant;
    Resources res;
    Intent intent;
    Lagrange lagrange = new Lagrange();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = getResources();
        graphButton = findViewById(R.id.graphButton);
        polyButton = findViewById(R.id.polyButton);
        newTemp = findViewById(R.id.newTemp);
        newDeviant = findViewById(R.id.newDeviant);
        polyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double deviation = lagrange.polynomial(res, Double.parseDouble(newTemp.getText().toString()));
                newDeviant.setText(String.valueOf(deviation));
            }
        });
        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, GraphActivity.class);
                startActivity(intent);
            }
        });
    }
}