package com.CashGuard.model;

import java.util.List;

/**
 * Represents a single ATM at a specific location, along with its max cash capacity
 */
public class ATM
{
    //Attributes
    private String  id;
    private String location;
    private double maxCashCapacity; // max amount of cash the ATM can hold
    private List<ATMDayTransaction> atmTransactionHistory;

    /**
     * No args constructor
     */
    public ATM()
    {}

    /**
     * Parameterized Constructor
     * @param id
     * @param location
     * @param maxCashCapacity
     * @param atmTransactionHistory
     */
    public ATM(String id, String location, double maxCashCapacity, List<ATMDayTransaction> atmTransactionHistory) {
        this.id = id;
        this.location = location;
        this.maxCashCapacity = maxCashCapacity;
        this.atmTransactionHistory = atmTransactionHistory;
    }

    //Getter and Setter Pairs

    public String getId() {
        return id;
    }


    public String getLocation() {
        return location;
    }


    public double getMaxCashCapacity() {
        return maxCashCapacity;
    }

    public void setMaxCashCapacity(double maxCashCapacity) {
        this.maxCashCapacity = maxCashCapacity;
    }

    public List<ATMDayTransaction> getAtmTransactionHistory() {
        return atmTransactionHistory;
    }

    /**
     * Returns the most recent day recorded for this ATM.
     * Used by the predictor to know the "current" balance.
     */
    public ATMDayTransaction getLatestRecord() {

        if (atmTransactionHistory == null || atmTransactionHistory.isEmpty()) {
            return null;
        }
        return atmTransactionHistory.getLast();
    }

}
