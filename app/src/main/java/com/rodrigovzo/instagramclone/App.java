package com.rodrigovzo.instagramclone;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Remove for production, use to verify FCM is working
        // Look for ParseFCM: FCM registration success messages in Logcat to confirm.
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("xIpHi3gvR5RzorI8meF1JcP71Yk0TkOSXR4REwJB")
                .clientKey("IEyRdb9bB59istPCzOGzm8W7zN5OKLMbvVqrQrfe")
                .server("https://parseapi.back4app.com/")
                .build()
        );


        /* teste do servidor
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
        */
    }

}

