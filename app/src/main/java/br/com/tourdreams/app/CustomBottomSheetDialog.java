package br.com.tourdreams.app;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ellen on 09/10/2017.
 */

public class CustomBottomSheetDialog extends BottomSheetDialog{
    List<BuscaAvancada> lst_sheet;
    ListView lst_espandida;
    BuscaAvancadaAdapter adapter;

    private Context context;
    public CustomBottomSheetDialog (Context context ){
        super(context);
        this.context = context;

    }
     @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
        View layout = getLayoutInflater().inflate(R.layout.bottom_sheet_dialog,null);
        setContentView(layout);
        GridView gv = (GridView) layout.findViewById(R.id.gv_sheet);
        lst_sheet = new ArrayList<>();

        lst_sheet.add(new BuscaAvancada(R.drawable.ic_wifi_black_24dp,"Wifi"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_spa_black_24dp,"SPA"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_fitness_center_black_24dp,"Academia"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_local_parking_black_24dp,"Estacionamento"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_accessible_black_24dp,"Cadeirante"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_wc_black_24dp,"Banheiro"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_tv_black_24dp,"TV"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_airplanemode_active_black_24dp,"Aeroporto"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_attach_money_black_24dp,"Dinheiro"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_beach_access_black_24dp,"Praia"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_directions_subway_black_24dp,"Metrô"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_laptop_black_24dp,"Notbook"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_payment_black_24dp,"Cartão"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_pets_black_24dp,"Animais"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_restaurant_black_24dp,"Restaurante"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_rowing_black_24dp,"Remo"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_room_service_black_24dp,"Serviço de quarto"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_smoking_rooms_black_24dp,"Fumantes"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_wifi_black_24dp,"WIFI"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_wifi_black_24dp,"WIFI"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_wifi_black_24dp,"WIFI"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_wifi_black_24dp,"WIFI"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_wifi_black_24dp,"WIFI"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_wifi_black_24dp,"WIFI"));
        lst_sheet.add(new BuscaAvancada(R.drawable.ic_wifi_black_24dp,"WIFI"));

        adapter = new BuscaAvancadaAdapter(context,R.layout.modelo_sheet,lst_sheet);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(context,"Pos"+position,Toast.LENGTH_SHORT).show();

            }
        });
    }
}
