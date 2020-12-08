package com.example.adsegundapractica.model.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.adsegundapractica.model.entity.Amigo;
import java.util.List;

@Dao
public interface AmigoDao {

    @Query("select * from amigo order by id")
    LiveData<List<Amigo>> getAllLive();

    @Query("delete from amigo where id = :id")
    void deleteAmigo(int id);

    @Query("delete from amigo")
    void deleteAll();

    @Query("select * from amigo where nombre = :amigo")
    Amigo getNom(String amigo);

    @Query("select * from amigo where telef = :amigo")
    Amigo getTelef(String amigo);

    @Query("select * from amigo where fechNac = :amigo")
    Amigo getFechNac(String amigo);

    @Query("select id from amigo where telef = :numero")
    int getIdAmigo(String numero);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Amigo amigo);

    @Update
    void update(Amigo amigo);
}

