package com.example.trackthesattelite;

import android.content.res.Resources;

import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class LagrangeGraph extends Fragment {
    GraphView lagrangeGraph;
    ArrayList<Double> temp, deviants;
    FileReader array = new FileReader();
    Resources res;
    LineGraphSeries<DataPoint> series;
}
