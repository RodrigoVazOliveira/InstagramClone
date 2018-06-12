package com.rodrigovzo.instagramclone.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rodrigovzo.instagramclone.R;

public class CadastroActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText email;
    private EditText senha;
    private Button btnCadastrar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // setando variaveis
        usuario         = findViewById(R.id.editTextUsuario);
        email           = findViewById(R.id.editTextEmail);
        senha           = findViewById(R.id.editTextSenha);
        btnCadastrar    = findViewById(R.id.buttonCadastrarUsuario);


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });


    }

    public void abrirLoginUsuario(View view){
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
