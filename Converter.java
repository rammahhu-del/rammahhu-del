import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Customer{
    private String name;
    private double dollarAmount;
    private String currency;


    public Customer(String name, double dollarAmount, String currency){
        this.name = name;
        this.dollarAmount = dollarAmount;
        this.currency = currency;
    }
    public String getName(){
        return name;
    }
    public Double getdollarAmount(){
        return dollarAmount;
    }
    public String getCurrency(){
        return currency;
    }
    public String toString(){
        return String.format("%s has %.2f %s", name, dollarAmount, currency); //formatting the output string to display the name, dollar amount, and currency
    }
}

public class Converter extends JFrame implements ActionListener{
    private JPanel p1;
    private JTextArea outputArea;
    private JLabel l1, l2;
    private JTextField t1,t2;
    private JButton b1,b2;
    
    int count =0;
    Customer array[] = new Customer[10];

    //Conversion rates
    private static final double CAD_TO_USD = 0.75;
    private static final double CAD_TO_EUR = 0.65;

    public static void main(String[] args) {
        Converter app = new Converter();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Converter(){
    super("Currency Converter");
    Container c =getContentPane();

    p1 = new JPanel();
    p1.setLayout(new GridLayout(3,2));//3 rows, 2 columns
    l1 = new JLabel ("Enter Customer Name");
    l2 = new JLabel("Enter Amount in CAD");
    t1 = new JTextField(10);
    t2 = new JTextField (10); 
    b1 = new JButton("Convert to USD"); 
    b2 = new JButton("Convert to Euros");

    b1.addActionListener(this);
    b2.addActionListener(this);
   
    p1.add(l1);
    p1.add(t1);
    p1.add(l2);
    p1.add(t2);
    p1.add(b1);
    p1.add(b2);

    outputArea = new JTextArea(10,20);
    outputArea.setEditable(false);

    c.add(p1,BorderLayout.NORTH);
    c.add(new JScrollPane(outputArea), BorderLayout.CENTER);
    
    setSize(500,400);
    setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
       if(count >= array.length){
        JOptionPane.showMessageDialog(this, "Customer array limit reached(max 10)", "Error", JOptionPane.ERROR_MESSAGE);
        return;
       }

       String name = t1.getText().trim(); //gets the customer name from the text field and trims any leading or trailing whitespace
       String amountText = t2.getText().trim(); //gets the amount in CAD from the text field and trims any leading or trailing whitespace

       if (name.isEmpty() || amountText.isEmpty()){ //if either the name or amount field is empty, show an error message and return
        JOptionPane.showMessageDialog(this, "Please enter both name and amount", "Error", JOptionPane.ERROR_MESSAGE);
        return;
       }

       try{
        double cadAmount = Double.parseDouble(amountText); //parses the amount text to a double value
        double convertedAmount;
        String currency;

        if (e.getSource() == b1){
            convertedAmount = cadAmount * CAD_TO_USD;
            currency = "USD";
        } else {
            convertedAmount = cadAmount * CAD_TO_EUR;
            currency = "EUR";
        }
        //create a new customer object and add it to the array
        Customer newCustomer = new Customer(name, convertedAmount, currency);
        array[count++] = newCustomer; //increment the count of customers in the array
        //Append output display
        outputArea.append(newCustomer.toString() + "\n");

        //clear input fields
        t1.setText("");
        t2.setText("");
       }
         catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Please enter a valid number for amount", "Error", JOptionPane.ERROR_MESSAGE);
            return;
         }

    }
}


