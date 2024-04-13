package com.example.chefmate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PrimoQuestionario extends AppCompatActivity implements View.OnClickListener {

    private Button prosegui,circle1,circle2,circle3;
    private ToggleButton diabete,renali;
    private static final int STEP=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_primo_questionario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.prova), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //recupero le view
        diabete=findViewById(R.id.diabete);
        renali=findViewById(R.id.patorenali);
        prosegui=findViewById(R.id.vaiadomanda2);
        circle1=findViewById(R.id.circle1);
        circle2=findViewById(R.id.circle2);
        circle3=findViewById(R.id.circle3);
        Circle circle=new Circle(circle1,circle2,circle3);
        circle.stateCircle(STEP);


        if (savedInstanceState != null) {
            //restore degli stati salvati
            //countValue = savedInstanceState.getInt(SCORE_KEY,0)
            diabete.setChecked(savedInstanceState.getBoolean("diabete"));
        }





        // definisco un oggetto listener per gestire i campbiamenti di stato dei togglebutton
        CompoundButton.OnCheckedChangeListener toggleListener=new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //se almeno uno dei toggle é attivo, si può proseguire alla pagina succ. del questionario
                if(diabete.isChecked()||renali.isChecked()){
                    prosegui.setEnabled(true);
                }else{
                    prosegui.setEnabled(false);
                }
            }
        };

        diabete.setOnCheckedChangeListener(toggleListener);
        renali.setOnCheckedChangeListener(toggleListener);
        circle1.setOnClickListener(this);
        circle2.setOnClickListener(this);
        circle3.setOnClickListener(this);
        prosegui.setOnClickListener(this);







    }

    @Override
    public void onClick(View v) {
        Button clickedButton=(Button) v;

        if(clickedButton.getId()==R.id.vaiadomanda2){
            //if(clickedButton.isSelected()){
                //updateCircleUI();
                Intent intent = new Intent(PrimoQuestionario.this,SecondoQuestionario.class);
                startActivity(intent);//avvia la seconda activity
            //}

        }else{
            clickedButton.setPressed(true);
        }

    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putInt(SCORE_KEY,countValue)
        /*SCORE_KEY -> string key used to save counter value in bundle
        countValue -> integer value which contains actual value of counter
         */
        Log.i("save","sto in onsave");
        outState.putInt("boh",1);
        outState.putInt("wow",2);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState)  {
        super.onRestoreInstanceState(savedInstanceState);

        // countValue = savedInstanceState.getInt(SCORE_KEY,0)
        Log.i("restore","sto in onrestore");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("pausa","sto in onpause");
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("diabete", diabete.isChecked());
        editor.putBoolean("renali", renali.isChecked());
        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("stop","sto in onstop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        diabete.setChecked(sharedPref.getBoolean("diabete",false));
        renali.setChecked(sharedPref.getBoolean("renali",false));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("resume","sto in onresume");
    }

    @Override
    protected void onRestart() {
        Log.i("restart","sto in onrestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("restart","sto in onrestart");
    }
}