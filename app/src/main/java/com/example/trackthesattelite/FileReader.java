package com.example.trackthesattelite;

import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class FileReader {
    ArrayList<Integer> temps = new ArrayList<>();
    ArrayList<Double> deviants = new ArrayList<>();

    public void FileReader(Resources res) {
        BufferedReader temperaturesReader, deviationsReader;
        String line;
        int tempAmount = 0, deviantAmount = 0;
        try {
            temperaturesReader = new BufferedReader(new InputStreamReader(res.openRawResource(R.raw.temperatures)));
            while ((line = temperaturesReader.readLine()) != null) {
                temps.add(Integer.valueOf(line.replace(" ", "")));
                tempAmount++;
            }
            deviationsReader = new BufferedReader(new InputStreamReader(res.openRawResource(R.raw.deviations)));
            while ((line = deviationsReader.readLine()) != null) {
                deviants.add(Double.valueOf(line.substring(1, 5)));
                deviantAmount++;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
