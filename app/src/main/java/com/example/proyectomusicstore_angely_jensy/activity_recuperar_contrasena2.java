package com.example.proyectomusicstore_angely_jensy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class activity_recuperar_contrasena2 extends AppCompatActivity {

    /*Declaración de variables*/
    Button btnRecuperarVerificar;

    EditText txtRecuperarVerificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena2);

        /*Amarrando los valores de las variables al objeto de la interfaz*/
        btnRecuperarVerificar = (Button) findViewById(R.id.btnRecuperarVerificar);
        txtRecuperarVerificar = (EditText) findViewById(R.id.txtRecuperarVerificar);

        btnRecuperarVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //la función validar permite validar cuando los campos están vacios y lanza una alerta
                if(validar() == true){
                    /*Mandar a llamar la siguiente ventana donde se ingresara la nueva contraseña*/
                    Intent contrasenaNueva = new Intent(getApplicationContext(),activity_recuperar_contrasena3.class);
                    startActivity(contrasenaNueva);

                }else{
                    /*Aquí debe de mostrarse el mensaje personalizado*/
                }
            }
        });
    }

    public boolean validar(){
        boolean retorna = true;
        if(txtRecuperarVerificar.getText().toString().isEmpty()){
            txtRecuperarVerificar.setError("Confirmación de correo vacío");
            retorna = false;
        }
        return retorna;
    }
}
