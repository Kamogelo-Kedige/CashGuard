package com.CashGuard.ATM;

import java.time.LocalDate;
import java.util.Map;

/**
 * Bundles everything needed for the dashboard
 */
public record DashboardSummary(int atmId,
                               String location,
                               String riskLevel,
                               double hoursToEmpty,
                               String mostUsedDenomination,
                               Denomination denominationTotals,
                               Map<LocalDate, Double> withdrawalTrend,
                               Map<LocalDate, Double> balanceTrend)
{ }
