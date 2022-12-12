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

/**
 *
 * @author szymo
 */
public class Savings extends Application
{
    private double billsRelativeToTimeFrame;
    private double totalIncome;
    private String timeFrame;
    private Stage primaryStage = new Stage();
    private Button yes, no;
    private double totalBillsMonthly;
    private double moneyLeft;
      
    //constructor
    public Savings(double billsRelativeToTimeFrame, double totalIncome, String timeFrame, double totalBillsMonthly)
    {
        this.billsRelativeToTimeFrame = billsRelativeToTimeFrame;
        this.totalIncome = totalIncome;
        this.timeFrame = timeFrame;
        this.totalBillsMonthly = totalBillsMonthly;
        this.moneyLeft = totalIncome - billsRelativeToTimeFrame;
        
        
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage)
    {
        //so I can close this window when needed
        this.primaryStage = primaryStage;
        
        Label devotePrompt = new Label("Devote any income to savings, investments, or anything else?");
        
        //create an hbox and add previously created label to it
        HBox devotePromptHBox = new HBox(devotePrompt);
        
        //create a yes and no button
        Button yes = new Button("Yes");
        Button no = new Button("No");
        
        //create event handler for 'no' button
        no.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                //close the current window and open up Display GUI by creating a Display object
                primaryStage.close();
                Display display = new Display(billsRelativeToTimeFrame, totalIncome, timeFrame, totalBillsMonthly, moneyLeft, 0, 0);
                Stage stage = new Stage();
                display.start(stage);
            }
        });
        //create event handler for 'yes' button
        yes.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                //close the current window and open up SavingsAndInvestments GUI by creating a SavingsAndInvestments object
                primaryStage.close();
                Stage stage = new Stage();
                SavingsAndInvestments x = new SavingsAndInvestments(billsRelativeToTimeFrame, totalIncome, timeFrame, totalBillsMonthly, moneyLeft);
                x.start(stage);
            }
        });
        
        
        
        
        //add buttons to an hbox
        HBox buttonsHBox = new HBox(10, yes, no);
        
        //create a vbox and add hbox's to them
        VBox vbox = new VBox(20, devotePromptHBox, buttonsHBox);
        
        //center all hbox's inside the vbox
        devotePromptHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setAlignment(Pos.CENTER);
        
        //add padding inside the vbox
        vbox.setPadding(new Insets(30));
        
        //create a scene
        Scene scene = new Scene(vbox);
        
        //add the scene to the stage
        primaryStage.setScene(scene);
        
        //set the stage title
        primaryStage.setTitle("Savings");
        
        //show the window
        primaryStage.show();
    }
}
