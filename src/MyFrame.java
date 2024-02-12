import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private final String[] buttonLabels = {
            "(", ")", "mc", "m+", "m-", "mr", "c", "+/-", "%", "÷",
            "2ⁿᵈ", "x²", "x³", " xʸ", "eˣ", "10ˣ", "7", "8", "9", "×",
            "1/x", "²√x", "∛x", "ʸ√ₓ", "ln", "log₁₀", "4", "5", "6", "-",
            "X!", "sin", "cos", "tan", "e", "EE", "1", "2", "3", "+",
            "Rad", "sinh", "cosh", "tanh", "π", "Rand", "0", " ", ".", "=",
    };

    public MyFrame() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setMinimumSize(new Dimension(700,400));
        getContentPane().setBackground(new Color(50, 50, 47));

        setLayout(new BorderLayout());

        JTextField screen = new JTextField();
        screen.setFont(new Font("Arial", Font.BOLD, 20));
        screen.setHorizontalAlignment(JTextField.RIGHT);
        screen.setEditable(false);
        screen.setBackground(new Color(50,50,47));
        screen.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(50,50,47)));
        screen.setSize(900,300);
        add(screen, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 10, -2,-2 ));

        buttonsPanel.setBackground(new Color(50, 50, 47)); // Set background color of buttons panel
        add(buttonsPanel, BorderLayout.CENTER);

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFocusable(false);
            button.setForeground(new Color(235, 233, 232));
            button.setBorder(BorderFactory.createEtchedBorder());
            button.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(50,50,47)));
            if ( ".".equals(label) || label.matches("[0-9 ]")) {
                button.setBackground(new Color(100, 100, 98));
            } else if ("÷×-+= ".contains(label)) {
                button.setBackground(new Color(255, 159, 9));
            } else {
                button.setBackground(new Color(69, 68, 66));
            }
            buttonsPanel.add(button);
        }

        pack(); // Adjust frame size to fit components
        setLocationRelativeTo(null);
        setVisible(true);
    }

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

