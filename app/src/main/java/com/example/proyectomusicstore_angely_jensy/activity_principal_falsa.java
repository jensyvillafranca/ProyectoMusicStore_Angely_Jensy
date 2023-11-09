package com.example.proyectomusicstore_angely_jensy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_principal_falsa extends AppCompatActivity {
    Button cerrarSesion;
    private token acceso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_falsa);

        cerrarSesion = findViewById(R.id.btnCerrarSesion);
        acceso = new token(this);
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion();
            }
        });
    }

    private void cerrarSesion() {
        acceso.borrarToken();
        // Regresar al usuario a la pantalla de inicio de sesión
        Intent intent = new Intent(this, activity_login.class);
        startActivity(intent);
        finish();
    }
}