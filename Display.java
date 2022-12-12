/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BudgetProgram;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author szymo
 */
public class Display extends Application
{
    private double savingsAmount;
    private double investmentAmount;
    private double billsRelativeToTimeFrame;
    private double totalIncome;
    private String timeFrame;
    private double totalBillsMonthly;
    private double moneyLeft;
    
    //constructor
    public Display(double billsRelativeToTimeFrame, double totalIncome, String timeFrame, double totalBillsMonthly, double moneyLeft, double savingsAmount, double investmentAmount)
    {
        this.savingsAmount = savingsAmount;
        this.investmentAmount = investmentAmount;
        this.billsRelativeToTimeFrame = billsRelativeToTimeFrame;
        this.totalIncome = totalIncome;
        this.timeFrame = timeFrame;
        this.totalBillsMonthly = totalBillsMonthly;
        this.moneyLeft = moneyLeft;
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage)
    {       
        //create a label to display on what kind of timeframe this all is
        Label timeFrameLabel = new Label("On a " + timeFrame + " basis:");
        
        //create a label to display the budget
        Label budget = new Label(String.format("Budget: $%,.2f", moneyLeft));
        //create a hbox and add budget Label to it so I can center it later on
        HBox budgetHBox = new HBox(budget);
        
        //create a label to display income
        Label incomeLabel = new Label(String.format("Income: $%,.2f", totalIncome));
        
        //create a label to display bills due per period
        Label billsDuePerPaycheck = new Label(String.format("Bills due: $%,.2f", billsRelativeToTimeFrame));
        
        //create a label to display amount devoted to savings
        Label devotedToSavings = new Label(String.format("Savings: $%,.2f", savingsAmount));
        
        //create a label to display amount devoted to investments
        Label devotedToInvestments = new Label(String.format("Investments: $%,.2f", investmentAmount));
        
        //create a vbox and add the titleHBox and all Labels previously created to it
        VBox vbox = new VBox(20, timeFrameLabel, budgetHBox, incomeLabel, billsDuePerPaycheck, devotedToSavings, devotedToInvestments);
        
        //center the hbox
        budgetHBox.setAlignment(Pos.CENTER);
        
        //create some padding for the vbox
        vbox.setPadding(new Insets(40));
        
        //make the font bigger for all labels
        timeFrameLabel.setFont(new Font(25));
        budget.setFont(new Font(20));
        makeFontBigger(incomeLabel);
        makeFontBigger(billsDuePerPaycheck);
        makeFontBigger(devotedToSavings);
        makeFontBigger(devotedToInvestments);
        
        //create a scene
        Scene scene = new Scene(vbox);
        
        //add the scene to the stage
        primaryStage.setScene(scene);
        
        //set the stage title
        primaryStage.setTitle("Budget Breakdown");
        
        //show the window
        primaryStage.show();
    }
    private void makeFontBigger(Label label)
    {
        label.setFont(new Font(15));
    }
}
