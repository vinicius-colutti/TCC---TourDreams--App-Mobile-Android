package br.com.tourdreams.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Ellen on 17/10/2017.
 */

public class QuartoAdapter extends ArrayAdapter<DadosQuarto>{
    int resource;

    public QuartoAdapter(Context context, int resource, DadosQuarto[] objects) {
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
                    .inflate(R.layout.modelo /*resource é o layout do item da lista*/,null);
        }

        DadosQuarto item = getItem(position);// pegar o item que esta sendo carregado

        if(item != null){
            ImageView img_quarto_modelo = (ImageView) v.findViewById(R.id.img_quarto_modelo);
            TextView txt_hotel_modelo = (TextView) v.findViewById(R.id.txt_hotel_modelo);
            TextView txt_modelo = (TextView) v.findViewById(R.id.txt_modelo);
            TextView preco_modelo = (TextView) v.findViewById(R.id.preco_modelo);

            txt_modelo.setText(item.getNome_quarto());
            preco_modelo.setText(item.getPreco_quarto());
            //img_quarto_modelo.setImageDrawable(Drawable.createFromPath(item.getNome_imagem()));

         Picasso.with(getContext())
                    .load(getContext().getString(R.string.caminhoImagem)+item.getNome_imagem()) // pega a imagem e carrega ela na image view
                    .into(img_quarto_modelo); // a imgview q vai carregar a imagem*/
        }
        return v;
    }
}
