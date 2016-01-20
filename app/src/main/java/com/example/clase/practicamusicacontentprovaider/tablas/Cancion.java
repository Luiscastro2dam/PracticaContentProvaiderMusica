package com.example.clase.practicamusicacontentprovaider.tablas;

/**
 * Created by Clase on 18/01/2016.
 */
public class Cancion {
    private String nombreCancion;
    private int idDisco;
    private int idCancion;

    public Cancion() {
    }

    public Cancion(String nombreCancion, int idDisco, int idCancion) {
        this.nombreCancion = nombreCancion;
        this.idDisco = idDisco;
        this.idCancion = idCancion;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "nombreCancion='" + nombreCancion + '\'' +
                ", idDisco=" + idDisco +
                ", idcancion=" + idCancion +
                '}';
    }
    /*  private String nombre,ruta,tipo;
    private int idCancion;

    public Cancion() {
    }

    public Cancion(String nombre, String ruta, String tipo,int idCancion) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.tipo = tipo;
        this.idCancion = idCancion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "nombre='" + nombre + '\'' +
                ", ruta='" + ruta + '\'' +
                ", tipo='" + tipo + '\'' +
                ", idcancion='"+idCancion+
                '}';
    }*/
}
