package com.example.adsegundapractica.view.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adsegundapractica.R;
import com.example.adsegundapractica.view.activity.AmigosActivity;

public class AmigosViewHolder extends RecyclerView.ViewHolder {

    private final AmigosActivity amigosActivity;
    private final ImageButton btEditar;
    private final ImageButton btBorrar;
    private final TextView tvAmigo;

    public AmigosViewHolder(@NonNull View itemView) {
        super(itemView);
        this.amigosActivity = (AmigosActivity) itemView.getContext();
        this.btEditar = itemView.findViewById(R.id.btEditar);
        this.btBorrar = itemView.findViewById(R.id.btBorrar);
        this.tvAmigo = itemView.findViewById(R.id.tvAmigo);
    }

    @SuppressLint("ResourceType")
    public void bind(String text, int position) {
        tvAmigo.setText(text);

        Log.v("xyz", "" + position);

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amigosActivity.edit(position);
            }
        });

        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amigosActivity.delete(position);
            }
        });
    }

    static AmigosViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new AmigosViewHolder(view);
    }
}
