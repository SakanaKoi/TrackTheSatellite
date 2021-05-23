package com.example.trackthesattelite;

import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class FileReader {
    ArrayList<Double> temps = new ArrayList<>();
    ArrayList<Double> deviants = new ArrayList<>();
    ArrayList<Double> rawTemps = new ArrayList<>();

    public void FileReader(Resources res) {
        BufferedReader temperaturesReader, deviationsReader;
        String line;
        temps.clear();
        rawTemps.clear();
        deviants.clear();
        try {
            temperaturesReader = new BufferedReader(new InputStreamReader(res.openRawResource(R.raw.temperatures)));
            while ((line = temperaturesReader.readLine()) != null) {
                rawTemps.add(Double.valueOf(line.replace(" ", "")));
            }
            deviationsReader = new BufferedReader(new InputStreamReader(res.openRawResource(R.raw.deviations)));
            while ((line = deviationsReader.readLine()) != null) {
                deviants.add(Double.valueOf(line.replace(" ", "")));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int counter = rawTemps.size() % deviants.size();
        int sub = rawTemps.size() / deviants.size();
        double putIn;
        for (int i = 0; i < rawTemps.size(); i += sub) {
            putIn = 0;
            for (int j = i; j < i + sub; j++) {
                putIn += rawTemps.get(j);
                }
            if (counter != 0) {
                counter--;
                putIn += rawTemps.get(i + sub);
                i++;
                putIn /= sub + 1;
            }
            else {
                putIn /= sub;
            }
            temps.add(putIn);
        }
        int outCount = 0, inCount;
        while (outCount != temps.size() - 2) {
            inCount = outCount + 1;
            while (inCount != temps.size() - 1) {
                if (temps.get(inCount).equals(temps.get(outCount))) {
                    temps.remove(inCount);
                    deviants.remove(inCount);
                }
                inCount++;
            }
            outCount++;
        }
    }
}
