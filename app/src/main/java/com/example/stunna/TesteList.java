package com.example.stunna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.stunna.DBHelper.SocioDB;
import com.example.stunna.model.Socio;

import java.util.ArrayList;

public class TesteList extends AppCompatActivity {
    ListView lista;
    SocioDB bdHelp;
    ArrayList<Socio> listaP2;
    Socio socio;
    ArrayAdapter adapter;
    private Button btnIr;
    private TextView txtA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_list);

        lista = (ListView) findViewById(R.id.testeUsers);
        txtA = findViewById(R.id.txto);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){
                Socio s = (Socio) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(TesteList.this, AddUser.class);
                intent.putExtra("socio-escolhido", s);
                startActivity(intent);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id){
                socio = (Socio) adapterView.getItemAtPosition(position);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem menuDelete = menu.add("Deletar Este Socio");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {

                bdHelp =  new SocioDB((TesteList.this));
                bdHelp.deletarSocio(socio);
                bdHelp.close();
                Intent intent = new Intent(TesteList.this, TesteList.class);
                startActivity(intent);
                return true;
            }
        });
    }

    protected void onResume(){
        super.onResume();
        carregarListaSocio();
    }

    public void carregarListaSocio() {
        bdHelp = new SocioDB(TesteList.this);
        listaP2 = bdHelp.getListaSocio2();
        bdHelp.close();
        if(listaP2!=null){
            adapter = new ArrayAdapter<Socio>(TesteList.this, android.R.layout.simple_list_item_1, listaP2);
            lista.setAdapter(adapter);
//            txtA.setText(listaP2.get(0).getNome());
        }else{
            txtA.setText("Vazio");
        }
    }
}