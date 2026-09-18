package com.CashGuard.ATM;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads a single ATM's data file and turns it into an ATM Object.
 */
public class ATMDataHandler
{
    //Add a method to read in a file containing transactions from the atm
    public static ATM loadDataFromFile(String path, double maxCashCapacity)
    {
        //list of transaction records from atm
        List<ATMDayTransaction> transactions = new ArrayList<ATMDayTransaction>();
        //ATM particulars
        String id = null;
        String location = "";

        //capacity guard
        if(maxCashCapacity <= 0)
            throw new IllegalArgumentException("Max cash capacity must be greater than 0.");

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path)))
        {

            // skip header line
            String line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] tokens = line.split(",");

                //get atm id and its location once on the first row, doesn't change on each row
                if(id == null)
                {
                    id = tokens[0];
                    location = tokens[1];
                }

                //get rest of the data from csv file
                ATMDayTransaction transaction = getTransaction(tokens);
                //add to list of day transactions
                transactions.add(transaction);

            }

        }
        catch (IllegalArgumentException e) {
            System.err.println("Invalid argument: " + e.getMessage());
        }
        catch (IOException e) 
       {
           System.err.println("File error while reading " + path + ": " + e.getMessage());
       }

        return new ATM(id,location,maxCashCapacity,transactions);
    }

    //helper method to get the rest of the data from the remaining columns
    private static ATMDayTransaction getTransaction(String[] tokens) {
        ATMDayTransaction transaction = new ATMDayTransaction();

        transaction.setDate(LocalDate.parse(tokens[2]));
        transaction.setDate(LocalDate.parse(tokens[2]));
        transaction.setWithdrawalAmount(Double.parseDouble(tokens[3]));
        transaction.setDenominationBreakdown(new Denomination(
                Double.parseDouble(tokens[4]),
                Double.parseDouble(tokens[5]),
                Double.parseDouble(tokens[6]),
                Double.parseDouble(tokens[7]),
                Double.parseDouble(tokens[8])
        ));
        transaction.setClosingBalance(Double.parseDouble(tokens[10]));
        transaction.setDowntime(Boolean.parseBoolean(tokens[11]));
        transaction.setPayday(Boolean.parseBoolean(tokens[12]));
        transaction.setMonthEnd(Boolean.parseBoolean(tokens[13]));
        transaction.setPublicHoliday(Boolean.parseBoolean(tokens[14]));
        return transaction;
    }
}
