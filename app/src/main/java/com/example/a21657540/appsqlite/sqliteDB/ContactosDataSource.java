package com.example.a21657540.appsqlite.sqliteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a21657540.appsqlite.model.Contacto;

import java.util.ArrayList;

/**
 * Created by 21657540 on 12/02/2018.
 */

public class ContactosDataSource {

    private Context miContext;
    private ContactosSQLiteHelper miSqlHelper;

    public ContactosDataSource(Context miContext) {
        this.miContext = miContext;
        this.miSqlHelper = new ContactosSQLiteHelper(miContext);
    }


    public SQLiteDatabase openReadable() {
        return miSqlHelper.getReadableDatabase();
    }
    public SQLiteDatabase openWriteable() {
        return miSqlHelper.getWritableDatabase();
    }

    public void close(SQLiteDatabase database) {
        database.close();
    }


    public long insertarContacto(Contacto contacto) {
        //Abrimos la BD(database)
        SQLiteDatabase database = openWriteable();
        //Iniciamos la transacción
        database.beginTransaction();
        //Instanciamos clase esquema de la BD
        ContentValues contactoValues = new ContentValues();
        //Añadimos lo que se debe insertar en la tabla (clave - valor)
        contactoValues.put(ContactosDBContract.ContactoEntry.COLUMN_NAME,contacto.getNombre());
        contactoValues.put(ContactosDBContract.ContactoEntry.COLUMN_MAIL,contacto.getEmail());
        long idContacto = database.insert(ContactosDBContract.ContactoEntry.TABLE_NAME, null,contactoValues);

        if(idContacto != -1){
            database.setTransactionSuccessful();
        }
        database.endTransaction();
        //cerramos la base de datos
        close(database);
        //retornamos el IDContacto para un futuro uso
        return idContacto;
    }

    public void modificarContacto(Contacto contacto){
        //Abrimos la BD(database)
        SQLiteDatabase database = openWriteable();
        //Iniciamos la transacción
        database.beginTransaction();
        //Instanciamos clase esquema de la BD
        ContentValues contactoValues = new ContentValues();
        //añadimos las modificaciones
        contactoValues.put(ContactosDBContract.ContactoEntry.COLUMN_NAME, contacto.getNombre());
        contactoValues.put(ContactosDBContract.ContactoEntry.COLUMN_MAIL, contacto.getEmail());

        //modificamos
        String where1 = ContactosDBContract.ContactoEntry.COLUMN_ID + " = " + contacto.getId();
        database.update(ContactosDBContract.ContactoEntry.TABLE_NAME, contactoValues, where1, null);

        /*Otras formas
         database.update(ContactosDBContract.ContactoEntry.TABLE_NAME, contactoValues, String.format("%s=%d",
                ContactosDBContract.ContactoEntry.COLUMN_ID, contacto.getId()), null);

        String where2 = ContactosDBContract.ContactoEntry.COLUMN_ID + " = ?";
        String [] argd = {String.valueOf(contacto.getId())};
        database.update(ContactosDBContract.ContactoEntry.TABLE_NAME, contactoValues, where2, null);
        */
        database.setTransactionSuccessful();
        database.endTransaction();
        //cerramos la base de datos
        close(database);
    }

    public void borrarContacto(long idContacto){
        //Abrimos la BD(database)
        SQLiteDatabase database = openWriteable();
        //Iniciamos la transacción
        database.beginTransaction();

        /* Otra forma
        String [] args = {String.valueOf(idContacto)};
        database.delete(ContactosDBContract.ContactoEntry.TABLE_NAME,
                ContactosDBContract.ContactoEntry.COLUMN_ID + "=?", args);
          */
        database.delete(ContactosDBContract.ContactoEntry.TABLE_NAME,
                ContactosDBContract.ContactoEntry.COLUMN_ID + "="+idContacto, null);

        database.endTransaction();
        //cerramos la base de datos
        close(database);
    }


    public Contacto leerContacto(long idContacto){
        //Abrimos la Base de datos en modo lectura
        SQLiteDatabase database = openReadable();
        Contacto contacto = new Contacto();

        //Ejecutamos consulta
        String sentencia = "SELECT " + ContactosDBContract.ContactoEntry.COLUMN_ID + ", "
                + ContactosDBContract.ContactoEntry.COLUMN_NAME + ", "
                + ContactosDBContract.ContactoEntry.COLUMN_MAIL
                +" FROM contactos WHERE"
                + ContactosDBContract.ContactoEntry.COLUMN_ID + " = " +idContacto ;
        Cursor miCursor = database.rawQuery(sentencia, null);
        //Recorremos el cursor
        String nombre;
        String email;
        if(miCursor.moveToFirst()){
            nombre = miCursor.getString(miCursor.getColumnIndex(ContactosDBContract.ContactoEntry.COLUMN_NAME));
            email =  miCursor.getString(miCursor.getColumnIndex(ContactosDBContract.ContactoEntry.COLUMN_MAIL));
            contacto = new Contacto(idContacto, nombre, email);
        }
        database.endTransaction();
        //cerramos la base de datos
        close(database);
        return contacto;
    }

    public ArrayList<Contacto> leerContactos(){
        ArrayList<Contacto> misContactos = new ArrayList<Contacto>();
        SQLiteDatabase database = openReadable();


        String [] columnas = {ContactosDBContract.ContactoEntry.COLUMN_ID,
                                ContactosDBContract.ContactoEntry.COLUMN_NAME,
                                ContactosDBContract.ContactoEntry.COLUMN_MAIL};
        Cursor miCursor = database.query(ContactosDBContract.ContactoEntry.TABLE_NAME, columnas,
                    null, null,null,null,null);
        long idContacto;
        String nombre, email;
        Contacto contacto = null;
        if (miCursor.moveToFirst()) {
            do {
                idContacto = miCursor.getLong(miCursor.getColumnIndex(ContactosDBContract.ContactoEntry.COLUMN_ID));
                nombre = miCursor.getString(miCursor.getColumnIndex(ContactosDBContract.ContactoEntry.COLUMN_NAME));
                email = miCursor.getString(miCursor.getColumnIndex(ContactosDBContract.ContactoEntry.COLUMN_MAIL));
                contacto = new Contacto(idContacto, nombre, email);
                misContactos.add(contacto);
            } while (miCursor.moveToNext());
        }
        miCursor.close();
        database.close();
        return misContactos;
    }

}
