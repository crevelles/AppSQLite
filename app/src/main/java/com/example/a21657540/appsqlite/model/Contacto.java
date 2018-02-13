package com.example.a21657540.appsqlite.model;

import java.io.Serializable;

/**
 * Created by 21657540 on 12/02/2018.
 */

public class Contacto implements Serializable {

    private long id;
    private String nombre, email;


    public Contacto() {
    }

    public Contacto(long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public Contacto(String nombre, String email) {
        //inicializamos el ID a -1 porque será INCREMENTAL
        this.id = -1;
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
