package com.example.adsegundapractica.model.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "amigo")
public class Amigo {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    @Nullable
    @ColumnInfo(name = "fechNac")
    private String fechNac;

    @NonNull
    @ColumnInfo(name = "telef")
    private String telef;

    public Amigo(@NonNull String nombre, @Nullable String fechNac, @NonNull String telef) {
        this.nombre = nombre;
        this.fechNac = fechNac;
        this.telef = telef;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @Nullable
    public String getFechNac() {
        return fechNac;
    }

    public void setFechNac(@Nullable String fechNac) {
        this.fechNac = fechNac;
    }

    public String getTelef() {
        return telef;
    }

    public void setTelef(String telef) {
        this.telef = telef;
    }
}
