package com.example.clase.practicamusicacontentprovaider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.clase.practicamusicacontentprovaider.Adaptadores.Adaptador;
import com.example.clase.practicamusicacontentprovaider.Adaptadores.AdaptadorInterprete;
import com.example.clase.practicamusicacontentprovaider.Adaptadores.AdaptadorVideos;
import com.example.clase.practicamusicacontentprovaider.Gestion.GestorCanciones;
import com.example.clase.practicamusicacontentprovaider.Gestion.GestorDiscos;
import com.example.clase.practicamusicacontentprovaider.Gestion.GestorInterpretes;
import com.example.clase.practicamusicacontentprovaider.tablas.Cancion;
import com.example.clase.practicamusicacontentprovaider.tablas.Disco;
import com.example.clase.practicamusicacontentprovaider.tablas.Interprete;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
 //para guardar los datos de las tablas creamos arrays para meter nuestros objetos
    private List<Cancion> listaMusica = new ArrayList<Cancion>();
    private List<Disco> listaVideos = new ArrayList<Disco>();
    private List<Interprete> listaInterpretes = new ArrayList<Interprete>();
 //declaramos los gestores de las BD
    private GestorInterpretes gestorInterpretes;
    private GestorCanciones gestorCanciones;
    private GestorDiscos gestorDiscos;

    private ListView lv;
    private Adaptador ad;
    private AdaptadorVideos adv;
    private AdaptadorInterprete adi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //INICIAMOS los gestores de la bd
        gestorCanciones = new GestorCanciones(this);
        gestorDiscos = new GestorDiscos(this);
        gestorInterpretes = new GestorInterpretes(this);

    }
    public void init(){

    }
    //------------menu principal--------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_mostrarMusica: {
                lv = (ListView) findViewById(R.id.lvMostrar);
                //listaDisco = gestorDiscos.selectDiscos();
                listaMusica.clear();
                listaMusica= new ArrayList<>();
                listaMusica= this.buscadorAudio();
                System.out.println("kira"+listaMusica.toString());
                ad = new Adaptador(this, R.layout.item, (ArrayList<Cancion>) listaMusica);
                lv.setAdapter(ad);
                this.registerForContextMenu(lv);
                return true;
            }
            case R.id.menu_mostrarDiscos: {
                lv = (ListView) findViewById(R.id.lvMostrar);

                listaVideos= gestorDiscos.selectDiscos();

                System.out.println("luisVIDEOS" + listaVideos.toString());
               // listaVideos = gestorDiscos.selectDiscos();
                adv = new AdaptadorVideos(this, R.layout.item, (ArrayList<Disco>) listaVideos);
                lv.setAdapter(adv);
                this.registerForContextMenu(lv);
                return true;
            }
            case R.id.menu_mostrarAudiobd: {

               // gestorCanciones.borrarTodasCanciones();
                lv = (ListView) findViewById(R.id.lvMostrar);
               // listaMusica = this.buscadorAudio();
                listaMusica = gestorCanciones.selectCanciones();
                System.out.println("luis"+listaMusica.toString());
                ad = new Adaptador(this, R.layout.item, (ArrayList<Cancion>) listaMusica);
                lv.setAdapter(ad);
                this.registerForContextMenu(lv);
                return true;
            }
            case R.id.menu_mostrSincronicar: {
                this.mensajeAlerta();
                return true;
            }
            case R.id.menu_mostraArtistas: {
                lv = (ListView) findViewById(R.id.lvMostrar);
                listaInterpretes= gestorInterpretes.selectInterpretes();
                System.out.println("luisInterpretes" + listaInterpretes.toString());
                adi = new AdaptadorInterprete(this, R.layout.item, (ArrayList<Interprete>) listaInterpretes);
                lv.setAdapter(adi);
                this.registerForContextMenu(lv);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    //-----------------------------------------------------------

    /*--------------------------------------------------------------------*/
    @Override
    protected void onResume() {
        gestorCanciones.open();
        gestorDiscos.open();
        gestorInterpretes.open();
        super.onResume();
        this.init();

    }
    @Override
    protected void onPause() {
        gestorCanciones.close();
        gestorDiscos.close();
        gestorInterpretes.close();
        super.onPause();
    }
    /*---------buscarAudio------------------------------------------------*/

    private ArrayList buscadorAudio() {
        try {
            String TAG = "Audio";
            Cursor cur = getContentResolver().query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                    null);

            if (cur == null) {
                //si esta nulo el cursor
                Log.e(TAG, "cursor nulo al leer las canciones(buscadorAudio)");

            } else if (!cur.moveToFirst()) {
                // la consulta esta mal en canciones
                Log.e(TAG, "conuslta errona canciones(buscadorAudio)");

            } else {
                Log.i(TAG, "lee canciones(buscadorAudio)");
                //vamos a rellenar nuestra array obtenemos los datos que nos interesan.
                do {
                    int titleColumn = cur.getColumnIndex(MediaStore.Audio.Media.TITLE);
                    int filePathIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    int title2 = cur.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                    int title3 = cur.getColumnIndex(MediaStore.Audio.Media.ALBUM);
                    int title4 = cur.getColumnIndex(MediaStore.Audio.Media.DURATION);
                    int title5 = cur.getColumnIndex(MediaStore.Audio.Media.COMPOSER);

                    System.out.println("artista*="+cur.getString(title2));
                    System.out.println("titulocancion*="+cur.getString(titleColumn));
                    System.out.println("albun*="+cur.getString(title3));
                    System.out.println("DURATION="+cur.getString(title4));
                    System.out.println("COMPOSER="+cur.getString(title5));
                    System.out.println("--------------------------------------------");

                  /*  Cancion cancion = new Cancion();
                    cancion.setNombre(cur.getString(titleColumn));
                    cancion.setRuta(cur.getString(filePathIndex));
                    cancion.setTipo("audio");
                    listaMusica.add(cancion);*/

                     Cancion cancion = new Cancion();
                     Disco disco = new Disco();
                     Interprete interprete = new Interprete();

                    /*creamos el interprete y guardamos*/
                    interprete.setNombre(cur.getString(title2));
                    listaInterpretes.add(interprete);
                 //   gestorInterpretes.insertarInterpretes((ArrayList<Interprete>) listaInterpretes);

                     /*creamos el disco y guardamos*/
                    disco.setTiulo(cur.getString(title3));
                    disco.setIdInterprete(interprete.getIdInterprete());
                    listaVideos.add(disco);
                  //  gestorDiscos.insertarDiscos((ArrayList<Disco>) listaVideos);
                    /*creamos la cancion y la cuadarmos */
                    cancion.setNombreCancion(cur.getString(titleColumn));
                    cancion.setIdDisco(disco.getIdDisco());
                    listaMusica.add(cancion);
                  //  gestorCanciones.insertarCanciones((ArrayList<Cancion>) listaMusica);



                } while (cur.moveToNext());
                System.out.println("luis" + listaMusica.toString());
                System.out.println("luis" + listaVideos.toString());
                System.out.println("luis" + listaInterpretes.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList) listaMusica;
    }

    /*------------------------------------------------------------------------*/
  /*  public ArrayList  buscadorVideo(){

           // int title1 = cur.getColumnIndex(MediaStore.Audio.Media.ALBUM);//devuelve nombre carpeta donde esta
           // int title1 = cur.getColumnIndex(MediaStore.Audio.Media.COMPOSER);// devuelve 0
           //int title3 = cur.getColumnIndex(MediaStore.Audio.Media.YEAR);
         //   int title4 = cur.getColumnIndex(MediaStore.Audio.Media.DURATION);
           //int title2 = cur.getColumnIndex(MediaStore.Audio.Media.ARTIST);//devuelve <unknown>
           // int rutaimage = cur.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
     /* String rutafoto = cur.getString(a);
            Bitmap bmThumbnail = ThumbnailUtils.
                    extractThumbnail(ThumbnailUtils.createVideoThumbnail(rutafoto,
                            MediaStore.Video.Thumbnails.MINI_KIND), 80, 50);
img.setImageBitmap(bmThumbnail);*/
          //  System.out.println(cur.getString(titleColumn)+"titulo luise");
          //  System.out.println(cur.getString(title1)+"luise");
         //  System.out.println(cur.getString(title3)+"año");
        //    System.out.println(cur.getString(title4)+"duracion");
           // System.out.println(cur.getString(rutaimage)+"luise");

      /*  try {
            String TAG = "Video";
            Cursor cur = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    null, null, null, null);
            cur.moveToFirst();

            if (cur == null) {
                //si esta nulo el cursor
                System.out.println("nulo el cursor(buscadorVideo)");

            } else if (!cur.moveToFirst()) {
                // la consulta esta mal en canciones
                System.out.println("la consulta esta mal(buscadorVideo)");

            } else {
                System.out.println("lee canciones(buscadorVideo)");
                //vamos a rellenar nuestra array obtenemos los datos que nos interesan.
                do {
                    int titleColumn = cur.getColumnIndex(MediaStore.Audio.Media.TITLE) ;
                    int rutaimage = cur.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                    Disco disco = new Disco();
                    disco.setTiulo(cur.getString(titleColumn));
                    disco.setRutaImagen(cur.getString(rutaimage));
                    disco.setTipo("video");
                    listaVideos.add(disco);

                } while (cur.moveToNext());
                System.out.println("luisVIDEOS" + listaVideos.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList) listaVideos;
    }*/
    public void mensajeAlerta() {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿  Si usted Acepta se borraran los datos actuales ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                //borramos todas las bases de datos
                gestorCanciones.borrarTodasCanciones();
                gestorDiscos.borrarTodosDiscos();
                gestorInterpretes.borrarTodosInterpretes();
                //insertamos los datos en la bd
                System.out.println("pepe"+listaVideos.toString());
                gestorDiscos.insertarDiscos((ArrayList<Disco>) listaVideos);
                gestorCanciones.insertarCanciones((ArrayList<Cancion>) listaMusica);
                gestorInterpretes.insertarInterpretes((ArrayList<Interprete>) listaInterpretes);

              /*  gestorCanciones.borrarTodasCanciones();
                System.out.println(listaMusica.toString()+"lu");
                int cont=0;
                for (int i = 0;i<listaMusica.size();i++){
                    listaMusica.get(i).setIdCancion(cont);
                    cont=cont+1;
                }
                System.out.println(listaMusica.toString()+"lu");
                //ya hemos introducidos los id a las canciones
                gestorCanciones.insertarCanciones((ArrayList<Cancion>) listaMusica);*/
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();

    }
}
