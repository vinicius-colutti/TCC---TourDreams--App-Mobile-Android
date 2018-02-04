package br.com.tourdreams.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 15251365 on 03/10/2017.
 */

public class CaracteristicasAdapter extends ArrayAdapter<Caracteristicas> {


    public CaracteristicasAdapter(Context context, int listview, List<Caracteristicas> lstCarateristicas) {
        super(context, 0,lstCarateristicas);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null){

            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.listview, null);
        }

        Caracteristicas caracteristicas = getItem(position);

        TextView txt_caracteristicas = (TextView) v.findViewById(R.id.txt_caracteristicas);

        txt_caracteristicas.setText(caracteristicas.getDescricao());

        return v;
    }
}
