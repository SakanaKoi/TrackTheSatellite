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
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class LagrangeGraph extends Fragment {
    GraphView lagrangeGraph;
    ArrayList<Double> temp, deviants;
    Map<Double, Double> relations = new TreeMap<>();
    FileReader array = new FileReader();
    Resources res;
    LineGraphSeries<DataPoint> series;
    Lagrange lagrange = new Lagrange();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.temp_graph_layout, null);
        lagrangeGraph = view.findViewById(R.id.tempGraph);
        lagrangeGraph.getViewport().setXAxisBoundsManual(true);
        lagrangeGraph.getViewport().setMinX(13.3);
        lagrangeGraph.getViewport().setMaxX(35);
        lagrangeGraph.getViewport().setScalable(true);

        res = getResources();
        array.FileReader(res);
        temp = new ArrayList<>(array.temps);
        deviants = new ArrayList<>(array.deviants);
        series = new LineGraphSeries<>();

        for (int i = 0; i < temp.size(); i++) {
            relations.put(temp.get(i), deviants.get(i));
        }

        Set<Map.Entry<Double, Double>> keySet = relations.entrySet();
        Map.Entry<Double, Double>[] keyArray = keySet.toArray(new Map.Entry[keySet.size()]);

        for (int i = 0; i < keyArray.length; i++) {
            series.appendData(new DataPoint(keyArray[i].getKey(), lagrange.polynomial(res, keyArray[i].getKey())), true, temp.size());
        }
        lagrangeGraph.addSeries(series);

        return view;
    }
}
