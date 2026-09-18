package com.CashGuard;


import com.CashGuard.ATM.ATM;
import com.CashGuard.ATM.ATMDataHandler;
import com.CashGuard.ATM.CashShortagePredictor;

public class Main {
    public static void main(String[] args) {

        ATM mallATM = ATMDataHandler.loadDataFromFile(
                "C:\\Users\\kedig\\Documents\\GitHub\\CashGuard\\src\\main\\resources\\atm_taxirank.csv",
                500000
        );


        //Sanity checks
        System.out.println("Taxi Rank ATM ID: " + mallATM.getId());
        System.out.println("Capa:" + mallATM.getMaxCashCapacity());
        System.out.println("Loca:" + mallATM.getLocation());
        CashShortagePredictor predictor =  new CashShortagePredictor();
        System.out.println("Hours to empty: " + predictor.predictHoursToEmpty(mallATM));
        System.out.println("Risk level: " + predictor.determineRiskLevel(mallATM));

    }
}