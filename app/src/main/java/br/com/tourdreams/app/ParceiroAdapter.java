package br.com.tourdreams.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 15251365 on 18/10/2017.
 */

public class ParceiroAdapter extends ArrayAdapter<Parceiro>{
    int resource;
    public ParceiroAdapter(Context context, int resource, List<Parceiro> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflagem de layout
        View v = convertView;
        if(v == null){
            v = LayoutInflater.from(getContext())
                    .inflate(R.layout.modelo /*resource é o layout do item da lista*/,null);
        }
        Parceiro item = getItem(position);// pegar o item que esta sendo carregado

        if(item != null){
            ImageView img_quarto_modelo = (ImageView) v.findViewById(R.id.img_quarto_modelo);
            TextView txt_modelo = (TextView) v.findViewById(R.id.txt_modelo);
            TextView preco_modelo = (TextView) v.findViewById(R.id.preco_modelo);
            txt_modelo.setText(item.getNome());
            preco_modelo.setText(item.getDonoDoHotel());

            Picasso.with(getContext())
                    .load(item.getImagem()) // pega a imagem e carrega ela na image view
                    .transform(new CircleTransform()) // classe do android para deixar a imagem redonda
                    .into(img_quarto_modelo); // a imgview q vai carregar a imagem
        }

        return v;
    }
}
