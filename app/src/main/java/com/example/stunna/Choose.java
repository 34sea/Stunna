package com.example.stunna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Choose extends AppCompatActivity {
    private TextView btnAdmin;
    private TextView btnUser;
    public String cargoB = "clkd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        Intent telaChoose = getIntent();
        String telaEscolha = telaChoose.getStringExtra("nome");

        btnAdmin = findViewById(R.id.txtAdmin);
        btnUser = findViewById(R.id.txtUser);


        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargoB="admin";
                if(telaEscolha.equals("login")){
                    Intent telaLogin = new Intent(Choose.this, Login.class);
                    telaLogin.putExtra("cargo", cargoB);
                    startActivity(telaLogin);
                }else{
                    Intent telaSign = new Intent(Choose.this, Registar.class);
                    telaSign.putExtra("cargo", cargoB);
                    startActivity(telaSign);
                }
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoB="user";
                if(telaEscolha.equals("login")){
                    Intent telaLogin = new Intent(Choose.this, Login.class);
                    telaLogin.putExtra("cargo", cargoB);
                    startActivity(telaLogin);
                }else{
                    Intent telaSign = new Intent(Choose.this, AddUser.class);
                    telaSign.putExtra("cargo", cargoB);
                    startActivity(telaSign);
                }
            }
        });
    }
}