package br.com.tourdreams.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class QuartoAllActivity extends BaseActivity {
    TextView txt_modelo,preco_modelo,txt_hotel_modelo;
    ImageView img_quarto_modelo;
    GridView gridView;
    Context context;
    List<DadosQuarto> lst_quarto = new ArrayList<>();
    QuartoAdapter adapter;
    int abrir;
    int idQuarto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        definirConteudo(R.layout.activity_quarto_all);
        context = this;
        Intent intent = getIntent();
        abrir =  intent.getIntExtra("abrirQuarto",0);
        txt_hotel_modelo = (TextView) findViewById(R.id.txt_hotel_modelo);
        img_quarto_modelo = (ImageView) findViewById(R.id.img_quarto_modelo);
        txt_modelo = (TextView) findViewById(R.id.txt_modelo);
        preco_modelo = (TextView) findViewById(R.id.preco_modelo);
        gridView = (GridView) findViewById(R.id.gridView);

     /*   lst_quarto.add(new DadosQuarto("quarto tal",399.99,"asd",R.drawable.quarto));
        lst_quarto.add(new DadosQuarto("quartohaha",299.99,"asd",R.drawable.hotel));
        lst_quarto.add(new DadosQuarto("quarto tal",399.99,"asd",R.drawable.quarto));
        lst_quarto.add(new DadosQuarto("quatal",199.90,"asd",R.drawable.hotel));
        lst_quarto.add(new DadosQuarto("quarto tal",399.99,"asd",R.drawable.quarto));
        lst_quarto.add(new DadosQuarto("quartohaha",299.99,"asd",R.drawable.hotel));
        lst_quarto.add(new DadosQuarto("quarto tal",399.99,"asd",R.drawable.quarto));
        lst_quarto.add(new DadosQuarto("quatal",199.90,"asd",R.drawable.hotel));*/

        new quarto().execute();

       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                DadosQuarto dados = adapter.getItem(position);

                Intent intent = new Intent(context,ReservaActivity.class);

                idQuarto= dados.getId_quarto();

                SharedPreferences preferencesQuarto= getSharedPreferences("quarto",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencesQuarto.edit();
                editor.putString("nome_quarto", dados.getNome_quarto());
                editor.putString("preco_quarto",dados.getPreco_quarto());
                //editor.putString("nome_hotel",dados.getNome_hotel());
                editor.putString("telefone_hotel",dados.getTelefone());
                editor.apply();

                intent.putExtra("abrirReserva", idQuarto);

                //Toast.makeText(context,"Quarto"+idQuarto, Toast.LENGTH_SHORT).show();

                startActivity(intent);

                overridePendingTransition(R.anim.botton_in, R.anim.top_out);

                //new reserva().execute();
            }
        });

    }

    /*private class reserva extends AsyncTask<Void,Void,Void> {
        String server;
        DadosQuarto [] dados;

        @Override
        protected Void doInBackground(Void... voids) {
            server = QuartoAllActivity.this.getString(R.string.endServidor)+"quarto.php?id_hotel="+abrir;
            String json = HttpConnection.get(server);
            Gson gson = new Gson();
            dados = gson.fromJson(json, DadosQuarto[].class);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            adapter = new QuartoAdapter(context,R.layout.modelo,dados);
            gridView.setAdapter(adapter);

        }
    }*/


    private class quarto extends AsyncTask<Void,Void,Void> {
        String server;
        DadosQuarto [] dados;

        @Override
        protected Void doInBackground(Void... voids) {
            server = QuartoAllActivity.this.getString(R.string.endServidor)+"quarto.php?id_hotel="+abrir;
            String json = HttpConnection.get(server);
            Gson gson = new Gson();
            dados = gson.fromJson(json, DadosQuarto[].class);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            adapter = new QuartoAdapter(context,R.layout.modelo,dados);
            gridView.setAdapter(adapter);

        }
    }
}
