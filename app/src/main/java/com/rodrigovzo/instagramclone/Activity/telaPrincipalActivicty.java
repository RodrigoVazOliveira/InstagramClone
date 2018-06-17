package com.rodrigovzo.instagramclone.Activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.parse.ParseUser;
import com.rodrigovzo.instagramclone.Adapter.TabsAdapter;
import com.rodrigovzo.instagramclone.R;
import com.rodrigovzo.instagramclone.Util.SlidingTabLayout;

public class telaPrincipalActivicty extends AppCompatActivity {

    private Toolbar toolbarPrincipal;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal_activicty);

        slidingTabLayout = findViewById(R.id.telaprincipal_sliding_tab_layout);
        viewPager        = findViewById(R.id.telaprincipal_view_pager);

        TabsAdapter tabsAdapter =  new TabsAdapter(getSupportFragmentManager(), this);

        // configurando a toolbar
        toolbarPrincipal = findViewById(R.id.toolbar_principal);
        toolbarPrincipal.setLogo(R.drawable.instagramlogo);

        setSupportActionBar( toolbarPrincipal );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuinflater = getMenuInflater();
        menuinflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_sair:
                logoutInstagram();
                return true;
            case R.id.action_configuracaoes:
                return true;
            case R.id.action_compartilhar:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void logoutInstagram(){
        ParseUser.logOut();
        Intent intent = new Intent(telaPrincipalActivicty.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
