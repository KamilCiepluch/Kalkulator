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
import net.objecthunter.exp4j.operator.Operator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AdvancedCalc extends AppCompatActivity {

    private final int numberOfDigitToRound = 4;
    Boolean error = false;
    final String errorMsg = "Invalid equation!";
    LastOperation lastOperation;
    Integer number_Of_leftBrackets;
    Integer number_Of_rightBrackets;
    Boolean dot_in_number;
    StringBuilder equation;
    StringBuilder currentInput;
    boolean inv_on = false;
    private TextView text;




    private static boolean isAbs(String str)
    {
        if(str.length()<4) return false;
        if(str.charAt(str.length()-1)=='(')
        {
            return str.endsWith("abs(");
        }
        return false;
    }
    private static boolean isSin(String str)
    {
        if(str.length()<4) return false;
        if(str.charAt(str.length()-1)=='(')
        {
            return str.endsWith("sin(");
        }
        return false;
    }
    private static boolean isCos(String str)
    {
        if(str.length()<4) return false;
        if(str.charAt(str.length()-1)=='(')
        {
            return str.endsWith("cos(");
        }
        return false;
    }
    private static boolean isTan(String str)
    {
        if(str.length()<4) return false;
        if(str.charAt(str.length()-1)=='(')
        {
            return str.endsWith("tan(");
        }
        return false;
    }
    private static boolean isExp(String str)
    {
        if(str.length()<4) return false;
        if(str.charAt(str.length()-1)=='(')
        {
            return str.endsWith("exp(");
        }
        return false;
    }
    private static boolean isSqrt(String str)
    {
        if(str.length()<5) return false;
        if(str.charAt(str.length()-1)=='(')
        {
            return str.endsWith("sqrt(");
        }
        return false;
    }
    private static boolean isAsin(String str)
    {
        if(str.length()<5) return false;
        if(str.charAt(str.length()-1)=='(')
        {
            return str.endsWith("asin(");
        }
        return false;
    }

    private static boolean isAcos(String str)
    {
        if(str.length()<5) return false;
        if(str.charAt(str.length()-1)=='(')
        {
            return str.endsWith("acos(");
        }
        return false;
    }
    private static boolean isAtan(String str)
    {
        if(str.length()<5) return false;
        if(str.charAt(str.length()-1)=='(')
        {
            return str.endsWith("atan(");
        }
        return false;
    }

    private static boolean isLog2(String str)
    {
        if(str.length()<5) return false;
        if(str.charAt(str.length()-1)=='(')
        {
            return str.endsWith("log2(");
        }
        return false;
    }

    private static boolean isLog10(String str)
    {
        if(str.length()<6) return false;
        if(str.charAt(str.length()-1)=='(')
        {
            return str.endsWith("log10(");
        }
        return false;
    }



    private static boolean is4CharsFunction(String str)
    {
        if(isAbs(str)) return true;
        if(isSin(str)) return true;
        if(isCos(str)) return true;
        if(isTan(str)) return true;
        return isExp(str);
    }

    private static boolean is5CharsFunction(String str)
    {
        if(isSqrt(str)) return true;
        if(isAsin(str)) return true;
        if(isAcos(str)) return true;
        if(isAtan(str)) return true;
        return isLog2(str);
    }

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
    private LastOperation parseToEnum(String str) {
        switch (str) {
            case "NUMBER": return LastOperation.NUMBER;
            case "EQUALS": return LastOperation.EQUALS;
            case "LEFT_BR": return LastOperation.LEFT_BR;
            case "RIGHT_BR": return LastOperation.RIGHT_BR;
            case "FACTORIAL": return LastOperation.FACTORIAL;
            case "COMMA" : return LastOperation.COMMA;
            case "HAT": return LastOperation.HAT;
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
        setContentView(R.layout.advanced);
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
            clearScreen();
        });

        Button buttonEquals = findViewById(R.id.Button_equals);
        buttonEquals.setOnClickListener(view -> {
            try {

                Operator factorial = new Operator("!", 1, true, Operator.PRECEDENCE_POWER + 1) {
                    @Override
                    public double apply(double... args) {
                        final int arg = (int) args[0];
                        if ((double) arg != args[0]) {
                            throw new IllegalArgumentException("Operand for factorial has to be an integer");
                        }
                        if (arg < 0) {
                            throw new IllegalArgumentException("The operand of the factorial can not be less than zero");
                        }
                        double result = 1;
                        for (int i = 1; i <= arg; i++) {
                            result *= i;
                        }
                        return result;
                    }
                };

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

                Expression e = new ExpressionBuilder(equation.toString()).operator(factorial).build();

                BigDecimal res = BigDecimal.valueOf(e.evaluate())
                        .setScale(numberOfDigitToRound, RoundingMode.HALF_UP);


                Log.i("My App", String.valueOf(res.doubleValue()));
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
                if(is4CharsFunction(currentInput.toString()) ||
                        is5CharsFunction(currentInput.toString())
                        || isLog10(currentInput.toString()))
                {
                    if(is5CharsFunction(currentInput.toString()))
                    {
                        currentInput.delete(currentInput.length()-5, currentInput.length());
                    }
                    else if(is4CharsFunction(currentInput.toString()))
                    {
                        currentInput.delete(currentInput.length()-4, currentInput.length());
                    }
                    else
                    {
                        currentInput.delete(currentInput.length()-6, currentInput.length());
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

                if(is4CharsFunction(equation.toString())
                        || is5CharsFunction(equation.toString())
                        || isLog10(equation.toString()))
                {
                    if(is5CharsFunction(equation.toString()))
                    {
                        equation.delete(equation.length()-5, equation.length());
                    }
                    else if(is4CharsFunction(equation.toString()))
                    {
                        equation.delete(equation.length()-4, equation.length());
                    }
                    else
                    {
                        equation.delete(equation.length()-6, equation.length());
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
            clearScreen();
        });

        Button buttonComma = findViewById(R.id.Button_comma);
        buttonComma.setOnClickListener(view -> {

            if(lastOperation!=LastOperation.EQUALS)
            {
                if(currentInput.length()==0 && equation.length()==0 || lastOperation == LastOperation.ARITHMETIC)
                {
                    currentInput.append("0.");
                    dot_in_number = true;
                }
                else if(!dot_in_number)
                {
                    currentInput.append(".");
                    dot_in_number = true;
                }
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
            clearScreen();
        });

        Button buttonSqrt = findViewById(R.id.Button_sqrt);
        buttonSqrt.setOnClickListener(view -> {
            dot_in_number = false;
            currentInput.append("sqrt(");
            lastOperation = LastOperation.LEFT_BR;
            number_Of_leftBrackets++;
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

            clearScreen();
        });

        Button buttonHat = findViewById(R.id.Button_hat);
        buttonHat.setOnClickListener(view -> {
            dot_in_number = false;
            currentInput.append("^");
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
                lastOperation = LastOperation.NUMBER;
                clearScreen();
            }
        });

        Button buttonAbs = findViewById(R.id.Button_abs);
        buttonAbs.setOnClickListener(view -> {
            currentInput.append("abs(");
            number_Of_leftBrackets++;
            lastOperation = LastOperation.ARITHMETIC;
            clearScreen();
        });

        Button buttonPercent = findViewById(R.id.Button_percent);
        buttonPercent.setOnClickListener(view -> {
            dot_in_number = false;
            currentInput.append("%");
            lastOperation = LastOperation.ARITHMETIC;
            clearScreen();
        });

        Button buttonDiv = findViewById(R.id.Button_div);
        buttonDiv.setOnClickListener(view -> {

            dot_in_number = false;
            currentInput.append("/");
            lastOperation = LastOperation.ARITHMETIC;
            clearScreen();
        });

        Button buttonMul = findViewById(R.id.Button_mul);
        buttonMul.setOnClickListener(view -> {
            dot_in_number = false;
            currentInput.append("*");
            lastOperation = LastOperation.ARITHMETIC;
            clearScreen();
        });


        Button buttonSin = findViewById(R.id.Button_sin);
        buttonSin.setOnClickListener(view -> {
            if(inv_on)
                currentInput.append("asin(");
            else
                currentInput.append("sin(");

            number_Of_leftBrackets++;
            lastOperation = LastOperation.ARITHMETIC;
            clearScreen();
        });

        Button buttonCos = findViewById(R.id.Button_cos);
        buttonCos.setOnClickListener(view -> {
            if(inv_on)
                currentInput.append("acos(");
            else
                currentInput.append("cos(");

            number_Of_leftBrackets++;
            lastOperation = LastOperation.ARITHMETIC;
            clearScreen();
        });

        Button buttonTan = findViewById(R.id.Button_tan);
        buttonTan.setOnClickListener(view -> {
            if(inv_on)
                currentInput.append("atan(");
            else
                currentInput.append("tan(");

            number_Of_leftBrackets++;
            lastOperation = LastOperation.ARITHMETIC;
            clearScreen();
        });


        //todo check
        Button buttonE = findViewById(R.id.Button_e);
        buttonE.setOnClickListener(view -> {
            if(lastOperation!=LastOperation.NUMBER)
            {
                currentInput.append(BigDecimal.valueOf(Math.E)
                        .setScale(numberOfDigitToRound, RoundingMode.HALF_UP));
                lastOperation = LastOperation.NUMBER;
                clearScreen();
            }
        });

        Button buttonLn = findViewById(R.id.Button_ln);
        buttonLn.setOnClickListener(view -> {
            if(inv_on)
                currentInput.append("exp(");
            else
                currentInput.append("log2(");
            number_Of_leftBrackets++;
            lastOperation = LastOperation.ARITHMETIC;
            clearScreen();
        });

        Button buttonLog = findViewById(R.id.Button_log);
        buttonLog.setOnClickListener(view -> {
            if(inv_on)
            {
                currentInput.append("10^");
                lastOperation = LastOperation.HAT;
            }
            else
            {
                currentInput.append("log10(");
                number_Of_leftBrackets++;
                lastOperation = LastOperation.ARITHMETIC;
            }
            clearScreen();
        });

        Button buttonINV = findViewById(R.id.Button_inv);
        buttonINV.setOnClickListener(view -> {

            if(inv_on)
            {
                buttonSin.setText(R.string.Button_sin);
                buttonCos.setText(R.string.Button_cos);
                buttonTan.setText(R.string.Button_tan);
                buttonLn.setText(R.string.Button_ln);
                buttonLog.setText(R.string.Button_log);
                inv_on = false;
            }
            else
            {
                inv_on = true;
                buttonSin.setText(R.string.Button_asin);
                buttonCos.setText(R.string.Button_acos);
                buttonTan.setText(R.string.Button_atan);
                buttonLn.setText(R.string.Button_exp);
                buttonLog.setText(R.string.Button_10x);
            }
            clearScreen();
        });

        Button buttonFac = findViewById(R.id.Button_fac);
        buttonFac.setOnClickListener(view -> {
            currentInput.append("!");
            lastOperation = LastOperation.FACTORIAL;
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
