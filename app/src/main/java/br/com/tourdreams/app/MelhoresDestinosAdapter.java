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

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by 15251365 on 31/10/2017.
 */

public class MelhoresDestinosAdapter extends ArrayAdapter<MelhoresDestinos> {
    int resource;
    private LayoutInflater layinfla;
    Context c;

    public MelhoresDestinosAdapter(Context context, int resource, MelhoresDestinos[] objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflagem de layout
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_view_hotel /*resource é o layout do item da lista*/, null);
        }

        final MelhoresDestinos item = getItem(position);// pegar o item que esta sendo carregado

        if (item != null) {
            ImageView img_hotel = (ImageView) v.findViewById(R.id.img_hotel);
            TextView txt_nomee_hotel = (TextView) v.findViewById(R.id.txt_nomee_hotel);
            TextView txt_local_hotel = (TextView) v.findViewById(R.id.txt_local_hotel);
            TextView txt_preco_hotel = (TextView) v.findViewById(R.id.txt_preco_hotel);

            //img_hotel.setImageResource(item.getImagem_hotel_1());
            txt_nomee_hotel.setText(item.getNome_hotel());
            txt_local_hotel.setText(item.getCidade_hotel());

           NumberFormat f = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

            txt_preco_hotel.setText( f.format( Double.parseDouble(item.getPreco_quarto()) ));

           Picasso.with(getContext())
                    .load(getContext().getString(R.string.caminhoImagem)+item.getImagem_hotel_1())
                    .into(img_hotel); // pega a imagem e carrega ela na image view

        /*    img_hotel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(),"chegou"+item.getId_hotel(), Toast.LENGTH_SHORT).show();
                    Integer idCategoria = item.getId_hotel();
                    Intent intent = new Intent(getContext(),QuartoAllActivity.class);
                    intent.putExtra("abrirQuarto",idCategoria);
                    getContext().startActivity(intent);

                }
            });*/

        }
            return v;
    }

}
