package com.example.tayiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaProductos extends AppCompatActivity {

    private ListView listView;
    private TextView tv1;
    ArrayList<String> productos = new ArrayList<String>();
    ArrayList<String> precios = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);

        listView = (ListView) findViewById (R.id.lv1);
        tv1=(TextView) findViewById(R.id.txt1);
        ConsultaProductos();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item_bar,productos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv1.setText("El precio de "+listView.getItemAtPosition(i)+" es $"+ precios.get(i)+" pesos");
            }
        });

    }

    private void ConsultaProductos(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor fila = BaseDeDatos.rawQuery("select descripcion, precio from articulos ",null);

            while (fila.moveToNext()) {
                productos.add(fila.getString(0));
                precios.add(fila.getString(1));
                BaseDeDatos.close();
            }
    }
}
