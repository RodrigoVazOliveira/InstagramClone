package com.rodrigovzo.instagramclone.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.rodrigovzo.instagramclone.R;

public class LoginActivity extends AppCompatActivity {


    private EditText loginUsuario;
    private EditText senhaUsuario;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // setando valores da tela
        loginUsuario = findViewById(R.id.editTextUsuario);
        senhaUsuario = findViewById(R.id.editTextSenha);
        btnLogin     = findViewById(R.id.buttonLogin);

        // verificar se o usuário está ligado.
        verificarUsuarioLogado();

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String usuario = loginUsuario.getText().toString();
                    String senha   = senhaUsuario.getText().toString();
                    verificarLogin(usuario, senha);
                }
            });

    }


    private void verificarLogin(String usuario, String senha){
        ParseUser.logInInBackground(usuario, senha, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

                if (e == null){
                    Toast.makeText(LoginActivity.this,
                                    "Login efetuado com sucesso!",
                            Toast.LENGTH_LONG).show();
                    abrirTelaPrincial();
                }else {
                    Toast.makeText(LoginActivity.this,
                            e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void abrirTelaPrincial(){
        Intent intent = new Intent(LoginActivity.this, telaPrincipalActivicty.class);
        startActivity(intent);
        finish(); // finilizar a tela de login
    }

    private void abrirCadatroUsuario( View view ){
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

    private void verificarUsuarioLogado(){

        if (ParseUser.getCurrentUser() != null){
            abrirTelaPrincial();
        }

    }
}
