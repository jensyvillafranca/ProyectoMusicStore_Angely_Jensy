package com.example.proyectomusicstore_angely_jensy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.proyectomusicstore_angely_jensy.Adapters.CustomAdapterBuscarUsuario;
import com.example.proyectomusicstore_angely_jensy.Models.vistaDeUsuario;

import java.util.ArrayList;
import java.util.List;

public class ActivityUsuarioBuscar extends AppCompatActivity {

    // Declaración de variables
    RecyclerView lista;
    DrawerLayout drawerLayout;
    ImageButton openMenuButton, botonAtras;
    TextView textviewAtras, textviewUsuarioBuscar, Grupos, Inicio;
    EditText txtUsuarioBuscar;
    ImageView imgBuscar, imgBuscar2, iconGrupos, iconInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios_buscar);

        // Inicialización de vistas y elementos del diseño
        lista = (RecyclerView) findViewById(R.id.recyclerview_UsuarioBuscar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layoutUsuarioBuscar);
        openMenuButton = (ImageButton) findViewById(R.id.btn_UsuarioBuscarMenu);
        botonAtras = (ImageButton) findViewById(R.id.btn_UsuariosBuscarAtras);
        textviewAtras = (TextView) findViewById(R.id.textview_UsuariosBuscarbotAtras);
        textviewUsuarioBuscar = (TextView) findViewById(R.id.txtUsuarioBuscarBuscarUsuario);
        txtUsuarioBuscar = (EditText) findViewById(R.id.editTextUsuarioBuscar);
        imgBuscar = (ImageView) findViewById(R.id.imageViewUsuarioBuscar);
        imgBuscar2 = (ImageView) findViewById(R.id.imageViewUsuarioBuscar2);


        // Creación de una lista de elementos de vistaDeUsuario, en esta parte la base de datos llenara estos con un metodo for
        List<vistaDeUsuario> dataList = new ArrayList<>();
        dataList.add(new vistaDeUsuario("Nombre del usuario",  R.drawable.iconodeusuariossinfoto));
        dataList.add(new vistaDeUsuario("Nombre del usuario", R.drawable.iconodeusuariossinfoto));
        dataList.add(new vistaDeUsuario("Nombre del usuario",  R.drawable.iconodeusuariossinfoto));
        dataList.add(new vistaDeUsuario("Nombre del usuario", R.drawable.iconodeusuariossinfoto));
        dataList.add(new vistaDeUsuario("Nombre del usuario",  R.drawable.iconodeusuariossinfoto));
        dataList.add(new vistaDeUsuario("Nombre del usuario",  R.drawable.iconodeusuariossinfoto));
        dataList.add(new vistaDeUsuario("Nombre del usuario",  R.drawable.iconodeusuariossinfoto));
        dataList.add(new vistaDeUsuario("Nombre del usuario", R.drawable.iconodeusuariossinfoto));

        // Configuración del administrador de diseño y adaptador para el RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        lista.setLayoutManager(layoutManager);
        CustomAdapterBuscarUsuario adapter = new CustomAdapterBuscarUsuario(this, dataList);
        lista.setAdapter(adapter);

        // Listener para abrir el menú lateral
        //openMenuButton.setOnClickListener(v -> {
         //   drawerLayout.openDrawer(findViewById(R.id.side_menu));
        //});

        // Listener para manejar los botones de "Atrás"
        View.OnClickListener buttonClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class<?> actividad = null;
                if (view.getId() == R.id.btn_UsuarioBuscarMenu) {
                   // actividad = ActivityGrupoPrincipal.class;
                }
                if (view.getId() == R.id.textview_UsuariosBuscarbotAtras) {
                   // actividad = ActivityGrupoPrincipal.class;
                }

                if (actividad != null) {
                    moveActivity(actividad);
                }
            }
        };

        //Listener para manerjar la visibilidad del boton de busqueda
        textviewUsuarioBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Esconde el textview y muestra el edittext
                textviewUsuarioBuscar.setVisibility(View.GONE);
                txtUsuarioBuscar.setVisibility(View.VISIBLE);
                imgBuscar.setVisibility(View.GONE);
                imgBuscar2.setVisibility(View.VISIBLE);
                txtUsuarioBuscar.requestFocus();

                // Mostrar el Teclado
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(txtUsuarioBuscar, InputMethodManager.SHOW_IMPLICIT);

            }
        });

        //Listener para manerjar la visibilidad del boton de busqueda
        txtUsuarioBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Esconde el edittext y muestra el textview
                textviewUsuarioBuscar.setVisibility(View.VISIBLE);
                txtUsuarioBuscar.setVisibility(View.GONE);
                imgBuscar.setVisibility(View.VISIBLE);
                imgBuscar2.setVisibility(View.GONE);
                // Cierra el teclado
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        //Listener para manejar el cierre del teclado con el boton de enter
        txtUsuarioBuscar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Cierra el teclado
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        // Asigna los listeners a los botones de "Atrás"
        botonAtras.setOnClickListener(buttonClick);
        textviewAtras.setOnClickListener(buttonClick);

    }

    private void moveActivity(Class<?> actividad) {
        Intent intent = new Intent(getApplicationContext(), actividad);
        startActivity(intent);
    }
}