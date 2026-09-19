package com.CashGuard;

import javafx.application.Application;
import javafx.stage.Stage;
import com.CashGuard.ATM.ATM;
import com.CashGuard.ATM.ATMDataHandler;
import com.CashGuard.ATM.CashShortagePredictor;



public class Main extends Application {
    public static void main(String[] args) {

       launch(args);
       
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

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("CashRunway");
        primaryStage.show();
    }
}
