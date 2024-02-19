// This class provides a simple evaluator for mathematical expressions.
public class Calculator implements BasicCalculator{
    // Method to evaluate the given mathematical expression and return the result.
    public double eval(final String str) {
        // Create an anonymous inner class to handle parsing of the expression.
        return new Object() {
            int pos = -1, ch;

            // Method to move to the next character in the expression.
            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            // Method to consume whitespace and check if a particular character is eaten.
            boolean eat(int charToEat) {
                while (ch == ' ') nextChar(); // Consume whitespace
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            // Method to parse the entire expression.
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
            //         | functionName `(` expression `)` | functionName factor
            //         | factor `^` factor

            // Method to parse an expression.
            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            // Method to parse a term.
            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            // Method to parse a factor.
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
                    // Evaluate various functions
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("cbrt")) x = Math.cbrt(x);
                    else if (func.equals("log10")) x = Math.log10(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));

                    else if (func.equals("asin")) x = Math.toDegrees(Math.asin(x));
                    else if (func.equals("acos")) x = Math.toDegrees(Math.acos(x));
                    else if (func.equals("atan")) x = Math.toDegrees(Math.atan(x));
                    else if (func.equals("ln")) x = Math.log(x);
                    else if (func.equals("log")) x = Math.log(x) / Math.log(10);
                    else if (func.equals("exp")) x = Math.exp(x);
                    else if (func.equals("abs")) x = Math.abs(x);
                    else if (func.equals("Rand")) x = Math.random();
                    else if (func.equals(("π"))) x = Math.PI;
                    else if(func.equals("e")) x = Math.E;
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }
                // Check for implicit multiplication if the next character is a number or opening parenthesis
                while ((ch >= '0' && ch <= '9') || ch == '(') {
                    if (ch == '(') {
                        // Insert a multiplication operator
                        x *= parseFactor();
                    } else {
                        // Skip any numbers
                        nextChar();
                    }
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse(); // Parse the expression and return the result.
    }
}
