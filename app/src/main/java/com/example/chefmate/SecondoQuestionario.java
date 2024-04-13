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

public class SecondoQuestionario extends AppCompatActivity implements View.OnClickListener {

    private Button prosegui,circle1,circle2,circle3;
    private ToggleButton lattosio,celiachia;
    private static final int STEP=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_secondo_questionario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.secondo_questionario), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        circle1=findViewById(R.id.circle1_sec);
        circle2=findViewById(R.id.circle2_sec);
        circle3=findViewById(R.id.circle3_sec);
        lattosio=findViewById(R.id.lattosio);
        celiachia=findViewById(R.id.celiachia);
        prosegui=findViewById(R.id.vaiadomanda3);
        Circle circle=new Circle(circle1,circle2,circle3);
        circle.stateCircle(STEP);

        // definisco un oggetto listener per gestire i campbiamenti di stato dei togglebutton
        CompoundButton.OnCheckedChangeListener toggleListener=new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //se almeno uno dei toggle é attivo, si può proseguire alla pagina succ. del questionario
                if(lattosio.isChecked()||celiachia.isChecked()){
                    prosegui.setEnabled(true);
                }else{
                    prosegui.setEnabled(false);
                }
            }
        };

        lattosio.setOnCheckedChangeListener(toggleListener);
        celiachia.setOnCheckedChangeListener(toggleListener);
        circle1.setOnClickListener(this);
        circle2.setOnClickListener(this);
        circle3.setOnClickListener(this);
        prosegui.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Button clickedButton=(Button) v;

        if(clickedButton.getId()==R.id.vaiadomanda3){
            //if(clickedButton.isSelected()){
            //updateCircleUI();
            Intent intent = new Intent(SecondoQuestionario.this,TerzoQuestionario.class);
            startActivity(intent);//avvia la seconda activity
            //}

        }else{
            clickedButton.setPressed(true);
        }

        if(clickedButton.getId()==R.id.circle1_sec){
            Intent intent = new Intent(SecondoQuestionario.this,PrimoQuestionario.class);
            startActivity(intent);//avvia la seconda activity
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("lattosio", lattosio.isChecked());
        editor.putBoolean("celiachia", celiachia.isChecked());
        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        lattosio.setChecked(sharedPref.getBoolean("lattosio",false));
        celiachia.setChecked(sharedPref.getBoolean("celiachia",false));
    }
}