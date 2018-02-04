package br.com.tourdreams.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


public class FaleConoscoActivity extends BaseActivity {

    private Toolbar toolbar_bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        definirConteudo(R.layout.activity_fale_conosco);

        toolbar_bottom = (Toolbar) findViewById(R.id.inc_tb_bottom);
        toolbar_bottom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()){
                    case R.id.facebook:
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.facebook.com"));
                        break;
                    case R.id.instagram:
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.instagram.com"));
                        break;
                    case R.id.twitter:
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.twitter.com"));
                        break;
                    case R.id.youtube:
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.youtube.com"));
                        break;
                }
                startActivity(intent);
                return false;
            }
        });

        toolbar_bottom.inflateMenu(R.menu.menu_faleconosco);

    }


}
