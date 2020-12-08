package com.example.adsegundapractica.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.adsegundapractica.model.AmigoRepository;
import com.example.adsegundapractica.model.entity.Amigo;
import com.example.adsegundapractica.model.entity.LLamadas;

import java.util.List;

public class AndroidAmigoViewModel extends AndroidViewModel {

    private AmigoRepository repository;

    private LiveData<List<Amigo>> liveAmigos;
    private LiveData<List<LLamadas>> liveLlamadas;

    public AndroidAmigoViewModel(@NonNull Application application) {
        super(application);
        repository = new AmigoRepository(application);
        liveAmigos = repository.getLiveListaAmigos();
        liveLlamadas = repository.getLiveListaLlamadas();
    }

    public LiveData<List<Amigo>> getAllAmigos() {
        return liveAmigos;
    }
    public LiveData<List<LLamadas>> getAllLlamadas() {
        return liveLlamadas;
    }

    public void insert(Amigo amigo) {
        repository.insert(amigo);
    }

    public void insertCall(String num, String fechLlam) {
        repository.insertCall(num, fechLlam);
    }

    public void delete(int id){ repository.delete(id); }

    public void edit(Amigo amigo){repository.edit(amigo);}

}
