package com.example.stunna;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.stunna.DBHelper.AdminDB;
import com.example.stunna.DBHelper.SocioDB;
import com.example.stunna.model.Admin;
import com.example.stunna.model.Socio;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    EditText email, senha;
    private TextView txtReg;
    private  TextView btnLog;

    AdminDB bdHelp;
    SocioDB bdHelp2;
    ArrayList<Admin> listaP2;
    ArrayList<Socio> listaP3;
    Admin socio;
    ArrayAdapter adapter;
    public String e, s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtReg = findViewById(R.id.txtRegistrarIr);
        btnLog = findViewById(R.id.btnLogin);
        Intent receberI = getIntent();
        String cargo = receberI.getStringExtra("cargo");
        email = findViewById(R.id.editTextTextEmailAddress);
        senha = findViewById(R.id.editTextNumberPassword);

        txtReg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(cargo.equals("admin")) {
                    Intent telaRegistrar = new Intent(Login.this, Registar.class);
                    startActivity(telaRegistrar);
                }else{
                    Intent telaRegistrar = new Intent(Login.this, AddUser.class);
                    telaRegistrar.putExtra("cargo", "user");
                    startActivity(telaRegistrar);
                }
            }
        });

        btnLog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(cargo.equals("admin")) {
                    e=email.getText().toString();
                    s=senha.getText().toString();

                    carregarListaSocio(e,s);
                }else{
                    e=email.getText().toString();
                    s=senha.getText().toString();

                    carregarListaSocio2(e,s);

                }
            }
        });
    }



    public void carregarListaSocio(String e2, String s2) {
        bdHelp = new AdminDB(Login.this);
        listaP2 = bdHelp.getListaAdmin();
        bdHelp.close();
        int verify=0;
        if(listaP2!=null){

            for(int i=0; i<listaP2.size(); i++){
                if(e2.equals(listaP2.get(i).getNomeAdmin().toString())){
                    if(s2.equals(listaP2.get(i).getSenhaAdmin().toString())){
                        btnLog.setText("Certo");
                        Intent telaHome = new Intent(Login.this, Home.class);
                        telaHome.putExtra("nome", e2);
                        startActivity(telaHome);
                    }
                    verify=0;
                }
                verify++;
            }
            if(verify>=(listaP2.size())){
                AlertDialog.Builder msg = new AlertDialog.Builder(this);
                msg.setMessage("Dados incorretos");
                msg.show();
                verify=0;
            }

        }
    }

    public void carregarListaSocio2(String e2, String s2) {
        bdHelp2 = new SocioDB(Login.this);
        listaP3 = bdHelp2.getListaSocio2();
        bdHelp2.close();
        int verify=0;
        if(listaP3!=null){

            for(int i=0; i<listaP3.size(); i++){
                if(e2.equals(listaP3.get(i).getNome().toString())){
                    if(s2.equals(listaP3.get(i).getSenha().toString())){
                        btnLog.setText("Certo");
                        Intent telaHome = new Intent(Login.this, Detalhes.class);
                        telaHome.putExtra("nome", e2);
                        telaHome.putExtra("perfil", listaP3.get(i));
                        startActivity(telaHome);

                    }
                    verify=0;
                }
                verify++;
            }
            if(verify>=(listaP3.size())){
                AlertDialog.Builder msg = new AlertDialog.Builder(this);
                msg.setMessage("Dados incorretos");
                msg.show();
                verify=0;
            }

        }
    }
}