package com.example.trackthesattelite;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.TreeMap;

public class Lagrange {
    FileReader arrays = new FileReader();

    public double polynomial(Resources res, double newX) {
        arrays.FileReader(res);
        ArrayList<Double> x = new ArrayList<>(arrays.temps);
        ArrayList<Double> y = new ArrayList<>(arrays.deviants);
        double newY = 0;
        for (int i = 0; i < y.size(); i++) {
          double numerator = 1, denominator = 1;
            for (int j = 0; j < x.size(); j++) {
//                if (x.get(i).equals(x.get(j))) {
//                    continue;
//                }
                if (j != i) {
                    numerator *= (newX - x.get(j));
                    denominator *= (x.get(i) - x.get(j));
                }
            }
            newY += (y.get(i) * numerator / denominator);

        }
        return newY;
    }
}
