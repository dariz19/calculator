package com.test.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv, solutionTv;
    MaterialButton btnC,btnOpenBracket,btnCloseBracket,btnDivide,btnDot;
    MaterialButton btnMultiply,btnAdd,btnSubtract,btnEquals,buttonAC;
    MaterialButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignId(btnC,R.id.button_c);
        assignId(btnOpenBracket,R.id.button_open_bracket);
        assignId(btnCloseBracket,R.id.button_close_bracket);
        assignId(btnDivide,R.id.button_divide);
        assignId(btnMultiply,R.id.button_multiply);
        assignId(btnDot,R.id.button_dot);
        assignId(btnAdd,R.id.button_add);
        assignId(btnSubtract,R.id.button_subtract);
        assignId(btnEquals,R.id.button_equals);
        assignId(buttonAC,R.id.button_ac);
        assignId(btn1,R.id.button_1);
        assignId(btn2,R.id.button_2);
        assignId(btn3,R.id.button_3);
        assignId(btn4,R.id.button_4);
        assignId(btn5,R.id.button_5);
        assignId(btn6,R.id.button_6);
        assignId(btn7,R.id.button_7);
        assignId(btn8,R.id.button_8);
        assignId(btn9,R.id.button_9);
        assignId(btn0,R.id.button_0);
    }

    void assignId (MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if (buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("");
            return;
        }
        if (buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }

        if (buttonText.equals("c")){
            if (dataToCalculate == ""){
                dataToCalculate = "";
            }
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        } else {
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("err")){
            resultTv.setText(finalResult);
        }
    }

    String getResult (String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        } catch (Exception ex){
            return "err";
        }
    }
}