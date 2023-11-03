package com.example.proyectomusicstore_angely_jensy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

public class botonpruebaa extends AppCompatActivity {

    Button PruebaSeguir;
    Button MostrarDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botonpruebaa);
        PruebaSeguir=findViewById(R.id.btnMostrarSeguirUsuario);
        MostrarDialog=findViewById(R.id.btnMostrarDialog);
        PruebaSeguir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent PruebaSeguir = new Intent(getApplicationContext(),ActivityUsuarioBuscar.class);
                startActivity(PruebaSeguir);
            }
        });

        MostrarDialog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent MostrarDialog = new Intent(getApplicationContext(), activity_personalizado_advertencia.class);
                startActivity(MostrarDialog);
            }
        });


        Button MostrarDialog = findViewById(R.id.btnMostrarDialog);
        MostrarDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //mostrarDialog();
                }
            });
        }







       /* private void mostrarDialog() {
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.activity_dialog_layout);

            Button btnAceptar = dialog.findViewById(R.id.btnAceptar);
            btnAceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
    }*/



}