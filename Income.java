/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BudgetProgram;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author szymo
 */
public class Income extends Application
{
    private double totalBillsMonthly;
    private TextField income = new TextField();
    private Button weekly, biweekly, monthly;
    private String message;
    private double totalIncome;
    private Stage primaryStage = new Stage();
    private String timeFrame;
    private String timeFrameString;
    private String timeFrameForMessageString;
    
    //constructor
    public Income(double totalBillsMonthly)
    {
        this.totalBillsMonthly = totalBillsMonthly;
    }
    
    public static void main(String[] args) 
    {
        //launch the gui
        launch(args);
    }
    public void start(Stage primaryStage)
    {
        //so I can close this window when needed
        this.primaryStage = primaryStage;
        
        //create an incomePromptLabel that asks the user for their income
        Label incomePromptLabel = new Label("How much do you make every paycheck? $");
        
        //add incomePromptLabel and income TextField to an hbox
        HBox incomeHbox = new HBox(incomePromptLabel, income);
        
        //create a label for the next hbox
        Label howOften = new Label("How often do you receive this?");
        
        //create an hbox to add the howOften label to 
        HBox howOftenHbox = new HBox(howOften);
        
        //create 3 buttons
        weekly = new Button("Week");
        biweekly = new Button("2 Weeks");
        monthly = new Button("Month");
        
        //call all three functions that hold the event handlers for these buttons
        //create an event handler for the weekly button
        buttonEventHandler(weekly);
        buttonEventHandler(biweekly);
        buttonEventHandler(monthly);
        
        //create an hbox and add all these buttons to it
        HBox buttons = new HBox(10, weekly, biweekly, monthly);
        
        //add all hbox's to a vbox
        VBox vbox = new VBox(10, incomeHbox, howOftenHbox, buttons);
        
        //add padding to vbox
        vbox.setPadding(new Insets(30));
        
        //center the howOftenHbox and buttons hbox
        incomeHbox.setAlignment(Pos.CENTER);
        howOftenHbox.setAlignment(Pos.CENTER);
        buttons.setAlignment(Pos.CENTER);
        
        //create a scene
        Scene scene = new Scene(vbox);
        
        //add the scene to the stage
        primaryStage.setScene(scene);
        
        //set the stage title
        primaryStage.setTitle("Income");
        
        //show the window
        primaryStage.show();
        
    }
    private void buttonEventHandler(Button button)
    {
        //create an event handler for the weekly button
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                //only carry out the next code if textfield has a value in it
                try
                {
                    //gather a message to use in VerifyIncome class
                    totalIncome = Double.parseDouble(income.getText());
                    if(button.equals(weekly))
                    {
                        timeFrame = "weekly";
                        timeFrameString = "every week.";
                    }
                    else if(button.equals(biweekly))
                    {
                        timeFrame = "bi-weekly";
                        timeFrameString = "every 2 weeks.";
                    }
                    else
                    {
                        timeFrame = "monthly";
                        timeFrameString = "every month.";
                    }
                    message = String.format("You roughly make $%,.2f " + timeFrameString + "\n", totalIncome);   
                    VerifyIncome verify = new VerifyIncome(message, totalBillsMonthly, totalIncome, timeFrame);
                    Stage stage = new Stage();
                    //close the current window
                    primaryStage.close();
                    verify.start(stage);
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Invalid income entered(Note: Do not use commas). Please try again.");
                    income.setText("");
                }
            }
        });
    }
}
