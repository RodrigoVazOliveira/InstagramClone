package com.rodrigovzo.instagramclone.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.rodrigovzo.instagramclone.Activity.FeedUsuarioActivity;
import com.rodrigovzo.instagramclone.Adapter.UsuarioAdapter;
import com.rodrigovzo.instagramclone.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsuarioFragment extends Fragment {

    private ListView listaUsuarios;
    private ArrayAdapter<ParseUser> adapter;
    private ArrayList<ParseUser> usuarios;
    private ParseQuery<ParseUser> query;

    public  UsuarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_usuario, container, false);

        usuarios = new ArrayList<>();
        listaUsuarios = view.findViewById(R.id.listView_usuarios);
        adapter       = new UsuarioAdapter(getActivity(), usuarios);
        listaUsuarios.setAdapter(adapter);


        /// recuperar dados usuários
        getUsuarios();


        /**
         * colocar evento de onClick no listView
         */

        listaUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // recuperar a posição [item] que foi clicado
                ParseUser parseUser = usuarios.get(position);

                Intent intent = new Intent(getActivity(), FeedUsuarioActivity.class);
                intent.putExtra("username", parseUser.getUsername());
                startActivity(intent);
            }
        });

        return view;
    }

    private void getUsuarios() {

        query = ParseUser.getQuery();
        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.addAscendingOrder("username");

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null){

                    if (objects.size() > 0){
                        usuarios.clear();

                        for (ParseUser parseUser : objects){
                            usuarios.add(parseUser);
                        }
                        adapter.notifyDataSetChanged();
                    }

                }else {
                    e.printStackTrace();
                }
            }
        });

    }
}
