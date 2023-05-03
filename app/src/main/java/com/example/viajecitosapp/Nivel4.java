package com.example.viajecitosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Nivel4 extends AppCompatActivity {
    private TextView tv_nombre, tv_score, respuesta, tv_vidas;
    private RadioButton rb_opcion1, rb_opcion2, rb_opcion3;
    private ImageView iv_banderas, iv_vidas;
    SoundPool sp;
    int sonidodeacierto,sonidodefallo,sonidowow,sonidoderrota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel4);

        tv_nombre = (TextView) findViewById(R.id.tv_nombre4);
        tv_score = (TextView) findViewById(R.id.tv_score);
        rb_opcion1 = (RadioButton) findViewById(R.id.rb_opcion1);
        rb_opcion2 = (RadioButton) findViewById(R.id.rb_opcion2);
        rb_opcion3 = (RadioButton) findViewById(R.id.rb_opcion3);
        iv_banderas = (ImageView) findViewById(R.id.iv_banderas);
        respuesta = (TextView) findViewById(R.id.tv_invisible);
        iv_vidas = (ImageView) findViewById(R.id.iv_vidas);
        tv_vidas = (TextView) findViewById(R.id.tv_vidas);

        sp= new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        sonidodeacierto=sp.load(this,R.raw.aciertos,1);
        sonidodefallo=sp.load(this,R.raw.sonidofallo,1);
        sonidowow=sp.load(this,R.raw.wow,1);
        sonidoderrota=sp.load(this,R.raw.derrota,1);

        int puntosInt = getIntent().getIntExtra("puntos", 30);
        if (puntosInt == 30) {
            tv_score.setText("30");
        } else {
            String valor = String.valueOf(puntosInt);
            tv_score.setText(valor);
        }

        int vidasInt = getIntent().getIntExtra("nvidas", 3);
        if(vidasInt ==3){
            tv_vidas.setText("3");
        }else{
            String vidasString = Integer.toString(vidasInt);
            tv_vidas.setText(vidasString);
            if(vidasInt==2){
                iv_vidas.setImageResource(R.drawable.dosvidas);
            }
            if(vidasInt==1){
                iv_vidas.setImageResource(R.drawable.unavida);
            }
            if(vidasInt==0){
                iv_vidas.setImageResource(R.drawable.logo);
            }
        }

        String viajero = getIntent().getStringExtra("nombre");
        tv_nombre.setText(viajero);

        ArrayList<String> asia = new ArrayList<String>();
        int asia_bandera[] = {R.drawable.emiratosarabesunidos, R.drawable.kubait, R.drawable.malasya, R.drawable.jordania, R.drawable.nuevazelanda};
        asia.add("EmiratosArabes");
        asia.add("Kubait");
        asia.add("Malasya");
        asia.add("Jordania");
        asia.add("NuevaZelanda");

        int n_bandera = numeroAleatorio("pais", asia.size());

        ArrayList<Integer> elegido = new ArrayList<Integer>();
        elegido.add(n_bandera);

        int i = 1;
        while (i < 5) {
            n_bandera = numeroAleatorio("pais", asia.size());
            if (!elegido.contains(n_bandera)) {
                elegido.add(n_bandera);
                i++;
            }
        }
        iv_banderas.setImageResource(asia_bandera[elegido.get(0)]);
        respuesta.setText(asia.get(elegido.get(0)));

        int posicion_rb = numeroAleatorio("rb", 3);

        if (posicion_rb == 1) {
            rb_opcion1.setText(asia.get(elegido.get(0)));
            rb_opcion2.setText(asia.get(elegido.get(1)));
            rb_opcion3.setText(asia.get(elegido.get(2)));

        } else if (posicion_rb == 2) {
            rb_opcion1.setText(asia.get(elegido.get(2)));
            rb_opcion2.setText(asia.get(elegido.get(0)));
            rb_opcion3.setText(asia.get(elegido.get(1)));

        } else if (posicion_rb == 3) {
            rb_opcion1.setText(asia.get(elegido.get(1)));
            rb_opcion2.setText(asia.get(elegido.get(2)));
            rb_opcion3.setText(asia.get(elegido.get(0)));
        }
    }
    public static int numeroAleatorio(String tipo, int size) {
        int numero = 0;

        if (tipo.equals("rb")) {
            numero = (int) (Math.random() * size + 1);
        } else {
            numero = (int) ((Math.random()) * size);
        }
        return numero;
    }

    public void comprobar(View view) {
        String correcta = respuesta.getText().toString();
        String scoreString = tv_score.getText().toString();
        int scoreInt = 30;

        String seleccion = "";

        if (rb_opcion1.isChecked()) {
            seleccion = rb_opcion1.getText().toString();
        }
        if (rb_opcion2.isChecked()) {
            seleccion = rb_opcion2.getText().toString();
        }
        if (rb_opcion3.isChecked()) {
            seleccion = rb_opcion3.getText().toString();
        }

        String vidasString = tv_vidas.getText().toString();
        int vidasInt = Integer.parseInt(vidasString);
        String viajero=tv_nombre.getText().toString();

        if (correcta.equals(seleccion)) {
            Toast.makeText(this, "Respuesta correcta", Toast.LENGTH_SHORT).show();
            sp.play(sonidodeacierto,1,1,1,0,1);
            scoreInt = Integer.parseInt(scoreString);
            scoreInt += 2;

            Intent nivel4 = new Intent(this, Nivel4.class);
            nivel4.putExtra("puntos", scoreInt);
            nivel4.putExtra("nvidas", vidasInt);
            nivel4.putExtra("nombre",viajero);
            startActivity(nivel4);

            if (scoreInt == 40) {
                Toast.makeText(this, "Pasas de nivel", Toast.LENGTH_SHORT).show();
                sp.play(sonidowow,1,1,1,0,1);

                Intent siguiente3 = new Intent(this, com.example.viajecitosapp.NivelFinal.class);
                siguiente3.putExtra("nombre", tv_nombre.getText().toString());
                siguiente3.putExtra("puntos", tv_score.getText().toString());
                startActivity(siguiente3);
            }

        } else {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            sp.play(sonidodefallo,1,1,1,0,1);
            vidasInt -= 1;

            if (vidasInt < 0) {
                Toast.makeText(this, "has perdido la partida", Toast.LENGTH_SHORT).show();
                sp.play(sonidoderrota,1,1,1,0,1);
                Intent empezar = new Intent(this, MainActivity.class);
                startActivity(empezar);

            } else {
                Toast.makeText(this, "has perdido una vida", Toast.LENGTH_SHORT).show();
                vidasString = Integer.toString(vidasInt);
                tv_vidas.setText(vidasString);

                if (vidasInt == 3)  {
                    iv_vidas.setImageResource(R.drawable.tresvidas);
                }
                if (vidasInt == 2) {
                    iv_vidas.setImageResource(R.drawable.dosvidas);
                }
                if (vidasInt == 1) {
                    iv_vidas.setImageResource(R.drawable.unavida);
                }
                if (vidasInt == 0) {
                    iv_vidas.setImageResource(R.drawable.logo);

                }

            }
        }

    }
}