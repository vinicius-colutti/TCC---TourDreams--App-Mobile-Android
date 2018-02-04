package br.com.tourdreams.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import br.com.tourdreams.app.cmlibrary.CircleMenu;
import br.com.tourdreams.app.cmlibrary.OnMenuSelectedListener;

public class PerfilActivity extends BaseActivity {
    Context context;
    TextView adquirir, oqMtf;
    EditText edit_avaliacao;
    RatingBar avalacao_estrelas;
    Button avaliar;
    String arrayCicle[] = {"Editar","transacoes","sair"};
    LinearLayout linear_msg,linear_principal,linear_status,linear_img;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton msg_recente, msg_nova;
    List<Parceiro> lst_parceiro = new ArrayList<>();
    ListView list_msg;
    ParceiroAdapter adapter;
    public static Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        definirConteudo(R.layout.activity_perfil);
        context = this;
        adquirir =(TextView) findViewById(R.id.adquirir);
        oqMtf =(TextView) findViewById(R.id.oqMtf);
        edit_avaliacao= (EditText) findViewById(R.id.edit_avaliacao);
        avalacao_estrelas = (RatingBar) findViewById(R.id.avalacao_estrelas);
        avaliar = (Button) findViewById(R.id.avaliar);
        linear_principal = (LinearLayout) findViewById(R.id.linear_principal);
        linear_msg = (LinearLayout) findViewById(R.id.linear_msg);
        linear_status = (LinearLayout) findViewById(R.id.linear_status);
        linear_img = (LinearLayout) findViewById(R.id.linear_img);
        linear_msg.setVisibility(View.GONE);
        list_msg = (ListView) findViewById(R.id.list_msg);

        floatingButton();
        circleMenu();
        conversasRecentes();
    }

    private class exibirReservas extends AsyncTask<Void,Void,Void> {
        String server;
        String json;
        @Override
        protected Void doInBackground(Void... voids) {
            server = PerfilActivity.this.getString(R.string.endServidor)+"exibirReserva.php?id_usuario=";
            json = HttpConnection.get(server);
            return null;
        }
    }
    private void conversasRecentes() {
        lst_parceiro.add(new Parceiro("Francisco","Dono do Hotel LALAALA",R.drawable.account));
        lst_parceiro.add(new Parceiro("Francisco","Dono do Hotel LALAALA",R.drawable.carinha));
        lst_parceiro.add(new Parceiro("Francisco","Dono do Hotel LALAALA",R.drawable.carinha2));
        adapter = new ParceiroAdapter(context,R.layout.modelo,lst_parceiro);
        list_msg.setAdapter(adapter);
    }
    private void circleMenu() {
        CircleMenu circleMenu = (CircleMenu) findViewById(R.id.cicle_menu);
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"),R.drawable.ic_add_black_24dp,R.drawable.ic_remove_black_24dp);
        circleMenu.addSubMenu(Color.parseColor("#cd4c41"),R.drawable.ic_sentiment_very_satisfied_black_24dp)
                .addSubMenu(Color.parseColor("#258CFF"),R.drawable.ic_mode_edit_black_24dp)
                .addSubMenu(Color.parseColor("#cd4c41"),R.drawable.ic_reorder_black_24dp)
                .addSubMenu(Color.parseColor("#CDCDCD"),R.drawable.ic_settings_power_black_24dp)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {

                        switch (i){
                            case 1:
                                Toast.makeText(context,"Editar Perfil",Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(context,"Minhas Viagens",Toast.LENGTH_SHORT).show();

                                break;
                            case 3:
                                Toast.makeText(context,"Logout",Toast.LENGTH_SHORT).show();

                                SharedPreferences preferences = getSharedPreferences("userInfo",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.clear();
                                editor.commit();
                                finish();

                                SharedPreferences preferences2 = getSharedPreferences("pegarData",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor2 = preferences2.edit();
                                editor2.clear();
                                editor2.commit();
                                finish();

                                break;
                            case 4:
                                Toast.makeText(context,"Escolha uma opção",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        if(i==3){
                            startActivity(new Intent(context,MainActivity.class));
                        }else if (i==1){
                            startActivity(new Intent(context,CadastrarUserActivity.class));
                        }
                    }
                });
    }
    public void adquirir(View view) {
        adquirir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Quanto mais você compra mais você ganha", Snackbar.LENGTH_LONG)
                        .setAction("MTF", null).show();
            }
        });
    }
    public void oqMtf(View view) {
        oqMtf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "MILHAS TREVEL FIDELIDADE, quanto mais você compra mais você ganha", Snackbar.LENGTH_LONG)
                        .setAction("MTF", null).show();
            }
        });
    }
    public void avaliacao(View view) {
        Toast.makeText(this,"Faça um comentário/Avaliação",Toast.LENGTH_SHORT).show();
        edit_avaliacao.setVisibility(View.VISIBLE);
        avalacao_estrelas.setVisibility(View.VISIBLE);
        avaliar.setVisibility(View.VISIBLE);
    }

    public void avaliar (View view) {
        Toast.makeText(this,"Obrigado! Sua avaliação é muito importante pra nós",Toast.LENGTH_LONG).show();
        edit_avaliacao.setVisibility(View.GONE);
        avalacao_estrelas.setVisibility(View.GONE);
        avaliar.setVisibility(View.GONE);
    }
    public void floatingButton(){
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.floating_menu);
        msg_recente = (FloatingActionButton) findViewById(R.id.msg_recente);
        msg_nova = (FloatingActionButton) findViewById(R.id.msg_nova);

        msg_recente.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                linear_principal.setVisibility(View.GONE);
                linear_status.setVisibility(View.GONE);
                linear_img.setVisibility(View.GONE);

                linear_msg.setVisibility(View.VISIBLE);
            }
        });
        msg_nova.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(context,Chat.class));
            }
        });
    }

}
