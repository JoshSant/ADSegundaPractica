package com.example.adsegundapractica.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.adsegundapractica.model.entity.LLamadas;

import java.util.Date;
import java.util.List;

@Dao
public interface LlamadasDao {

    @Query("select * from llamadas order by id")
    LiveData<List<LLamadas>> getAllLive();

    @Query("delete from llamadas where idAmigo = :llamadas")
    void deleteLlamada(int llamadas);

    @Query("delete from llamadas")
    void deleteAll();

    @Query("select * from llamadas where idAmigo = :llamada")
    LLamadas getIdAmigo(int llamada);

    @Query("select * from llamadas where fechLlam = :llamada")
    LLamadas getFechNac(String llamada);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(LLamadas llamadas);

    @Update
    void update(LLamadas llamadas);

}
