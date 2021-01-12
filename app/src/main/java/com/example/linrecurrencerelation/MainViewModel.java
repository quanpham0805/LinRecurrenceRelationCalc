package com.example.linrecurrencerelation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class MainViewModel extends AndroidViewModel {

    private int counter = 1;
    private int[] termsData = new int[102];
    private int[] coeffData = new int[102];

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void AddOne() {
        if (counter < 100) { termsData[counter] = 0; coeffData[counter] = 0; counter ++;}
    }

    public void SubtractOne() {
        if (counter > 1) counter --;
    }

    public int getCounter() {
        return counter;
    }

    public void setTerms(int position, int value) {
        termsData[position] = value;
    }

    public int getTerms(int position) {
        return termsData[position];
    }

    public void setCoeff(int position, int value) {
        coeffData[position] = value;
    }

    public int getCoeff(int position) {
        return coeffData[position];
    }

    public void reset() {
        counter = 1;
        termsData[0] = 0;
        coeffData[0] = 0;
    }

    public int[] extractTermsData(int len) {
        int[] tempData = new int[len];
        for (int i = 0 ; i < len ; i ++)
            tempData[i] = termsData[i];

        return tempData;
    }

    public int[] extractCoeffData(int len) {
        int[] tempData = new int[len];
        for (int i = 0 ; i < len ; i ++)
            tempData[i] = coeffData[i];

        return tempData;
    }
}
