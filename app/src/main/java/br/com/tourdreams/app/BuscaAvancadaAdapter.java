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
 * Created by Ellen on 19/10/2017.
 */

public class BuscaAvancadaAdapter extends ArrayAdapter<BuscaAvancada> {
    int resource;
    private LayoutInflater infla;

    public BuscaAvancadaAdapter(Context context, int resource, List<BuscaAvancada> objects) {
        //super(context, 0,lstCarateristicas);
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null){

            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(resource,null);
        }
        if(v == null ){
            v = infla.inflate(R.layout.modelo_sheet, parent,false);
        }

        BuscaAvancada item = getItem(position);

        if(item != null){
            TextView txt_sheet = (TextView)v.findViewById(R.id.txt_sheet);
            ImageView img_quarto_modelo = (ImageView) v.findViewById(R.id.img_quarto_modelo);

            txt_sheet.setText(item.getDescricao());
            //img_quarto_modelo.setImageResource(item.getImagem());

            Picasso.with(getContext())
                    .load(item.getImagem()) // pega a imagem e carrega ela na image view
                    .transform(new CircleTransform()) // classe do android para deixar a imagem redonda
                    .into(img_quarto_modelo); // a imgview q vai carregar a imagem

        }
        return v;
    }
}