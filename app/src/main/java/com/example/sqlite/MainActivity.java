package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edt1,edt2,edt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1=(EditText) findViewById(R.id.editText);
        edt2=(EditText) findViewById(R.id.editText2);
        edt3=(EditText) findViewById(R.id.editText3);
    }
    public void  InsertarUsuario(View view){
        String nombre,apellido,fono;
        nombre=edt1.getText().toString();
        apellido=edt2.getText().toString();
        fono=edt3.getText().toString();

        dbHelper baseHelper=new dbHelper(this,"gym",null,1);
        SQLiteDatabase db=baseHelper.getWritableDatabase();

        if (db!=null){
            ContentValues registro=new ContentValues();
            registro.put("nombre",nombre);
            registro.put("apellido",apellido);
            registro.put("fono",fono);

            long nfilas=db.insert("tblpersona",null,registro);

            if(nfilas>0){
                //Toast.makeText(this, "nfilas="+nfilas, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Registro insertado con exito", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Error al insertar", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void CambiarActivity(View view){
        Intent intent=new Intent(this,ListarUsuario.class);
        startActivity(intent);
    }
}
