package com.example.clase.practicamusicacontentprovaider.BaseDatos;

import android.provider.BaseColumns;

/**
 * Created by Clase on 11/01/2016.
 */
public class Galeria {

    private Galeria(){

    }
    public static abstract class TablaCanciones implements
            BaseColumns {
        public static final String TABLA = "Canciones";
        public static final String NOMBRECANCION = "nombreCancion";
        public static final String IDDISCO = "idDisco";
      //  public static final String IDCANCION = "idCancion";
    }
    public static abstract class TablaDiscos implements
            BaseColumns{
        public static final String TABLA = "Discos";
        public static final String TITULO = "titulo";
       // public static final String IDDISCO = "idDisco";
        public static final String IDINTERPRETE = "idInterprete";
    }
    public static abstract class TablaInterpretes implements
            BaseColumns{
        public static final String TABLA = "Interpretes";
        public static final String NOMBRE = "nombreInterprete";
     //   public static final String IDINTERPRETE= "idInterprete";
    }
   /* public static abstract class TablaCanciones implements
            BaseColumns {
        public static final String TABLA = "Canciones";
        public static final String NOMBRECANCION = "nombreCancion";
        public static final String RUTA = "ruta";
        public static final String TIPO = "tipo";
    }
    public static abstract class TablaDiscos implements
            BaseColumns{
        public static final String TABLA = "Discos";
        public static final String TITULO = "titulo";
        public static final String IDDISCO = "idDisco";
        public static final String RUTAIMAGEN = "rutaImagen";
        public static final String TIPO = "tipo";
    }
    public static abstract class TablaInterpretes implements
            BaseColumns{
        public static final String TABLA = "Interpretes";
        public static final String NOMBRE = "nombreInterprete";
        public static final String IDINTERPRETE= "idInterprete";
    }*/
}
