package com.example.proyectomusicstore_angely_jensy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proyectomusicstore_angely_jensy.R;

import com.example.proyectomusicstore_angely_jensy.Models.vistaDeUsuario;

import java.util.List;


public class CustomAdapterBuscarUsuario extends RecyclerView.Adapter<CustomAdapterBuscarUsuario.CustomViewHolder> {
    private List<vistaDeUsuario> dataList;
    private Context context;

    public CustomAdapterBuscarUsuario(Context context, List<vistaDeUsuario> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_custom_list_item_seguir_usuario, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        vistaDeUsuario data = dataList.get(position);

        // Vincula los datos a las vistas en tu diseño de elemento de lista personalizado
        holder.nombreUsuario.setText(data.getText1());
        holder.image.setImageResource(data.getImageResource());

        // Obtiene el ImageView del diseño
       // ImageView imgSeguir = holder.itemView.findViewById(R.id.imageviewBuscarUnirse);

        // Listener para ir a la pantalla de un grupo especifico y unirse
       // imgUnirse.setOnClickListener(new View.OnClickListener() {
          //  @Override
          //  public void onClick(View v) {
            //    Intent pantallaUnirse = new Intent(v.getContext(), ActivitySeguir.class);
          //      v.getContext().startActivity(pantallaUnirse);
          //  }
       // });
    }



    //Devuelve el número total de elementos en la lista de datos.
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView nombreUsuario; // TextView para mostrar el nombre del grupo
        ImageView image; // ImageView para mostrar una imagen asociada al grupo

        public CustomViewHolder(View itemView) {
            super(itemView);
            nombreUsuario = itemView.findViewById(R.id.txtListItemNombreUsuario); // Asocia la vista de nombre del grupo

            image = itemView.findViewById(R.id.imageviewListItemImageUsuario); // Asocia la vista de la imagen asociada al grupo
        }
    }
}
