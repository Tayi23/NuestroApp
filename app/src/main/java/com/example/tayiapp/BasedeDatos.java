package com.example.tayiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BasedeDatos extends AppCompatActivity {

    private EditText et_codigo, et_descripcion, et_precio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basede_datos);

        et_codigo=(EditText)findViewById(R.id.txt_codigo);
        et_descripcion=(EditText)findViewById(R.id.txt_descripcion);
        et_precio=(EditText)findViewById(R.id.txt_precio);
    }

    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo= et_codigo.getText().toString();
        String descripcion= et_descripcion.getText().toString();
        String precio= et_precio.getText().toString();

        if(!codigo.isEmpty()&& !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio",precio);

            BaseDeDatos.insert("articulos", null, registro);
            BaseDeDatos.close();

            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            Toast.makeText(this, "Registrado con Exito!!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Complete todo los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void  Buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo= et_codigo.getText().toString();


        if(!codigo.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("select descripcion, precio from articulos where codigo="+codigo,null);

            if(fila.moveToFirst()){
                et_descripcion.setText(fila.getString(0));
                et_precio.setText((fila.getString(1)));
                BaseDeDatos.close();
            }else{
                Toast.makeText(this, "No Existe el Producto", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Complete todo los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void Modificar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo= et_codigo.getText().toString();
        String descripcion= et_descripcion.getText().toString();
        String precio= et_precio.getText().toString();

        if(!codigo.isEmpty()&& !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio",precio);

            int cantidad = BaseDeDatos.update("articulos", registro,"codigo="+codigo,null);
            BaseDeDatos.close();

           if(cantidad == 1){
               Toast.makeText(this, "Modificacion Exitosa", Toast.LENGTH_SHORT).show();

           }else {
               Toast.makeText(this, "Producto no Existe", Toast.LENGTH_SHORT).show();
           }

        }else{
            Toast.makeText(this, "Complete todo los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void Eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos= admin.getWritableDatabase();

        String codigo= et_codigo.getText().toString();

        if(!codigo.isEmpty()){
            int cantidad = BaseDeDatos.delete("articulos","codigo="+codigo,null);
            BaseDeDatos.close();

            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            if (cantidad == 1){
                Toast.makeText(this, "Eliminado con Exito", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "El Producto no Existe", Toast.LENGTH_SHORT).show();
            }


        }else{
            Toast.makeText(this, "Ingrese el codigo del producto ", Toast.LENGTH_SHORT).show();
        }

    }


}
