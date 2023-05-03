package com.example.viajecitosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;

public class MainActivity extends AppCompatActivity {

    private EditText et_nombre;
    private Button bt_jugar;
    private TextView tv1,tv_nuestro,tv_record;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);



        et_nombre = (EditText) findViewById(R.id.et_nombre);
        String nombre = et_nombre.getText().toString();



    }
    public void jugar(View view) {
        String nombre = et_nombre.getText().toString();

        if (nombre.isEmpty()) {
            Toast.makeText(this, "debes introducir tu nombre", Toast.LENGTH_SHORT).show();

        } else {
            Intent jugar = new Intent(this,com.example.viajecitosapp.Nivel1.class);
            jugar.putExtra("nombre", et_nombre.getText().toString());
            startActivity(jugar);
        }
    }

}