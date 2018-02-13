package com.example.a21657540.appsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a21657540.appsqlite.model.Contacto;
import com.example.a21657540.appsqlite.recyclerViewUtils.Adaptador;
import com.example.a21657540.appsqlite.sqliteDB.ContactosDataSource;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnConsultar, btnInsertar;
    private RecyclerView rv;
    private ContactosDataSource cds;
    private ArrayList<Contacto> misContactos;
    private Adaptador adaptador;
    private LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnConsultar = findViewById(R.id.btnConsultar);
        btnInsertar = findViewById(R.id.btnInsertar);
        rv = findViewById(R.id.rvContactos);
        cds = new ContactosDataSource(this);
        misContactos = new ArrayList<Contacto>();
        cargarRV();

    }

    private void cargarRV(){
        rv.setHasFixedSize(true);
        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        adaptador = new Adaptador(misContactos);
        rv.setAdapter(adaptador);
    }

    public  void consultar(View view){
        misContactos = cds.leerContactos();
        if(misContactos.size() == 0){
            Toast.makeText(this, "No se ha encontrado ningun contacto", Toast.LENGTH_LONG).show();
        } else {
            cargarRV();
        }
    }

    public void insertar(View view){
        Intent intent = new Intent(this, InsertarActivity.class);
        startActivity(intent);
    }
}
