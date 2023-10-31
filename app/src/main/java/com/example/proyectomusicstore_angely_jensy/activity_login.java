package com.example.proyectomusicstore_angely_jensy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class activity_login extends AppCompatActivity {

    /*Declaración de botones y textfields*/
    Button btnLoginEntrar;
    EditText txtLoginUsuario, txtLoginPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        /*Amarrando la variable de tipo edit text al objeto de la interfaz de usuario*/

        btnLoginEntrar = (Button) findViewById(R.id.btnLoginEntrar);
        txtLoginUsuario = (EditText) findViewById(R.id.txtLoginUsuario);
        txtLoginPass = (EditText) findViewById(R.id.txtLoginPassword);


        /*Evento de click para el botón de entrar*/
        btnLoginEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Llamado a la función de validar, encargada de saber si el campo de usuario y/o contraseña están vacíos*/
                validar();
            }
        });

    }


    public boolean validar(){
        boolean retorna = true;

        if(txtLoginUsuario.getText().toString().isEmpty()){
            txtLoginUsuario.setError("Campo usuario inválido");
            retorna = false;
        }
        if(txtLoginPass.getText().toString().isEmpty()){
            txtLoginPass.setError("Campo password inválido");
            retorna = false;
        }
        return retorna;
    }



}