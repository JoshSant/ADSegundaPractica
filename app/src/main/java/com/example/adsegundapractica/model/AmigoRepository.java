package com.example.adsegundapractica.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.adsegundapractica.AmigoApplication;
import com.example.adsegundapractica.model.dao.AmigoDao;
import com.example.adsegundapractica.model.dao.LlamadasDao;
import com.example.adsegundapractica.model.entity.Amigo;
import com.example.adsegundapractica.model.entity.LLamadas;
import com.example.adsegundapractica.model.room.AmigoDatabase;
import com.example.adsegundapractica.model.room.LlamadasDatabase;

import java.util.List;

public class AmigoRepository {

    private AmigoDatabase dbAmigo;
    private LlamadasDatabase dbLlamada;

    private AmigoDao amigoDao;
    private LlamadasDao llamadasDao;

    private LiveData<List<Amigo>> liveListaAmigos;
    private LiveData<List<LLamadas>> liveListaLlamadas;

    public AmigoRepository(Application context) {
        dbAmigo = AmigoDatabase.getDatabase(context);
        dbLlamada = LlamadasDatabase.getDatabase(context);
        amigoDao = dbAmigo.getAmigoDao();
        llamadasDao = dbLlamada.getLlamadasDao();

        liveListaAmigos = amigoDao.getAllLive();
        liveListaLlamadas = llamadasDao.getAllLive();
    }

    public LiveData<List<Amigo>> getLiveListaAmigos() {
        return liveListaAmigos;
    }
    public LiveData<List<LLamadas>> getLiveListaLlamadas() {
        return liveListaLlamadas;
    }

    public void insert(Amigo amigo) {

        AmigoApplication.EjecutorDeHilos.execute(new Runnable() {
            @Override
            public void run() {
                Log.v("xyz","INSERT");
                amigoDao.insert(amigo);
            }
        });
    }

    public void insertCall(String numero, String fechaLLam) {

        AmigoApplication.EjecutorDeHilos.execute(new Runnable() {
            @Override
            public void run() {
                int idAmigo = amigoDao.getIdAmigo(numero);
                Log.v("xyz", idAmigo + "," + fechaLLam);
                llamadasDao.insert(new LLamadas(idAmigo, fechaLLam));
            }
        });
    }

    public void delete(int id) {

        AmigoApplication.EjecutorDeHilos.execute(new Runnable() {
            @Override
            public void run() {
                amigoDao.deleteAmigo(id);
                llamadasDao.deleteLlamada(id);
            }
        });

    }

    public void edit(Amigo amigo) {

        AmigoApplication.EjecutorDeHilos.execute(new Runnable() {
            @Override
            public void run() {
                amigoDao.update(amigo);
            }
        });

    }

}
