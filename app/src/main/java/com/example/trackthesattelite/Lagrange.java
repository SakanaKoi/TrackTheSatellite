package com.example.trackthesattelite;

import android.content.res.Resources;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeMap;

public class Lagrange {
    FileReader arrays = new FileReader();

    public double polynomial(Resources res, double newX) {
        arrays.FileReader(res);
        ArrayList<Double> x = new ArrayList<>(arrays.temps);
        ArrayList<Double> y = new ArrayList<>(arrays.deviants);
        double newY = 0;
        double st = Math.pow(10, 6);
        for (int i = 0; i < y.size(); i++) {
            double sum = 1;

            for (int j = 0; j < x.size(); j++) {
                if (j != i) {
                    sum = sum * Math.ceil(((newX - x.get(j)) / (x.get(i) - x.get(j))) * st) / st;
                }
            }
            newY = newY + (y.get(i) * sum);
        }
        return newY;
    }
}
