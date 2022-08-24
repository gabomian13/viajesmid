package com.example.viajesmid;

public class viajes {


    private
    String idviaje;
    String nombre;
    String descripcion;
    String foto;
    String precio;

    public String getIdviaje() {
        return idviaje;
    }

    public void setIdviaje(String idviaje) {
        this.idviaje = idviaje;
    }

    public String getNombre() { return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() { return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public viajes(String idviaje, String nombre, String descripcion, String foto, String precio) {
        this.idviaje = idviaje;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.precio = precio;
    }

}
