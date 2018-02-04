package br.com.tourdreams.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.bloder.magic.view.MagicButton;


public class MainActivity extends BaseActivity {

    ImageView img_hotel, img_logo;
    Context context;
    MagicButton chat_usuario;
    ViewPager banner_promocao;
    LinearLayout sliderDots;
    private int dotscount;
    private ImageView[] dots;
    ListView lst_main;
    List<base> lstHotel = new ArrayList<>();
    baseAdapter adapter;
    MelhoresDestinosAdapter adapterMelhoresDestinos;
    int  categoria = 0;
    /*SearchView.OnQueryTextListener listennerBusca = new SearchView.OnQueryTextListener() {

        @Override
        // é executado quando termina a busca e clica em pesquisar
        public boolean onQueryTextSubmit(String query) {
            small.setVisibility(View.VISIBLE);
            return false;
        }
        @Override
        // é executado a cada letra clicada
        public boolean onQueryTextChange(String newText) {
            Log.d("OnQueryTextListener",newText);
            // filtragem do adapter
            adapter.getFilter().filter(newText);
            banner_promocao.setVisibility(View.GONE);
            sliderDots.setVisibility(View.GONE);
            return false;
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        definirConteudo(R.layout.content_main);
        context = this;
        img_hotel = (ImageView) findViewById(R.id.img_hotel);
        img_logo = (ImageView) findViewById(R.id.img_logo);
        //banner_promocao = (ViewPager) findViewById(R.id.banner_promocao);
        //sliderDots = (LinearLayout) findViewById(R.id.sliderDots);
        lst_main = (ListView) findViewById(R.id.lst_main);
       // new promocao().execute();

        LayoutInflater inflate = LayoutInflater.from(context);
        inflate.inflate(R.layout.small_bar,null);
        preencherAdapter();
        CliqueDaLista();

      /*  banner_promocao.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i = 0; i <dotscount; i++) {

                    dots[i].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.nonactive_dot));
                    dots[position].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.active_dot));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // definindo o tempo de passagem das imagens do slide_promocao
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimeTask(), 2000, 4000);*/
    }
    /*private class promocao extends AsyncTask<Void,Void,Void> {
        Promocao[] imagens;
        String promocao;
        Integer id_promocao ;

        @Override
        protected Void doInBackground(Void... voids) {
            //promocao = HttpConnection.get("http://localhost/Projetos/TourDreams/API/promocoes.php");
            promocao = MainActivity.this.getString(R.string.promocao);
            String json =  HttpConnection.get(promocao);
            Gson gson = new Gson();
            //JsonReader reader = new JsonReader(new StringReader(json));
            //reader.setLenient(true);
            imagens = gson.fromJson(json, Promocao[].class);
            for(Promocao p : imagens ){
                String img = p.getBanner_promocao();
                Log.d("promocao_img", img);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // imagens do slide
            PromocaoAdapter adapter = new PromocaoAdapter(context,imagens);
            banner_promocao.setAdapter(adapter);

            // definindo os pontos de passagem do slide_promocao
            dotscount = adapter.getCount();
            dots = new ImageView[dotscount];

            // definingo a imagem dos pontos do slide_promocao
            for(int i = 0; i <dotscount; i++){
                dots[i] = new ImageView(context);
                dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactive_dot));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(8,0,8,0);
                sliderDots.addView(dots[i],params);
            }
            dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));

        }
    }*/


    public void btn_filtro_sheet(View view) {
        //Toast.makeText(this,"bottom sheet",Toast.LENGTH_SHORT).show();
        CustomBottomSheetDialog dialog = new CustomBottomSheetDialog(this);
        dialog.show();
       /*Bundle bundle = new Bundle();
        bundle.putStringArray();
       CustomBottomSheetDialog fragment = new CustomBottomSheetDialog();
*/
    }
    /*public class MyTimeTask extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(banner_promocao.getCurrentItem()==0){
                        banner_promocao.setCurrentItem(1);
                    }else if (banner_promocao.getCurrentItem()==1){
                        banner_promocao.setCurrentItem(2);

                    }else{
                        banner_promocao.setCurrentItem(0);
                    }
                }
            });
        }

    }*/
    private void CliqueDaLista() {
        lst_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position){
                    case 0:
                        categoria = 1;
                        break;
                    case 1:
                        categoria = 2;
                        break;
                    case 2:
                        categoria = 3;
                        break;
                    case 3:
                        categoria = 4;
                        break;
                }

                Intent intent = new Intent(context,LugaresActivity.class);
                intent.putExtra("idCategoria",categoria);
                startActivity(intent);
                overridePendingTransition(R.anim.botton_in, R.anim.top_out);

                //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                //int id_categoria = m.getId_hotel();
                //preferences.edit().putInt("idCategoria",id_categoria).commit();

                //Toast.makeText(context,""+categoria,Toast.LENGTH_SHORT).show();
               //startActivity(new Intent(context,LugaresActivity.class));
            }
        });
    }

    private void preencherAdapter() {

        // adicionando hoteis a lista
        lstHotel.add(new base("PRAIAS", R.drawable.praia));
        lstHotel.add(new base("REGIÕES FRIAS",  R.drawable.frio));
        lstHotel.add(new base("CAMPOS",  R.drawable.campo));
        lstHotel.add(new base("HOTÉIS FAZENDA",  R.drawable.fazenda));

        adapter = new baseAdapter(this, R.layout.main, lstHotel);
        lst_main.setAdapter(adapter);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        //id do icone
        SearchView searchView = (SearchView) menu.findItem(R.id.busca).getActionView();
        searchView.setQueryHint("Pesquisar..");
        searchView.setOnQueryTextListener(listennerBusca);

        return true;
    }*/

}
