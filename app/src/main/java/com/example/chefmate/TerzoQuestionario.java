package com.example.chefmate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TerzoQuestionario extends AppCompatActivity implements View.OnClickListener{
    private Button prosegui,circle1,circle2,circle3;
    private ToggleButton fruttasecca,uova;
    private static final int STEP=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_terzo_questionario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.terzo_questionario), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        circle1=findViewById(R.id.circle1_ter);
        circle2=findViewById(R.id.circle2_ter);
        circle3=findViewById(R.id.circle3_ter);
        fruttasecca=findViewById(R.id.fruttasecca);
        uova=findViewById(R.id.uova);
        //prosegui=findViewById(R.id.);
        Circle circle=new Circle(circle1,circle2,circle3);
        circle.stateCircle(STEP);

        // definisco un oggetto listener per gestire i campbiamenti di stato dei togglebutton
        CompoundButton.OnCheckedChangeListener toggleListener=new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //se almeno uno dei toggle é attivo, si può proseguire alla pagina succ. del questionario
                if(fruttasecca.isChecked()||uova.isChecked()){
                    //prosegui.setEnabled(true);
                }else{
                    //prosegui.setEnabled(false);
                }
            }
        };

        fruttasecca.setOnCheckedChangeListener(toggleListener);
        uova.setOnCheckedChangeListener(toggleListener);
        //prosegui.setOnClickListener(this);
        circle1.setOnClickListener(this);
        circle2.setOnClickListener(this);
        circle3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button clickedButton=(Button) v;

       /* if(clickedButton.getId()==R.id.vaiadomanda3){
            //if(clickedButton.isSelected()){
            //updateCircleUI();
            //Intent intent = new Intent(TerzoQuestionario.this,TerzoQuestionario.class);
            //startActivity(intent);//avvia la seconda activity
            //}

        }else{
            clickedButton.setPressed(true);
        }*/

        if(clickedButton.getId()==R.id.circle2_ter){
            Intent intent = new Intent(TerzoQuestionario.this,SecondoQuestionario.class);
            startActivity(intent);//avvia la seconda activity
        }

        if(clickedButton.getId()==R.id.circle1_ter){
            Intent intent = new Intent(TerzoQuestionario.this,PrimoQuestionario.class);
            startActivity(intent);//avvia la seconda activity
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("fruttasecca", fruttasecca.isChecked());
        editor.putBoolean("uova", uova.isChecked());
        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        fruttasecca.setChecked(sharedPref.getBoolean("fruttasecca",false));
        uova.setChecked(sharedPref.getBoolean("uova",false));
    }
}