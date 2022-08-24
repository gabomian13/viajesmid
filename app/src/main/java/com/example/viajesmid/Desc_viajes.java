package com.example.viajesmid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Desc_viajes extends AppCompatActivity {


    private ArrayList<viajes> list_detalles;
    private ViajeAdapter adapter;
    TextView nombre, detalle, precio, total;
    ImageView foto;
    Button btmas, btmenos, agregar, carrito;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
        setContentView(R.layout.desc_viaje);

        nombre = (TextView) findViewById(R.id.tvNameDetalle);
        detalle = (TextView) findViewById(R.id.tvDescripcionDetalle);
        precio = (TextView) findViewById(R.id.tvPrecioDetalle);
        total = (TextView) findViewById(R.id.total);
        foto = (ImageView) findViewById(R.id.imgDetalle);
        btmas = (Button) findViewById(R.id.btnMas);
        btmenos = (Button) findViewById(R.id.btnMenos);
        agregar = (Button) findViewById(R.id.btnAgregar);
        carrito = (Button) findViewById(R.id.btnCarrito);


        preferences=getSharedPreferences("pedidos", Context.MODE_PRIVATE);
        validadCantidad("idviaje");

        carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Viaje Agregado Correctamente" , Toast.LENGTH_LONG).show();


            }
        });


        btmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cantidad = preferences.getString("1","0");
                int cant = Integer.parseInt(cantidad);

                cant = cant + 1;

                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("1",cant+"");
                editor.commit();
                total.setText("Cantidad: "+cant+"");
            }
        });

        btmenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cantidad = preferences.getString("1","0");
                int cant = Integer.parseInt(cantidad);
                if (cant > 0)
                    cant = cant - 1;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("1",cant+"");
                editor.commit();
                total.setText("Cantidad: "+cant+"");

            }
        });

        list_detalles = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.get("idviaje") != null) {
                Toast.makeText(getApplicationContext(), "Se mostrara el viaje: " + extras.get("idviaje").toString(), Toast.LENGTH_LONG).show();
                GetViajes(extras.get("idviaje").toString());
            }

        }
    }
    private void validadCantidad(String idviaje) {
        String cantidad = preferences.getString(idviaje,"0");
        total.setText("Cantidad: "+cantidad);

    }


    private void GetViajes(String idviaje) {
        RequestQueue queque = Volley.newRequestQueue(this);
        String url = "https://viajesmid.000webhostapp.com/cons.php?detalles=" + idviaje;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response.optString("Status").equals("success")) {
                    String data = response.optString("Datos");
                    try {
                        JSONArray jsonArray = new JSONArray(data);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject = jsonArray.getJSONObject(i);
                            nombre.setText(jsonObject.optString("nombre"));
                            detalle.setText(jsonObject.optString("descripcion"));
                            precio.setText("Precio: $"+jsonObject.optString("precio")+".00");
                            Picasso.get().load("https://viajesmid.000webhostapp.com/images/" + jsonObject.optString("foto") + ".png").into(foto);

                        }
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else
                    Toast.makeText(getApplicationContext(), "Error al cargar los productos", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error no se pudo establecer la comunicacion", Toast.LENGTH_LONG).show();
            }
        });

        queque.add(jsonObjectRequest);
    }


}
