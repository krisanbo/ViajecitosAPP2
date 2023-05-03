package com.example.viajecitosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PantallaFinal extends AppCompatActivity {
    private TextView tv_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_final);

        tv_nombre= (TextView)findViewById(R.id.tv_nombre5);

        String viajero = getIntent().getStringExtra("nombre");
        tv_nombre.setText(viajero);


    }
    public void Navegar(View view){
        Intent i= new Intent(this,Web.class);
        startActivity(i);
    }
}