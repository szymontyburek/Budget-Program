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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author szymo
 */
public class VerifyIncome extends Application
{
    private String message;
    private Button yes, no;
    private double totalBillsMonthly;
    private double totalIncome;
    private String timeFrame;
    private double billsRelativeToTimeFrame;
    private Stage primaryStage = new Stage();
    
    public VerifyIncome(String message, double totalBillsMonthly, double totalIncome, String timeFrame)
    {
        this.message = message;
        //pass the values in these fields below to the next class
        this.totalBillsMonthly = totalBillsMonthly;
        this.totalIncome = totalIncome;
        this.timeFrame = timeFrame;
    }
    public static void main(String[] args) 
    {
        launch(args);
    }
    public void start(Stage primaryStage)
    {
        //so I can close this window when needed
        this.primaryStage = primaryStage;
        //create a label
        Label displayIncome = new Label(message);
        
        //add displayIncome label to an hbox
        HBox displayIncomeHBox = new HBox(displayIncome);
        
        //create another label
        Label verify = new Label("Is this correct?");
        
        //add verify label to an hbox
        HBox verifyHBox = new HBox(verify);
        
        //create a 'yes' and 'no' button
        yes = new Button("Yes");
        no = new Button("No");
        
        //call buttonEventHandler function for both of these buttons
        buttonEventHandler(yes);
        buttonEventHandler(no);
        
        //add these buttons to a hbox
        HBox buttonsHBox = new HBox(10, yes, no);
        
        //add the previously created hbox's to a vbox
        VBox verifyVBox = new VBox(20, displayIncomeHBox, verifyHBox, buttonsHBox);
        
        //set the padding of the vbox
        verifyVBox.setPadding(new Insets(30));
        
        //center the hbox's in the verifyVBox vbox
        displayIncomeHBox.setAlignment(Pos.CENTER);
        verifyHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setAlignment(Pos.CENTER);
        
        //create a scene
        Scene scene2 = new Scene(verifyVBox);
        
        //add the scene to the stage
        primaryStage.setScene(scene2);
        
        //show the window
        primaryStage.show();
    }
    
    private void buttonEventHandler(Button button)
    {
        //create an event handler for the yes and no buttons
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                //check to make sure that income is more than bills relative to the time frame. For ex, if the user gets paid weekly, their income needs to be more than their monthly income divided by four
                if(timeFrame.equals("weekly"))
                {
                    billsRelativeToTimeFrame = totalBillsMonthly / 4;
                }
                else if(timeFrame.equals("bi-weekly"))
                {
                    billsRelativeToTimeFrame = totalBillsMonthly / 2;
                            
                }
                //if user gets paid monthly
                else
                {
                    billsRelativeToTimeFrame = totalBillsMonthly;
                }
                if(totalIncome > billsRelativeToTimeFrame)
                {
                    //if the yes button was clicked, create a savings object
                    if(button.equals(yes))
                    {
                        //close current window
                        primaryStage.close();
                        //open new window
                        Savings savings = new Savings(billsRelativeToTimeFrame, totalIncome, timeFrame, totalBillsMonthly);
                        //start GUI in Savings class
                        Stage stage = new Stage();
                        savings.start(stage);
                    
                    }
                    //if the no button was clicked
                    else
                    {
                        //make the 
                        openIncomeGUI();
                    } 
                }
                else
                {
                    //capitalize the first letter of timeFrame variable for String incomeMessage  
                    String s1 = timeFrame.substring(0, 1).toUpperCase();   
                    String s2 = timeFrame.substring(1);    
                    String timeFrameCapitalized = timeFrame.substring(0, 1).toUpperCase() + timeFrame.substring(1);
                    String incomeMessage = String.format("\n" + timeFrameCapitalized + " Income: $%,.2f\n", totalIncome);
                    String billsMessage = String.format("\nAmount owed for bills " + timeFrame + ": $%,.2f\n", billsRelativeToTimeFrame);
                    //throw up an error message stating that bills are more than income
                    JOptionPane.showMessageDialog(null, "Error: Income over a month span is less than bills due each month.\n" + incomeMessage + billsMessage +  "\nPlease try again.");
                    openIncomeGUI();
                }
                
            }
        });
        
    }
    private void openIncomeGUI()
    {
        //create an Income object and open that gui back up
                    Income tryAgain = new Income(totalBillsMonthly);
                    Stage stage = new Stage();
                    //close current Stage window
                    primaryStage.close();
                    tryAgain.start(stage);
    }
}
