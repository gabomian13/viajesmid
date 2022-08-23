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

public class MenuAdapter extends ArrayAdapter<menus> {
    public MenuAdapter(@NonNull Context context, int resource, @NonNull List<menus> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View result = convertView;
        if (result == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            result = inflater.inflate(R.layout.item_menu,null);
        }
        TextView name = (TextView) result.findViewById(R.id.txtcategoria);
        ImageView image = (ImageView) result.findViewById(R.id.img);

        menus cate_row = getItem(position);
        name.setText(cate_row.getName());
        Picasso.get().load("https://viajesmid.000webhostapp.com/images/"+cate_row.getImagen()+".png").into(image);

        return result;

    }
}
