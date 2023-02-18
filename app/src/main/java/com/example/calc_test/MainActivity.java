package com.example.calc_test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView resultTv, solutionTv;
    MaterialButton buttonAC, butonc, buttonClose, buttonOpen, buttonDot;
    MaterialButton buttonPlus, buttonMinus, buttonMultiply, buttonDivide, buttonEqual;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    Button btn_his;
    String history="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_main);
        }

        btn_his=findViewById(R.id.btn_his);
        btn_his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg=history;
                Intent intent =new Intent(getApplicationContext(), Act_history.class );
                intent.putExtra("username", msg);
                startActivity(intent);
            }
        });

        resultTv = findViewById(R.id.result);
        solutionTv = findViewById(R.id.solution);
        assignId(butonc,R.id.button_c);
        assignId(buttonAC,R.id.button_ac);
        assignId(buttonClose,R.id.button_close);
        assignId(buttonOpen,R.id.button_open);
        assignId(buttonDot,R.id.button_Dot);
        assignId(buttonPlus,R.id.button_plus);
        assignId(buttonMinus,R.id.button_minus);
        assignId(buttonMultiply,R.id.button_multyply);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonEqual,R.id.button_equal);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);


    }
    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataCalculate = solutionTv.getText().toString();
        //String msg=solutionTv.getText().toString();
        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            history+=solutionTv.getText().toString()+"="+resultTv.getText()+"_";
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C"))
        {
            dataCalculate = dataCalculate.substring(0, dataCalculate.length() - 1);
        }else{
            dataCalculate = dataCalculate+buttonText;
        }

        solutionTv.setText(dataCalculate);
        String finalResult = getResult(dataCalculate);
        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }
    }
    String getResult(String data)
    {
        try {

            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0"))
            {
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e)
        {  return "Err";}

    }
    @Override
    protected  void onRestoreInstanceState( Bundle savedInstanceState){
        super.onRestoreInstanceState((savedInstanceState));
        if(savedInstanceState.get("Count")!=null){
            solutionTv.setText(savedInstanceState.get("Count").toString());
        }
        if(savedInstanceState.get("Count2")!=null){
            resultTv.setText(savedInstanceState.get("Count2").toString());
        }
    }
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putString("Count",
                String.valueOf(solutionTv.getText()));
        outState.putString("Count2",
                String.valueOf(resultTv.getText()));
    }

}
