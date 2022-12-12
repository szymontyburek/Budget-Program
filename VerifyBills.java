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
public class VerifyBills extends Application
{
    private double total;
    private Stage primaryStage;
    private Button yes, no;
    
    //constructor
    public VerifyBills(double total)
    {
        this.total = total;
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage)
    {
        //so I can close this window when needed
        this.primaryStage = primaryStage;
        
        //create a message for the next label
        String message = String.format("You owe $%,.2f in bills monthly", total);
        //create a promptLabel
        Label promptLabel = new Label(message);
        
        //create an hbox and add promptLabel to it
        HBox promptHBox = new HBox(promptLabel);
        
        //create another label
        Label isThisCorrect = new Label("Is this correct?");
        
        //create a hbox and add previously created label to it
        HBox verifyHBox = new HBox(isThisCorrect);
        
        //create two buttons
        Button yes = new Button("Yes");
        Button no = new Button("No");
        
        //add buttons to an hbox
        HBox buttonsHBox = new HBox(20, yes, no);
        
        //create event handler for 'yes' button
        yes.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                //close the current window and open up Income GUI by creating an Income class object
                primaryStage.close();
                Stage stage = new Stage();
                Income income = new Income(total);
                income.start(stage);
            }
        });
        
        //create event handler for 'no' button
        //create event handler for 'yes' button
        no.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                //close the current window and open up Bills GUI by creating an Income class object
                primaryStage.close();
                Stage stage = new Stage();
                Bills bills = new Bills();
                bills.start(stage);
            }
        });
        
        
        
        
        //add all hbox's to a vbox
        VBox vbox = new VBox(20, promptHBox, verifyHBox, buttonsHBox);
        
        //center all the hbox's inside the vbox
        promptHBox.setAlignment(Pos.CENTER);
        verifyHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setAlignment(Pos.CENTER);
        
        //make some padding for the vbox
        vbox.setPadding(new Insets(30));
        
        //create a scene
        Scene scene = new Scene(vbox);
        
        //add the scene to the stage
        primaryStage.setScene(scene);
        
        //set the title
        primaryStage.setTitle("Verify Information");
        
        //show the window
        primaryStage.show();
    }

}
