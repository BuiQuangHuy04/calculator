package com.example.calculator;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;

public class calculator extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9, btnDot,btnDel,btnEqual,btnPlus,
            btnMinus,btnMultiply, btnDivision, btnPercent, btnOpenRoundBrackets, btnCloseRoundBrackets,
            btnFactorial, btnSquareRoot, btnPowerOf, btnReset;
    private ListView listvOldResults;
    private List<String> oldResults = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ArrayList<String> strExpressions = new ArrayList<>();
    private TextView txtvResult, txtvExpressions;

    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.c.hellosharedprefs";

    private int width;
    private int btnWidth;
    private  int btnTextSize;

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(String.valueOf(Level.INFO), "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(String.valueOf(Level.INFO), "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(String.valueOf(Level.INFO), "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(String.valueOf(Level.INFO), "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(String.valueOf(Level.INFO), "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(String.valueOf(Level.INFO), "onRestart");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (!oldResults.isEmpty()) {
            outState.putStringArrayList("old_data", (ArrayList<String>) oldResults);
            oldResults.forEach(System.out::println);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            oldResults = savedInstanceState.getStringArrayList("old_data");
            adapter = new ArrayAdapter<>(this, R.layout.list_old_result,R.id.item_listv_old_result, oldResults);
            listvOldResults.setAdapter(adapter);
        }

        Log.d(String.valueOf(Level.INFO), "onCreate");
        setContentView(R.layout.activity_caculator);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        btnWidth = width*75/100/4;
        btnTextSize = 0;

        initUI();

        adapter = new ArrayAdapter<>(this, R.layout.list_old_result,R.id.item_listv_old_result, oldResults);

        listvOldResults.setAdapter(adapter);

        handleUI();
    }

    private void initUI() {
        btn0 = findViewById(R.id.btn_0);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btnDot = findViewById(R.id.btn_dot);
        btnDel = findViewById(R.id.btn_del);
        btnEqual = findViewById(R.id.btn_equal);
        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        btnMultiply = findViewById(R.id.btn_multiply);
        btnDivision = findViewById(R.id.btn_division);
        btnPercent = findViewById(R.id.btn_percent);
        btnOpenRoundBrackets = findViewById(R.id.btn_openroundbrackets);
        btnCloseRoundBrackets = findViewById(R.id.btn_closeroundbrackets);
        btnFactorial = findViewById(R.id.btn_factorial);
        btnSquareRoot = findViewById(R.id.btn_squareroot);
        btnPowerOf = findViewById(R.id.btn_powerof);
        btnReset = findViewById(R.id.btn_reset);
        listvOldResults = findViewById(R.id.lv_present_result);
        txtvResult = findViewById(R.id.txtv_result);
        txtvExpressions = findViewById(R.id.txtv_equation);

        btn0.setWidth(btnWidth);
        btn1.setWidth(btnWidth);
        btn2.setWidth(btnWidth);
        btn3.setWidth(btnWidth);
        btn4.setWidth(btnWidth);
        btn5.setWidth(btnWidth);
        btn6.setWidth(btnWidth);
        btn7.setWidth(btnWidth);
        btn8.setWidth(btnWidth);
        btn9.setWidth(btnWidth);
        btnDot.setWidth(btnWidth);
        btnDel.setWidth(btnWidth);
        btnEqual.setWidth(btnWidth);
        btnPlus.setWidth(btnWidth);
        btnMinus.setWidth(btnWidth);
        btnMultiply.setWidth(btnWidth);
        btnDivision.setWidth(btnWidth);
        btnCloseRoundBrackets.setWidth(btnWidth);
        btnOpenRoundBrackets.setWidth(btnWidth);
        btnFactorial.setWidth(btnWidth);
        btnPowerOf.setWidth(btnWidth);
        btnSquareRoot.setWidth(btnWidth);
        btnPercent.setWidth(btnWidth);
        btnReset.setWidth(btnWidth);

        btn0.setHeight(btnWidth*86/100);
        btn1.setHeight(btnWidth*86/100);
        btn2.setHeight(btnWidth*86/100);
        btn3.setHeight(btnWidth*86/100);
        btn4.setHeight(btnWidth*86/100);
        btn5.setHeight(btnWidth*86/100);
        btn6.setHeight(btnWidth*86/100);
        btn7.setHeight(btnWidth*86/100);
        btn8.setHeight(btnWidth*86/100);
        btn9.setHeight(btnWidth*86/100);
        btnDot.setHeight(btnWidth*86/100);
        btnDel.setHeight(btnWidth*86/100);
        btnEqual.setHeight(btnWidth*86/100);
        btnPlus.setHeight(btnWidth*86/100);
        btnMinus.setHeight(btnWidth*86/100);
        btnMultiply.setHeight(btnWidth*86/100);
        btnDivision.setHeight(btnWidth*86/100);
        btnCloseRoundBrackets.setHeight(btnWidth*86/100);
        btnOpenRoundBrackets.setHeight(btnWidth*86/100);
        btnFactorial.setHeight(btnWidth*86/100);
        btnPowerOf.setHeight(btnWidth*86/100);
        btnSquareRoot.setHeight(btnWidth*86/100);
        btnPercent.setHeight(btnWidth*86/100);
        btnReset.setHeight(btnWidth*86/100);

        if (btnWidth>=260) {
            btnTextSize = 36;
        } else if (btnWidth>=100) {
            btnTextSize =22;
        }

        btn0.setTextSize(btnTextSize);
        btn1.setTextSize(btnTextSize);
        btn2.setTextSize(btnTextSize);
        btn3.setTextSize(btnTextSize);
        btn4.setTextSize(btnTextSize);
        btn5.setTextSize(btnTextSize);
        btn6.setTextSize(btnTextSize);
        btn7.setTextSize(btnTextSize);
        btn8.setTextSize(btnTextSize);
        btn9.setTextSize(btnTextSize);
        btnDot.setTextSize(btnTextSize);
        btnDel.setTextSize(btnTextSize);
        btnEqual.setTextSize(btnTextSize);
        btnPlus.setTextSize(btnTextSize);
        btnMinus.setTextSize(btnTextSize);
        btnMultiply.setTextSize(btnTextSize);
        btnDivision.setTextSize(btnTextSize);
        btnCloseRoundBrackets.setTextSize(btnTextSize);
        btnOpenRoundBrackets.setTextSize(btnTextSize);
        btnFactorial.setTextSize(btnTextSize);
        btnPowerOf.setTextSize(btnTextSize);
        btnSquareRoot.setTextSize(btnTextSize);
        btnPercent.setTextSize(btnTextSize);
        btnReset.setTextSize(btnTextSize);
    }

    private void handleUI() {
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivision.setOnClickListener(this);
        btnPercent.setOnClickListener(this);
        btnOpenRoundBrackets.setOnClickListener(this);
        btnCloseRoundBrackets.setOnClickListener(this);
        btnFactorial.setOnClickListener(this);
        btnSquareRoot.setOnClickListener(this);
        btnPowerOf.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }

    private boolean validOperator() {

        return txtvExpressions.getText().toString().length() != 0
                && !txtvExpressions.getText().toString().endsWith(".")
                && !txtvExpressions.getText().toString().endsWith("+")
                && !txtvExpressions.getText().toString().endsWith("-")
                && !txtvExpressions.getText().toString().endsWith("x")
                && !txtvExpressions.getText().toString().endsWith(":")
                && !txtvExpressions.getText().toString().endsWith("√")
                && !txtvExpressions.getText().toString().endsWith("^");
    }

    private String roundResult(String strResult) {

        if (strResult.length() - strResult.lastIndexOf(".") - 2 == 0) {
            return String.valueOf(Math.round(Double.parseDouble(strResult)));
        }

        return String.valueOf(Math.ceil(Double.parseDouble(strResult) * 10000) / 10000);
    }

    private void handleNumberBtnPressed(Button btn) {

        if (!txtvResult.getText().toString().isEmpty()) {
            txtvResult.setText("");
        }

        if (btn.equals(btnDot)) {

            if (validOperator()) {
                txtvExpressions.setText(txtvExpressions.getText().toString().concat(btn.getText().toString()));
            }

        } else {
            txtvExpressions.setText(txtvExpressions.getText().toString().concat(btn.getText().toString()));
        }
    }

    @SuppressLint("SetTextI18n")
    private void handleFunctionBtnPressed(Button btn) {
        if (btnEqual.equals(btn)) {
            try {
                strExpressions = handleStrExpressions(txtvExpressions.getText().toString());
                if (strExpressions != null) {
                    txtvResult.setText(roundResult(handleOperators(strExpressions)));
                }
                oldResults.add(txtvExpressions.getText().toString() + "=" + txtvResult.getText().toString());
                txtvExpressions.setText("");
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
                txtvResult.setText("LỖI BIỂU THỨC!!");
            }
        } else if (btnDel.equals(btn)) {
            txtvExpressions.setText(txtvExpressions.getText().toString().substring(0, txtvExpressions.getText().length() - 1));
        } else if (btnReset.equals(btn)) {
            txtvResult.setText("");
            txtvExpressions.setText("");
        }
    }

    private void handleOperatorBtnPressed(Button btn) {
        if (validOperator() && !btn.equals(btnOpenRoundBrackets) && !btn.equals(btnCloseRoundBrackets)) {
            txtvExpressions.setText(txtvExpressions.getText().toString().concat(btn.getText().toString()));
        } else if (btn.equals(btnOpenRoundBrackets) || btn.equals(btnCloseRoundBrackets)) {
            txtvExpressions.setText(txtvExpressions.getText().toString().concat(btn.getText().toString()));
        }
    }


    //calculating process
    private static boolean isNumber(String checkedStr) {

        switch (checkedStr) {
            //Operator
            case "x": //multiplication
            case ":": //division
            case "+": //addition
            case "-": //subtraction
            case "%": //percent
            case "!": //factorial
            case "√": //squareroot
            case "^": //powerof
            case "(":
            case ")":
                return false;
        }

        return true;
    }

    private static boolean checkStrExpressionsIsEnd(String strExpressions) {
        switch (strExpressions.charAt(strExpressions.length()-1)) {
            case '+':
            case '-':
            case 'x':
            case ':':
            case '(':
            case '√':
            case '^':
                return false;
        }
        return true;
    }

    private static ArrayList<String> handleStrExpressions(String strEquation) {

        ArrayList<String> listParts = new ArrayList<>();

        if (!checkStrExpressionsIsEnd(strEquation)) {
            return null;
        } else {
            String num = "";

            for (int i = 0; i < strEquation.length(); i++) {

                if (!isNumber(String.valueOf(strEquation.charAt(i)))) {
                    listParts.add(num);
                    listParts.add(String.valueOf(strEquation.charAt(i)));
                    num = "";
                } else {
                    if (i==strEquation.length()-1){
                        num = num.concat(String.valueOf(strEquation.charAt(i)));
                        listParts.add(num);
                    } else {
                        num = num.concat(String.valueOf(strEquation.charAt(i)));
                    }
                }
            }

            listParts.removeAll(Collections.singleton(""));
        }
        return listParts;
    }

    private static String handleOperator(String strNum1, String strOperator, String strNum2) {

        double result = 0;
        double num1 = Double.parseDouble(strNum1.trim());
        double num2 = Double.parseDouble(strNum2.trim());

        switch (strOperator) {
            case "√":
                result = Math.sqrt(num1);
                break;
            case "^":
                result = 1;
                for (int i = 1; i <= num1; i++) {
                    result *= num2;
                }
                break;
            case "!":
                result = 1;
                for (int i = 2; i <= num1; i++) {
                    result *= i;
                }
                break;
            case "%":
                result = num1/100.0;
                break;
            case "x":
                result = num2*num1;
                break;
            case ":":
                result = num2/num1;
                break;
            case "+":
                result = num2+num1;
                break;
            case "-":
                result = num2-num1;
                break;
        }

        return String.valueOf(result);
    }

    private static int priorityLevel(String str)
    {
        switch (str) {
            case "√":
            case "^":
            case "!":
            case "%":
                return 3;
            case "x":
            case ":":
                return 2;
            case "+":
            case "-":
                return 1;
            case "(":
            case ")":
                return 0;
        }
        return -1;
    }

    private static String handleOperators(ArrayList<String> listParts) {

        Stack<String> stackOperators = new Stack<>();
        Stack<String> stackNumbers = new Stack<>();

        if (listParts.size()==1) return listParts.get(0);

        System.out.println("calculating...");
        System.out.println("listParts: " + listParts);

        for (String part : listParts) {
            System.out.println("\n\npart: " + part);

            if (isNumber(part)) {

                System.out.println("if: isNum");
                stackNumbers.push(part);

            } else {
//            } else if (!isNumber(part)) {
                System.out.println("if: !isNum");

                String num1, num2;
                String operator;

                switch (part) {

                    case "(":
                        stackOperators.push(part);
                        break;

                    case "!":
                    case "%":
                        stackOperators.push(part);
                        System.out.println("\ncalculating when " + part);

                        System.out.println("stackNumbers: " + stackNumbers);
                        System.out.println("stackOperators: " + stackOperators);

                        num1 = stackNumbers.pop();
                        System.out.println("num 1: " + num1);
                        System.out.println("stackNumbers: " + stackNumbers);

                        operator = stackOperators.pop();
                        System.out.println("operator: " + operator);
                        System.out.println("stackOperators: " + stackOperators);

                        stackNumbers.push(handleOperator(num1, operator, "0"));
                        break;

                    case "^":
                    case "√":
                        stackOperators.push(part);
                        if (part.equals("^")) System.out.println("\nadd ^");
                        else System.out.println("\nadd √");

                        break;

                    case ")":
                        while (!stackOperators.peek().equals("(")) {

                            System.out.println("\ncalculating when )");

                            System.out.println("stackNumbers: " + stackNumbers);
                            System.out.println("stackOperators: " + stackOperators);

                            num1 = stackNumbers.pop();
                            System.out.println("num 1: " + num1);
                            System.out.println("stackNumbers: " + stackNumbers);

                            operator = stackOperators.pop();
                            System.out.println("operator: " + operator);
                            System.out.println("stackOperators: " + stackOperators);

                            num2 = stackNumbers.pop();
                            System.out.println("num 2: " + num2);
                            System.out.println("stackNumbers: " + stackNumbers);

                            stackNumbers.push(handleOperator(num1, operator, num2));
                        }

                        if (stackOperators.peek().equals("(")) {
                            System.out.println("remove (");
                            stackOperators.remove(stackOperators.lastIndexOf("("));
                            break;
                        }
                        break;
                    case "+":
                    case "-":
                    case "x":
                    case ":":
                        while (!stackOperators.isEmpty() && priorityLevel(part) <= priorityLevel(stackOperators.peek())) {
                            System.out.println("\ncalculating when " + part);

                            System.out.println("stackNumbers: " + stackNumbers);
                            System.out.println("stackOperators: " + stackOperators);

                            num1 = stackNumbers.pop();
                            System.out.println("num 1: " + num1);
                            System.out.println("stackNumbers: " + stackNumbers);

                            operator = stackOperators.pop();
                            System.out.println("operator: " + operator);
                            System.out.println("stackOperators: " + stackOperators);

                            num2 = stackNumbers.pop();
                            System.out.println("num 2: " + num2);
                            System.out.println("stackNumbers: " + stackNumbers);

                            stackNumbers.push(handleOperator(num1, operator, num2));
                        }
                        stackOperators.push(part);
                        break;
                }
            }

            System.out.println("stackNumbers: " + stackNumbers);
            System.out.println("stackOperators: " + stackOperators);

        }

        while (!stackOperators.isEmpty()) {

            System.out.println("\ncalculating others");
            System.out.println("stackNumbers: " + stackNumbers);
            System.out.println("stackOperators: " + stackOperators);

            String num1 = stackNumbers.pop();
            System.out.println("num 1: " + num1);
            System.out.println("stackNumbers: " + stackNumbers);

            String operator = stackOperators.pop();
            System.out.println("operator: " + operator);
            System.out.println("stackOperators: " + stackOperators);

            String num2 = stackNumbers.pop();
            System.out.println("num 2: " + num2);
            System.out.println("stackNumbers: " + stackNumbers);

            stackNumbers.push(handleOperator(num1, operator, num2));
        }

        System.out.println("\nresult: " + stackNumbers.peek());
        System.out.println("done");

        return stackNumbers.pop();
    }

    @Override
    public void onClick(View v) {
        Button btn = findViewById(v.getId());
        if (btn0.equals(btn) || btn1.equals(btn) || btn2.equals(btn) || btn3.equals(btn)
                || btn4.equals(btn) || btn5.equals(btn) || btn6.equals(btn) || btn7.equals(btn)
                || btn8.equals(btn) || btn9.equals(btn) || btnDot.equals(btn)) {
            handleNumberBtnPressed(btn);
        }

        else if (btnDel.equals(btn) || btnEqual.equals(btn) || btnReset.equals(btn)) {
            handleFunctionBtnPressed(btn);
        }

        else if (btnPlus.equals(btn) || btnMinus.equals(btn) || btnMultiply.equals(btn)
                || btnDivision.equals(btn) || btnOpenRoundBrackets.equals(btn)
                || btnCloseRoundBrackets.equals(btn) || btnFactorial.equals(btn)
                || btnSquareRoot.equals(btn) || btnPowerOf.equals(btn) || btnPercent.equals(btn)) {
            handleOperatorBtnPressed(btn);
        }

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

}