package br.com.tourdreams.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ConhecaSeuDestinoActivity extends BaseActivity {
    ListView lst_destino;
    List<ConhecaSeuDestino> lstDestino = new ArrayList<>();
    DestinosAdapter adapter;
    TextView txtcomentario;
    Context context;

    SearchView.OnQueryTextListener listennerBusca = new SearchView.OnQueryTextListener() {

        @Override
        // é executado quando termina a busca e clica em pesquisar
        public boolean onQueryTextSubmit(String query) {
          adapter.getFilter().filter(query);
            return false;
        }
        @Override
        // é executado a cada letra clicada
        public boolean onQueryTextChange(String newText) {
            Log.d("OnQueryTextListener",newText);
            // filtragem do adapter
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        definirConteudo(R.layout.activity_conheca_seu_destino);
        context = this;
        lst_destino = (ListView) findViewById(R.id.lst_destino);
        txtcomentario = (TextView) findViewById(R.id.txtcomentario);

        // adicionando hoteis a lista
        lstDestino.add(new ConhecaSeuDestino("hotelLALALA", 299.00, "São Paulo", R.drawable.hotel,"quarto muito legaaa aaaaaa aaaaaa aa aaa aaaaaaaa aaaaa aaa asdadasda dasdad asdasdasdasdasdasd asdasdasdasd asss sssssss ssssss sssss sssss sssssssssssss  das l"));
        lstDestino.add(new ConhecaSeuDestino("hotelLULULU", 299.00, "Rio de Janeiro", R.drawable.quarto,"quarto legal"));
        lstDestino.add(new ConhecaSeuDestino("hotelLALALA", 299.00, "Sao sao sao", R.drawable.hotel,"asdasd"));

        adapter = new DestinosAdapter(this, R.layout.list_view_destino, lstDestino);

        lst_destino.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        //id do icone
        SearchView searchView = (SearchView) menu.findItem(R.id.busca).getActionView();
        searchView.setQueryHint("Qual seu destino ? ..");
        searchView.setOnQueryTextListener(listennerBusca);

        return true;
    }
}
