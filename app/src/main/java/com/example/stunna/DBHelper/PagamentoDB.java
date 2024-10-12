package com.example.stunna.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.stunna.model.Pagamento;

import java.util.ArrayList;

public class PagamentoDB extends SQLiteOpenHelper {
    private static final String DATABASE = "bdpagamento";
    private static final int VERSION = 1;
    public PagamentoDB(Context context){
        super(context, DATABASE, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String pagamento = "CREATE TABLE pagamentos(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, data TEXT NOT NULL, valor INTEGER NOT NULL, idSocio TEXT NOT NULL, nome TEXT NOT NULL);";
        db.execSQL(pagamento);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String pagamento = "DROP TABLE IF EXISTS pagamentos";
        db.execSQL(pagamento);
    }

    public void salvarPagamento(Pagamento pagamento){
        ContentValues values = new ContentValues();
        values.put("data",pagamento.getData());
        values.put("valor",pagamento.getValor());
        values.put("idSocio",pagamento.getIdSocio());
        values.put("nome",pagamento.getNome());

        getWritableDatabase().insert("pagamentos",null,values);
    }

    public void alterarPagamento(Pagamento pagamento){
        ContentValues values = new ContentValues();
        values.put("data",pagamento.getData());
        values.put("valor",pagamento.getValor());
        values.put("idSocio",pagamento.getIdSocio());
        values.put("nome",pagamento.getNome());

        String [] args = {String.valueOf(pagamento.getId())};
        getWritableDatabase().update("pagamentos",values,"id=?",args);
    }

    public void deletarPagamento(Pagamento pagamento){
        String [] args = {String.valueOf(pagamento.getId())};
        getWritableDatabase().delete("pagamentos","id=?",args);
    }

    public ArrayList<Pagamento> getListaPagamento(){
        String [] columns = {"id","data", "valor", "idSocio", "nome"};
        Cursor cursor = getWritableDatabase().query("pagamentos",columns,null,null,null,null,null,null);
        ArrayList<Pagamento> pag = new ArrayList<Pagamento>();

        while(cursor.moveToNext()){
            Pagamento pagamento = new Pagamento();
            pagamento.setId(cursor.getLong(0));
            pagamento.setData(cursor.getString(1));
            pagamento.setValor(cursor.getInt(2));
            pagamento.setIdSocio(cursor.getString(3));
            pagamento.setNome(cursor.getString(4));
            pag.add(pagamento);
        }

        return pag;
    }

}
