package org.samyak.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.samyak.calculator.Presenter.BasicCalculator;
import org.samyak.calculator.Presenter.Calculator;

public class MainActivity extends AppCompatActivity {

    private final Calculator mCalculator = new BasicCalculator();
    private TextView mExpressionTextView;
    private TextView mResultTextView;
    private Button mButtonZero;
    private Button mButtonOne;
    private Button mButtonTwo;
    private Button mButtonThree;
    private Button mButtonFour;
    private Button mButtonFive;
    private Button mButtonSix;
    private Button mButtonSeven;
    private Button mButtonEight;
    private Button mButtonNine;
    private Button mButtonClear;
    private Button mButtonDelete;
    private Button mButtonPercentage;
    private Button mButtonDivide;
    private Button mButtonMultiply;
    private Button mButtonPlus;
    private Button mButtonMinus;
    private Button mButtonDecimal;
    private Button mButtonEqual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mExpressionTextView = findViewById(R.id.tv_expression);
        mResultTextView = findViewById(R.id.tv_result);

        initializeNumberPad();
    }

    public void onClearKeyClicked(View view){
        mResultTextView.setText("");
        mExpressionTextView.setText("");
    }

    public void onDeleteKeyClicked(View view){
        String expression = mExpressionTextView.getText().toString();
        expression = expression.substring(0, expression.length() - 1);
        mExpressionTextView.setText(expression);
        mResultTextView.setText(calculate());
    }

    public void onNumberKeyClicked(View view){
        TextView numberKey = (TextView)view;
        if(numberKey.getId() == R.id.btn_decimal && mExpressionTextView.getText().length() == 0){
            mExpressionTextView.setText("0");
        }

        mExpressionTextView.append(numberKey.getText());
        mResultTextView.setText(calculate());
    }

    public void onOperationKeyClicked(View view){
        if(mExpressionTextView.getText().length() == 0){
            mExpressionTextView.setText("0");
        }

        TextView operationKey = (TextView)view;
        String keyText = " " + operationKey.getText().toString() + " ";
        mExpressionTextView.append(keyText);
    }

    public void onEqualKeyClicked(View view){
        mResultTextView.setText(calculate());
        mExpressionTextView.setText("");
    }

    private void initializeNumberPad() {
        mButtonZero = findViewById(R.id.btn_0);
        mButtonOne = findViewById(R.id.btn_1);
        mButtonTwo = findViewById(R.id.btn_2);
        mButtonThree = findViewById(R.id.btn_3);
        mButtonFour = findViewById(R.id.btn_4);
        mButtonFive = findViewById(R.id.btn_5);
        mButtonSix = findViewById(R.id.btn_6);
        mButtonSeven = findViewById(R.id.btn_7);
        mButtonEight = findViewById(R.id.btn_8);
        mButtonNine = findViewById(R.id.btn_9);
        mButtonDecimal = findViewById(R.id.btn_decimal);
        mButtonClear = findViewById(R.id.btn_clear);
        mButtonDelete = findViewById(R.id.btn_delete);
        mButtonPercentage = findViewById(R.id.btn_percentage);
        mButtonDivide = findViewById(R.id.btn_divide);
        mButtonMultiply = findViewById(R.id.btn_multiply);
        mButtonPlus = findViewById(R.id.btn_plus);
        mButtonMinus = findViewById(R.id.btn_minus);
        mButtonEqual = findViewById(R.id.btn_Equal);
    }

    private String calculate(){
        String expression = mExpressionTextView.getText().toString();
        double result = mCalculator.calculate(expression);

        double fractionalPart = result % 1;
        long integralPart = (long)result;

        return fractionalPart == 0 ? integralPart + "" : result + "";
    }
}
