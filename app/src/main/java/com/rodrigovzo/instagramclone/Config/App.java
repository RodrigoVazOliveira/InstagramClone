package com.rodrigovzo.instagramclone.Config;

import android.app.Application;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;


/**
 * Configuração do back4app na inicialização do aplicativo para acesso ao banco de dados via http
 *
 *
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Remove for production, use to verify FCM is working
        // Look for ParseFCM: FCM registration success messages in Logcat to confirm.
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("xIpHi3gvR5RzorI8meF1JcP71Yk0TkOSXR4REwJB") // id do app
                .clientKey("IEyRdb9bB59istPCzOGzm8W7zN5OKLMbvVqrQrfe") // key de autenticação do serve ssh
                .server("https://parseapi.back4app.com/") // endereço de acesso aos serviços
                .build() // compilação das configurações
        );

        ParseACL acesso = new ParseACL();
        acesso.setPublicReadAccess(true);
        acesso.setPublicWriteAccess(true);


        /* teste do servidor para gravação de dados no mongoDB
        ParseObject pontuaoao = new ParseObject("pontuacao");
        pontuaoao.put("pontos", 100);
        pontuaoao.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Log.i("enviar dados", "done: salvo com sucesso!");
                }
            }
        });



        * listagem de dados exemplo


        ParseQuery<ParseObject> filtro = new ParseQuery<ParseObject>("pontuacao");

        // aplicando filtros
        //filtro.whereGreaterThan("pontos", 800); // > x valor
        //filtro.whereGreaterThanOrEqualTo("pontos", 800); // maior ou igual
        //filtro.whereLessThan("pontos",500); // menor que
        //filtro.whereLessThanOrEqualTo("pontos", 500); // menor ou igual
        //filtro.whereStartsWith("nome", "a"); // nomes começado com a
        //filtro.whereEndsWith("nome", "a"); // nomes terminados com a
        //filtro.addAscendingOrder("pontos"); // orderna por pontos de forma do menor para maior
        //filtro.addDescendingOrder("pontos"); // ordernapor pontos de forma do maior para menro
        //filtro.setLimit(1); // limitar o número de dados exibido

        filtro.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null){

                    Log.i("Listagem","dados listado: " + objects.size());

                }els {
                    Log.i("Error", "Ocorreu um erro: " + e.getMessage());
                }

            }
        });
        */

    }

}

