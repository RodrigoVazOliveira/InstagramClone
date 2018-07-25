package com.rodrigovzo.instagramclone.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseUser;
import com.rodrigovzo.instagramclone.R;

import java.util.ArrayList;

public class UsuarioAdapter extends ArrayAdapter<ParseUser> {

    private Context context;
    private ArrayList<ParseUser> usuarios;

    public UsuarioAdapter(@NonNull Context c,  ArrayList<ParseUser> objects) {
        super(c, 0, objects);
        this.context = c;
        this.usuarios = objects;
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

            view = inflate.inflate(R.layout.lista_usuarios,parent, false);
        }


        TextView userNameTextViewList = view.findViewById(R.id.textView_usuarios_lista);


        // configurar usuários
        ParseUser parseUser = usuarios.get(position);
        userNameTextViewList.setText(parseUser.getUsername());

        return view;

    }
}
