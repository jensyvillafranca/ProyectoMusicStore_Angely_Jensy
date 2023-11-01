package com.example.proyectomusicstore_angely_jensy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class activity_codigoverificacion_crearcuenta extends AppCompatActivity {

    /*Declaración de variables*/
    Button btnRecuperarRegistrarse;

    EditText txtRecuperarRegistrarse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigoverificacion_crearcuenta);


        /*Amarrando los valores de las variables al objeto de la interfaz*/
        btnRecuperarRegistrarse = (Button) findViewById(R.id.btnRecuperarRegistrarse);
        txtRecuperarRegistrarse = (EditText) findViewById(R.id.txtRecuperarRegistrarse);


        btnRecuperarRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //la función validar permite validar cuando los campos están vacios y lanza una alerta
                if(validar() == true){

                    /*Aquí debe de ir el llamado a la ventana donde se encuentra el menú desplegable*/

                }else{
                    /*Aquí debe de mostrarse el mensaje personalizado*/
                }
            }
        });
    }
    public boolean validar(){
        boolean retorna = true;
        if(txtRecuperarRegistrarse.getText().toString().isEmpty()){
            txtRecuperarRegistrarse.setError("Confirmación de código vacío");
            retorna = false;
        }
        return retorna;
    }
}