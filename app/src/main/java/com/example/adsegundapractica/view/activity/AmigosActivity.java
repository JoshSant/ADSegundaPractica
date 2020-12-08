package com.example.adsegundapractica.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.adsegundapractica.R;
import com.example.adsegundapractica.model.entity.Amigo;
import com.example.adsegundapractica.model.entity.LLamadas;
import com.example.adsegundapractica.view.adapter.AmigosAdapter;
import com.example.adsegundapractica.viewmodel.AndroidAmigoViewModel;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;

public class AmigosActivity extends AppCompatActivity {

    private static final int REQUEST_CHOOSE_PHONE = 1;
    int permisoContactos;
    int permisoLlamadas;
    private static List<LLamadas> llamadasLista;
    private static List<Amigo> amigoLista;

    private Button btInsertar;

    private static AndroidAmigoViewModel androidViewModel;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == REQUEST_CHOOSE_PHONE)
                && (resultCode == Activity.RESULT_OK)) {
            try {
                String nombre = data.getStringExtra("nombre");
                String telef = data.getStringExtra("telef");
                Log.v("xyzyx", "nombre"+telef+","+nombre);

                Amigo amigo = new Amigo(nombre, null, telef);

                androidViewModel.insert(amigo);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permisoContactos = ActivityCompat.checkSelfPermission
                (this, Manifest.permission.READ_CONTACTS);
        permisoLlamadas = ActivityCompat.checkSelfPermission
                (this, Manifest.permission.READ_PHONE_STATE);
        androidViewModel = new ViewModelProvider(this).get(AndroidAmigoViewModel.class);

        btInsertar = findViewById(R.id.btInsertar);

        pedirPermisos();

        init();
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final AmigosAdapter adapter = new AmigosAdapter(new AmigosAdapter.AmigoDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        androidViewModel.getAllAmigos().observe(this, new Observer<List<Amigo>>() {
            @Override
            public void onChanged(List<Amigo> amigos) {
                Log.v("xyz", "onChanged: " + amigos.toString());
                adapter.submitList(amigos);
                amigoLista = amigos;
            }
        });

        androidViewModel.getAllLlamadas().observe(this, new Observer<List<LLamadas>>() {
            @Override
            public void onChanged(List<LLamadas> Llamadas) {
                llamadasLista = Llamadas;
            }
        });



        btInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(permisosPermitidos()) {
                    Intent intent = new Intent(AmigosActivity.this, IntroducirActivity.class);
                    startActivityForResult(intent, REQUEST_CHOOSE_PHONE);
                }else{
                    pedirPermisos();
                }
            }
        });
    }

    public void delete(int position){
        androidViewModel.delete
                (androidViewModel.getAllAmigos().getValue().get(position).getId());
    }

    public void edit(int position){
        Intent intent = new Intent(AmigosActivity.this, EditarActivity.class);
        intent.putExtra("id", amigoLista.get(position).getId());
        intent.putExtra("nombre", amigoLista.get(position).getNombre());
        intent.putExtra("telef", amigoLista.get(position).getTelef());
        startActivity(intent);
    }

    public static int numeroLlamadas(Amigo amigo){
        int llamadas = 0;

        if(llamadasLista.size() != 0) {
            for (int i = 0; i <= llamadasLista.size() - 1; i++) {
                if (llamadasLista.get(i).getIdAmigo() == amigo.getId()) {
                    llamadas = llamadas + 1;
                }
            }
        }

        return llamadas;
    }

    public static void llamada(String incomingNumber, String dateString){
        for (int i = 0; i<androidViewModel.getAllAmigos().getValue().size(); i++) {
            if (androidViewModel.getAllAmigos().getValue().get(i).getTelef().equals(incomingNumber)) {
                androidViewModel.insertCall(incomingNumber, dateString);
            }
        }
    }

    private void pedirPermisos(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS,
                    Manifest.permission.READ_PHONE_STATE}, REQUEST_CHOOSE_PHONE);
        }

    }

    private boolean permisosPermitidos(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }

}