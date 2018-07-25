package com.rodrigovzo.instagramclone.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.rodrigovzo.instagramclone.Adapter.HomeAdapter;
import com.rodrigovzo.instagramclone.R;

import java.util.ArrayList;
import java.util.List;

public class FeedUsuarioActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private ListView listView;
    private String username;
    private ArrayAdapter<ParseObject> adapter;
    private ArrayList<ParseObject> postagens;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_usuario);

        // recuperar dados que foram enviados da intent
        Intent intent = getIntent();
        username      = intent.getStringExtra("username").toString();

        // configura a toolbar
        toolbar = findViewById(R.id.toolbar_feed_usuarios);
        toolbar.setTitle(username);
        toolbar.setTitleTextColor(R.color.preto);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        setSupportActionBar(toolbar);


        // configurando list views
        postagens = new ArrayList<>();
        listView = findViewById(R.id.list_feed_usuario);
        adapter  = new HomeAdapter(getApplicationContext(),postagens );
        listView.setAdapter(adapter);

        getPostagens();


    }

    private void getPostagens() {

        /***
         * recuperar imagens do usuário clicado
         */

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("imagem");
        query.whereEqualTo("username", username);
        query.orderByAscending("createdAt");



        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null){

                    if (objects.size() > 0){
                        postagens.clear();

                        for (ParseObject parseObject : objects){
                            postagens.add(parseObject);
                        }
                        adapter.notifyDataSetChanged();
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Erro ao recuperar o feed - " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}
