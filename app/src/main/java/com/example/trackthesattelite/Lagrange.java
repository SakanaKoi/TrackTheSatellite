package com.example.trackthesattelite;

import android.content.res.Resources;

import java.util.ArrayList;

public class Lagrange {
    FileReader arrays = new FileReader();

    public double polynomial(Resources res, int newX) {
        double newY = 0;
        arrays.FileReader(res);
        ArrayList<Integer> x = new ArrayList<>(arrays.temps);
        ArrayList<Double> y = new ArrayList<>(arrays.deviants);
        for (int i = 0; i < y.size(); i++) {
            double numerator = 1, denominator = 1;
            for (int j = 0; j < x.size(); j++) {
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
