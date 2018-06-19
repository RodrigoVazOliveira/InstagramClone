package com.rodrigovzo.instagramclone.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.ImageSpan;

import com.rodrigovzo.instagramclone.Fragments.HomeFragment;
import com.rodrigovzo.instagramclone.Fragments.UsuarioFragment;
import com.rodrigovzo.instagramclone.R;

public class TabsAdapter extends FragmentPagerAdapter {

    private Context context;
    //private String[] abas = new String[]{"HOME", "USUARIO"};
    private Integer[] icones = new Integer[]{R.drawable.ic_action_home, R.drawable.ic_perm_contact_calendar };
    private int tamanhoicone;

    public TabsAdapter(FragmentManager fm, Context c) {
        super(fm);
        this.context  = c;
        double escala = this.context.getResources().getDisplayMetrics().density;
        this.tamanhoicone = (int) (36 * escala);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new UsuarioFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return icones.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        // definindo icone
        Drawable drawable = ContextCompat.getDrawable(this.context, icones[position]);
        drawable.setBounds(0,0, this.tamanhoicone, this.tamanhoicone);

        // convertendo imagem para ser exibida
        ImageSpan imageSpan = new ImageSpan(drawable);
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }
}
