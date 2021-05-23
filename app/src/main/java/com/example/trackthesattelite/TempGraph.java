package com.example.trackthesattelite;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class TempGraph extends Fragment {
    GraphView tempGraph;
    ArrayList<Double> temp;
    FileReader array = new FileReader();
    Resources res;
    LineGraphSeries<DataPoint> series;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.temp_graph_layout, null);
        tempGraph = view.findViewById(R.id.tempGraph);
        tempGraph.getViewport().setXAxisBoundsManual(true);
        tempGraph.getViewport().setMinX(0);
        tempGraph.getViewport().setMaxX(200);
        tempGraph.getViewport().setScalable(true);

        res = getResources();
        array.FileReader(res);
        temp = new ArrayList<>(array.temps);
        double x = (double) 1440 / temp.size();
        series = new LineGraphSeries<>();

        for (int i = 0; i < temp.size(); i++) {
            series.appendData(new DataPoint(x * i, temp.get(i)), true, temp.size());
        }
        tempGraph.addSeries(series);

        return view;
    }
}
