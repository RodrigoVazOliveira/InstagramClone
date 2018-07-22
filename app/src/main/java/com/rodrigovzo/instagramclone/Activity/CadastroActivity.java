package com.rodrigovzo.instagramclone.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.rodrigovzo.instagramclone.R;
import com.rodrigovzo.instagramclone.Util.ParseErros;

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
        senha           = findViewById(R.id.editTextSenhaUsuario);
        btnCadastrar    = findViewById(R.id.buttonCadastrarUsuario);


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String userName     = usuario.getText().toString();
                String emailUser    = email.getText().toString();
                String passwordUser = senha.getText().toString();

                if (userName.isEmpty()){
                    Toast.makeText(CadastroActivity.this,
                                    "Nome de usuário não foi preeenchido!",
                                    Toast.LENGTH_LONG).show();
                } else if (emailUser.isEmpty()){
                    Toast.makeText(CadastroActivity.this,
                            "e-mail não foi preeenchido!",
                            Toast.LENGTH_LONG).show();
                } else if (passwordUser.isEmpty()){
                    Toast.makeText(CadastroActivity.this,
                            "Senha não foi preeenchido!",
                            Toast.LENGTH_LONG).show();
                }else {

                    // configurando o parse
                    ParseUser cadatroParse = new ParseUser();
                    cadatroParse.setUsername(userName);
                    cadatroParse.setEmail(emailUser);
                    cadatroParse.setPassword(passwordUser);

                    cadatroParse.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {

                            if (e == null){
                                Toast.makeText(CadastroActivity.this,
                                        "Cadastro realizado com sucesso!",
                                        Toast.LENGTH_LONG).show();
                                abrirLoginUsuario();
                            }else {
                                ParseErros erros = new ParseErros();
                                Toast.makeText(CadastroActivity.this,
                                                e.getMessage(),
                                            Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
            }
        });


    }

    public void abrirLoginUsuario(){
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // fechar activity atual
    }
}
