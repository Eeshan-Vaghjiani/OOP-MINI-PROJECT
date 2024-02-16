import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Define a class CalculatorGUI which extends JFrame and implements ActionListener.
public class CalculatorGUI extends JFrame implements ActionListener {

    // Define instance variables
    public String currentText = ""; // Store current input text
    public JTextField screen = new JTextField(); // Text field to display input and result
    Calculator calc = new Calculator(); // Calculator instance

    // Define button labels
    private final String[] buttonLabels = {
            "(", ")", "mc", "m+", "m-", "mr", "c", "+/-", "%", "÷",
            "2ⁿᵈ", "x²", "x³", " xʸ", "eˣ", "10ˣ", "7", "8", "9", "×",
            "1/x", "²√x", "∛x", "ʸ√ₓ", "ln", "log₁₀", "4", "5", "6", "-",
            "X!", "sin", "cos", "tan", "e", "EE", "1", "2", "3", "+",
            "Rad", "sin⁻¹", "cos⁻¹", "tan⁻¹", "π", "Rand", "0","0", ".", "=",
    };

    // Constructor
    public CalculatorGUI() {
        // Set up the frame
        setTitle("Calculator"); // Set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setResizable(true); // Allow resizing of window
        setMinimumSize(new Dimension(700, 400)); // Set minimum window size
        getContentPane().setBackground(new Color(50, 50, 47)); // Set background color
        setLayout(new BorderLayout()); // Set layout manager for the frame

        // Initialize and configure the text field
        Font buttonFont = new Font("Arial", Font.PLAIN, 200); // Set font for text field
        screen.setHorizontalAlignment(JTextField.RIGHT); // Set text alignment to right
        screen.setEditable(false); // Make text field non-editable
        screen.setPreferredSize(new Dimension(900, 100)); // Increase the height of the screen
        // Modify font size for screen text
        Font screenFont = screen.getFont();
        screen.setFont(new Font(screenFont.getName(), Font.BOLD, 30));
        screen.setBackground(new Color(50, 50, 47)); // Set background color
        screen.setForeground(new Color(235, 233, 232)); // Set foreground color
        screen.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(50, 50, 47))); // Set border
        screen.setSize(900, 300); // Set initial size
        add(screen, BorderLayout.NORTH); // Add text field to the frame's north position

        // Initialize and configure the buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 10, -2, -2)); // Set grid layout with gaps between buttons
        buttonsPanel.setBackground(new Color(50, 50, 47)); // Set background color of buttons panel
        add(buttonsPanel, BorderLayout.CENTER); // Add buttons panel to the frame's center position

        // Loop through button labels array to create buttons
        for (String label : buttonLabels) {
            JButton button = new JButton(label); // Create button with current label
            button.addActionListener(this); // Add action listener to the button
            button.setFocusable(false); // Set focusable to false to prevent focus highlighting
            button.setForeground(new Color(235, 233, 232)); // Set text color
            button.setBorder(BorderFactory.createEtchedBorder()); // Set border
            button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(50, 50, 47))); // Set border color

            // Adjust width of the "0" button
            if ("0".equals(label)) {
                button.setPreferredSize(new Dimension(150, 70)); // Set custom size for "0" button
            } else {
                button.setPreferredSize(new Dimension(75, 70)); // Set default size for other buttons
            }

            // Set background color based on button type
            if (".".equals(label) || label.matches("[0-9 ]")) {
                button.setBackground(new Color(100, 100, 98)); // Set background color for numbers and decimal point
            } else if ("÷×-+= ".contains(label)) {
                button.setBackground(new Color(255, 159, 9)); // Set background color for operators
            } else {
                button.setBackground(new Color(69, 68, 66)); // Set background color for other buttons
            }

            buttonsPanel.add(button); // Add button to the buttons panel
        }

        pack(); // Adjust frame size to fit components
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true); // Set frame visibility to true
    }

    // ActionListener implementation
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // Get the action command of the button clicked
        switch (command) {
            // Handle different button actions
            case "=":
                // Evaluate expression
                currentText = currentText.replace("×", "*");
                currentText = currentText.replace("÷", "/");
                double result = calc.eval(currentText);
                if (result % 1 == 0) {
                    int int_result = (int) result;
                    currentText = int_result + "";
                } else {
                    currentText = result + "";
                }
                break;
            case "c":
                currentText = ""; // Clear current text
                break;
            case "e":
                currentText += Math.E; // Append Euler's number to current text
                break;
            case "π":
                currentText += Math.PI; // Append Pi to current text
                break;
            case "%":
                currentText += "/100"; // Append division by 100 to current text
                break;
            case "+/-":
                // Handle change of sign
                if (!currentText.isEmpty()) {
                    String[] parts = currentText.split("[+\\-*/]");
                    String lastNumber = parts[parts.length - 1];
                    if (!lastNumber.isEmpty() && !lastNumber.equals(".")) {
                        if (lastNumber.charAt(0) == '-') {
                            currentText = currentText.substring(0, currentText.length() - lastNumber.length()) + lastNumber.substring(1);
                        } else {
                            currentText = currentText.substring(0, currentText.length() - lastNumber.length()) + "-" + lastNumber;
                        }
                    }
                }
                break;
            case "10ˣ":
                currentText += "*10^"; // Append exponentiation expression to current text
                break;
            case "sin":
                currentText = "sin(" + currentText + ")"; // Append sine function to current text
                break;
            // Add cases for other buttons as needed
            default:
                currentText += command; // Append the command to current text
                break;
        }
        screen.setText(currentText); // Update the text field with the current text
    }
}
