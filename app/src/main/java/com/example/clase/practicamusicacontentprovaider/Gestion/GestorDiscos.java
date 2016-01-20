package com.example.clase.practicamusicacontentprovaider.Gestion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.clase.practicamusicacontentprovaider.BaseDatos.Ayudante;
import com.example.clase.practicamusicacontentprovaider.BaseDatos.Galeria;
import com.example.clase.practicamusicacontentprovaider.tablas.Disco;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clase on 11/01/2016.
 */
public class GestorDiscos {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorDiscos(Context c) {
        abd = new Ayudante(c);
    }
    public void open() {
        bd = abd.getWritableDatabase();
    }

    public void close() {
        abd.close();
    }
    //-----------------Principal--------------------------------------------
    public List<Disco> selectDiscos() {
        List<Disco> lista;
        lista = new ArrayList<Disco>();
        Cursor cursor = bd.query(Galeria.TablaDiscos.TABLA, null,
                null, null, null, null, null, null);
        cursor.moveToFirst();
       Disco disco;
        while (!cursor.isAfterLast()) {
            disco= getRowDisco(cursor);
            lista.add(disco);
            cursor.moveToNext();
        }
        cursor.close();
        if(lista==null){
            lista.add(new Disco());
            return lista;
        }
        System.out.println("luisillo" + lista.toString());
        return lista;
    }
    public Disco getRowDisco(Cursor c) {
        Disco disco = new Disco();
        disco.setIdDisco(c.getInt(c.getColumnIndex(Galeria.TablaDiscos._ID)));
        disco.setTiulo(c.getString(1));
        disco.setIdInterprete(c.getInt(2));
        return disco;
    }
    public void borrarTodosDiscos(){
        bd.delete(Galeria.TablaDiscos.TABLA, null,
                null);
    }
    public void insertarDiscos(ArrayList<Disco> listaDiscos) {
        for (int i = 0; i < listaDiscos.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("titulo", listaDiscos.get(i).getTiulo());
            values.put("idInterprete", listaDiscos.get(i).getIdInterprete());
            bd.insert(Galeria.TablaDiscos.TABLA, null, values);
        }
    }
    /*
    public List<Disco> selectDiscos() {
        List<Disco> lista;
        lista = new ArrayList<Disco>();
        Cursor cursor = bd.query(Galeria.TablaDiscos.TABLA, null,
                null, null, null, null, null, null);
        cursor.moveToFirst();
        Disco disco;
        while (!cursor.isAfterLast()) {
            disco = getRowDisco(cursor);
            lista.add(disco);
            cursor.moveToNext();
        }
        cursor.close();
        if(lista==null){
            lista.add(new Disco());
            return lista;
        }
        return lista;
    }
    public Disco getRowDisco(Cursor c) {
        Disco disco = new Disco();
        disco.setIdDisco(c.getInt(c.getColumnIndex(Galeria.TablaDiscos._ID)));
        disco.setTiulo(c.getString(1));
        disco.setRutaImagen(c.getString(2));
        return disco;
    }

    public int updateDiscosFoto(Disco disco){
        ContentValues valores = new ContentValues();
        valores.put(Galeria.TablaDiscos.CARATULA, disco.getCaratula());
        String condicion = Galeria.TablaDiscos._ID + " = ?";
        String[] argumentos = { disco.getIdDisco() + "" };
        int cuenta = bd.update(Galeria.TablaDiscos.TABLA, valores,
                condicion, argumentos);
        return cuenta;
    }*/
}
