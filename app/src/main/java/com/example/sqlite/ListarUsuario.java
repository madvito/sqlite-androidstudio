package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListarUsuario extends AppCompatActivity {

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuario);
        lv=(ListView) findViewById(R.id.listaUsuarios);
        ListarDatos();
    }
    public void ListarDatos(){
        dbHelper baseHelper=new dbHelper(this,"gym",null,1);
        SQLiteDatabase db=baseHelper.getReadableDatabase();

        if(db!=null){
            Cursor c= db.rawQuery("select * from tblpersona",null);
            int i=c.getCount(); //contar la cantidad de filas retornadas
            int indice=0;
            if (i>0){
              String array[]= new String[i];//repositorio de datos de 1 dimension
              if (c.moveToFirst()){
                  do{
                      String item=c.getString(0)+" "+c.getString(1)+" "+c.getString(2);
                      array[indice]=item;
                      indice++;
                  }while (c.moveToNext());
              }
              c.close();
              //cargar listview
                ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,array);
              lv.setAdapter(adapter);

            }else{
                Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
