package com.example.proyectomusicstore_angely_jensy;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class activity_login extends AppCompatActivity {

    /*Declaración de variables*/
    EditText txtLoginUsuario, txtLoginPassword;

    TextView txtviewOlvidaPassword;
    Button btnLoginEntrar, btnLoginRegistrarse;
    String contraseniaParaClave = "programacionMovil1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*Amarrando las variables con el elemento de la interfaz*/
        btnLoginEntrar = (Button) findViewById(R.id.btnLoginEntrar);
        btnLoginRegistrarse = (Button) findViewById(R.id.btnRegistrarCrear);

        txtLoginUsuario = (EditText) findViewById(R.id.txtRegistrarUsuario);
        txtLoginPassword = (EditText) findViewById(R.id.txtLoginPassword);
        txtviewOlvidaPassword = (TextView) findViewById(R.id.txtviewOlvidaPassword);


        /*Evento para el botón de entrar al sistema*/
        btnLoginEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //la función validar permite validar cuando los campos están vacios y lanza una alerta
                if (validar() == true) {
                    //código de validar si el usuario y el password es correcto o no y dejarlo acceder a la pantalla de funcionalidades.
                    try {
                        validarUsuarioPassword();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    /*Aquí debe de mostrarse el mensaje personalizado*/
                    mensajesPersonalizadas();
                }
            }
        });


        /*Evento para el botón de ir al formulario de registrarse*/
        btnLoginRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrate = new Intent(getApplicationContext(), activity_registrarse.class);
                startActivity(registrate);
            }
        });

        /*Evento para mandar a llamar la ventana de recuperar contraseña*/
        txtviewOlvidaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recuperarContra = new Intent(getApplicationContext(), activity_recuperar_contrasena1.class);
                startActivity(recuperarContra);
            }
        });
    }

    /*Metódo para validar credenciales de autenticación*/
    private void validarUsuarioPassword() throws Exception {
        final String encriptarUser = encriptarUsuario(txtLoginUsuario.getText().toString(), contraseniaParaClave);
        final String encriptarPass = encriptarPassword(txtLoginPassword.getText().toString(), contraseniaParaClave);

        String url = "https://phpclusters-152621-0.cloudclusters.net/busquedaAutenticacion.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest resultadoPost = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /*Aquí es donde debere comprobar si hay autenticación o no*/
                        Log.d("Mensaje",response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Mensaje","NO VIENE UNA RESPUESTA : )");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("encrypted_user", encriptarUser); // Envías los datos encriptados como parámetro
                params.put("encrypted_password", encriptarPass);
                return params;
            }
        };
        queue.add(resultadoPost);
    }


    /*Encriptar la contraseña y password antes de mandarlo al PHP con Advance Encryption Standar*/
        public static String encriptarUsuario(String usuario, String password) throws Exception {
            SecretKeySpec key = generateKey(password);
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, key); // aquí es donde se utiliza la clave para desencriptar, esta clave viene del metódod de abajo
            byte[] encVal = c.doFinal(usuario.getBytes());
            return Base64.encodeToString(encVal, Base64.DEFAULT);
        }
        public static String encriptarPassword(String pass, String password) throws Exception {
            SecretKeySpec key = generateKey(password);
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, key); // aquí es donde se utiliza la clave para desencriptar, esta clave viene del metódod de abajo
            byte[] encVal = c.doFinal(pass.getBytes());
            return Base64.encodeToString(encVal, Base64.DEFAULT);
        }


        /*Generar una clave secreta para ser utilizada para encriptar y desencriptar los datos con el algoritmo AES*/
        private static SecretKeySpec generateKey(String password) throws Exception {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = password.getBytes("UTF-8");
            digest.update(bytes, 0, bytes.length);
            byte[] key = digest.digest();
            return new SecretKeySpec(key, "AES");
        }


    public boolean validar(){
        boolean retorna = true;
        if(txtLoginUsuario.getText().toString().isEmpty()){
            retorna = false;
        }
        if(txtLoginPassword.getText().toString().isEmpty()){
            retorna = false;
        }
        return retorna;
    }

    public void mensajesPersonalizadas(){
        if(txtLoginUsuario.getText().toString().isEmpty()){
            String textoAdvertencia = "No se permite este campo vacío. Por favor, ingresa tu usuario para autenticarse.";
            activity_personalizado_advertencia dialogFragment = activity_personalizado_advertencia.newInstance(textoAdvertencia);
            dialogFragment.show(getSupportFragmentManager(), "advertencia");
        }
        if(txtLoginPassword.getText().toString().isEmpty()){
            String textoAdvertencia = "No se permite este campo vacío. Por favor, ingresa tu contraseña para autenticarse.";
            activity_personalizado_advertencia dialogFragment = activity_personalizado_advertencia.newInstance(textoAdvertencia);
            dialogFragment.show(getSupportFragmentManager(), "advertencia");
        }
    }


}