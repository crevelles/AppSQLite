package com.example.a21657540.appsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.WindowDecorActionBar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a21657540.appsqlite.model.Contacto;
import com.example.a21657540.appsqlite.sqliteDB.ContactosDataSource;

public class InsertarActivity extends AppCompatActivity {

    private EditText tvNombre, email;
    ContactosDataSource cds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        tvNombre = findViewById(R.id.etNombre);
        email = findViewById(R.id.etMail);
        cds = new ContactosDataSource(this);
    }

    public void insertarContacto(View view){
        Contacto contacto = new Contacto(tvNombre.getText().toString(), email.getText().toString());
        long idContacto = cds.insertarContacto(contacto);
        if(idContacto != -1){
            Toast.makeText(this, "Realizada la inserción correctamente",
                    Toast.LENGTH_LONG).show();
            tvNombre.setText(null);
            email.setText(null);
        } else {
            Toast.makeText(this, "Error en la inserción",
                    Toast.LENGTH_LONG).show();
        }
    }
}
