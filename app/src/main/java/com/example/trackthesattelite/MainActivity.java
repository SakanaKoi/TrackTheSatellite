package com.example.trackthesattelite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    Button polyButton;
    EditText newTemp;
    TextView newDeviant;
    Resources res;
    Lagrange lagrange = new Lagrange();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = getResources();
        polyButton = findViewById(R.id.polyButton);
        newTemp = findViewById(R.id.newTemp);
        newDeviant = findViewById(R.id.newDeviant);
        polyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double deviation = lagrange.polynomial(res, Integer.parseInt(newTemp.getText().toString()));
                newDeviant.setText(String.valueOf(deviation));
            }
        });
    }
}