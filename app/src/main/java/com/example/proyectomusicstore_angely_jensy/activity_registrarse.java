package com.example.proyectomusicstore_angely_jensy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class activity_registrarse extends AppCompatActivity {

    /*Asignación de variables*/
    EditText nombres, apellidos,correo_electronico,usuario,password,confirmar_password;
    Button btn_insertar;
    String token = "Nulo";
    String enlaceFoto = "Nulo";
    int idVisualizacion = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        /*Relacionando las variables con el objeto de la interfaz*/
        nombres = (EditText) findViewById(R.id.txtRegistrarNombres);
        apellidos = (EditText) findViewById(R.id.txtRegistrarApellidos);
        correo_electronico = (EditText) findViewById(R.id.txtRegistrarCorreo);
        usuario = (EditText) findViewById(R.id.txtRegistrarUsuario);
        password = (EditText) findViewById(R.id.txtRegistrarPassword);
        confirmar_password = (EditText) findViewById(R.id.txtRegistrarConfirmarPassword);
        btn_insertar = (Button) findViewById(R.id.btnRegistrarCrear);


        btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBtnInsertar();
            }
        });
    }



    public void clickBtnInsertar() {
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
                parametros.put("nombres", nombres.getText().toString());
                parametros.put("apellidos", apellidos.getText().toString());
                parametros.put("correo", correo_electronico.getText().toString());
                parametros.put("usuario", usuario.getText().toString());
                parametros.put("contrasenia", password.getText().toString());
                parametros.put("token", token.toString());
                parametros.put("enlacefoto", enlaceFoto.toString());
                parametros.put("idvisualizacion", Integer.toString(idVisualizacion));
                return parametros;
            }
        };

        queue.add(resultadoPost);
    }
}