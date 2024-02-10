import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame  {
    private String[] buttonLabels = {
            "(", ")", "mc", "m+", "m-","mr", "c", "+/-", "%", "÷",
            "2ⁿᵈ", "x²", "x³", " xʸ", "eˣ","10ˣ","7", "8", "9", "×",
            "1/x","²√x","∛x","ʸ√ₓ","ln","log₁₀","4","5","6","-",
            "X!","sin","cos","tan","e","EE","1", "2","3","+",
            "Rad","sinh","cosh","tanh", "π","Rand","0"," ",".","=",

    };


    MyFrame() {
        this.setTitle("Calculator");//sets title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);//blocks the resize or maximize
        this.setSize(900, 700);//sets dimension of the app
        this.setVisible(true);//program is visible//create image icon
        this.getContentPane().setBackground(new Color(50, 50, 47));
//        JPanel screen = new JPanel();
//        screen.setVisible(true);
//        screen.setBackground(Color.red);
//        screen.setBounds(0,0,900,700);
//        screen.setLayout(new FlowLayout(1,0,0));
//        this.add(screen);


        this.setLayout(new GridLayout(5, 10,-2,-2));
        // Create and add buttons to the frame
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setFocusable(false);
            button.setBackground(new Color(69,68,66));
            button.setForeground(new Color(235,233,232));

            if (buttonLabels[i].equals("0")) {
                button.setSize(200,10);
            }
            else {
                button.setSize(20,15);
            }
            if(
                    buttonLabels[i].equals("0") ||buttonLabels[i].equals(".") ||buttonLabels[i].equals("1") ||
                            buttonLabels[i].equals("2") ||buttonLabels[i].equals("3") ||buttonLabels[i].equals("4") ||
                            buttonLabels[i].equals("5") ||buttonLabels[i].equals("6") ||buttonLabels[i].equals("7") ||
                            buttonLabels[i].equals("8") || buttonLabels[i].equals("9")|| buttonLabels[i].equals(" ")
            ){
                button.setBackground(new Color(100,100,98));
            }
            if(
                    buttonLabels[i].equals("÷") ||buttonLabels[i].equals("×") ||buttonLabels[i].equals("-") ||
                            buttonLabels[i].equals("=") ||buttonLabels[i].equals("+")
            ){
                button.setBackground(new Color(255,159,9));
            }

            add(button);
            pack();
            setLocationRelativeTo(null); // Center the frame
            setVisible(true);

        }


//
//        btn1.setBounds(1,1,75,45);
//        btn1.addActionListener((ActionListener) e-> System.out.println("("));
//        btn1.setSize(70,45);
//        btn1.setForeground(new Color(45,68,66));
//        btn2.setBackground(new Color(255,233,232));
//
//        btn2.setBounds(1,70,70,45);
//        btn2.setSize(70,45);
//        btn2.addActionListener((ActionListener) e-> System.out.println("2nd"));
//        btn2.setForeground(new Color(45,68,66));
//        btn2.setBackground(new Color(255,233,232));
//
//
//        btn3.setBounds(1,140,70,45);
//        btn3.setSize(70,45);
//        btn3.addActionListener((ActionListener) e-> System.out.println("2nd"));
//        btn3.setForeground(new Color(45,68,66));
//        btn3.setBackground(new Color(255,233,232));


    }
}
