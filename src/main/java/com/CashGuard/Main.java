package com.CashGuard;


import com.CashGuard.ATM.ATM;
import com.CashGuard.ATM.ATMDataHandler;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello and welcome! My name is Kamogelo");

        ATM taxiRankATM = ATMDataHandler.loadDataFromFile(
                "C:\\Users\\kedig\\Documents\\GitHub\\CashGuard\\src\\main\\resources\\atm_mall.csv",
                500000
        );


        System.out.println("Taxi Rank ATM ID: " + taxiRankATM.getId());
        System.out.println("Capa:" + taxiRankATM.getMaxCashCapacity());
        System.out.println("Loca:" + taxiRankATM.getLocation());

    }
}