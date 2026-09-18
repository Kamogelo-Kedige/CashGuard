package com.CashGuard.ATM;

import java.util.List;

/**
 * Predicts how soon an ATM is likely to run out of money based on its recent 3-day behaviour
 * Then assigns a risk level
 */
public class CashShortagePredictor {

    // Number of recent days used to calculate average withdrawals
    private static final int WINDOW_DAYS = 3;

    // If cash falls below 15% of capacity, the ATM has Critical cash levels
    private static final double SAFETY_PERCENTAGE = 15.0;



    public double calculateRecentAverage(ATM atm)
    {
        List<ATMDayTransaction> atmHistory = atm.getAtmTransactionHistory();
        int historySize = atmHistory.size();

        //Get the starting index for the latest 3-day window
        int startTransactionIndex = Math.max(0, historySize - WINDOW_DAYS);

        double total = 0;
        int count = 0;
        double average = 0;

        for(int i = startTransactionIndex; i < historySize; i++)
        {
            //get the withdrawal amount
            total += atmHistory.get(i).getWithdrawalAmount();
            count++;
        }

        average = total / count;

        return average;
    }

}
