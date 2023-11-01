package com.example.proyectomusicstore_angely_jensy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.Dialog;

import android.view.View;
import android.widget.Button;

public class botonpruebaa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botonpruebaa);


            Button btnMostrarDialog = findViewById(R.id.btnMostrarDialog);
            btnMostrarDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mostrarDialog();
                }
            });
        }




        private void mostrarDialog() {
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
    }
}