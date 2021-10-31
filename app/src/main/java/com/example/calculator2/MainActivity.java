package com.example.calculator2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

//import org.mariuszgromada.math.mxparser.Expression;

import org.mariuszgromada.math.mxparser.Expression;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    
    private TextView InputTextView;
    private TextView ExpressionTextView;
    private String InputText = "";
    private String[] operators = {"+", "-", "*", "/", "="};
    private boolean isInput = false;
    private boolean isPlusMinusClicked = false;
    private String LastInput = "";
    private String operator = "+";
    private DecimalFormat format;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputTextView = findViewById((R.id.inputText));
        ExpressionTextView = findViewById(R.id.expressionText);
        InputText = getString(R.string.defaultInputValue);
        LastInput = getString(R.string.defaultInputValue);

        format = new DecimalFormat("0.#");
    }

    private void setInputTextView()
    {
        InputTextView.setText(InputText);
    }

    private void setExpressionTextView(boolean isEqual)
    {

        if (isEqual)
            ExpressionTextView.setText(LastInput + " " + operator + " " + InputText + " " + "=");
        else
            ExpressionTextView.setText(LastInput + " " + operator);

    }

    private double calculate()
    {
        Log.i("calculate", LastInput + operator + InputText);
        Expression result = new Expression(LastInput + operator + InputText);
        Log.i("calculate","result: " + String.valueOf(result.calculate()));
        return result.calculate();
    }

    private void updateInputText(String addstr)
    {
        if (Arrays.asList(operators).contains(addstr)) //nhap phep toan
        {
            if (isInput)
            {
                BigDecimal bd = new BigDecimal(calculate());
                String result = String.valueOf(bd.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros());
                InputText = result;

                LastInput = InputText;
                setInputTextView();
            }
            else
            {
                LastInput = InputText;
            }
            operator = addstr;
            setExpressionTextView(false);
            isInput = false;
        }
        else if (addstr.equals("."))
        {
            if (InputText.indexOf(".") == -1)
            {
//                Log.i("pos", String.valueOf(InputText.indexOf(".") == -1));
                InputText += addstr;
                isInput = true;
                setInputTextView();
            }
        }
        else //nhap so
        {

            if (getString(R.string.defaultInputValue).equals(InputText) || isInput == false)
            {
                InputText = "";
            }

            InputText += addstr;
            isInput = true;

            setInputTextView();
        }

    }

    public void zeroBTN(View view)
    {
        updateInputText("0");
    }

    public void oneBTN(View view)
    {
        updateInputText("1");

    }

    public void twoBTN(View view)
    {
        updateInputText("2");

    }

    public void threeBTN(View view)
    {
        updateInputText("3");

    }

    public void fourBTN(View view)
    {
        updateInputText("4");

    }

    public void fiveBTN(View view)
    {
        updateInputText("5");

    }

    public void sixBTN(View view)
    {
        updateInputText("6");

    }

    public void sevenBTN(View view)
    {
        updateInputText("7");

    }

    public void eightBTN(View view)
    {
        updateInputText("8");

    }

    public void nineBTN(View view)
    {
        updateInputText("9");

    }

    public void plusMinusBTN(View view)
    {
        if (!InputText.equals(getString(R.string.defaultInputValue)))
        {
            isPlusMinusClicked = !isPlusMinusClicked;

            if (isPlusMinusClicked)
            {
                InputText = '-' + InputText;
            }
            else
            {
                InputText = InputText.substring(1);
            }

            setInputTextView();
        }
    }

    public void pointBTN(View view)
    {
        updateInputText(".");
    }

    //////////////

    public void equalsBTN(View view)
    {
        isInput = false;

        setExpressionTextView(true);
        BigDecimal bd = new BigDecimal(calculate());
        String result = String.valueOf(bd.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros());
        InputText = result;
        LastInput = InputText;
        setInputTextView();
    }

    public void addBTN(View view)
    {
        updateInputText("+");
    }

    public void subtractBTN(View view)
    {
        updateInputText("-");
    }

    public void multiplyBTN(View view)
    {
        updateInputText("*");

    }

    public void divideBTN(View view)
    {
        updateInputText("/");

    }

    public void backspaceBTN(View view)
    {
        isInput = true;

        if (InputText.length() != 0)
        {
            InputText = InputText.substring(0, InputText.length()-1);
        }

        if (InputText.length() == 0 || (InputText.length() == 1 && InputText.equals("-")))
        {
            InputText = getString(R.string.defaultInputValue);
        }

        setInputTextView();
    }

    public void clearBTN(View view)
    {
        InputText = getString(R.string.defaultInputValue);
        InputTextView.setText(InputText);
        ExpressionTextView.setText(R.string.defaultExpressionValue);
        isInput = false;
        operator = "+";
        LastInput = getString(R.string.defaultInputValue);
        isPlusMinusClicked = false;

    }

    public void clearEntryBTN(View view)
    {
        isInput = true;
        InputText = getString(R.string.defaultInputValue);
        setInputTextView();
    }
}