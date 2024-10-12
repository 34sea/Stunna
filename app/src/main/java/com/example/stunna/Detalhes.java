package com.example.stunna;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.stunna.model.Socio;

public class Detalhes extends AppCompatActivity {

    private LinearLayout btnEd, btnPa, txtDe, btnLoL;
    private TextView txtNome;
    Socio editarSocio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        Intent intTake = getIntent();
        editarSocio = (Socio) intTake.getSerializableExtra("perfil");
        txtNome = findViewById(R.id.txtNomeUser);
        txtNome.setText(editarSocio.getNome().toString());

        btnLoL = findViewById(R.id.btnLogoutL);

        btnLoL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaAddUser = new Intent(Detalhes.this, MainActivity.class);
                startActivity(telaAddUser);
            }
        });

        btnEd = findViewById(R.id.btnEdit);
        btnEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaEdtUser = new Intent(Detalhes.this, AddUser.class);
                telaEdtUser.putExtra("perfil", editarSocio);
                telaEdtUser.putExtra("cargo", "detail");
                startActivity(telaEdtUser);
            }
        });

        btnPa = findViewById(R.id.btnPayment);
        btnPa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editarSocio.getPagamento().equals("false")) {
                    Intent telaEdtUser = new Intent(Detalhes.this, Payment.class);
                    telaEdtUser.putExtra("perfil", editarSocio);
                    startActivity(telaEdtUser);
                }else{
                    AlertDialog.Builder msg = new AlertDialog.Builder(Detalhes.this);
                    msg.setMessage("O pagamento ja foi efetuado");
                    msg.show();
                }
            }
        });


        txtDe = findViewById(R.id.txtDetailPerfil);

        txtDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaEdtUser = new Intent(Detalhes.this, DadodosUser.class);
                telaEdtUser.putExtra("perfil", editarSocio);
                telaEdtUser.putExtra("cargo", "user");
                startActivity(telaEdtUser);
            }
        });


    }
}