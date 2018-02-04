package br.com.tourdreams.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class LugaresActivity extends BaseActivity {
    TextView txt_nomee_hotel, txt_preco_hotel, txt_local_hotel, txt_mensagem;
    ImageView img_hotel;
    ListView lst_lugares;
    List<Hotel> lstLugares = new ArrayList<>();
    HotelAdapter adapter;
    Context context;
    MelhoresDestinosAdapter mAdapter;
    int categoria;
    String abrir;
    boolean primeiroAcesso = true;

    SearchView.OnQueryTextListener listennerBusca = new SearchView.OnQueryTextListener() {

        @Override
        // é executado quando termina a busca e clica em pesquisar
        public boolean onQueryTextSubmit(String query) {
         //   small.setVisibility(View.VISIBLE);

            //chamada da api
            new categoria().execute(query);
            //Toast.makeText(context,""+query,Toast.LENGTH_SHORT).show();

            return false;
        }

        @Override
        // é executado a cada letra clicada
        public boolean onQueryTextChange(String newText) {
            Log.d("OnQueryTextListener", newText);

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        definirConteudo(R.layout.activity_lugares);
        context = this;

        img_hotel = (ImageView) findViewById(R.id.img_hotel);
        txt_nomee_hotel = (TextView) findViewById(R.id.txt_nomee_hotel);
        txt_preco_hotel = (TextView) findViewById(R.id.txt_preco_hotel);
        txt_local_hotel = (TextView) findViewById(R.id.txt_local_hotel);
        lst_lugares = (ListView) findViewById(R.id.lst_lugares);
        txt_mensagem =(TextView) findViewById(R.id.txt_mensagem);
        //preencherAdapter();



        //chamada da api
        new categoria().execute();

        lst_lugares.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               // startActivity(new Intent(context, QuartoAllActivity.class));
                MelhoresDestinos m = mAdapter.getItem(position);
               // Intent intent = getIntent();
                Intent intent = new Intent(context,QuartoAllActivity.class);
                int idHotel= m.getId_hotel();

                SharedPreferences preferencesHotel= getSharedPreferences("nome_hotel",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencesHotel.edit();
                editor.putString("nome_hotel", m.getNome_hotel());
                editor.apply();

                //m.getNome_hotel();

                intent.putExtra("abrirQuarto", idHotel);

               //Toast.makeText(context,""+idHotel,Toast.LENGTH_SHORT).show();

                startActivity(intent);

                overridePendingTransition(R.anim.botton_in, R.anim.top_out);
            }
        });
    }

    private class categoria extends AsyncTask<String,Void,Void> {
        MelhoresDestinos[] melhoresDestinos_lst;
        String server = "";
        String query = "";
        @Override
        protected Void doInBackground(String... params) {
           // Intent intent = getIntent();
            categoria = getIntent().getExtras().getInt("idCategoria");

            if(params.length > 0){
                query = params[0];
                server = LugaresActivity.this.getString(R.string.endServidor)+"busca.php?q=" + query+ "&cat=" + categoria;
            }else{
                server = LugaresActivity.this.getString(R.string.endServidor) + "categoria.php?id_categoria=" + categoria;
            }

            server = server.replace(" ","%20");

            String json = HttpConnection.get(server);
            Gson gson = new Gson();
            melhoresDestinos_lst = gson.fromJson(json, MelhoresDestinos[].class);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        // ****************************busca*************************************************
            if(melhoresDestinos_lst !=null) {

                if (melhoresDestinos_lst.length > 0 && melhoresDestinos_lst[0].getId_hotel() ==-1){
                    //quando não acha o hotel
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("Opss");
                    alert.setMessage("Nenhum hotel encontrado.");
                    alert.setPositiveButton("OK", null);

                    alert.show();

                    txt_mensagem.setText("Nenhum hotel encontrado com o termo \""+ query + "\"");
                    txt_mensagem.setVisibility(View.VISIBLE);

                    melhoresDestinos_lst =  new MelhoresDestinos[0];

                    mAdapter = new MelhoresDestinosAdapter(LugaresActivity.this.context, R.layout.list_view_hotel, melhoresDestinos_lst);
                    lst_lugares.setAdapter(mAdapter);

                }else{

                    if(primeiroAcesso){
                        txt_mensagem.setVisibility(View.GONE);
                    }else {
                        txt_mensagem.setText(melhoresDestinos_lst.length + " hotéis encontrados em \"" + query + "\"");
                        txt_mensagem.setVisibility(View.VISIBLE);
                    }

                    primeiroAcesso =false;

                    mAdapter = new MelhoresDestinosAdapter(LugaresActivity.this.context, R.layout.list_view_hotel, melhoresDestinos_lst);
                    lst_lugares.setAdapter(mAdapter);
                }
            }else{

                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Erro");
                alert.setMessage("Erro de conexão");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                alert.show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        //id do icone
        SearchView searchView = (SearchView) menu.findItem(R.id.busca).getActionView();
        searchView.setQueryHint("Pesquisar...");
        searchView.setOnQueryTextListener(listennerBusca);

        return true;
    }
}
