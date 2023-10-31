package com.example.proyectomusicstore_angely_jensy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class activity_principal extends AppCompatActivity {
    Button registrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        registrate =(Button)findViewById(R.id.btnRegistrarse);

        registrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrate = new Intent(getApplicationContext(),activity_registrarse.class);
                startActivity(registrate);
            }
        });
    }

}
