package br.com.tourdreams.app;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class SobreActivity extends BaseActivity {
    TextView txt_sobre,txt_missao,txt_visao,txt_valores;
    sobreAdapter adapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        definirConteudo(R.layout.activity_sobre);
        context = this;

        txt_sobre = (TextView) findViewById(R.id.txt_sobre);
        txt_missao = (TextView) findViewById(R.id.txt_missao);
        txt_visao = (TextView) findViewById(R.id.txt_visao);
        txt_valores = (TextView) findViewById(R.id.txt_valores);



    }


}
