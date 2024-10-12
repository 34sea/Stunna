package com.example.stunna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.stunna.DBHelper.PagamentoDB;
import com.example.stunna.DBHelper.SocioDB;
import com.example.stunna.model.Pagamento;
import com.example.stunna.model.Socio;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    private LinearLayout btnUs, btnLo;
    private LinearLayout btnEst;
    private LinearLayout btnS;
    private LinearLayout btnUser;
    private LinearLayout btnAddU;
    private TextView nomeAm, nome1, nome2, nome3, valor1, valor2, valor3, data1, data2, data3, userNum, divi, vTotal, valorr1, valorr2, valorr3;
    ArrayList<Socio> listaP2;
    SocioDB bdHelp;
    Socio p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnUs = findViewById(R.id.btnUser);
        btnEst = findViewById(R.id.btnEstatistica);
        btnS = findViewById(R.id.btnSettings);
        btnUser = findViewById(R.id.user1);
        btnAddU = findViewById(R.id.btnAddUs);
        btnLo = findViewById(R.id.btnLogout);

        nomeAm = findViewById(R.id.textNomeAdmin);
        Intent adminN = getIntent();
        String nomeAdmin = adminN.getStringExtra("nome");
        nome1 = findViewById(R.id.txtNome1);
        nome2 = findViewById(R.id.txtNome2);
        nome3 = findViewById(R.id.txtNome3);

        valor1 = findViewById(R.id.txtValor1);
        valor2 = findViewById(R.id.txtValor2);
        valor3 = findViewById(R.id.txtValor3);

        valorr1 = findViewById(R.id.txtValorr1);
        valorr2 = findViewById(R.id.txtValorr2);
        valorr3 = findViewById(R.id.txtValorr3);

        data1 = findViewById(R.id.txtdata1);
        data2 = findViewById(R.id.txtdata2);
        data3 = findViewById(R.id.txtdata3);

        userNum = findViewById(R.id.txtCountUser);
        divi = findViewById(R.id.txtDividas);
        vTotal = findViewById(R.id.txtValorTotal);

        if(nomeAdmin!=null){
            nomeAm.setText(nomeAdmin);
        }
        btnUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaUser = new Intent(Home.this, TesteList.class);
                startActivity(telaUser);
            }
        });



        btnEst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaUser = new Intent(Home.this, ListPayment.class);
                startActivity(telaUser);
            }
        });

        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaAdmin = new Intent(Home.this, adminacount.class);
                startActivity(telaAdmin);
            }
        });
//
//        btnUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent telaUser = new Intent(Home.this, Detalhes.class);
//                startActivity(telaUser);
//            }
//        });

        btnAddU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaAddUser = new Intent(Home.this, AddUser.class);
                telaAddUser.putExtra("btnbackCadastro", "admin");
                telaAddUser.putExtra("nomeAdmin", nomeAdmin);
                startActivity(telaAddUser);
            }
        });

        btnLo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaAddUser = new Intent(Home.this, MainActivity.class);
                startActivity(telaAddUser);
            }
        });
    }

    protected void onResume(){
        super.onResume();
        carregarListaSocio();
    }

    public void carregarListaSocio() {
        bdHelp = new SocioDB(Home.this);
        listaP2 = bdHelp.getListaSocio2();
        bdHelp.close();
        if(listaP2!=null) {
            if(listaP2.size()>3){
                nome1.setText(listaP2.get(0).getNome());
                nome2.setText(listaP2.get(1).getNome());
                nome3.setText(listaP2.get(2).getNome());
                if(listaP2.get(0).getPagamento().equals("false")){
                    valor1.setText(String.valueOf(listaP2.get(0).getValor())+",00mt");
                    valor1.setTextColor(Color.parseColor("#ff0000"));
                    valorr1.setText("Valor em divida: ");
                } else if (listaP2.get(0).getPagamento().equals("true")) {
                    valor1.setText(String.valueOf(listaP2.get(0).getValor())+",00mt");
                    valor1.setTextColor(Color.parseColor("#00764c"));
                    valorr1.setText("Valor pago: ");
                }

                if(listaP2.get(1).getPagamento().equals("false")){
                    valor2.setText(String.valueOf(listaP2.get(1).getValor())+",00mt");
                    valor2.setTextColor(Color.parseColor("#ff0000"));
                    valorr2.setText("Valor em divida: ");
                }else if (listaP2.get(1).getPagamento().equals("true")) {
                    valor2.setText(String.valueOf(listaP2.get(1).getValor())+",00mt");
                    valor2.setTextColor(Color.parseColor("#00764c"));
                    valorr2.setText("Valor pago: ");
                }

                if(listaP2.get(2).getPagamento().equals("false")){
                    valor3.setText(String.valueOf(listaP2.get(2).getValor())+",00mt");
                    valor3.setTextColor(Color.parseColor("#ff0000"));
                    valorr3.setText("Valor em divida: ");
                }else if (listaP2.get(2).getPagamento().equals("true")) {
                    valor3.setText(String.valueOf(listaP2.get(2).getValor())+",00mt");
                    valor3.setTextColor(Color.parseColor("#00764c"));
                    valorr3.setText("Valor pago: ");
                }

                data1.setText(String.valueOf(listaP2.get(0).getData()));
                data2.setText(String.valueOf(listaP2.get(1).getData()));
                data3.setText(String.valueOf(listaP2.get(2).getData()));
            }

            userNum.setText(String.valueOf(listaP2.size() < 10 ? "0" + listaP2.size() : listaP2.size()));
            int valorL = 0;
            int valorL2 = 0;

            for (int i = 0; i < listaP2.size(); i++) {
                if(listaP2.get(i).getPagamento().toString().equals("false")){
                    valorL += listaP2.get(i).getValor();
                }else{
                    valorL2 += listaP2.get(i).getValor();
                }

            }

            divi.setText(String.valueOf(valorL));
            vTotal.setText(String.valueOf(valorL2));

        }
    }
}