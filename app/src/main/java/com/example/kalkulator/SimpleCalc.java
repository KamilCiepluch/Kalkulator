package com.example.kalkulator;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class SimpleCalc extends AppCompatActivity {

    boolean error = false;
    final String errorMsg = "Invalid equation!";
    LastOperation lastOperation;



    int number_Of_leftBrackets;
    int number_Of_rightBrackets;

    boolean dot_in_number;
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
            text.setText("");
            text.append(equation);
            text.append(currentInput);
        }



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        currentInput = new StringBuilder();
        equation = new StringBuilder();
        number_Of_leftBrackets = 0;
        number_Of_rightBrackets =0;
        dot_in_number = false;
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        text = findViewById(R.id.textView);

        lastOperation = LastOperation.ARITHMETIC;

        Button button1 = findViewById(R.id.Button_1);
        button1.setOnClickListener(view -> {
            if(currentInput.length()==1 && currentInput.charAt(0)=='0')
                currentInput.delete(0,1);
            currentInput.append(1);


            lastOperation = LastOperation.NUMBER;
            Log.i("My App", String.valueOf(currentInput));
            clearScreen();
        });


        Button button2 = findViewById(R.id.Button_2);
        button2.setOnClickListener(view -> {
            if(currentInput.length()==1 && currentInput.charAt(0)=='0')
                currentInput.delete(0,1);
            currentInput.append(2);

            lastOperation = LastOperation.NUMBER;
            Log.i("My App", String.valueOf(currentInput));
            clearScreen();
        });



        Button button3 = findViewById(R.id.Button_3);
        button3.setOnClickListener(view -> {
            if(currentInput.length()==1 && currentInput.charAt(0)=='0')
                currentInput.delete(0,1);

            currentInput.append(3);

            lastOperation = LastOperation.NUMBER;
            Log.i("My App", String.valueOf(currentInput));
            clearScreen();
        });


        Button button4 = findViewById(R.id.Button_4);
        button4.setOnClickListener(view -> {
            if(currentInput.length()==1 && currentInput.charAt(0)=='0')
                currentInput.delete(0,1);

            currentInput.append(4);
            lastOperation = LastOperation.NUMBER;
            Log.i("My App", String.valueOf(equation));
            System.out.println(equation);
            clearScreen();
        });


        Button button5 = findViewById(R.id.Button_5);
        button5.setOnClickListener(view -> {
            if(currentInput.length()==1 && currentInput.charAt(0)=='0')
                currentInput.delete(0,1);

            currentInput.append(5);
            lastOperation = LastOperation.NUMBER;
            Log.i("My App", String.valueOf(equation));
            System.out.println(equation);
            clearScreen();
        });



        Button button6 = findViewById(R.id.Button_6);
        button6.setOnClickListener(view -> {
            if(currentInput.length()==1 && currentInput.charAt(0)=='0')
                currentInput.delete(0,1);
            currentInput.append(6);
            lastOperation = LastOperation.NUMBER;
            Log.i("My App", String.valueOf(equation));
            System.out.println(equation);
            clearScreen();
        });



        Button button7 = findViewById(R.id.Button_7);
        button7.setOnClickListener(view -> {
            if(currentInput.length()==1 && currentInput.charAt(0)=='0')
                currentInput.delete(0,1);
            currentInput.append(7);
            lastOperation = LastOperation.NUMBER;
            Log.i("My App", String.valueOf(equation));
            System.out.println(equation);
            clearScreen();
        });




        Button button8 = findViewById(R.id.Button_8);
        button8.setOnClickListener(view -> {
            if(currentInput.length()==1 && currentInput.charAt(0)=='0')
                currentInput.delete(0,1);
            currentInput.append(8);
            lastOperation = LastOperation.NUMBER;
            Log.i("My App", String.valueOf(equation));
            System.out.println(equation);
            clearScreen();
        });



        Button button9 = findViewById(R.id.Button_9);
        button9.setOnClickListener(view -> {
            if(currentInput.length()==1 && currentInput.charAt(0)=='0')
                currentInput.delete(0,1);


            currentInput.append(9);
            lastOperation = LastOperation.NUMBER;
            Log.i("My App", String.valueOf(equation));
            System.out.println(equation);
            clearScreen();
        });

        Button button0 = findViewById(R.id.Button_0);
        button0.setOnClickListener(view -> {
            currentInput.append(0);
            lastOperation = LastOperation.NUMBER;
            Log.i("My App", String.valueOf(equation));
            System.out.println(equation);
            clearScreen();
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
                while (number_Of_leftBrackets!=number_Of_rightBrackets)
                {
                    equation.append(")");
                    number_Of_rightBrackets++;
                }
                if(lastOperation==LastOperation.HAT)
                    equation.append(1);
                System.out.println(equation);
                Expression e = new ExpressionBuilder(equation.toString()).build();
                double res = e.evaluate();
                Log.i("My App", String.valueOf(res));
                System.out.println(res);
                equation.delete(0, currentInput.capacity());
                equation.append(res);
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
                if(currentInput.charAt(currentInput.length()-1)=='(')
                    number_Of_leftBrackets--;
                else if(currentInput.charAt(currentInput.length()-1)==')')
                    number_Of_leftBrackets--;

                currentInput.deleteCharAt(currentInput.length()-1);
            }
            else if(equation.length()!=0)
            {
                equation.deleteCharAt(equation.length()-1);
            }


            Log.i("My App", String.valueOf(equation));
            System.out.println(equation);
            clearScreen();
            lastOperation = LastOperation.DEL;
        });


        Button buttonComma = findViewById(R.id.Button_comma);
        buttonComma.setOnClickListener(view -> {
            if(currentInput.length()==0 || lastOperation == LastOperation.ARITHMETIC)
                    currentInput.append("0.");
            else if(!dot_in_number)
            {
                currentInput.append(".");
                dot_in_number = true;
            }
            System.out.println(currentInput);
            clearScreen();
            lastOperation = LastOperation.COMMA;
        });

        Button buttonAC = findViewById(R.id.Button_ac);
        buttonAC.setOnClickListener(view -> {
            dot_in_number = false;
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

            dot_in_number = true;
            currentInput.append("3.14");
            System.out.println(currentInput);
            lastOperation = LastOperation.NUMBER;
            clearScreen();
        });
        Button buttonExcl = findViewById(R.id.Button_abs);
        buttonExcl.setOnClickListener(view -> {

            currentInput.append("!");
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

        Button buttonMult = findViewById(R.id.Button_mult);
        buttonMult.setOnClickListener(view -> {

            dot_in_number = false;
            currentInput.append("*");
            System.out.println(currentInput);
            lastOperation = LastOperation.ARITHMETIC;
            clearScreen();
        });
    }
}
