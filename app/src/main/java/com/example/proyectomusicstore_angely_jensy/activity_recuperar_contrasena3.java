package com.example.proyectomusicstore_angely_jensy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class activity_recuperar_contrasena3 extends AppCompatActivity {

    /*Declaración de variables*/
    Button btnRecuperarRestaurar;

    EditText txtRecuperarNuevoPass, txtRecuperarConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena3);

        /*Amarrando los valores de las variables al objeto de la interfaz*/
        /*Botón*/
        btnRecuperarRestaurar = (Button) findViewById(R.id.btnRecuperarRestaurar);

        /*Textfields*/
        txtRecuperarNuevoPass = (EditText) findViewById(R.id.txtRecuperarNuevoPass);
        txtRecuperarConfirmar = (EditText) findViewById(R.id.txtRecuperarConfirmar);

        btnRecuperarRestaurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //la función validar permite validar cuando los campos están vacios y lanza una alerta
                if(validar() == true){

                }else{
                    /*Aquí debe de mostrarse el mensaje personalizado*/
                }
            }
        });
    }

    public boolean validar(){
        boolean retorna = true;
        if(txtRecuperarNuevoPass.getText().toString().isEmpty()){
            txtRecuperarNuevoPass.setError("Campo nueva contraseña vacio");
            retorna = false;
        }
        if(txtRecuperarConfirmar.getText().toString().isEmpty()){
            txtRecuperarConfirmar.setError("Confirmación de contraseña vacío");
            retorna = false;
        }
        return retorna;
    }
}
