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


    /**
     * Calculates the average daily withdrawal amount using the most recent 3 days.
     * @param atm
     * @return
     */
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

    public double calculateWithdrawalDemandMultiplier(ATM atm, ATMDayTransaction day)
    {
        List<ATMDayTransaction> atmHistory = atm.getAtmTransactionHistory();

        //normal typical days
        double normalDaysTotal = 0;
        int normalDaysCount = 0;

        //Special days like payday, holidays or month end
        double specialDaysTotal = 0;
        int specialDaysCount = 0;

        for(ATMDayTransaction atmDayTransaction : atmHistory)
        {
            boolean isSpecialDay = atmDayTransaction.isPayday() ||  atmDayTransaction.isMonthEnd() || atmDayTransaction.isPublicHoliday();
            if(isSpecialDay)
            {
                specialDaysTotal += atmDayTransaction.getWithdrawalAmount();
                specialDaysCount++;
            }else
            {
                //Handle normal days
                normalDaysTotal += atmDayTransaction.getWithdrawalAmount();
                normalDaysCount++;
            }
        }

        boolean todayIsSpecial = day.isPayday() || day.isMonthEnd() || day.isPublicHoliday();

        // If today is normal, or we don't have enough data no adjustment is needed.
        if (!todayIsSpecial || normalDaysCount == 0  || specialDaysCount == 0)
            return 1.0;

        double normalDaysAverage =  normalDaysTotal / normalDaysCount;

        double specialDaysAverage =  specialDaysTotal / specialDaysCount;

        return specialDaysAverage / normalDaysAverage;
    }

    /**
     * Calculates the predicted amount that will likely be withdrawn from the ATM per day.
     * @param atm
     */
    public double calculatePredictedDailyWithdrawal(ATM atm) {

        ATMDayTransaction today = atm.getLatestRecord();

        double recentAverage = calculateRecentAverage(atm);

        double demandMultiplier = calculateWithdrawalDemandMultiplier(atm, today);

        return recentAverage * demandMultiplier;
    }

    /**
     * Predicts how many hours the ATM has likely before its cash balance reaches zero.
     * @param atm
     */
    public double predictHoursToEmpty(ATM atm) {

        ATMDayTransaction today = atm.getLatestRecord();

        double predictedDailyWithdrawal = calculatePredictedDailyWithdrawal(atm);

        //  If no predicted withdrawals then ATM is not expected to run out of cash.
        if (predictedDailyWithdrawal <= 0) {
            return Double.MAX_VALUE;
        }

        double currentBalance = today.getClosingBalance();

        return (currentBalance / predictedDailyWithdrawal) * 24;
    }


    /**
     * Determines a risk level based on the predicted time until the ATM runs out of cash.
     */
    public String determineRiskLevel(ATM atm) {

        double hoursToNoCash = predictHoursToEmpty(atm);

        double currentBalance = atm.getLatestRecord().getClosingBalance();

        double percentOfCapacity = (currentBalance / atm.getMaxCashCapacity()) * 100;

        String riskLevel = "";

        if (hoursToNoCash < 24) {

            riskLevel = "Critical";

        } else if (hoursToNoCash < 48) {

            riskLevel = "High";

        } else if (hoursToNoCash < 72) {

            riskLevel = "Medium";

        } else {

            riskLevel = "Low";
        }

        // Safety-percentage rule overrides the calculated risk
        if (percentOfCapacity < SAFETY_PERCENTAGE) {

            riskLevel = "Critical";
        }

        return riskLevel;
    }

}
