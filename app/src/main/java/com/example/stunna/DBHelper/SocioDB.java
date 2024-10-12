package com.example.stunna.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.stunna.model.Socio;

import java.util.ArrayList;

public class SocioDB extends SQLiteOpenHelper {
    private static final String DATABASE = "bdsocio";
    private static final int VERSION = 1;
    public SocioDB(Context context){
        super(context, DATABASE, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String socio = "CREATE TABLE socios(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome TEXT NOT NULL, telefone INTEGER NOT NULL, endereco TEXT NOT NULL, email TEXT NOT NULL, genero TEXT NOT NULL, idade INTEGER NOT NULL, horario TEXT NOT NULL, escalao TEXT NOT NULL, data TEXT NOT NULL, valor INTEGER NOT NULL, urlImg TEXT NOT NULL, senha TEXT NOT NULL, pagamento TEXT NOT NULL);";
        db.execSQL(socio);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String socio = "DROP TABLE IF EXISTS socios";
        db.execSQL(socio);
    }

    public void salvarSocio(Socio socio){
        ContentValues values = new ContentValues();
        values.put("nome",socio.getNome());
        values.put("telefone",socio.getTelefone());
        values.put("endereco",socio.getEndereco());
        values.put("email",socio.getEmail());
        values.put("genero",socio.getGenero());
        values.put("idade",socio.getIdade());
        values.put("horario",socio.getHorario());
        values.put("escalao",socio.getEscalao());
        values.put("data",socio.getData());
        values.put("valor",socio.getValor());
        values.put("urlImg",socio.getUrlImg());
        values.put("senha",socio.getSenha());
        values.put("pagamento",socio.getPagamento());

        getWritableDatabase().insert("socios",null,values);
    }

    public void alterarSocio(Socio socio){
        ContentValues values = new ContentValues();
        values.put("nome",socio.getNome());
        values.put("telefone",socio.getTelefone());
        values.put("endereco",socio.getEndereco());
        values.put("email",socio.getEmail());
        values.put("genero",socio.getGenero());
        values.put("idade",socio.getIdade());
        values.put("horario",socio.getHorario());
        values.put("escalao",socio.getEscalao());
        values.put("data",socio.getData());
        values.put("valor",socio.getValor());
        values.put("urlImg",socio.getUrlImg());
        values.put("senha",socio.getSenha());
        values.put("pagamento",socio.getPagamento());

        String [] args = {String.valueOf(socio.getId())};
        getWritableDatabase().update("socios",values,"id=?",args);
    }

    public void deletarSocio(Socio socio){
        String [] args = {String.valueOf(socio.getId())};
        getWritableDatabase().delete("socios","id=?",args);
    }

    public ArrayList<Socio> getListaSocio(){
        String [] columns = {"id","nome", "telefone", "endereco", "email","genero", "idade", "horario", "escalao","data", "valor", "urlImg", "senha", "pagamento"};
        Cursor cursor = getWritableDatabase().query("socios",columns,null,null,null,null,null,null);
        ArrayList<Socio> soc = new ArrayList<Socio>();

        while(cursor.moveToNext()){
            Socio socio = new Socio();
            socio.setId(cursor.getLong(0));
            socio.setNome(cursor.getString(1));
            socio.setTelefone(cursor.getInt(2));
            socio.setEndereco(cursor.getString(3));
            socio.setEmail(cursor.getString(4));
            socio.setGenero(cursor.getString(5));
            socio.setIdade(cursor.getInt(6));
            socio.setHorario(cursor.getString(7));
            socio.setEscalao(cursor.getString(8));
            socio.setData(cursor.getString(9));
            socio.setValor(cursor.getInt(10));
            socio.setUrlImg(cursor.getString(11));
            socio.setSenha(cursor.getString(12));
            socio.setPagamento(cursor.getString(13));
            soc.add(socio);
        }

        return soc;
    }
    public ArrayList<Socio> getListaSocio2() {
        String [] columns = {"id","nome", "telefone", "endereco", "email","genero", "idade", "horario", "escalao","data", "valor", "urlImg", "senha", "pagamento"};
        Cursor cursor = getWritableDatabase().query("socios",columns,null,null,null,null,null,null);
        ArrayList<Socio> soc = new ArrayList<Socio>();
        while(cursor.moveToNext()){
            Socio socio = new Socio();
            socio.setId(cursor.getLong(0));
            socio.setNome(cursor.getString(1));
            socio.setTelefone(cursor.getInt(2));
            socio.setEndereco(cursor.getString(3));
            socio.setEmail(cursor.getString(4));
            socio.setGenero(cursor.getString(5));
            socio.setIdade(cursor.getInt(6));
            socio.setHorario(cursor.getString(7));
            socio.setEscalao(cursor.getString(8));
            socio.setData(cursor.getString(9));
            socio.setValor(cursor.getInt(10));
            socio.setUrlImg(cursor.getString(11));
            socio.setSenha(cursor.getString(12));
            socio.setPagamento(cursor.getString(13));
            soc.add(socio);
        }
        return soc;
    }
}
