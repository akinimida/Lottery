package com.pienkowska.swim.lottery;

import android.util.Log;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.sqrt;

public class Lottery {
    public int layoutId;
    public int lotsNum;
    public int range;
    public double bid;

    private int[] numbers;
    public double[] rewards;

    public Lottery(int range, int lotsNum, int layoutId, double bid) {
        this.range = range;
        this.lotsNum = lotsNum;
        numbers = new int[range];
        initNumbers();
        this.layoutId = layoutId;
        this.bid = bid;
        rewards = new double[lotsNum];
        generateRewards();
    }

    public int[] getLots() {
        int[] lots = new int[lotsNum];
        for(int i = 0; i < lotsNum; i++)
            lots[i] = numbers[i];
        return lots;
    }

    private void initNumbers() {
        for(int i = 0; i < range; i++)
            numbers[i] = i + 1;
        shuffle();
    }

    public void shuffle()
    {
        Random rand = ThreadLocalRandom.current();
        for (int i = range - 1; i > 0; i--)
        {
            int index = rand.nextInt(i + 1);
            int t = numbers[index];
            numbers[index] = numbers[i];
            numbers[i] = t;
        }
    }

    private void generateRewards() {
        rewards[lotsNum - 1] = bid;
        double div = 2.2;
        for(int i = lotsNum - 2; i >= 0; i--) {
            rewards[i] = rewards[i + 1] / div ;
            div -= 1 / lotsNum;
        }

        while(rewards[0] > 5) {
            for(int i = 0; i < lotsNum - 1; i++)
                rewards[i] /= lotsNum - (i+1);
        }

        for(int i = 0; i < lotsNum; i++)
            rewards[i] = Math.floor(rewards[i]);
    }

}
