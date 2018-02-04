package br.com.tourdreams.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class LoginParceiroActivity extends AppCompatActivity {

    EditText txt_email_parceiro,txt_senha_parceiro;
    String email , senha ;
    Button btnEntrar;
    public static Parceiro parceiro;
    Context context ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_parceiro);
        context = this;

        txt_email_parceiro = (EditText) findViewById(R.id.txt_email_parceiro);
        txt_senha_parceiro = (EditText) findViewById(R.id.txt_senha_parceiro);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = txt_email_parceiro.getText().toString();
                senha = txt_senha_parceiro.getText().toString();

                // Toast.makeText(context, "user  "+txt_email.getText().toString()+"logado",Toast.LENGTH_SHORT).show();

                new logar().execute();
            }
        });


    }

    private class logar extends AsyncTask<Void,Void,Void> {
        String retorno;

        ProgressDialog progressDialog;

        private void showProgressDialog() {
            progressDialog = new ProgressDialog(context,R.style.CustomDialog);
            //progressDialog = new ProgressDialog(context);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Estamos preparando tudo pra você");
            progressDialog.setIcon( R.drawable.logo);
            progressDialog.show();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected Void doInBackground(Void... params) {
        String json;
            retorno = LoginParceiroActivity.this.getString(R.string.endServidor)
                    +"logarParceiro.php?email="+email+"&senha="+senha;

            json = HttpConnection.get(retorno);

            Gson gson = new Gson();
            parceiro = gson.fromJson(json,Parceiro.class);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            //Toast.makeText(context,retorno,Toast.LENGTH_LONG).show();
            progressDialog.dismiss();

            if(parceiro.getId() == -1 ){
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(context, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();

            }else{

                SharedPreferences preferences = getSharedPreferences("parceiroInfo",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt("id", parceiro.getId() );
                editor.putString("email",parceiro.getEmail());
                editor.putString("senha",parceiro.getSenha());

                editor.putBoolean("login_parceiro",true);

                editor.apply();

                Toast.makeText(context, ""+parceiro.getId(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);

                finish();//sair da memoria

            }


        }
    }
}
