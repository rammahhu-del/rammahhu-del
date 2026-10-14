import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    
    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10]; //array to hold number buttons
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;

    Font myFont = new Font("Serif", Font.BOLD, 30);

    double num1 = 0, num2 = 0, result = 0; //variables to hold numbers and result
    char operator; //variable to hold operator


    Calculator(){

        frame = new JFrame("Calculator"); //create new JFrame with title "Calculator"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set default close operation to exit on close
        frame.setSize(420, 550); //set size of frame to 420x550
        frame.setLayout(null); //set layout of frame to null
        
        textfield = new JTextField(); //create new textfield
        textfield.setBounds(50, 25, 300, 50); //set bounds of textfield to x=50, y=25, width=300, height=50
        textfield.setFont(myFont); //set font of textfield to myFont
        textfield.setEditable(false); //set textfield to non-editable

        addButton = new JButton("+"); //create new button with text "+"
        subButton = new JButton("-"); //create new button with text "-"
        mulButton = new JButton("*"); //create new button with text "*"
        divButton = new JButton("/"); //create new button with text "/"
        decButton = new JButton("."); //create new button with text "."
        equButton = new JButton("="); //create new button with text "="
        delButton = new JButton("Del"); //create new button with text "Del"
        clrButton = new JButton("Clr"); //create new button with text "Clr"
        negButton = new JButton("-"); //create new button with text "Clr"

        functionButtons[0] = addButton; //add addButton to functionButtons array at index 0
        functionButtons[1] = subButton; //add subButton to functionButtons array at index 1
        functionButtons[2] = mulButton; //add mulButton to functionButtons array at index 2
        functionButtons[3] = divButton; //add divButton to functionButtons array at index 3
        functionButtons[4] = decButton; //add decButton to functionButtons array at index 4
        functionButtons[5] = equButton; //add equButton to functionButtons array at index 5
        functionButtons[6] = delButton; //add delButton to functionButtons array at index 6
        functionButtons[7] = clrButton; //add clrButton to functionButtons array at index 7
        functionButtons[8] = negButton;

        for(int i=0; i<9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for(int i=0; i<10; i++) {
             numberButtons[i] = new JButton(String.valueOf(i));
             numberButtons[i].addActionListener(this);
             numberButtons[i].setFont(myFont);
             numberButtons[i].setFocusable(false);
        }

        delButton.setBounds(50,430,100,50);
        clrButton.setBounds(205,435,100,50);
        negButton.setBounds(50,435,100,50);

        panel = new JPanel();
        panel.setBounds(50,100,300,300);
        panel.setLayout(new GridLayout(4,4,10,10));

        panel.add(numberButtons[1]); 
        panel.add(numberButtons[2]); 
        panel.add(numberButtons[3]); 
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);
        panel.add(negButton);

        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield); //add textfield to frame
        frame.setVisible(true); //set frame to visible

    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }
   // @Override
    public void actionPerformed(ActionEvent e) {
        
        for(int i=0; i<10;i++) {
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decButton) {
            textfield.setText(textfield.getText().concat("."));
        }

         if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '+';
            textfield.setText("");
        }

        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '-';
            textfield.setText("");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = 'x';
            textfield.setText("");
        }
        if (e.getSource() ==divButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '/';
            textfield.setText("");
        }
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textfield.getText());

            switch(operator) {
            case '+':
                result = num1 + num2; 
            break;
            case '-':
                result = num1 - num2; 
            break;
            case 'x':
                result = num1 * num2; 
            break;
            case '/':
                result = num1 / num2; 
            break;

            }
            textfield.setText(String.valueOf(result));
            num1 = result;
        }

        if(e.getSource() == clrButton) {
            textfield.setText("");     
        }
        if(e.getSource() == delButton) {
            String string = textfield.getText();
            textfield.setText("");
            for(int i=0; i<string.length()-1; i++) {
                textfield.setText(textfield.getText()+string.charAt(i));
            }
        }
        if(e.getSource() == negButton) {
           double temp = Double.parseDouble(textfield.getText());
           temp*=-1;
           textfield.setText(String.valueOf(temp));
        }

    }

    
}
