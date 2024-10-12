package com.example.stunna;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.stunna.DBHelper.PagamentoDB;
import com.example.stunna.DBHelper.SocioDB;
import com.example.stunna.model.Pagamento;
import com.example.stunna.model.Socio;

public class Payment extends AppCompatActivity {
    private TextView btnP, txtM;
    Pagamento payU;
    PagamentoDB dbHelper;
    Socio socio, socio2;
    SocioDB dbHelper2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent intTake = getIntent();
        socio = (Socio) intTake.getSerializableExtra("perfil");
        btnP = findViewById(R.id.btnPay);
        txtM = findViewById(R.id.txtValor);
        if(socio!=null){
            txtM.setText(String.valueOf(socio.getValor()));
        }
        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagamentoUser();
            }
        });
    }

    public void pagamentoUser(){
//        socio2.setId(socio.getId());
//        socio2.setNome(socio.getNome().toString());
//        socio2.setTelefone(socio.getTelefone());
//        socio2.setEndereco(socio.getEndereco().toString());
//        socio2.setEmail(socio.getEmail().toString());
//        socio2.setIdade(socio.getIdade());
//        socio2.setData(socio.getData());
//        socio2.setGenero(socio.getGenero());
//        socio2.setHorario(socio.getHorario());
//        socio2.setEscalao(socio.getEscalao());
//        socio2.setValor(Integer.parseInt(txtM.getText().toString()));
//        socio2.setUrlImg(socio.getUrlImg());
//        socio2.setSenha(socio.getSenha());
//        socio2.setPagamento("true");
//        dbHelper2.alterarSocio(socio2);
//        dbHelper2.close();
        Intent telaEdtUser = new Intent(Payment.this, AddUser.class);
        telaEdtUser.putExtra("perfil", socio);
        telaEdtUser.putExtra("cargo", "pagamento");
        startActivity(telaEdtUser);
    }
}