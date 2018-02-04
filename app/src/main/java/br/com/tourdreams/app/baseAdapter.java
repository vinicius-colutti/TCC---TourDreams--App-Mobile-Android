package br.com.tourdreams.app;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ellen on 22/10/2017.
 */

public class baseAdapter extends ArrayAdapter<base>{
    int resource;
    public baseAdapter(Context context, int resource, List<base> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflagem de layout
        View v = convertView;
        if(v == null){
            v = LayoutInflater.from(getContext())
                    .inflate(R.layout.main /*resource é o layout do item da lista*/,null);
        }

        base item = getItem(position);// pegar o item que esta sendo carregado

        if(item != null){
            ImageView img_hotel = (ImageView) v.findViewById(R.id.img_hotel);
            TextView txt_sobreposto =(TextView) v.findViewById(R.id.txt_sobreposto);


            // img_hotel.setImageResource(item.getImagem());
            txt_sobreposto.setText(item.getNome());
            Picasso.with(getContext())
                    .load(item.getImagem()) // pega a imagem e carrega ela na image view
                    .into(img_hotel); // a imgview q vai carregar a imagem
        }
        return v;
    }
}
