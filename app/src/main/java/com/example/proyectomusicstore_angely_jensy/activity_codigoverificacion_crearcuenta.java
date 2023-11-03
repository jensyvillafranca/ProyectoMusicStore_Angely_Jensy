package com.example.proyectomusicstore_angely_jensy;

import static com.example.proyectomusicstore_angely_jensy.activity_registrarse.form_apellidos;
import static com.example.proyectomusicstore_angely_jensy.activity_registrarse.form_correo;
import static com.example.proyectomusicstore_angely_jensy.activity_registrarse.form_nombres;
import static com.example.proyectomusicstore_angely_jensy.activity_registrarse.form_password;
import static com.example.proyectomusicstore_angely_jensy.activity_registrarse.form_usuario;
import static com.example.proyectomusicstore_angely_jensy.activity_registrarse.verificationCode;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import android.os.Handler;

import org.json.JSONException;
import org.json.JSONObject;



public class activity_codigoverificacion_crearcuenta extends AppCompatActivity {

    /*Declaración de variables*/
    Button btnRecuperarRegistrarse;

    EditText txtRecuperarRegistrarse;

    TextView txtviewVerificarEnviarNuevamente,txtviewActivaLetras;
    String token = "Nulo"; /*Para mensajes push*/
    String enlaceFoto = "Nulo";
    int idVisualizacion = 1;


    /*Variables para el tiempo de reenvio del código*/
    private int segundos = 60; //segundos
    private TextView txtviewCronometro; //textview donde aparecera el cronometro



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigoverificacion_crearcuenta);

        /*Amarrando los valores de las variables al objeto de la interfaz*/
        btnRecuperarRegistrarse = (Button) findViewById(R.id.btnRecuperarRegistrarse);
        txtRecuperarRegistrarse = (EditText) findViewById(R.id.txtRecuperarRegistrarse);
        txtviewVerificarEnviarNuevamente = (TextView) findViewById(R.id.txtviewVerificarEnviarNuevamente);
        txtviewCronometro = (TextView) findViewById(R.id.txtviewCronometro);
        txtviewActivaLetras = (TextView) findViewById(R.id.txtviewActivaLetras);


        /*Evento para el botón que deja acceder a la pantalla principal de la aplicación de acuerdo al código de verificación correcto*/
        btnRecuperarRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //la función validar permite validar cuando los campos están vacios y lanza una alerta
                if(validar() == true){
                    /*Aquí debe de ir el llamado a la ventana donde se encuentra el menú desplegable*/
                    insertarUsuario();

                }else{
                    /*Aquí debe de mostrarse el mensaje personalizado*/
                }
            }
        });

        /*Evento para poder reenviar el código de verificación*/
        txtviewVerificarEnviarNuevamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Llamado al método para el reenvio del código*/
                segundos = 60;
                reenviarCodigoVerificacion();
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



    /*Método para reenviar el código en caso de que el primero enviado ya este inactivo*/
    public void reenviarCodigoVerificacion() {
        //Log.d("Correo desde la otra ventana",form_correo);
        tiempoCodigo();
        String url = "https://phpclusters-152621-0.cloudclusters.net/verificacionCorreo.php";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest resultadoPost = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Código mandado", Toast.LENGTH_LONG).show();
                        try {
                            // Convertir la respuesta en un objeto JSON
                            JSONObject jsonObject = new JSONObject(response);

                            //Asignando a las variables status, message y verificationCode los valores que vienen del PHP
                            //String status = jsonObject.getString("status");
                            //String message = jsonObject.getString("message");
                            verificationCode = jsonObject.getString("verification_code");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("email", form_correo);
                return parametros;
            }
        };

        queue.add(resultadoPost);
    }


    /*Método para poner tiempo para reenvio del código*/
    public void tiempoCodigo(){
        /*Mostrar el conómetro*/
        txtviewCronometro.setVisibility(View.VISIBLE);

        /*Para acceder al recurso string*/
        txtviewActivaLetras.setText(R.string.tituloConometro);

        /*Desabilitar la opción de solicitar un nuevo código*/
        txtviewVerificarEnviarNuevamente.setEnabled(false);

        /*Para cambiar el color al textview de "Enviar de nuevo"*/
        txtviewVerificarEnviarNuevamente.setTextColor(Color.GRAY);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int minutes = segundos / 60;
                int secs = segundos % 60;

                String time = String.format("%02d:%02d", minutes, secs);
                txtviewCronometro.setText(time);

                if (segundos > 0) {
                    segundos--;
                    handler.postDelayed(this, 1000);
                }
                if(segundos == 0){ //Habilitar la opción de mandar otro código una vez terminado el tiempo o cuándo este llega a 0
                    txtviewVerificarEnviarNuevamente.setEnabled(true);

                    /*Para limpiar el mensaje que de tiempo de espera*/
                    txtviewActivaLetras.setText("");

                    /*Para ocultar nuevamente el cronometro*/
                    txtviewCronometro.setVisibility(View.INVISIBLE);

                    /*Pasar a color blanco el textview de "Enviar de nuevo"*/
                    txtviewVerificarEnviarNuevamente.setTextColor(Color.WHITE);
                }
            }
        });
    }


    /*Metódo para insertar usuario en la base de datos, luego de verificar el código*/
    public void insertarUsuario() {
        /*Comparar que el código que el usuario ingresa, es el mismo del correo electrónico*/
        String prueba = txtRecuperarRegistrarse.getText().toString();
        Log.d("El del usuario", prueba);
        Log.d("El código", verificationCode);


        if(prueba.trim().equals(verificationCode)){

            /*Mostrar mensaje de verificación completada*/
            /*Insertar el usuario en la base de datos.*/

            String url = "https://phpclusters-152474-0.cloudclusters.net/insertarUsuario.php";
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest resultadoPost = new StringRequest(
                    Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(), "Usuario insertado exitosamente", Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error " + error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> parametros = new HashMap<>();
                    parametros.put("nombres", form_nombres);
                    parametros.put("apellidos", form_apellidos);
                    parametros.put("correo", form_correo);
                    parametros.put("usuario", form_usuario);
                    parametros.put("contrasenia",  encriptarPassword(form_password));
                    parametros.put("token", token.toString());
                    parametros.put("enlacefoto", enlaceFoto.toString());
                    parametros.put("idvisualizacion", Integer.toString(idVisualizacion));
                    return parametros;
                }
            };
            queue.add(resultadoPost);
        }else{
            Toast.makeText(getApplicationContext(), "Código no válido", Toast.LENGTH_LONG).show();
        }
    }

    private String encriptarPassword(String formPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(formPassword.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}