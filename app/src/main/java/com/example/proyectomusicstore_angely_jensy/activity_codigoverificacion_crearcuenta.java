package com.example.proyectomusicstore_angely_jensy;

import static com.example.proyectomusicstore_angely_jensy.activity_registrarse.form_correo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
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

import java.util.HashMap;
import java.util.Map;

import android.os.Handler;


public class activity_codigoverificacion_crearcuenta extends AppCompatActivity {

    /*Declaración de variables*/
    Button btnRecuperarRegistrarse;

    EditText txtRecuperarRegistrarse;

    TextView txtviewVerificarEnviarNuevamente;


    /*Variables para el tiempo de reenvio del código*/
    private int segundos = 0; //segundos
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


        /*Evento para el botón que deja acceder a la pantalla principal de la aplicación de acuerdo al código de verificación correcto*/
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

        /*Evento para poder reenviar el código de verificación*/
        txtviewVerificarEnviarNuevamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Llamado al método para el reenvio del código*/

                tiempoCodigo();
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
        String url = "https://phpclusters-152621-0.cloudclusters.net/verificacionCorreo.php";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest resultadoPost = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Código mandado", Toast.LENGTH_LONG).show();
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
            }
        });
    }
}