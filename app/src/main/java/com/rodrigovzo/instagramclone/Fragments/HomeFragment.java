package com.rodrigovzo.instagramclone.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.rodrigovzo.instagramclone.Adapter.HomeAdapter;
import com.rodrigovzo.instagramclone.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private ListView listview;
    private ArrayList<ParseObject> postagemns;
    private ArrayAdapter<ParseObject> adapter;
    private ParseQuery<ParseObject> query;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        postagemns = new ArrayList<>();
        listview = view.findViewById(R.id.list_postagem_home);
        adapter  = new HomeAdapter(getActivity(), postagemns);
        listview.setAdapter(adapter);

        getPostagens();




        return view;
    }


    public void atualizaPostagens(){
        getPostagens();
    }


    private void getPostagens(){
        /**
         * rrecuperar as imagens das postagens
         *
         */
        query = ParseQuery.getQuery("imagem");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.orderByDescending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null){

                    if (objects.size() > 0){
                        postagemns.clear();
                        for (ParseObject parseObject : objects){
                            postagemns.add(parseObject);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }

            }
        });


    }
}
