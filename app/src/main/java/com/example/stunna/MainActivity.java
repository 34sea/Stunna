package com.example.stunna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtLog;
    private TextView txtSig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLog = findViewById(R.id.txtLogin);
        txtSig = findViewById(R.id.txtSignUp);

        txtLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Choose.class);
                intent.putExtra("nome", "login");
                startActivity(intent);
            }
        });

        txtSig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Choose.class);
                intent.putExtra("nome", "sigup");
                startActivity(intent);
            }
        });
    }
}