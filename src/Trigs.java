// Method 1
private void jbuttonSinhActionPerformed(java.awt.event.ActionEvent evt){
	double x = double.parseDouble(String.valueOf(jTextDisplay.getText()));
	display2.setText("sinh("+String.valueOf(x)+")");
	x=Math.sinh(x);
	jTextDisplay.setText(String.valueOf(x));
}

private void jbuttonCoshActionPerformed(java.awt.event.ActionEvent evt){
	double x = double.parseDouble(String.valueOf(jTextDisplay.getText()));
	display2.setText("cosh("+String.valueOf(x)+")");
	x=Math.cosh(x);
	jTextDisplay.setText(String.valueOf(x));
}

private void jbuttonTanhhActionPerformed(java.awt.event.ActionEvent evt){
	double x = double.parseDouble(String.valueOf(jTextDisplay.getText()));
	display2.setText("tanh("+String.valueOf(x)+")");
	x=Math.tanh(x);
	jTextDisplay.setText(String.valueOf(x));
}
// OR

private void applyHyperbolicFunction(String functionName) {
    try {
        double x = Double.parseDouble(jTextDisplay.getText());
        double result = 0;

        switch (functionName) {
            case "sinh":
                result = Math.sinh(x);
                break;
            case "cosh":
                result = Math.cosh(x);
                break;
            case "tanh":
                result = Math.tanh(x);
                break;
            default:
                throw new IllegalArgumentException("Invalid function name: " + functionName);
        }

        display2.setText(functionName + "(" + x + ")");
        jTextDisplay.setText(String.valueOf(result));
    } catch (NumberFormatException e) {
        jTextDisplay.setText("Error: Invalid input");
    }
}

private void jbuttonSinhActionPerformed(java.awt.event.ActionEvent evt) {
    applyHyperbolicFunction("sinh");
}

private void jbuttonCoshActionPerformed(java.awt.event.ActionEvent evt) {
    applyHyperbolicFunction("cosh");
}

private void jbuttonTanhhActionPerformed(java.awt.event.ActionEvent evt) {
    applyHyperbolicFunction("tanh");
}
// Method 2
// In CalculatorGUI
case "sinh":
currentText="sinh("+ currentText + ")";
break;

case "cosh":
currentText="cosh("+ currentText + ")";
break;

case "tanh":
currentText="tanh("+ currentText + ")";
break;

// In Calculator, after line 98
else if(func.equals("sinh")) x = Math.sinh(x);
else if(func.equals("cosh")) x = Math.cosh(x);
else if(func.equals("tanh")) x = Math.tanh(x);
