package com.example.proyectomusicstore_angely_jensy;

import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.app.Dialog;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

public class activity_personalizado_advertencia extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Inflar el layout personalizado
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_personalizado_advertencia, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                    // Acción para el botón Aceptar
                });
                /*.setNegativeButton("Cancelar", (dialog, id) -> {
                    // Acción para el botón Cancelar
                    getDialog().cancel();
                });*/
        return builder.create();
    }

}