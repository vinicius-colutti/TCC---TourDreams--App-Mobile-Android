package br.com.tourdreams.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import br.com.bloder.magic.view.MagicButton;


public class ReservaActivity extends AppCompatActivity {
    TextView txt_nome_do_hotel, txt_nome_quarto, txt_preco_reserva, txt_caracteristicas, dt_check_in, dt_check_out, total_dias, total_valor;
    ImageView img_quarto, img_contem_caracteristica;
    Button btn_reservar;
    ViewPager slide_quartos;
    Context context;
    ListView lst_caract;
    CaracteristicasAdapter adapter;
    List<Caracteristicas> lst_caracteristica = new ArrayList<>();
    LinearLayout linear_reserva;
    LinearLayout linear_reserva_finalizar;
    LinearLayout sliderDots;
    private int dotscount;
    private ImageView[] dots;
    MagicButton conheca_seu_destino_magic, chat_magic;
    int fotosReserva;
    SharedPreferences preferencesQuarto;
    SharedPreferences preferences;
    SharedPreferences preferencesHotel;
    int idUser;
    public static DadosQuarto dados;
    DadosQuarto[] dadosQuarto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        context = this;
        findView();
        Intent intent = getIntent();
        fotosReserva = intent.getIntExtra("abrirReserva", 0);

        new reserva().execute();

        String nome_quarto;
        String preco_quarto;
        String nome_hotel;

        preferencesQuarto = getSharedPreferences("quarto",Context.MODE_PRIVATE) ;
        nome_quarto = preferencesQuarto.getString("nome_quarto","");
        preco_quarto = preferencesQuarto.getString("preco_quarto","");
        //nome_hotel = preferencesQuarto.getString("nome_hotel","");

        preferencesHotel = getSharedPreferences("nome_hotel",Context.MODE_PRIVATE) ;
        nome_hotel = preferencesHotel.getString("nome_hotel","");

        txt_nome_quarto.setText(nome_quarto);
        txt_preco_reserva.setText(preco_quarto);
        txt_nome_do_hotel.setText(nome_hotel);


        conheca_seu_destino_magic.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ConhecaSeuDestinoActivity.class));
            }
        });


        chat_magic.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(context,Chat.class));
                //String telefone;
                //preferencesQuarto = getSharedPreferences("quarto",Context.MODE_PRIVATE) ;
                //telefone = preferencesQuarto.getString("telefone_hotel","");
                String url="tel:777777777";
                if (url.startsWith("tel:"))
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL,
                            Uri.parse(url));
                    startActivity(intent);
                }

            }
        });

        lst_caracteristica.add(new Caracteristicas("Wifi",true,R.drawable.ic_wifi_black_24dp));
        lst_caracteristica.add(new Caracteristicas("Academia",true,R.drawable.ic_fitness_center_black_24dp));
        lst_caracteristica.add(new Caracteristicas("Spa",true,R.drawable.ic_spa_black_24dp));
        adapter = new CaracteristicasAdapter(context,R.layout.listview,lst_caracteristica);
        lst_caract.setAdapter(adapter);

        slide_quartos.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                for(int i = 0; i < dotscount; i++) {
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
        timer.scheduleAtFixedRate(new MyTimeTask(), 2000, 4000);
    }
// ************************** imagens do quarto *****************
    private class reserva extends AsyncTask<Void,Void,Void> {
        String abrirReserva;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            abrirReserva = ReservaActivity.this.getString(R.string.endServidor)+"abrirQuarto.php?id_quarto="+fotosReserva;
            String jsonReserva = HttpConnection.get(abrirReserva);
            Gson gson = new Gson();
            dadosQuarto = gson.fromJson(jsonReserva,DadosQuarto[].class);

            for(DadosQuarto d : dadosQuarto ){
                String img = ReservaActivity.this.getString(R.string.caminhoImagem)+d.getNome_imagem();
                Log.d("reserva", img);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            viewPagerAdapter viewPagerAdapter = new viewPagerAdapter(context,dadosQuarto);
            slide_quartos.setAdapter(viewPagerAdapter);

            // definindo os pontos de passagem do slide_promocao
            dotscount = viewPagerAdapter.getCount();
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
/*
            Caracteristicas[] car = dadosQuarto.getCaracteristicas();
            adapter = new CaracteristicasAdapter(context,R.layout.listview,car);
            lst_caract.setAdapter(adapter);*/

        }
    }
    ///
    private class criar_reserva extends AsyncTask<Void,Void,Void>{
        int id_Quarto;
        String server;
        String checkIn;
        String checkOut;
        String status = "pendente";
        String json;

        @Override
        protected Void doInBackground(Void... voids) {
            Intent intent = getIntent();
            id_Quarto =  intent.getIntExtra("abrirReserva",0);

            preferences = getSharedPreferences("pegarData",Context.MODE_PRIVATE) ;
            checkIn = preferences.getString("check_in","");
            checkOut= preferences.getString("check_out","");

            SharedPreferences preferences_login = getSharedPreferences("userInfo",Context.MODE_PRIVATE) ;
            idUser = preferences_login.getInt("id",0);
            server = ReservaActivity.this.getString(R.string.endServidor)+"reservar.php?" +
                    "id_quarto="+id_Quarto+
                    "&data_entrada="+checkIn+
                    "&data_saida="+checkOut+
                    "&status_reserva="+status+
                    "&id_usuario="+idUser;

            json = HttpConnection.get(server);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Gson gson = new Gson();
            dados  = gson.fromJson(json,DadosQuarto.class);

            SharedPreferences  preferencesReserva = getSharedPreferences("reserva",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencesReserva.edit();

            //editor.putInt("idQuarto", dados.getId_quarto() );
           // editor.putInt("idReserva", dados.getId_reserva());
           // editor.putInt("DiasTotais",dados.getDias_totais());
           // editor.putInt("valorTotal",dados.getValor_total());
           // editor.apply();

            int dias_totais;
            double valor_total;

            dias_totais =  dados.getDias_totais();
            total_dias.setText(dias_totais);
            valor_total =  dados.getValor_total();
            total_valor.setText( valor_total + "");

            //Toast.makeText(context, ""+dados.getId_quarto(), Toast.LENGTH_SHORT).show();
        }
    }

    private void findView() {
        txt_nome_do_hotel = (TextView) findViewById(R.id.txt_nome_do_hotel);
        txt_nome_quarto = (TextView) findViewById(R.id.txt_nome_quarto);
        txt_preco_reserva = (TextView) findViewById(R.id.txt_preco_reserva);
        txt_caracteristicas = (TextView) findViewById(R.id.txt_caracteristicas);
        lst_caract = (ListView) findViewById(R.id.lst_caract);
        dt_check_in =(TextView) findViewById(R.id.data);
        dt_check_out =(TextView) findViewById(R.id.data2);
        total_dias =(TextView) findViewById(R.id.total_dias);
        total_valor =(TextView) findViewById(R.id.total_valor);
        slide_quartos = (ViewPager) findViewById(R.id.slide_quartos);
        sliderDots = (LinearLayout) findViewById(R.id.sliderDots);
        img_contem_caracteristica = (ImageView) findViewById(R.id.img_contem_caracteristica);
        linear_reserva = (LinearLayout) findViewById(R.id.linear_reserva);
        linear_reserva_finalizar = (LinearLayout) findViewById(R.id.linear_reserva_finalizar);
        btn_reservar = (Button) findViewById(R.id.btn_reservar);
        conheca_seu_destino_magic = (MagicButton) findViewById(R.id.conheca_seu_destino_magic);
        chat_magic = (MagicButton) findViewById(R.id.chat_magic);
    }
    public void entrada(View view) {
        //Abrir o calendario
        DialogFragment calendario = new Calendario( dt_check_in );
        calendario.show(getSupportFragmentManager(), "datepicker");

    }
    public void saida(View view) {
        //Abrir o calendario
        DialogFragment calendario = new Calendario( dt_check_out );
        calendario.show(getSupportFragmentManager(), "datepicker");
    }
    public void reservar(View view) {
        verificarUsuarioLogado();
        data();
        new criar_reserva().execute();
    }
    private void verificarUsuarioLogado() {
        String email;
        String senha;

        SharedPreferences preferences_login = getSharedPreferences("userInfo",Context.MODE_PRIVATE) ;
        email = preferences_login.getString("email","");
        senha= preferences_login.getString("senha","");
        idUser = preferences_login.getInt("id",0);

       // Toast.makeText(context,id+"id do usuariooooo",Toast.LENGTH_SHORT).show();

        if(email == "" && senha == ""){
            Toast.makeText(context,"para reservar voce precisa esta logado",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(context,LoginActivity.class));
        }

    }
    private void data() {
        preferences = getSharedPreferences("pegarData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("check_in", dt_check_in.getText().toString());
        editor.putString("check_out", dt_check_out.getText().toString());
        editor.apply();

        String checkIn;
        String checkOut;

        preferences = getSharedPreferences("pegarData",Context.MODE_PRIVATE) ;
        checkIn = preferences.getString("check_in","");
        checkOut= preferences.getString("check_out","");

        //Toast.makeText(context," "+checkIn+" "+checkOut,Toast.LENGTH_SHORT).show();

        if(checkIn.equals("") && checkOut.equals("")){
            Toast.makeText(context, "opss.. esqueceu de colocar a data!!!", Toast.LENGTH_SHORT).show();
            // startActivity(new Intent(context,ReservaActivity.class));
        }else{
            // Toast.makeText(context, "entrada: " + dt_check_in.getText().toString() + "saída: " + dt_check_out.getText().toString(), Toast.LENGTH_SHORT).show();
            String total = checkIn + checkOut;

            Toast.makeText(context,""+total,Toast.LENGTH_SHORT).show();

            linear_reserva.setVisibility(View.GONE);
            linear_reserva_finalizar.setVisibility(View.VISIBLE);
            btn_reservar.setText("FINALIZAR RESERVA");
            btn_reservar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(context,PerfilActivity.class));
                    overridePendingTransition(R.anim.botton_in, R.anim.top_out);

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {return false;}
    public class MyTimeTask extends TimerTask {

        @Override
        public void run() {
            ReservaActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(slide_quartos.getCurrentItem()==0){
                        slide_quartos.setCurrentItem(1);
                    }else if (slide_quartos.getCurrentItem()==1){
                        slide_quartos.setCurrentItem(2);

                    }else{
                        slide_quartos.setCurrentItem(0);
                    }
                }
            });
        }
    }

}
