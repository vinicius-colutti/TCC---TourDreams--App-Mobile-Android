package br.com.tourdreams.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class opActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_op);
    }

    public void btncliente(View view) {
        startActivity(new Intent(this,LoginActivity.class));
    }

    public void btnHoteleiro(View view) {
        startActivity(new Intent(this,LoginParceiroActivity.class));
    }
}
