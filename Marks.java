import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Student{
    private String name;
    private int mark;

    public Student(String name, int mark) {
    this.name = name;
    this.mark = mark;
    }
    public String getName(){
    return name;
    }
    public int getMark(){
        return mark;
    }
    public String toString(){
        return name + " has a " + mark + "\n";
    }
}

public class Marks extends JFrame implements ActionListener{
    private JPanel p1;
    private JTextArea outputArea;
    private JLabel l1, l2,l3;
    private JTextField t1,t2;
    private JButton b1,b2,b3;
    

    int count = 0;
    Student array[] = new Student[20];

    public static void main(String[] args){
        Marks app = new Marks();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public Marks(){
        super("Student Marks");
        Container c = getContentPane();

        p1 = new JPanel();
        p1.setLayout(new GridLayout(4,2));

        l1 = new JLabel("Enter Student Name: ");
        t1 = new JTextField(10);
        l2 = new JLabel("Enter Student Mark:");
        t2 = new JTextField(10);
        l3 = new JLabel("The Best Student: ", SwingConstants.CENTER); 

        
        b1 = new JButton("Enter Student Data");
        b2 = new JButton("Calculate Class Average");
        b3 = new JButton("Show Best Student");
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);


        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
        p1.add(l3);
        p1.add(b1);
        p1.add(b2);
        p1.add(b3);


        outputArea = new JTextArea(10,20);
        outputArea.setEditable(false);

        c.add(p1,BorderLayout.NORTH);
        c.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        setSize(500,400);
        setVisible(true);   

    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource() ==b1){
            if(count >= array.length) {
              JOptionPane.showMessageDialog(this, "Max Students reached!", "Error", JOptionPane.ERROR_MESSAGE);
              return;
            }

        String name = t1.getText().trim();
        String mark = t2.getText().trim();

            if (name.isEmpty() || mark.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter both name and mark", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }

            try {
            int stMark = Integer.parseInt(mark);
            Student newStudent = new Student(name,stMark);
            array[count++] = newStudent;

            outputArea.append(newStudent.toString());
            t1.setText("");
            t2.setText("");     

            } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer", "Error", JOptionPane.ERROR_MESSAGE);
            }

        // Avg
        } else if (e.getSource() == b2) {
            if(count ==0){
                JOptionPane.showMessageDialog(this, "No students entered", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int sum = 0;
            for (int i = 0; i< count; i++){
                sum += array[i].getMark();
            }
            int average = sum/count;
            outputArea.append("Class Average: " + average + "\n");
        }
        //best st
        else if (e.getSource() ==b3){
            if (count == 0){
                JOptionPane.showMessageDialog(this, "No students entered!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Student best = array[0];
            for(int i = 1; i<count; i++) {
                if (array[i].getMark() > best.getMark()) {
                    best = array[i];
                }
            }
            // display best student
            l3.setText("Best:" + best.getName());
            outputArea.append(" The Best Student is " + best.getName() + " they have a " + best.getMark() + "\n");
        }

    
    

    }
}