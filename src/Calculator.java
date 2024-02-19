// This class provides a simple calculator for evaluating mathematical expressions.
public class Calculator implements BasicCalculator {
    // Method to evaluate the given mathematical expression and return the result.
    public double eval(final String expression) {
        // Create an anonymous inner class to handle parsing of the expression.
        return new Object() {
            int position = -1, currentCharacter;

            // Method to move to the next character in the expression.
            // Method to move to the next character in the expression.
            void moveNextChar() {
                if (++position < expression.length())
                    currentCharacter = expression.charAt(position);
                else
                    currentCharacter = -1;
            }

            // Method to consume whitespace and check if a particular character is encountered.
            boolean consumeAndCheck(int characterToCheck) {
                while (currentCharacter == ' ') moveToNextChar(); // Consume whitespace
                if (currentCharacter == characterToCheck) {
                    moveToNextChar();
                    return true;
                }
                return false;
            }

            // Method to parse the entire expression.
            double parseExpression() {
                moveToNextChar();
                double result = parseTerm();
                if (position < expression.length()) throw new RuntimeException("Unexpected: " + (char)currentCharacter);
                return result;
            }

            // Method to parse an expression term.
            double parseTerm() {
                double result = parseFactor();
                for (;;) {
                    if      (consumeAndCheck('+')) result += parseFactor(); // addition
                    else if (consumeAndCheck('-')) result -= parseFactor(); // subtraction
                    else return result;
                }
            }

            // Method to parse a factor in the expression.
            double parseFactor() {
                // Handling unary plus and unary minus
                if (consumeAndCheck('+')) return +parseFactor(); // unary plus
                if (consumeAndCheck('-')) return -parseFactor(); // unary minus

                double result;
                int startPosition = this.position;
                // Parsing parentheses
                if (consumeAndCheck('(')) { // parentheses
                    result = parseExpression();
                    if (!consumeAndCheck(')')) throw new RuntimeException("Missing ')'");
                }
                // Parsing numbers
                else if ((currentCharacter >= '0' && currentCharacter <= '9') || currentCharacter == '.') { // numbers
                    while ((currentCharacter >= '0' && currentCharacter <= '9') || currentCharacter == '.') moveToNextChar();
                    result = Double.parseDouble(expression.substring(startPosition, this.position));
                    System.out.println(result);
                }
                // Parsing functions
                else if (currentCharacter >= 'a' && currentCharacter <= 'z') { // functions
                    while (currentCharacter >= 'a' && currentCharacter <= 'z') moveToNextChar();
                    String functionName = expression.substring(startPosition, this.position);
                    if (consumeAndCheck('(')) {
                        result = parseExpression();
                        if (!consumeAndCheck(')')) throw new RuntimeException("Missing ')' after argument to " + functionName);
                    } else {
                        result = parseFactor();
                    }
                    // Evaluating various functions
                    if (functionName.equals("sqrt")) result = Math.sqrt(result);
                    else if (functionName.equals("cbrt")) result = Math.cbrt(result);
                    else if (functionName.equals("log10")) result = Math.log10(result);
                    // Other function evaluations...
                    else throw new RuntimeException("Unknown function: " + functionName);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)currentCharacter);
                }
                // Checking for implicit multiplication
                while ((currentCharacter >= '0' && currentCharacter <= '9') || currentCharacter == '(') {
                    if (currentCharacter == '(') {
                        // Inserting a multiplication operator
                        result *= parseFactor();
                    } else {
                        // Skipping any numbers
                        moveToNextChar();
                    }
                }
                // Exponentiation
                if (consumeAndCheck('^')) result = Math.pow(result, parseFactor()); // exponentiation

                return result;
            }
        }; // Parse the expression and return the result.
    }
}
