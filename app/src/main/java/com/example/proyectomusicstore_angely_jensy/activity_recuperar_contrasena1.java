package com.example.proyectomusicstore_angely_jensy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class activity_recuperar_contrasena1 extends AppCompatActivity {

    /*Declaración de variables*/
    Button btnRecuperarEnviar;

    EditText txtRecuperarEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena1);

        /*Amarrando los valores de las variables al objeto de la interfaz*/
        btnRecuperarEnviar = (Button) findViewById(R.id.btnRecuperarEnviar);
        txtRecuperarEnviar = (EditText) findViewById(R.id.txtRecuperarEnviar);

        btnRecuperarEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //la función validar permite validar cuando los campos están vacios y lanza una alerta
                if(validar() == true){

                    /*Mandar a llamar la siguiente ventana donde se ingresara el código de verificación*/
                    Intent codigoVerificacion = new Intent(getApplicationContext(),activity_recuperar_contrasena2.class);
                    startActivity(codigoVerificacion);
                }else{
                    /*Aquí debe de mostrarse el mensaje personalizado*/
                }
            }
        });
    }

    public boolean validar(){
        boolean retorna = true;
        if(txtRecuperarEnviar.getText().toString().isEmpty()){
            txtRecuperarEnviar.setError("Confirmación de correo vacío");
            retorna = false;
        }
        return retorna;
    }
}
