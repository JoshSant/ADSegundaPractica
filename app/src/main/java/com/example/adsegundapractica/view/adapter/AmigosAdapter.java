package com.example.adsegundapractica.view.adapter;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.adsegundapractica.model.entity.Amigo;
import com.example.adsegundapractica.model.entity.LLamadas;
import com.example.adsegundapractica.view.activity.AmigosActivity;

import java.util.List;

public class AmigosAdapter extends ListAdapter<Amigo, AmigosViewHolder> {

    public AmigosAdapter( @NonNull DiffUtil.ItemCallback<Amigo> diffCallback) {
        super(diffCallback);

    }

    @NonNull
    @Override
    public AmigosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return AmigosViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull AmigosViewHolder holder, int position) {
        Amigo current = getItem(position);
        int numLlamadas = AmigosActivity.numeroLlamadas(current);
        Log.v("xyzyx", ""+current.getNombre() + "," + current.getTelef());
        holder.bind("Nombre: " + current.getNombre() +
                "\nTeléfono: " + current.getTelef() +
                "\nFecha de nacimiento: " + current.getFechNac() +
                "\nNúmero de llamadas recibidas: " + numLlamadas, position);
    }

    public static class AmigoDiff extends DiffUtil.ItemCallback<Amigo> {

        @Override
        public boolean areItemsTheSame(@NonNull Amigo oldItem, @NonNull Amigo newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Amigo oldItem, @NonNull Amigo newItem) {
            return oldItem.getTelef().equals(newItem.getTelef());
        }
    }
}