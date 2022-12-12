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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author szymo
 */
public class SavingsAndInvestments extends Application
{
    private double billsRelativeToTimeFrame;
    private double totalIncome;
    private String timeFrame;
    private double moneyLeft;
    private double percentageAmount;
    private double zeroPercentOfBudget, twentyPercentOfBudget, thirtyPercentOfBudget, fourtyPercentOfBudget;
    private Label moneyLeftLabel;
    private TextField customSavings;
    private TextField customInvestment;
    private boolean investmentsConfirmed = false;
    private boolean savingsConfirmed = false;
    private double savingsCustomAmount = 0;
    private double investmentCustomAmount = 0;
    private double totalBillsMonthly;
    private double moneyToSavings;
    private double moneyToInvestments;
    
    //constructor
    public SavingsAndInvestments(double billsRelativeToTimeFrame, double totalIncome, String timeFrame, double totalBillsMonthly, double moneyLeft)
    {
        this.billsRelativeToTimeFrame = billsRelativeToTimeFrame;
        this.totalIncome = totalIncome;
        this.timeFrame = timeFrame;
        this.moneyLeft = moneyLeft;
        this.totalBillsMonthly = totalBillsMonthly;
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage)
    {
        //create a message string for the income label
        String incomeMessage = String.format("Income: $%,.2f\n", totalIncome);
        
        //create a label that displays incomeMessage string
        Label incomeLabel = new Label(incomeMessage);
        incomeLabel.setFont(new Font(15));
        //create a hbox for the label that was just created
        HBox incomeHBox = new HBox(incomeLabel);
        
        //create a message string for the next lable
        String message = String.format("Budget: $%,.2f\n", moneyLeft);
        
        //create a label that displays the amount of money left for spending
        moneyLeftLabel = new Label(message);
        moneyLeftLabel.setFont(new Font(15));
        
        //create a hbox and add the label created to it
        HBox moneyLeftHBox = new HBox(moneyLeftLabel);
        
        //create a label for savings
        Label savings = new Label("Savings: ");
        
        //create an hbox for the savings label
        HBox savingsHBox = new HBox(savings);
        
        //create an option for devoting nothing to savings, using a label and button
        zeroPercentOfBudget = totalIncome * 0;
        Label zeroPercent = new Label("\t0%/$0.00");
        Button zeroSelect = new Button("Select");
        
        //create an option to devote 20 percent to savings
        twentyPercentOfBudget = totalIncome * 0.2;
        String twentyPercentString = String.format("\t20%%/$%,.2f(Recommended)", (twentyPercentOfBudget));
        //create an option for devoting 20% of income to savings
        Label twentyPercent = new Label(twentyPercentString);
        Button twentySelect = new Button("Select"); 
        
        //create an option to devote 30 percent to savings 
        thirtyPercentOfBudget = totalIncome * 0.3;
        //create a string to use for the thrity percent label
        String thrityPercentString = String.format("\t30%%/$%,.2f(Recommended)", (thirtyPercentOfBudget));
        //create a label
        Label thirtyPercent = new Label(thrityPercentString);
        Button thirtySelect = new Button("Select");
        
        //create an option to devote a custom percentage to savings
        Label customPercent = new Label("\tCustom");
        Button customSelect = new Button("Select");
        //add textfields and buttons below the customSelect button
        Label customSavingsLabel = new Label("$");
        customSavings = new TextField();
        HBox customSavingsHBox = new HBox(customSavingsLabel, customSavings);
        //create a confirm button to appear below this hbox
        Button confirmCustomSavings = new Button("Confirm");
        VBox customSavingsVBox = new VBox(10, customSavingsHBox, confirmCustomSavings);
       
        
        //create a vbox and add all the percent Labels to it
        VBox percentLabelsVBox = new VBox(20, zeroPercent, twentyPercent, thirtyPercent, customPercent);
        //create a second vbox and all all the percent Buttons to it
        VBox percentButtonsVBox = new VBox(10, zeroSelect, twentySelect, thirtySelect, customSelect, customSavingsVBox);
        //make the customSavingstHBox invisible, set to reappear is the customSelect button is clicked
        customSavingsVBox.setVisible(false);
        customSavingsVBox.setAlignment(Pos.CENTER);

        //create a hbox and add all the savings vbox's to it
        HBox hbox = new HBox(percentLabelsVBox, percentButtonsVBox);
        
        percentLabelsVBox.setAlignment(Pos.BASELINE_LEFT);
        percentButtonsVBox.setAlignment(Pos.BASELINE_RIGHT);
        percentLabelsVBox.setPadding(new Insets(5));
       //create a vbox for incomeHBox, moneyLeftHBox, and savingsHBox
        VBox vbox = new VBox(10, incomeHBox, moneyLeftHBox, savingsHBox);

        //create a title for the investments
        Label investmentsLabel = new Label("Investments: ");
        
        //create all the percent Labels and add them to a vbox
        fourtyPercentOfBudget = totalIncome * 0.4;
        Label zeroToInvestments = new Label("\t0%/$0.00");
        Label thirtyToInvestments = new Label(String.format("\t30%%/$%,.2f(Recommended)", (thirtyPercentOfBudget)));
        Label fourtyToInvestments = new Label(String.format("\t40%%/$%,.2f(Aggressive)", (fourtyPercentOfBudget)));
        Label customToInvestments = new Label("\tCustom");
        VBox investmentLabelsVBox = new VBox(20, zeroToInvestments, thirtyToInvestments, fourtyToInvestments, customToInvestments);
        //change the alignment
        investmentLabelsVBox.setAlignment(Pos.BASELINE_LEFT);
        
        //create all the button labels and add them to a vbox
        Button zeroPercentSelect = new Button("Select");
        Button thirtyPercentSelect = new Button("Select");
        Button fourtyPercentSelect = new Button("Select");
        Button customPercentSelect = new Button("Select");
        //add textfields below the customPercentSelect button(percentage and price)
        Label customInvestmentLabel = new Label("$");
        customInvestment = new TextField();
        HBox customInvestmentHBox = new HBox(customInvestmentLabel, customInvestment);
        //set a confirm button along with the hbox above
        Button confirmCustomInvestments = new Button("Confirm");
        VBox customInvestmentVBox = new VBox(10, customInvestmentHBox, confirmCustomInvestments);
        VBox investmentButtonsVBox = new VBox(10, zeroPercentSelect, thirtyPercentSelect, fourtyPercentSelect, customPercentSelect, customInvestmentVBox);
        //set the alignment
        investmentButtonsVBox.setAlignment(Pos.BASELINE_RIGHT);
        //make the customInvestmentHBox invisible, set to reappear is the customPercentSelect button is clicked
        customInvestmentVBox.setVisible(false);
        customInvestmentVBox.setAlignment(Pos.CENTER);
        
        //add investmentLabelsVBox and investmentButtonsVBox to a hbox
        HBox investmentHBox = new HBox(20, investmentLabelsVBox, investmentButtonsVBox);
        
        //create a 'done' button at the bottom of the gui
        Button done = new Button("Done");
        HBox doneHBox = new HBox(done);
        
        //add previously created hboxs and vboxs to another vbox to use in scene
        VBox container = new VBox(20, vbox, hbox, investmentsLabel, investmentHBox, doneHBox);
        
        //center certain hbox's
        incomeHBox.setAlignment(Pos.CENTER);
        moneyLeftHBox.setAlignment(Pos.CENTER);
        doneHBox.setAlignment(Pos.CENTER);
        
        //create padding for container and vbox
        container.setPadding(new Insets(30));
        
        //add event handlers for the savings select buttons
        zeroSelect.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                //if the buttons says select, make it say unselect and take the correct deductions off of the budget at the top of the GUI
                if(zeroSelect.getText().equals("Select") && twentySelect.getText().equals("Select") && thirtySelect.getText().equals("Select") && customSelect.getText().equals("Select"))
                {
                  //subtract percentage amount from budget
                  moneyLeft -= zeroPercentOfBudget;
                  zeroSelect.setText("Unselect");
                  moneyLeftLabel.setText(String.format("Budget: $%,.2f", moneyLeft));
                  //set moneyToSavings
                  moneyToSavings = zeroPercentOfBudget;
                }
                //if the button is clicked but another button in the savings section has already been clicked
                else if(zeroSelect.getText().equals("Select") && twentySelect.getText().equals("Unselect") || thirtySelect.getText().equals("Unselect") || customSelect.getText().equals("Unselect"))
                {
                    //pop up an error message using JOptionPane
                    JOptionPane.showMessageDialog(null, "Error: Only one choice can be selected in both the Savings and Investments section.");
                }
                //if the button says unselect, once clicked it will say select and will add money back to budget
                else
                {
                    //add percentage amount back to budget
                    moneyLeft += zeroPercentOfBudget;
                    zeroSelect.setText("Select");
                    moneyLeftLabel.setText(String.format("Budget: $%,.2f", moneyLeft));
                    //set moneyToSavings as zero
                    moneyToSavings = 0;
                }
                
            }
        });
        buttonEventHandler(twentySelect, zeroSelect, thirtySelect, customSelect, twentyPercentOfBudget, "savings");
        buttonEventHandler(thirtySelect, zeroSelect, twentySelect, customSelect, thirtyPercentOfBudget, "savings");
        //button event handler for customSelect button
        customSelect.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                //if the buttons says select, make it say unselect and take the correct deductions off of the budget at the top of the GUI
                if(zeroSelect.getText().equals("Select") && twentySelect.getText().equals("Select") && thirtySelect.getText().equals("Select") && customSelect.getText().equals("Select"))
                {
                  customSelect.setText("Unselect");
                  customSavingsVBox.setVisible(true);
                  //set moneyToSavings variable back to zero
                  moneyToSavings = 0;
                }
                //if the button is clicked but another button in the savings section has already been clicked
                else if(customSelect.getText().equals("Select") && twentySelect.getText().equals("Unselect") || thirtySelect.getText().equals("Unselect") || zeroSelect.getText().equals("Unselect"))
                {
                    //pop up an error message using JOptionPane
                    JOptionPane.showMessageDialog(null, "Error: Only one choice can be selected in both the Savings and Investments section.");
                }
                //if the button says unselect, once clicked it will say select and will add money back to budget
                else
                {
                    customSelect.setText("Select");
                    //add money back to budget
                    moneyLeft += savingsCustomAmount;
                    moneyLeftLabel.setText(String.format("Budget: $%,.2f", moneyLeft));
                    customSavingsVBox.setVisible(false);
                    //moneyLeftLabel.setText(String.format("Budget: $%,.2f", moneyLeft));
                }
                
            }
        });
        
        //add event handlers for the investment select buttons
        buttonEventHandler(zeroPercentSelect, thirtyPercentSelect, fourtyPercentSelect, customPercentSelect, zeroPercentOfBudget, "investments");
        buttonEventHandler(thirtyPercentSelect, zeroPercentSelect, fourtyPercentSelect, customPercentSelect, thirtyPercentOfBudget, "investments");
        buttonEventHandler(fourtyPercentSelect, zeroPercentSelect, thirtyPercentSelect, customPercentSelect, fourtyPercentOfBudget, "investments");
        //add event handler for customPercentSelect button
        customPercentSelect.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                //if the buttons says select, make it say unselect and take the correct deductions off of the budget at the top of the GUI
                if(zeroPercentSelect.getText().equals("Select") && thirtyPercentSelect.getText().equals("Select") && fourtyPercentSelect.getText().equals("Select") && customPercentSelect.getText().equals("Select"))
                {
                  customPercentSelect.setText("Unselect");
                  customInvestmentVBox.setVisible(true);
                  //set the moneyToInvestments back to zero
                  moneyToInvestments = 0;
                }
                //if the button is clicked but another button in the savings section has already been clicked
                else if(customPercentSelect.getText().equals("Select") && thirtyPercentSelect.getText().equals("Unselect") || fourtyPercentSelect.getText().equals("Unselect") || zeroPercentSelect.getText().equals("Unselect"))
                {
                    //pop up an error message using JOptionPane
                    JOptionPane.showMessageDialog(null, "Error: Only one choice can be selected in both the Savings and Investments section.");
                }
                //if the button says unselect, once clicked it will say select and will add money back to budget
                else
                {
                    customPercentSelect.setText("Select");
                    //add money back to budget
                    moneyLeft += investmentCustomAmount;
                    moneyLeftLabel.setText(String.format("Budget: $%,.2f", moneyLeft));
                    customInvestmentVBox.setVisible(false);
                    
                }
                
            }
        });
        
        //add event handler for 'done' button
        done.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                //if one option in both the savings and investments sections have been chosen
                boolean savingsChosen = false;
                boolean investmentsChosen = false;
                //check to see if any of the savings buttons read "Unselect". If so, mark savingsChosen boolean variabel as true                                                //make sure that if the custom button was selected, its corresponding boolean variable is true

                if(zeroSelect.getText().equals("Unselect") || twentySelect.getText().equals("Unselect") || thirtySelect.getText().equals("Unselect") || (customSelect.getText().equals("Unselect") && savingsConfirmed))
                {
                    savingsChosen = true;
                }
                //check to see if any of the investment buttons read "Unselect". If so, mark investmentsChosen boolean variabel as true                                                                                 //make sure that if the custom button was selected, its corresponding boolean variable is true

                if(zeroPercentSelect.getText().equals("Unselect") || thirtyPercentSelect.getText().equals("Unselect") || fourtyPercentSelect.getText().equals("Unselect") || (customPercentSelect.getText().equals("Unselect") && investmentsConfirmed))
                {
                    investmentsChosen = true;
                }
                //only move on to the next class if a savings and investments option has been chosen
                if(savingsChosen && investmentsChosen)
                {
                    //close this gui and open up the next
                    primaryStage.close();
                    Display display = new Display(billsRelativeToTimeFrame, totalIncome, timeFrame, totalBillsMonthly, moneyLeft, moneyToSavings, moneyToInvestments);
                    Stage stage = new Stage();
                    display.start(stage);
                    
                }
                else
                {
                    //if one option from both sections has not been chosen, prompt the user to do so
                    JOptionPane.showMessageDialog(null, "One option in both the Savings and Investments section must be selected to move on.\nNOTE: If a custom amount was selected, make sure to click the confirm button that appears.");
                }
            }
        });
        
        //add an event handler for savings confirm button underneath custom option
        confirmCustomSavings.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                //call extractCustomTFValue method on the corresponding textfield
                savingsCustomAmount = extractCustomTFValue(customSavings);
                //if the return value is greater than zero, meaning the double parse worked
                if(savingsCustomAmount > 0)
                {
                    //make the corresponding textfield and button dissappear
                    customSavingsVBox.setVisible(false);
                    //subtract the amount from the budget
                    moneyLeft -= savingsCustomAmount;
                    moneyLeftLabel.setText(String.format("Budget: $%,.2f", moneyLeft));
                    //make the corresponding boolean variable true
                    savingsConfirmed = true;
                    //set the moneyToSavings variable
                    moneyToSavings = savingsCustomAmount;
                }
                //if the number entered in the textfield is negative or zero
                else
                {
                    JOptionPane.showMessageDialog(null, "Must enter in a valid number above zero. Please try again.");
                }
            }
        });
        //add an event handler for investment confirm button underneath custom option
        confirmCustomInvestments.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                
                //call extractCustomTFValue function on the corresponding textfield
                investmentCustomAmount = extractCustomTFValue(customInvestment);
                //if the return value is greater than zero, meaning the double parse worked
                if(investmentCustomAmount > 0)
                {
                    //make the corresponding textfield and button dissappear
                    customInvestmentVBox.setVisible(false);
                    //subtract the amount from the budget
                    moneyLeft -= investmentCustomAmount;
                    moneyLeftLabel.setText(String.format("Budget: $%,.2f", moneyLeft));
                    //make the corresponding boolean variable true
                    investmentsConfirmed = true;
                    //set the moneyToInvestments variable
                    moneyToInvestments = investmentCustomAmount;
                }
                //if the number entered in the textfield is negative or zero
                else
                {
                    JOptionPane.showMessageDialog(null, "Must enter in a valid number above zero. Please try again.");
                }
            }
        });
       //create a scene
        Scene scene = new Scene(container, 450, 700);
        
        //add the scene to the stage
        primaryStage.setScene(scene);
        
        //set the stage title
        primaryStage.setTitle("Savings");
        
        //show the window
        primaryStage.show();
    }
    private double extractCustomTFValue(TextField textfield)
    {
        double customValue = 0;
        //extract the text inside the textfield, and use a try except block to double parse the value
        try
        {
            //try to double parse the value in the textfield and change boolean variable to true
            customValue = Double.parseDouble(textfield.getText());
        }
        catch(Exception e)
        {
            //display an error message
            JOptionPane.showMessageDialog(null, "Invalid input. Please try again");
            //set the textfield text to ""
            textfield.setText("");
        }
        return customValue;
    }
    
   private void buttonEventHandler(Button button, Button button2, Button button3, Button button4, double percentOfBudget, String whereTo)
   {
       button.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                //if the buttons says select, make it say unselect and take the correct deductions off of the budget at the top of the GUI
                if(button.getText().equals("Select") && button2.getText().equals("Select") && button3.getText().equals("Select") && button4.getText().equals("Select"))
                {
                  //subtract percentage amount from budget
                  moneyLeft -= percentOfBudget;
                  button.setText("Unselect");
                  moneyLeftLabel.setText(String.format("Budget: $%,.2f", moneyLeft));
                  //set the corresponding moneyTo variable to percentOfBudget
                  if(whereTo.equals("savings"))
                  {
                      moneyToSavings = percentOfBudget;
                  }
                  else if(whereTo.equals("investments"))
                  {
                      moneyToInvestments = percentOfBudget;
                  }
                }
                //if the button is clicked but another button in the savings section has already been clicked
                else if(button.getText().equals("Select") && button2.getText().equals("Unselect") || button3.getText().equals("Unselect") || button4.getText().equals("Unselect"))
                {
                    //pop up an error message using JOptionPane
                    JOptionPane.showMessageDialog(null, "Error: Only one choice can be selected in both the Savings and Investments section.");
                }
                //if the button says unselect, once clicked it will say select and will add money back to budget
                else
                {
                    //add percentage amount back to budget
                    moneyLeft += percentOfBudget;
                    button.setText("Select");
                    moneyLeftLabel.setText(String.format("Budget: $%,.2f", moneyLeft));
                    //set the corresponding moneyTo variable to zero
                    if(whereTo.equals("savings"))
                  {
                      moneyToSavings = 0;
                  }
                  else if(whereTo.equals("investments"))
                  {
                      moneyToInvestments = 0;
                  }
                }
                
            }
        });
   }
}
