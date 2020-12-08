package com.example.adsegundapractica.model.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "llamadas")
public class LLamadas {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "idAmigo")
    private int idAmigo;

    @NonNull
    @ColumnInfo(name = "fechLlam")
    private String fechLlam;

    public LLamadas(@NonNull int idAmigo, @NonNull String fechLlam) {
        this.idAmigo = idAmigo;
        this.fechLlam = fechLlam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public int getIdAmigo() {
        return idAmigo;
    }

    public void setIdAmigo(@NonNull int idAmigo) {
        this.idAmigo = idAmigo;
    }

    @NonNull
    public String getFechLlam() {
        return fechLlam;
    }

    public void setFechLlam(@NonNull String fechLlam) {
        this.fechLlam = fechLlam;
    }
}
