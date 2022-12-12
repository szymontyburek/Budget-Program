/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BudgetProgram;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author szymo
 */
public class Bills extends Application 
{
    //fields
    private TextField billAmount;
    Scanner keyboard = new Scanner(System.in);
    
    //create a GUI that will take in multiple entries and creates an object of the Bills class with each entry
    public static void main(String[] args) 
    {
        //launch the application
        launch(args);
    }
    @Override
    public void start(Stage primaryStage)
    {       
        //create a label to display a bill name prompt
        Label billPromptLabel = new Label("Amount owed in bills monthly: $");
                
        //create a TextField for input
        billAmount = new TextField();
               
        //create an hbox for billName and namePromptLabel
        HBox billPromptHBox = new HBox(billPromptLabel, billAmount);
        
        //create a 'confirm' button
        Button confirm = new Button("Confirm");
              
        //create a hbox and add confirm button to it
        HBox buttonHBox = new HBox(confirm);
        
        //create an event handler for the 'Add' button
        confirm.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                //use try except in case user input is invalid(contains letter)
                try
                {
                    //get the number from the textfield
                    double total = Double.parseDouble(billAmount.getText());
                    //if total is positive and greater than zero, close the current window and open the next GUI by creating a 'VerifyBills' class object
                    if(total > 0)
                    {
                       primaryStage.close();
                    Stage primary = new Stage();
                    VerifyBills verify = new VerifyBills(total);
                    verify.start(primary); 
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please try again.");
                        billAmount.setText("");
                    }
                }
                catch(Exception e)
                {
                    //print out a message
                    JOptionPane.showMessageDialog(null, "Invalid input. Please Try Again(Note: Do Not Use Commas).");
                    //make the textfields blank and set the focus back to it
                    billAmount.setText(" ");
                }
            }
        });
        
        //create a vbox and add both previously created hbox's to it
        VBox vbox = new VBox(30, billPromptHBox, buttonHBox);
        
         //center the hbox's
        billPromptHBox.setAlignment(Pos.CENTER);
        buttonHBox.setAlignment(Pos.CENTER);
        
        //set the vbox's padding
        vbox.setPadding(new Insets(30));
               
        //create a scene
        Scene scene = new Scene(vbox);
        
        //add the scene to the stage
        primaryStage.setScene(scene);
        
        //set the stage title
        primaryStage.setTitle("Budget Program");
        
        //show the window
        primaryStage.show();  
    }
}
