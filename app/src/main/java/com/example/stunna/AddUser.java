package com.example.stunna;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.stunna.DBHelper.SocioDB;
import com.example.stunna.model.Socio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddUser extends AppCompatActivity {
    private EditText edtN, edtT, edtE, edtEmail, edtI, edtV, edtS;
    private LinearLayout btnbackC;
    private String edtD, urlI;
    private Spinner spnG2, spnH2, spnEs2;
    private TextView btnL, dt, mnh;
    Socio editarSocio, socio, idUser;
    SocioDB dbHelper;
    String cargoU;
    String pagH = "false", btnBC, nomeAdmi;
    private String selectedItem, selectedItem2, selectedItem3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        socio = new Socio();
        dbHelper = new SocioDB(AddUser.this);
        Intent socioIntent = getIntent();
        editarSocio = (Socio) socioIntent.getSerializableExtra("socio-escolhido");
        cargoU = socioIntent.getStringExtra("cargo");
        idUser = (Socio) socioIntent.getSerializableExtra("perfil");
        btnBC = socioIntent.getStringExtra("btnbackCadastro");
        nomeAdmi = socioIntent.getStringExtra("nomeAdmin");
        Spinner spinner = findViewById(R.id.spnSexo);
        Spinner spinnerE = findViewById(R.id.spnEscalao);
        Spinner spinnerH = findViewById(R.id.spnHorario);

        // Array de itens de exemplo para o Spinner
        String[] items = {"Escolha Genero", "Masculino", "Feminino"};

        // Adapter para conectar os itens ao Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spnH = findViewById(R.id.spnHorario);

        // Configurar o Listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nada a fazer
            }
        });

        // Array de itens de exemplo para o Spinner
        String[] itemsH = {"Escolha a Hora", "06:00 - 08:00", "08:30 - 10:00", "10:30 - 12:00", "13:30 - 15:00", "15:30 - 17:00", "17:30 - 20:00"};

        // Adapter para conectar os itens ao Spinner
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemsH);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerH.setAdapter(adapter2);

        Spinner spnE = findViewById(R.id.spnEscalao);

        // Configurar o Listener
        spinnerH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem2 = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nada a fazer
            }
        });

        // Array de itens de exemplo para o Spinner
        String[] itemsE = {"Escolha o Escalão", "Iniciados", "Juniores", "Seniores", "Pré-federados", "Federados"};



        // Adapter para conectar os itens ao Spinner
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemsE);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerE.setAdapter(adapter3);

        // Configurar o Listener
        spinnerE.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem3 = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nada a fazer
            }
        });


        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(formatter);
        spnE.setAdapter(adapter3);
        edtN = findViewById(R.id.editTextNome);
        edtT = findViewById(R.id.editTextTelefone);
        edtE = findViewById(R.id.editTextEndereco);
        edtEmail = findViewById(R.id.editTextEmail);
        edtI = findViewById(R.id.editTextIdade);
        edtD = formattedDate;
        edtV = findViewById(R.id.editTextMensalidade);
        edtS = findViewById(R.id.editTextSenha);

        dt = findViewById(R.id.lData);
        dt.setText(formattedDate);

        mnh = findViewById(R.id.txtTesteItem);
//        spnG2 = findViewById(R.id.spnSexo);
//        spnH2 = findViewById(R.id.spnHorario);
//        spnEs2 = findViewById(R.id.spnEscalao);
        urlI="Imagem";


        btnL=findViewById(R.id.btnAddUser);

        btnbackC = findViewById(R.id.btnbackCadastroL);


        btnbackC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnBC!=null){
                    if(btnBC.equals("admin")){

                        Intent intent = new Intent(AddUser.this, Home.class);
                        intent.putExtra("nome", nomeAdmi);
                        startActivity(intent);
                    }
                }else{
                    Intent telaHome = new Intent(AddUser.this, Detalhes.class);
                    telaHome.putExtra("nome", idUser.getNome());
                    telaHome.putExtra("perfil", idUser);
                    startActivity(telaHome);
                }
            }
        });

        if(editarSocio!=null || idUser!=null){
            btnL.setText("Modificar");
            if(cargoU!=null){
                if(cargoU.equals("pagamento")){
                    btnL.setText("Confirmar");
                    pagH = "true";
                }
            }


            if(idUser!=null){
                edtN.setText(idUser.getNome());
                edtT.setText(String.valueOf(idUser.getTelefone()));
                edtE.setText(idUser.getEndereco());
                edtEmail.setText(idUser.getEmail());
                edtV.setText(String.valueOf(idUser.getValor()));
                edtI.setText(String.valueOf(idUser.getIdade()));
                edtS.setText(idUser.getSenha());
                socio.setId(idUser.getId());
            }else{
                edtN.setText(editarSocio.getNome());
                edtT.setText(String.valueOf(editarSocio.getTelefone()));
                edtE.setText(editarSocio.getEndereco());
                edtEmail.setText(editarSocio.getEmail());
                edtV.setText(String.valueOf(editarSocio.getValor()));
                edtI.setText(String.valueOf(editarSocio.getIdade()));
                edtS.setText(editarSocio.getSenha());
                socio.setId(editarSocio.getId());
            }


        }else{
            btnL.setText("Cadastrar");
        }

        btnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder msg = new AlertDialog.Builder(AddUser.this);
                msg.setMessage(edtS.getText().toString());
                msg.show();
                socio.setNome(edtN.getText().toString());
                socio.setTelefone(Integer.parseInt(edtT.getText().toString()));
                socio.setEndereco(edtE.getText().toString());
                socio.setEmail(edtEmail.getText().toString());
                socio.setIdade(Integer.parseInt(edtI.getText().toString()));
                socio.setData(edtD);
                socio.setGenero(selectedItem);
//                mnh.setText("Sexo: "+selectedItem+" Horario: "+selectedItem2+" Escalao: "+selectedItem3);
                socio.setHorario(selectedItem2);
                socio.setEscalao(selectedItem3);
                socio.setValor(Integer.parseInt(edtV.getText().toString()));
                socio.setUrlImg(urlI);
                socio.setSenha(edtS.getText().toString());
                socio.setPagamento(pagH);
                if(btnL.getText().toString().equals("Cadastrar")) {

                    dbHelper.salvarSocio(socio);
                    dbHelper.close();
                }else{

                    dbHelper.alterarSocio(socio);

                    dbHelper.close();

                }
                telaSocios();

            }
        });

    }

    public void telaSocios(){
        if(cargoU!=null){
            if(cargoU.equals("user")){
                Intent intent = new Intent(AddUser.this, Login.class);
                intent.putExtra("cargo", "user");
                startActivity(intent);
            } else if (cargoU.equals("detail")) {
//                Intent intent = new Intent(AddUser.this, Detalhes.class);
//                intent.putExtra("perfil", idUser);
//                startActivity(intent);
                Intent intent = new Intent(AddUser.this, Login.class);
                intent.putExtra("cargo", "user");
                startActivity(intent);
            }else if(cargoU.equals("pagamento")) {
                Intent intent = new Intent(AddUser.this, Login.class);
                intent.putExtra("cargo", "user");
                startActivity(intent);
            }

        }else{
            Intent intent = new Intent(AddUser.this, Home.class);
            intent.putExtra("nome", nomeAdmi);
            startActivity(intent);
        }

    }
}