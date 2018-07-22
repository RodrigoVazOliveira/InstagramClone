package com.rodrigovzo.instagramclone.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.parse.ParseObject;
import com.rodrigovzo.instagramclone.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends ArrayAdapter<ParseObject> {

    private Context context;
    private ArrayList<ParseObject> postagens;



    public HomeAdapter(@NonNull Context c, @NonNull ArrayList<ParseObject> objects) {
        super(c, 0, objects);
        this.context = c;
        this.postagens = objects;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;


        /***
         *
         * verificar se não existe o objeto view criado
         * pois a view utilizada é armazenada no cache do android e fica na variável
         * convertView
         */

            if (view == null){
                LayoutInflater inflate = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

                // MONTAR A VIEW PELO XML

                view = inflate.inflate(R.layout.lista_postagem,parent, false);
            }

            // verificar se exiate postagem

        if (postagens.size() > 0){

                // recupera postagem na tela
            ImageView imagePostada = view.findViewById(R.id.image_lista_postagem);


            // buscando dados no parseserver

            ParseObject parseObject = postagens.get(position);
           // parseObject.getParseFile("imagem");

            Picasso.get()
                    .load(parseObject.getParseFile("imagem").getUrl())
                    .fit()
                    .into(imagePostada);

        }

        return view;
    }
}
