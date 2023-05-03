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

public class NivelFinal extends AppCompatActivity {
    private TextView tv_nombre, tv_score, respuesta, tv_vidas;
    private RadioButton rb_opcion1, rb_opcion2, rb_opcion3;
    private ImageView iv_banderas, iv_vidas;
    SoundPool sp;
    int sonidodeacierto,sonidodefallo,sonidowow,sonidoderrota;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel_final);
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

        int puntosInt = getIntent().getIntExtra("puntos", 40);
        if (puntosInt == 40) {
            tv_score.setText("40");
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

        ArrayList<String> mundo = new ArrayList<String>();
        int mundo_bandera[] = {R.drawable.australia,R.drawable.spain,R.drawable.italia,R.drawable.argelia, R.drawable.egipto, R.drawable.kenya, R.drawable.sudafrica, R.drawable.tunez,R.drawable.polonia, R.drawable.monaco, R.drawable.dinamarca, R.drawable.noruega, R.drawable.suiza,R.drawable.emiratosarabesunidos, R.drawable.kubait, R.drawable.malasya, R.drawable.jordania, R.drawable.nuevazelanda,R.drawable.chile, R.drawable.cuba, R.drawable.mexico, R.drawable.ecuador, R.drawable.puertorico,};

        mundo.add("Australia");
        mundo.add("España");
        mundo.add("Italia");
        mundo.add("Argelia");
        mundo.add("Egipto");
        mundo.add("Kenya");
        mundo.add("Sudafrica");
        mundo.add("Tunez");
        mundo.add("Polonia");
        mundo.add("Monaco");
        mundo.add("Dinamarca");
        mundo.add("Noruega");
        mundo.add("Suiza");
        mundo.add("EmiratosArabes");
        mundo.add("Kubait");
        mundo.add("Malasya");
        mundo.add("Jordania");
        mundo.add("NuevaZelanda");
        mundo.add("Chile");
        mundo.add("Cuba");
        mundo.add("Mexico");
        mundo.add("Ecuador");
        mundo.add("Puertorico");


        int n_bandera = numeroAleatorio("pais", mundo.size());

        ArrayList<Integer> elegido = new ArrayList<Integer>();
        elegido.add(n_bandera);

        int i = 1;
        while (i < 23) {
            n_bandera = numeroAleatorio("pais", mundo.size());
            if (!elegido.contains(n_bandera)) {
                elegido.add(n_bandera);
                i++;
            }
        }
        iv_banderas.setImageResource(mundo_bandera[elegido.get(0)]);
        respuesta.setText(mundo.get(elegido.get(0)));

        int posicion_rb = numeroAleatorio("rb", 3);

        if (posicion_rb == 1) {
            rb_opcion1.setText(mundo.get(elegido.get(0)));
            rb_opcion2.setText(mundo.get(elegido.get(1)));
            rb_opcion3.setText(mundo.get(elegido.get(2)));

        } else if (posicion_rb == 2) {
            rb_opcion1.setText(mundo.get(elegido.get(2)));
            rb_opcion2.setText(mundo.get(elegido.get(0)));
            rb_opcion3.setText(mundo.get(elegido.get(1)));

        } else if (posicion_rb == 3) {
            rb_opcion1.setText(mundo.get(elegido.get(1)));
            rb_opcion2.setText(mundo.get(elegido.get(2)));
            rb_opcion3.setText(mundo.get(elegido.get(0)));
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
        int scoreInt = 40;

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

            Intent nivelFinal = new Intent(this, NivelFinal.class);
            nivelFinal.putExtra("puntos", scoreInt);
            nivelFinal.putExtra("nvidas", vidasInt);
            nivelFinal.putExtra("nombre",viajero);
            startActivity(nivelFinal);

            if (scoreInt == 100) {
                Toast.makeText(this, "LLegaste al destino", Toast.LENGTH_SHORT).show();
                sp.play(sonidowow,1,1,1,0,1);


                Intent siguiente = new Intent(this, com.example.viajecitosapp.PantallaFinal.class);
                siguiente.putExtra("nombre", tv_nombre.getText().toString());
                startActivity(siguiente);
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