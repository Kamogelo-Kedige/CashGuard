package com.CashGuard.ATM;

import java.util.List;

/**
 * Represents a single ATM at a specific location, along with its max cash capacity
 */
public class ATM
{
    //Attributes
    private String  id;
    private String location;
    private double maxCashCapacity; // max cash the ATM can hold
    private List<ATMDayTransaction> records;

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
     * @param records
     */
    public ATM(String id, String location, double maxCashCapacity, List<ATMDayTransaction> records) {
        this.id = id;
        this.location = location;
        this.maxCashCapacity = maxCashCapacity;
        this.records = records;
    }

    //Getter and Setter Pairs

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getMaxCashCapacity() {
        return maxCashCapacity;
    }

    public void setMaxCashCapacity(double maxCashCapacity) {
        this.maxCashCapacity = maxCashCapacity;
    }

    public List<ATMDayTransaction> getRecords() {
        return records;
    }

    public void setRecords(List<ATMDayTransaction> records) {
        this.records = records;
    }
}
