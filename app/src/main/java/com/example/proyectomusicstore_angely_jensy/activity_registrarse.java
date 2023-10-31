package com.example.proyectomusicstore_angely_jensy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class activity_registrarse extends AppCompatActivity {

    /*Asignación de variables*/
    EditText nombres, apellidos,correo_electronico,usuario,password,confirmar_password;
    Button btn_insertar;
    String token = "Nulo";
    String enlaceFoto = "Nulo";
    int idVisualizacion = 1;

    TextView txtviewRegistrarCuentaCreada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        /*Relacionando las variables con el objeto de la interfaz*/
        nombres = (EditText) findViewById(R.id.txtLoginUsuario);
        apellidos = (EditText) findViewById(R.id.txtRegistrarApellidos);
        correo_electronico = (EditText) findViewById(R.id.txtRegistrarCorreo);
        usuario = (EditText) findViewById(R.id.txtRegistrarUsuario);
        password = (EditText) findViewById(R.id.txtRegistrarPassword);
        confirmar_password = (EditText) findViewById(R.id.txtRegistrarConfirmarPassword);
        btn_insertar = (Button) findViewById(R.id.btnRegistrarEntrar);
        txtviewRegistrarCuentaCreada = (TextView) findViewById(R.id.txtviewRegistrarCuentaCreada);


        /*Evento del boton de crear la cuenta, en donde debe de mandar a llamar la ventana de verificar el correo electrónico*/
        btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //la función validar permite validar cuando los campos están vacios y lanza una alerta
                if(validar() == true){
                    /*Manda a llamar la ventana de verificación de correo*/
                    Intent verificarCorreo = new Intent(getApplicationContext(),activity_codigoverificacion_crearcuenta.class);
                    startActivity(verificarCorreo);
                }else{
                    /*Aquí debe de mostrarse el mensaje personalizado*/
                }


            }
        });

        /*Evento para regresar al login, desde el textview "Ya tengo una cuenta"*/
        txtviewRegistrarCuentaCreada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Levantar un item para poder invocar la pantalla del login*/
                Intent login = new Intent(getApplicationContext(),activity_login.class);
                startActivity(login);
            }
        });
    }


    /*Validación para no dejar campos vacíos*/
    public boolean validar(){
        boolean retorna = true;

        if(nombres.getText().toString().isEmpty()){
            nombres.setError("Campo nombres vacío");
            retorna = false;
        }
        if(apellidos.getText().toString().isEmpty()){
            apellidos.setError("Campo apellidos vacío");
            retorna = false;
        }
        if(correo_electronico.getText().toString().isEmpty()){
            correo_electronico.setError("Campo correo electrónico vacío");
            retorna = false;
        }
        if(usuario.getText().toString().isEmpty()){
            usuario.setError("Campo usuario vacío");
            retorna = false;
        }
        if(password.getText().toString().isEmpty()){
            password.setError("Campo password vacío");
            retorna = false;
        }
        return retorna;
    }

}