package com.example.stunna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class UpddateUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upddate_user);

        Spinner spinner = findViewById(R.id.spinner_selection);

        // Array de itens de exemplo para o Spinner
        String[] items = {"Escolha Genero", "Masculino", "Feminino"};

        // Adapter para conectar os itens ao Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spnH = findViewById(R.id.sspnHorario);

        // Array de itens de exemplo para o Spinner
        String[] itemsH = {"Escolha a Hora", "12h", "20h"};

        // Adapter para conectar os itens ao Spinner
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemsH);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnH.setAdapter(adapter2);

        Spinner spnE = findViewById(R.id.spnEscalao);

        // Array de itens de exemplo para o Spinner
        String[] itemsE = {"Escolha o Escalão", "Masculino", "Feminino"};

        // Adapter para conectar os itens ao Spinner
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemsE);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnE.setAdapter(adapter3);
    }
}