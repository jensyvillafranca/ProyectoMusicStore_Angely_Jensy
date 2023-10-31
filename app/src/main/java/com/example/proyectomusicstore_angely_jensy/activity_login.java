package com.example.proyectomusicstore_angely_jensy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class activity_login extends AppCompatActivity {

    /*Declaración de variables*/
    EditText txtLoginUsuario, txtLoginPassword;

    TextView txtviewOlvidaPassword;
    Button btnLoginEntrar, btnLoginRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*Amarrando las variables con el elemento de la interfaz*/
        btnLoginEntrar = (Button) findViewById(R.id.btnLoginEntrar);
        btnLoginRegistrarse = (Button) findViewById(R.id.btnRegistrarCrear);

        txtLoginUsuario = (EditText) findViewById(R.id.txtLoginUsuario);
        txtLoginPassword = (EditText) findViewById(R.id.txtLoginPassword);
        txtviewOlvidaPassword= (TextView) findViewById(R.id.txtviewOlvidaPassword);


        /*Evento para el botón de entrar al sistema*/
        btnLoginEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //la función validar permite validar cuando los campos están vacios y lanza una alerta
                if(validar() == true){
                    //código de validar si el usuario y el password es correcto o no y dejarlo acceder a la pantalla de funcionalidades

                }else{
                    /*Aquí debe de mostrarse el mensaje personalizado*/
                }
            }
        });


        /*Evento para el botón de ir al formulario de registrarse*/
        btnLoginRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrate = new Intent(getApplicationContext(),activity_registrarse.class);
                startActivity(registrate);
            }
        });

        /*Evento para mandar a llamar la ventana de recuperar contraseña*/
        txtviewOlvidaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recuperarContra = new Intent(getApplicationContext(),activity_recuperar_contrasena1.class);
                startActivity(recuperarContra);
            }
        });
    }

    public boolean validar(){
        boolean retorna = true;
        if(txtLoginUsuario.getText().toString().isEmpty()){
            txtLoginUsuario.setError("Campo usuario vacío");
            retorna = false;
        }
        if(txtLoginPassword.getText().toString().isEmpty()){
            txtLoginPassword.setError("Campo Password vacío");
            retorna = false;
        }
        return retorna;
}
}