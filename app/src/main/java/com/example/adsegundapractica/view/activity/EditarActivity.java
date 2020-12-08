package com.example.adsegundapractica.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.adsegundapractica.R;
import com.example.adsegundapractica.model.entity.Amigo;
import com.example.adsegundapractica.view.dialog.DatePickerFragment;
import com.example.adsegundapractica.viewmodel.AndroidAmigoViewModel;
import com.google.android.material.snackbar.Snackbar;

public class EditarActivity extends AppCompatActivity {

    private int id;

    private Intent intent;

    private EditText etNombre, etTelef, etFechNac;

    private AndroidAmigoViewModel androidViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        id = getIntent().getIntExtra("id", 0);
        androidViewModel = new ViewModelProvider(this).
                get(AndroidAmigoViewModel.class);

        init();
    }

    private void init() {

        etFechNac = findViewById(R.id.etFechNac);
        etNombre = findViewById(R.id.etNombre);
        etTelef = findViewById(R.id.etTelefono);

        etNombre.setText(getIntent().getStringExtra("nombre"));
        etTelef.setText(getIntent().getStringExtra("telef"));

        etFechNac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        Button btEditar = findViewById(R.id.btEditarEd);
        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(etNombre.getText()) ||
                        TextUtils.isEmpty(etTelef.getText())) {
                    Snackbar.make(v, "Algún campo esta vacío", Snackbar.LENGTH_LONG).show();
                } else {
                    String nombre = etNombre.getText().toString();
                    String telef = etTelef.getText().toString();
                    String fechNac;
                    if (etFechNac.getText().toString().isEmpty()){
                        fechNac = null;
                    }else {
                        fechNac = etFechNac.getText().toString();
                    }

                    Amigo amigo = new Amigo(nombre, fechNac, telef);
                    amigo.setId(id);

                    androidViewModel.edit(amigo);
                }
                finish();
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                etFechNac.setText(selectedDate);
            }
        });

        newFragment.show(this.getSupportFragmentManager(), "datePicker");
    }

}