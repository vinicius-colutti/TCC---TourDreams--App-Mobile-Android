package br.com.tourdreams.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {
    EditText txt_email,txt_senha;
    String email , senha ;
    Button btnEntrar;
    public static Usuario user;
    Context context;
    String json;
    SharedPreferences preferences;
    Integer idUsuario;
    String[]dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_senha = (EditText) findViewById(R.id.txt_senha);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = txt_email.getText().toString();
                senha = txt_senha.getText().toString();

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

            retorno = LoginActivity.this.getString(R.string.endServidor)
                    +"logar.php?email="+email+"&senha="+senha;

            json = HttpConnection.get(retorno);

            Gson gson = new Gson();
            user = gson.fromJson(json,Usuario.class);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            //Toast.makeText(context,retorno,Toast.LENGTH_LONG).show();
            progressDialog.dismiss();

          if(user.getId() == -1 ){
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(context, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();

            }else{

              preferences = getSharedPreferences("userInfo",Context.MODE_PRIVATE);
              SharedPreferences.Editor editor = preferences.edit();

              editor.putInt("id", user.getId() );
              editor.putString("email",user.getEmail());
              editor.putString("senha",user.getSenha());
              editor.putBoolean("login_parceiro",false);

              editor.apply();

                Toast.makeText(context, ""+user.getId(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);

                finish();//sair da memoria

            }

            /*Intent intentId = new Intent(context,ReservaActivity.class);
            intentId.putExtra("idUsuario",user.getId());
            startActivity(intentId);*/

        }
    }
    public void CadastrarUser(View view) {startActivity(new Intent(this, CadastrarUserActivity.class));}
    public void souHoteleiro(View view) {startActivity(new Intent(this, LoginParceiroActivity.class));overridePendingTransition(R.anim.botton_in, R.anim.top_out);}
}
