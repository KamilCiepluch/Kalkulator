package com.example.kalkulator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;




public class SimpleCalc extends AppCompatActivity {


    private final int numberOfDigitToRound = 4;
    Boolean error = false;
    final String errorMsg = "Invalid equation!";
    LastOperation lastOperation;


    Integer number_Of_leftBrackets;
    Integer number_Of_rightBrackets;
    Boolean dot_in_number;
    StringBuilder equation;
    StringBuilder currentInput;
    private TextView text;



    private void clearScreen()
    {
        if(error)
        {
            text.setText(errorMsg);
            error = false;
        }
        else {
            String res = String.valueOf(equation) + currentInput;
            text.setText(res);
        }
    }



    private boolean isAbs(String str)
    {
        if(str.length()<4) return false;
        if(str.charAt(str.length()-1)=='(')
        {
            return str.endsWith("abs(");
        }
        return false;
    }

    private boolean isSqrt(String str)
    {
        if(str.length()<5) return false;
        if(str.charAt(str.length()-1)=='(')
        {
            return str.endsWith("sqrt(");
        }
        return false;
    }






    private LastOperation parseToEnum(String str) {
        switch (str) {
            case "NUMBER": return LastOperation.NUMBER;
            case "EQUALS": return LastOperation.EQUALS;
            case "LEFT_BR": return LastOperation.LEFT_BR;
            case "RIGHT_BR": return LastOperation.RIGHT_BR;
            case "COMMA" : return LastOperation.COMMA;
            case "HAT": return LastOperation.HAT;
            case "FACTORIAL": return LastOperation.FACTORIAL;
            default : return LastOperation.ARITHMETIC;
        }
    }
    private void initData()
    {
        currentInput = new StringBuilder();
        equation = new StringBuilder();
        number_Of_leftBrackets = 0;
        number_Of_rightBrackets =0;
        dot_in_number = false;
        lastOperation = LastOperation.ARITHMETIC;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        initData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null)
        {
            equation = new StringBuilder(savedInstanceState.getString("Equation"));
            currentInput = new StringBuilder(savedInstanceState.getString("CurrentInput"));
            lastOperation = parseToEnum(savedInstanceState.getString("LastOperation"));
            number_Of_leftBrackets = savedInstanceState.getInt("LeftBrackets");
            number_Of_rightBrackets = savedInstanceState.getInt("RightBrackets");
            dot_in_number = savedInstanceState.getBoolean("DotInNumber");
            error = savedInstanceState.getBoolean("error");
        }

        text = findViewById(R.id.textView);
        text.setEllipsize(TextUtils.TruncateAt.START);
        text.setSelected(true);
        text.setText(equation.toString() + currentInput.toString());


        Button button0 = findViewById(R.id.Button_0);
        button0.setOnClickListener(view -> {
            if(lastOperation != LastOperation.EQUALS) {
                currentInput.append(0);
                lastOperation = LastOperation.NUMBER;
                Log.i("My App", String.valueOf(equation));
                System.out.println(equation);
                clearScreen();
            }
        });

        Button button1 = findViewById(R.id.Button_1);
        button1.setOnClickListener(view -> {
            if(lastOperation != LastOperation.EQUALS)
            {
                if(currentInput.length()==1 && currentInput.charAt(0)=='0')
                    currentInput.delete(0,1);
                currentInput.append(1);

                lastOperation = LastOperation.NUMBER;
                Log.i("My App", String.valueOf(currentInput));
                clearScreen();
            }
        });

        Button button2 = findViewById(R.id.Button_2);
        button2.setOnClickListener(view -> {
            if(lastOperation != LastOperation.EQUALS) {
                if (currentInput.length() == 1 && currentInput.charAt(0) == '0')
                    currentInput.delete(0, 1);
                currentInput.append(2);

                lastOperation = LastOperation.NUMBER;
                Log.i("My App", String.valueOf(currentInput));
                clearScreen();
            }
        });

        Button button3 = findViewById(R.id.Button_3);
        button3.setOnClickListener(view -> {
            if(lastOperation != LastOperation.EQUALS)
            {
            if(currentInput.length()==1 && currentInput.charAt(0)=='0')
                currentInput.delete(0,1);

            currentInput.append(3);

            lastOperation = LastOperation.NUMBER;
            Log.i("My App", String.valueOf(currentInput));
            clearScreen();
            }
        });

        Button button4 = findViewById(R.id.Button_4);
        button4.setOnClickListener(view -> {
            if(lastOperation != LastOperation.EQUALS)
            {
                if(currentInput.length()==1 && currentInput.charAt(0)=='0')
                    currentInput.delete(0,1);

                currentInput.append(4);
                lastOperation = LastOperation.NUMBER;
                Log.i("My App", String.valueOf(equation));
                System.out.println(equation);
                clearScreen();
            }
        });

        Button button5 = findViewById(R.id.Button_5);
        button5.setOnClickListener(view -> {
            if(lastOperation != LastOperation.EQUALS) {
                if (currentInput.length() == 1 && currentInput.charAt(0) == '0')
                    currentInput.delete(0, 1);

                currentInput.append(5);
                lastOperation = LastOperation.NUMBER;
                Log.i("My App", String.valueOf(equation));
                System.out.println(equation);
                clearScreen();
            }
        });

        Button button6 = findViewById(R.id.Button_6);
        button6.setOnClickListener(view -> {
            if(lastOperation != LastOperation.EQUALS) {
            if(currentInput.length()==1 && currentInput.charAt(0)=='0')
                currentInput.delete(0,1);
            currentInput.append(6);
            lastOperation = LastOperation.NUMBER;
            Log.i("My App", String.valueOf(equation));
            System.out.println(equation);
            clearScreen();
            }
        });

        Button button7 = findViewById(R.id.Button_7);
        button7.setOnClickListener(view -> {
            if(lastOperation != LastOperation.EQUALS){
            if(currentInput.length()==1 && currentInput.charAt(0)=='0')
                currentInput.delete(0,1);
            currentInput.append(7);
            lastOperation = LastOperation.NUMBER;
            Log.i("My App", String.valueOf(equation));
            System.out.println(equation);
            clearScreen();
            }
        });

        Button button8 = findViewById(R.id.Button_8);
        button8.setOnClickListener(view -> {
            if(lastOperation != LastOperation.EQUALS){
            if(currentInput.length()==1 && currentInput.charAt(0)=='0')
                currentInput.delete(0,1);
            currentInput.append(8);
            lastOperation = LastOperation.NUMBER;
            Log.i("My App", String.valueOf(equation));
            System.out.println(equation);
            clearScreen();}
        });

        Button button9 = findViewById(R.id.Button_9);
        button9.setOnClickListener(view -> {
            if(lastOperation != LastOperation.EQUALS){
            if(currentInput.length()==1 && currentInput.charAt(0)=='0')
                currentInput.delete(0,1);
            currentInput.append(9);
            lastOperation = LastOperation.NUMBER;
            Log.i("My App", String.valueOf(equation));
            System.out.println(equation);
            clearScreen();}
        });

        Button buttonPlus = findViewById(R.id.Button_plus);
        buttonPlus.setOnClickListener(view -> {
            dot_in_number = false;
            equation.append(currentInput);
            currentInput.delete(0,currentInput.length());
            if(equation.length()!=0)
                equation.append("+");


            lastOperation = LastOperation.ARITHMETIC;
            Log.i("My App", String.valueOf(equation));
            System.out.println(equation);
            clearScreen();
        });

        Button buttonMinus = findViewById(R.id.Button_minus);
        buttonMinus.setOnClickListener(view -> {
            dot_in_number = false;
            equation.append(currentInput);
            equation.append("-");
            currentInput.delete(0,currentInput.length());
            lastOperation = LastOperation.ARITHMETIC;
            Log.i("My App", String.valueOf(equation));
            System.out.println(equation);
            clearScreen();
        });

        Button buttonEquals = findViewById(R.id.Button_equals);
        buttonEquals.setOnClickListener(view -> {
            try {
                equation.append(currentInput);
                currentInput.delete(0,currentInput.length());
                if(lastOperation.equals(LastOperation.ARITHMETIC))
                    equation.append(0);
                while (!number_Of_leftBrackets.equals(number_Of_rightBrackets))
                {
                    equation.append(")");
                    number_Of_rightBrackets++;
                }
                if(lastOperation==LastOperation.HAT)
                    equation.append(1);

                System.out.println(equation.toString());
                Expression e = new ExpressionBuilder(equation.toString()).build();

                BigDecimal res = BigDecimal.valueOf(e.evaluate())
                        .setScale(numberOfDigitToRound, RoundingMode.HALF_UP);

                Log.i("My App", String.valueOf(res.doubleValue()));
                System.out.println(res);

                equation.delete(0, equation.capacity());
                equation.append(res.doubleValue());
                clearScreen();
                lastOperation = LastOperation.EQUALS;
            }
            catch (Exception e)
            {
                equation.delete(0,equation.length());
                currentInput.delete(0,currentInput.length());
                error = true;
                number_Of_leftBrackets = 0;
                number_Of_rightBrackets = 0;
                dot_in_number = false;
                clearScreen();
            }
        });

        // todo check if correct
        Button buttonDel = findViewById(R.id.Button_del);
        buttonDel.setOnClickListener(view -> {
            if(currentInput.length()!=0)
            {
                if(isAbs(currentInput.toString()) || isSqrt(currentInput.toString()))
                {
                    if(isSqrt(currentInput.toString()))
                    {
                        currentInput.delete(currentInput.length()-5, currentInput.length());
                    }
                    else
                    {
                        currentInput.delete(currentInput.length()-4, currentInput.length());
                    }
                    number_Of_leftBrackets--;
                }
                else
                {
                    if(currentInput.charAt(currentInput.length()-1)=='(')
                        number_Of_leftBrackets--;
                    else if(currentInput.charAt(currentInput.length()-1)==')')
                        number_Of_leftBrackets--;
                    else if(currentInput.charAt(currentInput.length()-1)=='.')
                        dot_in_number = false;

                    currentInput.deleteCharAt(currentInput.length()-1);
                    if(currentInput.length()!=0)
                    {
                        if(currentInput.charAt(currentInput.length()-1)>='0' &&
                                currentInput.charAt(currentInput.length()-1)<='9')
                        {
                            lastOperation = LastOperation.NUMBER;
                        }
                    }
                }
            }
            else if(equation.length()!=0)
            {

                if(isAbs(equation.toString()) || isSqrt(equation.toString()))
                {
                    if(isSqrt(equation.toString()))
                    {
                        equation.delete(equation.length()-5, equation.length());
                    }
                    else
                    {
                        equation.delete(equation.length()-4, equation.length());
                    }
                    number_Of_leftBrackets--;
                }

                else
                {
                    if(equation.charAt(equation.length()-1)=='(')
                        number_Of_leftBrackets--;
                    else if(equation.charAt(equation.length()-1)==')')
                        number_Of_leftBrackets--;
                    else if(equation.charAt(equation.length()-1)=='.')
                        dot_in_number = false;

                    equation.deleteCharAt(equation.length()-1);
                    if(equation.length()!=0)
                    {
                        if(equation.charAt(equation.length()-1)>='0' &&
                                equation.charAt(equation.length()-1)<='9')
                        {
                            lastOperation = LastOperation.NUMBER;
                        }
                    }
                }
            }

            Log.i("My App", String.valueOf(equation));
            System.out.println(equation);
            clearScreen();
        });

        Button buttonComma = findViewById(R.id.Button_comma);
        buttonComma.setOnClickListener(view -> {

            if(lastOperation!=LastOperation.EQUALS)
            {
                if(currentInput.length()==0 || lastOperation == LastOperation.ARITHMETIC)
                {
                    currentInput.append("0.");
                    dot_in_number = true;
                }
                else if(!dot_in_number)
                {
                    currentInput.append(".");
                    dot_in_number = true;
                }
                System.out.println(currentInput);
                clearScreen();
                lastOperation = LastOperation.COMMA;
            }
        });

        Button buttonAC = findViewById(R.id.Button_ac);
        buttonAC.setOnClickListener(view -> {
            number_Of_leftBrackets = 0;
            number_Of_rightBrackets =0;
            dot_in_number = false;
            lastOperation = LastOperation.ARITHMETIC;
            equation.delete(0,equation.length());
            currentInput.delete(0,currentInput.length());
            System.out.println(currentInput);
            clearScreen();
        });

        Button buttonSqrt = findViewById(R.id.Button_sqrt);
        buttonSqrt.setOnClickListener(view -> {
            dot_in_number = false;
            currentInput.append("sqrt(");
            lastOperation = LastOperation.LEFT_BR;
            number_Of_leftBrackets++;
            System.out.println(currentInput);
            clearScreen();
        });

        Button buttonBracket = findViewById(R.id.Button_brackets);
        buttonBracket.setOnClickListener(view -> {
            if(lastOperation==LastOperation.ARITHMETIC || lastOperation == LastOperation.HAT)
            {
                currentInput.append("(");
                number_Of_leftBrackets++;
                lastOperation = LastOperation.LEFT_BR;
            }
            else if(lastOperation==LastOperation.LEFT_BR || lastOperation == LastOperation.NUMBER || number_Of_leftBrackets>number_Of_rightBrackets)
            {
                currentInput.append(")");
                number_Of_rightBrackets++;
                lastOperation = LastOperation.RIGHT_BR;
            }

            System.out.println(currentInput);
            clearScreen();
        });

        Button buttonHat = findViewById(R.id.Button_hat);
        buttonHat.setOnClickListener(view -> {
            dot_in_number = false;
            currentInput.append("^");
            System.out.println(currentInput);
            lastOperation = LastOperation.HAT;
            clearScreen();
        });

        Button buttonPi = findViewById(R.id.Button_pi);
        buttonPi.setOnClickListener(view -> {
            if(lastOperation!=LastOperation.NUMBER)
            {
                dot_in_number = true;
                currentInput.append(BigDecimal.valueOf(Math.PI)
                        .setScale(numberOfDigitToRound, RoundingMode.HALF_UP));
                System.out.println(currentInput);
                lastOperation = LastOperation.NUMBER;
                clearScreen();
            }
        });

        Button buttonAbs = findViewById(R.id.Button_abs);
        buttonAbs.setOnClickListener(view -> {
            currentInput.append("abs(");
            number_Of_leftBrackets++;
            System.out.println(currentInput);
            lastOperation = LastOperation.ARITHMETIC;
            clearScreen();
        });

        Button buttonPercent = findViewById(R.id.Button_percent);
        buttonPercent.setOnClickListener(view -> {
            dot_in_number = false;
            currentInput.append("%");
            System.out.println(currentInput);
            lastOperation = LastOperation.ARITHMETIC;
            clearScreen();
        });

        Button buttonDiv = findViewById(R.id.Button_div);
        buttonDiv.setOnClickListener(view -> {

            dot_in_number = false;
            currentInput.append("/");
            System.out.println(currentInput);
            lastOperation = LastOperation.ARITHMETIC;
            clearScreen();
        });

        Button buttonMul = findViewById(R.id.Button_mul);
        buttonMul.setOnClickListener(view -> {
            dot_in_number = false;
            currentInput.append("*");
            System.out.println(currentInput);
            lastOperation = LastOperation.ARITHMETIC;
            clearScreen();
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("Equation", equation.toString());
        outState.putString("CurrentInput", currentInput.toString());
        outState.putString("LastOperation", lastOperation.toString());
        outState.putInt("LeftBrackets", number_Of_leftBrackets);
        outState.putInt("RightBrackets", number_Of_rightBrackets);
        outState.putBoolean("DotInNumber", dot_in_number);
        outState.putBoolean("Error", error);
        super.onSaveInstanceState(outState);
    }
}
