package com.example.clase.practicamusicacontentprovaider.BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Clase on 11/01/2016.
 */
public class Ayudante extends SQLiteOpenHelper {
//Para poder crear bases de datos en nuestra aplicación
// debemos usar las clases hijas de "SQLiteOpenHelper".

    // Nombre de nuestro archivo de base de datos
    public static final String DATABASE_NAME = "musicaNueva3.sqlite";
    public static final int DATABASE_VERSION = 4;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    //se encargara de crear las tablas si no existen
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql="create table "+ Galeria.TablaCanciones.TABLA+
                " ("+ Galeria.TablaCanciones._ID+
                " integer primary key autoincrement, "+
                Galeria.TablaCanciones.NOMBRECANCION+" text, "+
                Galeria.TablaCanciones.IDDISCO+" text) ";
        db.execSQL(sql);
        sql="create table "+Galeria.TablaDiscos.TABLA+
                " ("+ Galeria.TablaDiscos._ID+
                " integer primary key autoincrement, "+
                Galeria.TablaDiscos.TITULO+" text, "+
                Galeria.TablaDiscos.IDINTERPRETE+" text)";
        db.execSQL(sql);
                sql="create table "+Galeria.TablaInterpretes.TABLA+
                " ("+ Galeria.TablaInterpretes._ID+
                " integer primary key autoincrement, "+
                Galeria.TablaInterpretes.NOMBRE+" text)";
        db.execSQL(sql);
     /*   String sql;
        sql="create table "+ Galeria.TablaCanciones.TABLA+
                " ("+ Galeria.TablaCanciones._ID+
                " integer primary key autoincrement, "+
                Galeria.TablaCanciones.NOMBRECANCION+" text, "+
                Galeria.TablaCanciones.RUTA+" text, "+
                Galeria.TablaCanciones.TIPO+" text) ";
        db.execSQL(sql);
       sql="create table "+Galeria.TablaDiscos.TABLA+
                " ("+ Galeria.TablaDiscos._ID+
                " integer primary key autoincrement, "+
                Galeria.TablaDiscos.TITULO+" text, "+
                Galeria.TablaDiscos.IDDISCO+" text, "+
                Galeria.TablaDiscos.RUTAIMAGEN+" text)";
        db.execSQL(sql);*/
    /*     sql="create table "+Galeria.TablaInterpretes.TABLA+
                " ("+ Galeria.TablaInterpretes._ID+
                " integer primary key autoincrement, "+
                Galeria.TablaInterpretes.NOMBRE+" text, "+
                Galeria.TablaInterpretes.FOTO+" text)";
        db.execSQL(sql);*/

//--------------Insertamos datos de inicio---------------------------------------//

   /*    db.execSQL("INSERT INTO Canciones(idCanciones, nombreCancion, idDisco) VALUES(1,'MalaVida',1)");
          db.execSQL("INSERT INTO Canciones(idCanciones, nombreCancion, idDisco) VALUES(2,'OLE',1)");

        db.execSQL("INSERT INTO Disco(idDisco,titulo,idInterprete,Caratula)" +
           insert into Discos (_id, titulo,idInterprete,caratula) values(2,'Estopa',1,'foto.png')                  " VALUES(1,'Tortilla','Preparamos todo llsls','foto.jpg')");
      /*
        db.execSQL("INSERT INTO Ingredientes(idIngrediente, nombre) VALUES(1,'Patatas')");
        db.execSQL("INSERT INTO Ingredientes(idIngrediente, nombre) VALUES(2,'Cebollas')");
        db.execSQL("INSERT INTO Ingredientes(idIngrediente, nombre) VALUES(3,'Ajos')");
        db.execSQL("INSERT INTO Ingredientes(idIngrediente, nombre) VALUES(4,'Pimientos')");
*/
    }
    // las actualizara
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table if exists "+ Galeria.TablaCanciones.TABLA;
        db.execSQL(sql);
        String sql1="drop table if exists "+ Galeria.TablaDiscos.TABLA;
        db.execSQL(sql1);
        String sql2="drop table if exists "+ Galeria.TablaInterpretes.TABLA;
        db.execSQL(sql2);
        onCreate(db);
    }

}

