package com.example.proyectomusicstore_angely_jensy;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;



public class MainActivity extends AppCompatActivity {

    private ImageView gifImageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gifImageView = findViewById(R.id.gifImageView);
        progressBar = findViewById(R.id.progressBar);



        // Cargar el GIF con Glide
        Glide.with(this)
                .asGif()
                .load(R.drawable.cargando)
                .into(gifImageView);

        // Simular una carga
        simulateLoading();

        progressBar.setVisibility(View.INVISIBLE);

    }



    private void simulateLoading() {
        // Simular un proceso de carga (por ejemplo, 3 segundos)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Ocultar el GIF y el ProgressBar
                gifImageView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);

                // Abrir la actividad activity_principal
                Intent intent = new Intent(MainActivity.this, botonpruebaa.class);
                startActivity(intent);
                finish(); // Cierra la actividad actual
            }
        }, 5000); // 5000 milisegundos (5 segundos)
    }
}