package org.samyak.calculator.Presenter;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BasicCalculator implements Calculator {
    private static final List<Character> mArithmaticOperations = Arrays.asList('+', '-', 'x', '/', '%', '=');
    private final Stack<Double> mOperandStack = new Stack<>();
    private final Stack<Character> mOperatorStack = new Stack<>();

    @Override
    public double calculate(String expression) {
        String[] tokens = tokenize(expression);

        if(tokens[tokens.length - 1].contentEquals(" ")){
            tokens = Arrays.copyOf(tokens, tokens.length - 1);
        }

        return evaluate(tokens);
    }

    private static String[] tokenize(String expression){
        String pattern = "[+|\\-|x|/|%]";
        String[] tokens = expression.split("((?<="+pattern+")|(?="+pattern+"))");

        return tokens;
    }

    private static double evaluate(double num1, double num2, char operation){
        double result = 0;
        switch (operation){
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
            case '%':
                result = num1 * 0.01;
                break;
        }

        return result;
    }

    private double evaluate(String[] tokens){
        for(String token : tokens) {
            char op = token.trim().toCharArray()[0];
            if(mArithmaticOperations.contains(op)){ // operator
                mOperatorStack.push(op);
            }
            else if(!mOperatorStack.isEmpty()){ // operand
                double num1 = mOperandStack.pop();
                double num2 = Double.valueOf(token);
                char operator = mOperatorStack.pop();

                double result = evaluate(num1, num2, operator);
                mOperandStack.push(result);
            }
            else{ // operand
                mOperandStack.push(Double.valueOf(token));
            }
        }

        return mOperandStack.pop();
    }
}
