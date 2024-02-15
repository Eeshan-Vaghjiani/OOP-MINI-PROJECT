import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CalculatorGUI extends JFrame implements ActionListener {

    public String currentText = "";
    public JTextField screen = new JTextField();
    Calculator calc = new Calculator();

    private final String[] buttonLabels = {
            "(", ")", "mc", "m+", "m-", "mr", "c", "+/-", "%", "÷",
            "2ⁿᵈ", "x²", "x³", " xʸ", "eˣ", "10ˣ", "7", "8", "9", "×",
            "1/x", "²√x", "∛x", "ʸ√ₓ", "ln", "log₁₀", "4", "5", "6", "-",
            "X!", "sin", "cos", "tan", "e", "EE", "1", "2", "3", "+",
            "Rad", "sinh", "cosh", "tanh", "π", "Rand", "0", " ", ".", "=",
    };

    public CalculatorGUI() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setMinimumSize(new Dimension(700,400));
        getContentPane().setBackground(new Color(50, 50, 47));

        setLayout(new BorderLayout());

        screen.setFont(new Font("Arial", Font.BOLD, 20));
        screen.setHorizontalAlignment(JTextField.RIGHT);
        screen.setEditable(false);
        screen.setBackground(new Color(50,50,47));
        screen.setForeground(new Color(235, 233, 232));
        screen.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(50,50,47)));
        screen.setSize(900,400);
        add(screen, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 10, -2,-2 ));

        buttonsPanel.setBackground(new Color(50, 50, 47)); // Set background color of buttons panel
        add(buttonsPanel, BorderLayout.CENTER);

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("=")) {
            currentText = currentText.replace("×", "*");
            currentText = currentText.replace("÷", "/");
            System.out.println(calc.eval(currentText));
            double result = calc.eval(currentText);
            if (result % 1 == 0){
                int int_result = (int) result;
                currentText = int_result + "0";
            }
            else{
                currentText = result + "";
            }
        }
        else if(command.equals("c")){
            currentText = "0";
        }
        else if (command.equals("²√x")){
            currentText = currentText.replace("²√x","sqrt");
        }
        else if (command.matches("\\+/-")) {
            if (currentText.charAt(0) == '-') {
                currentText = currentText.substring(1);
            } else {
                currentText = "-" + currentText;
            }

        }
        else {
            currentText += command;
        }
        screen.setText(currentText);
    }

}
