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

public class DeviationsGraph extends Fragment {
    GraphView deviationsGraph;
    ArrayList<Double> deviants;
    FileReader array = new FileReader();
    Resources res;
    LineGraphSeries<DataPoint> series;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.deviations_graph_layout, null);
        deviationsGraph = view.findViewById(R.id.deviationsGraph);
        deviationsGraph.getViewport().setXAxisBoundsManual(true);
        deviationsGraph.getViewport().setMinX(0);
        deviationsGraph.getViewport().setMaxX(200);
        deviationsGraph.getViewport().setScalable(true);

        res = getResources();
        array.FileReader(res);
        deviants = new ArrayList<>(array.deviants);
        double x = (double) 1440 / deviants.size();

        series = new LineGraphSeries<>();
        for (int i = 0; i < deviants.size(); i++) {
            series.appendData(new DataPoint(x * i, deviants.get(i)), true, deviants.size());
        }
        deviationsGraph.removeAllSeries();
        deviationsGraph.addSeries(series);

        return view;
    }
}
