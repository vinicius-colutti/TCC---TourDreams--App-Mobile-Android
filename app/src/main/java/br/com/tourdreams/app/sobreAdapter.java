package br.com.tourdreams.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by 15251365 on 22/11/2017.
 */

public class sobreAdapter extends ArrayAdapter<sobre> {

    int resource;

    public sobreAdapter( Context context, int resource, sobre[] objects) {
        super(context, resource,objects);
        this.resource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflagem de layout
        View v = convertView;
        if(v == null){
            v = LayoutInflater.from(getContext())
                    .inflate(R.layout.activity_sobre /*resource é o layout do item da lista*/,null);
        }
        sobre item = getItem(position);// pegar o item que esta sendo carregado

        if(item != null){

            TextView txt_sobre = (TextView) v.findViewById(R.id.txt_sobre);
            TextView txt_visao = (TextView) v.findViewById(R.id.txt_visao);
            TextView txt_missao = (TextView) v.findViewById(R.id.txt_missao);
            TextView txt_valores = (TextView) v.findViewById(R.id.txt_valores);

            txt_sobre.setText(item.getSobre());
            txt_visao.setText(item.getVisao());
            txt_missao.setText(item.getMissao());
            txt_valores.setText(item.getValores());

        }
        return v;
    }
}
