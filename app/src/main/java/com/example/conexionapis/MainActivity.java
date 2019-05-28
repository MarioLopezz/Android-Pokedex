package com.example.conexionapis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private int start, end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        button5=findViewById(R.id.button5);
        button6=findViewById(R.id.button6);
        button7=findViewById(R.id.button7);
        button8=findViewById(R.id.button8);
        button9=findViewById(R.id.button9);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                start=0;
                end=1100;
                startPokedex();
                break;
            case R.id.button2:
                start=0;
                end=151;
                startPokedex();
                break;
            case R.id.button3:
                start=151;
                end=99;
                startPokedex();
                break;
            case R.id.button4:
                start=251;
                end=134;
                startPokedex();
                break;
            case R.id.button5:
                start=386;
                end=107;
                startPokedex();
                break;
            case R.id.button6:
                start=494;
                end=155;
                startPokedex();
                break;
            case R.id.button7:
                start=650;
                end=71;
                startPokedex();
                break;
            case R.id.button8:
                start=722;
                end=85;
                startPokedex();
                break;
            case R.id.button9:
                start=808;
                end=200;
                startPokedex();
                break;
            default:
                break;
        }
    }

    public void startPokedex(){
        Intent intent = new Intent(this, PokemonList.class);
        intent.putExtra("start",start);
        intent.putExtra("end",end);
        startActivity(intent);
    }
}
