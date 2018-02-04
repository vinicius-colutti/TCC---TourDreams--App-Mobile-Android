package br.com.tourdreams.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class CadastrarUserActivity extends AppCompatActivity {
    EditText txt_nome_cadastro,txt_email_cadastro,txt_celular_cadastro,txt_cpf_cadastro,
            txt_rg_cadastro,txt_senha_cadastro,txt_telefone_cadastro;
    ImageView img_upload;
    Button btnUpload;
    String login, senha, telefone, email, json,celular,rg,cpf;
    String server;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_user);
        context = this;
        txt_nome_cadastro =(EditText) findViewById(R.id.txt_nome_cadastro);
        txt_email_cadastro =(EditText) findViewById(R.id.txt_email_cadastro);
        txt_celular_cadastro =(EditText) findViewById(R.id.txt_celular_cadastro);
        txt_telefone_cadastro =(EditText) findViewById(R.id.txt_telefone_cadastro);
        txt_cpf_cadastro =(EditText) findViewById(R.id.txt_cpf_cadastro);
        txt_rg_cadastro =(EditText) findViewById(R.id.txt_rg_cadastro);
        txt_senha_cadastro =(EditText) findViewById(R.id.txt_senha_cadastro);
        img_upload = (ImageView) findViewById(R.id.img_upload);
        btnUpload = (Button) findViewById(R.id.btnUpload);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnSave);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login = txt_nome_cadastro.getText().toString();
                senha = txt_senha_cadastro.getText().toString();
                telefone = txt_celular_cadastro.getText().toString();
                celular = txt_celular_cadastro.getText().toString();
                email = txt_email_cadastro.getText().toString();
                rg = txt_rg_cadastro.getText().toString();
                cpf = txt_cpf_cadastro.getText().toString();

                new inserirNovoUser().execute();

                // Snackbar.make(getCurrentFocus(),"Conta criada",Snackbar.LENGTH_INDEFINITE)
                 //       .setAction("Action", null).show();
            }
        });

    }

    final int GALERIA_CODE = 1;

    public void abrirGaleria(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult( intent , GALERIA_CODE );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALERIA_CODE && resultCode == RESULT_OK){

            try {
                InputStream input = getContentResolver().openInputStream(data.getData());

                Bitmap bitmap = BitmapFactory.decodeStream(input);

                img_upload.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
    private class inserirNovoUser extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            server = CadastrarUserActivity.this.getString(R.string.endServidor)
                    +"cadastrarUser.php?nome_usuario="+login
                    +"&senha_usuario="+senha
                    +"&rg_usuario="+rg
                    +"&cpf_usuario="+cpf
                    +"&telefone_usuario="+telefone
                    +"&celular_usuario="+celular
                    +"&email_usuario=" + email;
            json = HttpConnection.get(server);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(json != null){
                Toast.makeText(context, "Cadastrado com sucesso",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context,MainActivity.class));
            }else{
                Toast.makeText(context, "Erro ao cadastrar",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, CadastrarUserActivity.class));
            }
        }
    }
}

