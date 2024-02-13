import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyFrame extends JFrame implements ActionListener {

    public String currentText = "";
    public JTextField screen = new JTextField();

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

        screen.setFont(new Font("Arial", Font.BOLD, 20));
        screen.setHorizontalAlignment(JTextField.RIGHT);
        screen.setEditable(false);
        screen.setBackground(new Color(50,50,47));
        screen.setForeground(new Color(235, 233, 232));
        screen.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(50,50,47)));
        screen.setSize(900,300);
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
            System.out.println(eval(currentText));
            currentText = eval(currentText) + "";
        }
        else if(command.equals("c")){
            currentText = "";
        }
        else {
            currentText += command;
        }
        screen.setText(currentText);
    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)` | number
            //        | functionName `(` expression `)` | functionName factor
            //        | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return +parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}

