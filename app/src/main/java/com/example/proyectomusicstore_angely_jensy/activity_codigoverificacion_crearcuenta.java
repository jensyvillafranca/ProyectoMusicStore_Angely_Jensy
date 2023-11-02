package com.example.proyectomusicstore_angely_jensy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;


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



        /*Expresión regular para que ingrese únicamente números en el textview de código*/
        InputFilter codigoVerificacion = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                // Utiliza la expresión regular para filtrar la entrada
                if (source != null && !source.toString().matches("^[0-9]+$")) {
                    return "";
                }
                return null;
            }
        };
        txtRecuperarRegistrarse.setFilters(new InputFilter[]{codigoVerificacion});
    }
    public boolean validar(){
        boolean retorna = true;
        if(txtRecuperarRegistrarse.getText().toString().isEmpty()){
            txtRecuperarRegistrarse.setError("Confirmación de código vacío");
            retorna = false;
        }
        if(txtRecuperarRegistrarse.getText().toString().length() < 6 || txtRecuperarRegistrarse.getText().toString().length() > 6){
            txtRecuperarRegistrarse.setError("Código de verificación incompleto");
            retorna = false;
        }
        return retorna;
    }
}