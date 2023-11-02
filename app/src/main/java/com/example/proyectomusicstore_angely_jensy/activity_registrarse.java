package com.example.proyectomusicstore_angely_jensy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.InputFilter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class activity_registrarse extends AppCompatActivity {

    /*Asignación de variables*/
    EditText nombres, apellidos,correo_electronico,usuario,password,confirmar_password;
    Button btn_insertar;

    /*Variables públicas para utilizar en la pantalla de verificación para poder insertar en la base de datos el usuario*/
    public static String form_nombres, form_apellidos, form_correo, form_usuario, form_password, verificationCode;


    TextView txtviewRegistrarCuentaCreada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        /*Relacionando las variables con el objeto de la interfaz*/
        nombres = (EditText) findViewById(R.id.txtRegistrarNombres);

        /*Repetimos el proceso para las demás variables*/
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
                    enviarCodigoVerificacion();
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


        /*Llamada al método de validar expresiones regulares*/
        expresiones_regulares();
    }

    /*Método para poder mandar el código de verificación*/
    public void enviarCodigoVerificacion() {
        /*Asignando a esa variable global el valor que el usuario escribe*/
        form_nombres = nombres.getText().toString();
        form_apellidos = apellidos.getText().toString();
        form_correo = correo_electronico.getText().toString();
        form_usuario = usuario.getText().toString();
        form_password = password.getText().toString();

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
                parametros.put("email", correo_electronico.getText().toString());
                return parametros;
            }
        };

        queue.add(resultadoPost);
    }



    /*Validación para no dejar campos vacíos*/
    public boolean validar(){
        boolean retorna = true;
        String hola = String.valueOf(nombres.getText());
        Log.d("Hola", hola);


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


    /*Expresión regular para el campo de nombres:
      Solo letras, no permite acentos, ni caracteres especiales, ni mayúsculas, ni numéros y un espacio*/

    public void expresiones_regulares(){

        //Filtro para los campos de nombres y apellidos
        InputFilter soloLetras = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                StringBuilder builder = new StringBuilder();
                for (int i = start; i < end; i++) {
                    if (Pattern.matches("[a-zA-Z\\s]", String.valueOf(source.charAt(i)))) {
                        builder.append(source.charAt(i));
                    }
                    // Los caracteres que no cumplen con la condición simplemente no se añaden al constructor
                }
                // Si todos los caracteres son válidos, devolver null no cambia la entrada
                return source.length() == builder.length() ? null : builder.toString();
            }
        };

        //Filtro para el correo electrónico
        InputFilter correoFiltro = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                StringBuilder builder = new StringBuilder(dest);

                builder.replace(dstart, dend, source.subSequence(start, end).toString());

                int atCount = 0;
                int dotCount = 0;
                for (int i = 0; i < builder.length(); i++) {
                    char c = builder.charAt(i);
                    if (c == '@') {
                        atCount++;
                    } else if (c == '.') {
                        dotCount++;
                    }
                }

                if (atCount > 1 || dotCount > 1) {
                    return "";
                }

                for (int i = start; i < end; i++) {
                    char currentChar = source.charAt(i);
                    if (!(Character.isLowerCase(currentChar) || Character.isDigit(currentChar) || currentChar == '@' || currentChar == '.')) {
                        return "";
                    }
                }

                return null;
            }
        };

        /*Expresión regular para usuario en donde no se permite ingresar los siguientes cáracteres:
         ''
         ""
         ;
         -
         /
         #
         =
         > <
         !
         ()
         ?
         y el asterisco*/
        InputFilter usuarioContrasenia = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Pattern.compile("^[^,#'\";\\-/\\*=>&%<!\\(\\)\\?]+$").matcher(String.valueOf(source.charAt(i))).matches()) {
                        return "";
                    }
                }
                return null; // Accept the original value
            }
        };

        nombres.setFilters(new InputFilter[]{soloLetras});
        apellidos.setFilters(new InputFilter[]{soloLetras});
        correo_electronico.setFilters(new InputFilter[]{correoFiltro});
        usuario.setFilters(new InputFilter[]{usuarioContrasenia});
        //la contra viaja encriptada.
    }
}