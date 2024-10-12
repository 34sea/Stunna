package com.example.stunna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.stunna.DBHelper.AdminDB;
import com.example.stunna.model.Admin;

public class Registar extends AppCompatActivity {
    EditText email, senha;
    private TextView txtLog;
    private TextView btnRe;
    Admin admin;
    AdminDB dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);
        admin = new Admin();
        dbHelper = new AdminDB(Registar.this);
        txtLog = findViewById(R.id.txtLoginIr);
        btnRe = findViewById(R.id.btnRegis);
        email = findViewById(R.id.editTextTextEmailAddress);
        senha = findViewById(R.id.editTextNumberPassword);
        txtLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaLogin();
            }
        });

        btnRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin.setNomeAdmin(email.getText().toString());
                admin.setSenhaAdmin(senha.getText().toString());
                dbHelper.salvarAdmin(admin);
                dbHelper.close();
                telaLogin();
            }
        });
    }

    public void telaLogin(){
        String cargoB="admin";
        Intent telaLogin = new Intent(Registar.this, Login.class);
        telaLogin.putExtra("cargo", cargoB);
        startActivity(telaLogin);
    }
}