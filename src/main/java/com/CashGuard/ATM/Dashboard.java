package com.CashGuard.ATM;

/**
 *  * Runs an ATM's data through the predictor, denomination analyser,
 *  * and trend analyser, and packages the results into one
 *  * DashboardSummary.
 */
public class Dashboard {

    public DashboardSummary build(ATM atm) {
        CashShortagePredictor predictor = new CashShortagePredictor();
        DenominationAnalyser denominationAnalyzer = new DenominationAnalyser();
        TrendAnalyser trendAnalyzer = new TrendAnalyser();

        return new DashboardSummary(
                atm.getId(),
                atm.getLocation(),
                predictor.determineRiskLevel(atm),
                predictor.predictHoursToEmpty(atm),
                denominationAnalyzer.mostUsedDenomination(atm),
                denominationAnalyzer.totalByDenomination(atm),
                trendAnalyzer.dailyWithdrawalSeries(atm),
                trendAnalyzer.dailyBalanceSeries(atm)
        );
    }
}
