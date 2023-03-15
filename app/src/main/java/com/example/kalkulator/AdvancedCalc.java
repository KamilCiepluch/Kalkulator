package com.example.kalkulator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AdvancedCalc extends AppCompatActivity {

    StringBuilder FirstNumber;
    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Button Button4;
    private Button Button5;
    private Button Button6;
    private Button Button7;
    private Button Button8;
    private Button Button9;
    private Button Button0;
    private Button ButtonSqrt;
    private Button ButtonPi;
    private Button ButtonPlus;
    private Button ButtonMinus;
    private Button ButtonDev;
    private Button ButtonMul;
    private Button ButtonAC;
    private TextView text;



    private void clearScreen()
    {
        text.setText("");
        text.append(FirstNumber);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        FirstNumber = new StringBuilder();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.advanced);

        text = findViewById(R.id.textView);

        Button1 = (Button) findViewById(R.id.Button_1);
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstNumber.append(1);
                Log.i("My App", String.valueOf(FirstNumber));
                System.out.println(FirstNumber);
                clearScreen();
            }
        });


        Button2 = (Button) findViewById(R.id.Button_2);
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstNumber.append(2);
                Log.i("My App", String.valueOf(FirstNumber));
                System.out.println(FirstNumber);
                clearScreen();
            }
        });



        Button3 = (Button) findViewById(R.id.Button_3);
        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstNumber.append(3);
                Log.i("My App", String.valueOf(FirstNumber));
                System.out.println(FirstNumber);
                clearScreen();
            }
        });


        Button4 = (Button) findViewById(R.id.Button_4);
        Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstNumber.append(4);
                Log.i("My App", String.valueOf(FirstNumber));
                System.out.println(FirstNumber);
                clearScreen();
            }
        });


        Button5 = (Button) findViewById(R.id.Button_5);
        Button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstNumber.append(5);
                Log.i("My App", String.valueOf(FirstNumber));
                System.out.println(FirstNumber);
                clearScreen();
            }
        });



        Button6 = (Button) findViewById(R.id.Button_6);
        Button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstNumber.append(6);
                Log.i("My App", String.valueOf(FirstNumber));
                System.out.println(FirstNumber);
                clearScreen();
            }
        });



        Button7 = (Button) findViewById(R.id.Button_7);
        Button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstNumber.append(7);
                Log.i("My App", String.valueOf(FirstNumber));
                System.out.println(FirstNumber);
                clearScreen();
            }
        });




        Button8 = (Button) findViewById(R.id.Button_8);
        Button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstNumber.append(8);
                Log.i("My App", String.valueOf(FirstNumber));
                System.out.println(FirstNumber);
                clearScreen();
            }
        });




        Button9 = (Button) findViewById(R.id.Button_9);
        Button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstNumber.append(9);
                Log.i("My App", String.valueOf(FirstNumber));
                System.out.println(FirstNumber);
                clearScreen();
            }
        });

        Button0 = (Button) findViewById(R.id.Button_0);
        Button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstNumber.append(0);
                Log.i("My App", String.valueOf(FirstNumber));
                System.out.println(FirstNumber);
                clearScreen();
            }
        });


    }
}
