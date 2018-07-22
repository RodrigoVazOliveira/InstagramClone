package com.rodrigovzo.instagramclone.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.provider.MediaStore.Images;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.rodrigovzo.instagramclone.Adapter.TabsAdapter;
import com.rodrigovzo.instagramclone.Fragments.HomeFragment;
import com.rodrigovzo.instagramclone.R;
import com.rodrigovzo.instagramclone.Util.SlidingTabLayout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


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

        // configurando a toolbar
        toolbarPrincipal = findViewById(R.id.toolbar_principal);
        toolbarPrincipal.setLogo(R.drawable.instagramlogo);
        setSupportActionBar( toolbarPrincipal );


        // configurando abas
        TabsAdapter tabsAdapter =  new TabsAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(tabsAdapter);
        slidingTabLayout.setCustomTabView(R.layout.tabs_view, R.id.text_item_view);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.indicador));
      //  slidingTabLayout.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        slidingTabLayout.setViewPager(viewPager);


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
                compartilhaFoto();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void compartilhaFoto(){

        // abrir galeria de fotos
        Intent intent = new Intent(Intent.ACTION_PICK, Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null){
            // SALVAR FOTO

            Uri localImagemSelecionada = data.getData();
            try {

                // momntando a imagem
                Bitmap images = Images.Media.getBitmap( getContentResolver() ,localImagemSelecionada);
                // COMPRIMIR IMAGEM PNG
                ByteArrayOutputStream stream  = new ByteArrayOutputStream();
                images.compress(Bitmap.CompressFormat.PNG,75,stream);


                // montando o arquivo de iamgem para ser salvo no banco de dados
                byte[] byteArray = stream.toByteArray();
                // criar um arquivo com formato própio
                SimpleDateFormat dataformat = new SimpleDateFormat("ddmmaaaahhmmss");
                String nomeImage            = dataformat.format(new Date());
                ParseFile arquivoParse = new ParseFile("image-" + nomeImage +".png", byteArray);

                // salvar no banco/servidor
                ParseObject parseObject = new ParseObject("imagem");
                parseObject.put("username", ParseUser.getCurrentUser().getUsername());
                parseObject.put("imagem", arquivoParse);
                parseObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            Toast.makeText(getApplicationContext(),
                                        "Sua imagem foi postada com sucesso!",
                                    Toast.LENGTH_LONG).show();

                            TabsAdapter tabsAdapterNovo = (TabsAdapter) viewPager.getAdapter();
                            HomeFragment  nomeFragmentNovo = (HomeFragment) tabsAdapterNovo.getFragment(0);
                            nomeFragmentNovo.atualizaPostagens();

                        }else {
                            Toast.makeText(getApplicationContext(),
                                    "Ocorreu um erro na postagem! " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    private void logoutInstagram(){
        ParseUser.logOut();
        Intent intent = new Intent(telaPrincipalActivicty.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
