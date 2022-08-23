package com.example.viajesmid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ViajeAdapter extends ArrayAdapter<viajes> {
    public ViajeAdapter(@NonNull Context context, int resource, @NonNull List<viajes> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View result = convertView;
        if (result == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            result = inflater.inflate(R.layout.item_viajes,null);
        }
        TextView nombre = (TextView) result.findViewById(R.id.nombreProducto);
        ImageView foto = (ImageView) result.findViewById(R.id.imagenProducto);
        TextView descripcion = (TextView) result.findViewById(R.id.descripcion);
        TextView precio = (TextView) result.findViewById(R.id.precioProducto);



        viajes cate_row = getItem(position);
        nombre.setText(cate_row.getNombre());
        descripcion.setText(cate_row.getDescripcion());
        precio.setText(cate_row.getPrecio());
        Picasso.get().load("https://viajesmid.000webhostapp.com/images/"+cate_row.getFoto()+".png").into(foto);

        return result;

    }
}
