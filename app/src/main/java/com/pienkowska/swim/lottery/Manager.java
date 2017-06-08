package com.pienkowska.swim.lottery;

import java.util.HashMap;

public class Manager {
    private static Manager instance = null;
    private static final int INIT_LOTTERIES_NUMBER = 3;

    private HashMap<String, Lottery> lotteries;
    private String currentName;

    private Manager() {
        lotteries = new HashMap<>(INIT_LOTTERIES_NUMBER);
    }

    public static Manager getInstance() {
        if(instance == null)
            instance = new Manager();
        return instance;
    }

    public boolean addLottery(Lottery lottery, String name) {
        if(lotteries.containsKey(name))
            return false;
        lotteries.put(name, lottery);
        return true;
    }

    public Lottery getLottery(String name) {
        return lotteries.get(name);
    }

    public String getLotteryName() {
        return currentName;
    }

    public void setCurrent(String name) {
        currentName = name;
    }


}
