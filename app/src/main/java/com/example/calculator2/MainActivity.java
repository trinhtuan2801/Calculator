package com.example.calculator2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

//import org.mariuszgromada.math.mxparser.Expression;

import org.mariuszgromada.math.mxparser.Expression;


public class MainActivity extends AppCompatActivity {
    
    private TextView line1;
    private TextView line2;
    private String line2str;
    private String operator = "+";
    private String line2oldstr = "0";
    private boolean isPressNumber = true;
    private String remember = "";
    // ÷
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        line1 = findViewById((R.id.inputline1));
        line2 = findViewById(R.id.inputline2);
        line2str = getString(R.string.defaultLine2);

    }

    private Double calculate()
    {
        Double result = 0.0;
        Double oldnum = Double.valueOf(line2oldstr);
        Double newnum = Double.valueOf(line2str);
        switch (operator)
        {
            case "+": result = oldnum + newnum;break;
            case "-": result = oldnum - newnum;break;
            case "*": result = oldnum * newnum;break;
            case "/": result = oldnum / newnum;break;
        }
        Log.i("oldnew", line2oldstr + " " + line2str);
        return result;
    }

    private void pressNumber(String input)
    {
        if (line2str.equals(getString(R.string.defaultLine2)) || !isPressNumber) line2str = "";
        line2str = line2str + input;
        line2.setText(line2str);

        isPressNumber = true;
    }

    private void pressOperator(String input)
    {
        if (remember.equals(""))
        {
            remember = line2str;
        }

        if (isPressNumber)
        {
            Double result = calculate();
            line2str = removeTrailingZero(String.valueOf(result));
            line2oldstr = line2str;
            line2.setText(line2str);
        }

        operator = input;
        line1.setText(removeTrailingZero(line2str) + " " + operator);

        isPressNumber = false;
    }

    private String removeTrailingZero(String str)
    {
        int dotindex = str.indexOf('.');

        if (dotindex != - 1)
            for (int i = str.length()-1; i >= dotindex; i--)
            {
                if (str.charAt(i) != '0' && str.charAt(i) != '.')
                {
                    break;
                }
                str = str.substring(0, i);
            }
        Log.i("zero", str);
        return str;
    }

    public void plusMinusBTN(View view)
    {
        if (!line2str.equals(getString(R.string.defaultLine2)))
        {
            if (line2str.charAt(0) == '-')
                line2str = line2str.substring(1, line2str.length());
            else
                line2str = "-" + line2str;

            line2.setText(line2str);
        }
    }

    public void pointBTN(View view)
    {
        if (line2str.equals(getString(R.string.defaultLine2)) || !isPressNumber) line2str = "0";
        int dotpos = line2str.indexOf('.');
        if (dotpos == -1)
            line2str += ".";
        line2.setText(line2str);
        isPressNumber = true;
    }

    //////////////

    public void equalsBTN(View view)
    {
        line1.setText(line2oldstr + " " + operator + " " + line2str + " =");
        Double result = calculate();
        line2str = removeTrailingZero(String.valueOf(result));
        line2oldstr = line2str;
        line2.setText(line2str);
    }

    public void addBTN(View view)
    {
        pressOperator("+");
    }

    public void subtractBTN(View view)
    {
        pressOperator("-");
    }

    public void multiplyBTN(View view)
    {
        pressOperator("*");

    }

    public void divideBTN(View view)
    {
        pressOperator("/");
    }

    //ok

    public void backspaceBTN(View view)
    {
        if (line2str.charAt(0) == '-')
        {
            if (line2str.length() > 2)
                line2str = line2str.substring(0, line2.length()-1);
            else
                line2str = getString(R.string.defaultLine2);
        }
        else
        {
            if (line2str.length() > 1)
                line2str = line2str.substring(0, line2.length()-1);
            else
                line2str = getString(R.string.defaultLine2);
        }

        line2.setText(line2str);
    }

    public void clearBTN(View view)
    {
        line2str = getString(R.string.defaultLine2);
        line2.setText(line2str);
        line1.setText(getString(R.string.defaultLine1));
        line2oldstr = "0";
        operator = "+";
        isPressNumber = true;
    }

    public void clearEntryBTN(View view)
    {
        line2str = getString(R.string.defaultLine2);
        line2.setText(line2str);

        isPressNumber = true;
    }

    public void zeroBTN(View view)
    {
        pressNumber("0");
    }

    public void oneBTN(View view)
    {
        pressNumber("1");
    }

    public void twoBTN(View view)
    {
        pressNumber("2");
    }

    public void threeBTN(View view)
    {
        pressNumber("3");
    }

    public void fourBTN(View view)
    {
        pressNumber("4");
    }

    public void fiveBTN(View view)
    {
        pressNumber("5");
    }

    public void sixBTN(View view)
    {
        pressNumber("6");
    }

    public void sevenBTN(View view)
    {
        pressNumber("7");
    }

    public void eightBTN(View view)
    {
        pressNumber("8");
    }

    public void nineBTN(View view)
    {
        pressNumber("9");
    }
}