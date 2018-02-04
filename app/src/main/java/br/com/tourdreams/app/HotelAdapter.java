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

import java.util.List;

/**
 * Created by Ellen on 07/10/2017.
 */

public class HotelAdapter extends ArrayAdapter <Hotel> {

    int resource;

    public HotelAdapter(Context context, int resource, List<Hotel> objects) {
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
                        .inflate(R.layout.list_view_hotel /*resource é o layout do item da lista*/,null);
            }

            Hotel item = getItem(position);// pegar o item que esta sendo carregado

            if(item != null){
                ImageView img_hotel = (ImageView) v.findViewById(R.id.img_hotel);
                TextView txt_nomee_do_hotel =(TextView) v.findViewById(R.id.txt_nomee_hotel);
                TextView txt_preco_hotel = (TextView) v.findViewById(R.id.txt_preco_hotel);
                TextView txt_local_hotel = (TextView) v.findViewById(R.id.txt_local_hotel);

               // img_hotel.setImageResource(item.getImagem());
                txt_nomee_do_hotel.setText(item.getNome());
                txt_local_hotel.setText(item.getLocal());
                txt_preco_hotel.setText(Double.toString(item.getPreco()));

                Picasso.with(getContext())
                        .load(getContext().getString(R.string.caminhoImagem)+item.getImagem()) // pega a imagem e carrega ela na image view
                        .into(img_hotel); // a imgview q vai carregar a imagem
            }
            return v;
        }
}
