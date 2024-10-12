package com.example.stunna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.stunna.model.Socio;

public class DadodosUser extends AppCompatActivity {

    Socio socio;
    String cargoU;
    private TextView txtN, txtNu, txtE, txtEm, txtMe, txtS;
    String colorI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dadodos_user);
        Intent socioIntent = getIntent();
        cargoU = socioIntent.getStringExtra("cargo");
        socio = (Socio) socioIntent.getSerializableExtra("perfil");

        txtN = findViewById(R.id.txtNomeUser);
        txtNu = findViewById(R.id.txtNum);
        txtE = findViewById(R.id.txtEndereco);
        txtEm = findViewById(R.id.txtEmail);
        txtMe = findViewById(R.id.txtMensalidade);
        txtS = findViewById(R.id.txtStatus);

        if(socio!=null){
            txtN.setText(socio.getNome());
            txtNu.setText(String.valueOf(socio.getTelefone()));
            txtE.setText(socio.getEndereco());
            txtEm.setText(socio.getEmail());
            txtMe.setText(String.valueOf(socio.getValor()));
            if(socio.getPagamento().equals("false")){
                colorI="#ff0000";
                txtS.setText("Em divida");
                txtS.setTextColor(Color.parseColor(colorI));
            }else{
                colorI="#00764c";
                txtS.setText("Pago - "+socio.getData());
                txtS.setTextColor(Color.parseColor(colorI));
            }
        }
    }
}