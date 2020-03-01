package com.example.carteiradeclientes;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.carteiradeclientes.Database.DadosOpenHelper;
import com.example.carteiradeclientes.dominio.entidades.Cliente;
import com.example.carteiradeclientes.dominio.repositorio.ClienteRepositorio;
import com.google.android.material.snackbar.Snackbar;

public class ActCardCliente extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtEndereco;
    private EditText edtEmail;
    private EditText edtPhone;

    private ConstraintLayout layoutContentActCardCliente;


//clienterepositorio para inserir, alterar excluir e pesquisar

    private ClienteRepositorio clienteRepositorio;

// Conexão com o banco de dados/import de funcionaridades

    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;
    private ConstraintLayout layoutContentMain;

// Conexão do metodo inserir

    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_card_cliente);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtNome     = (EditText)findViewById(R.id.edtNome);
        edtEndereco = (EditText)findViewById(R.id.edtEndereco);
        edtEmail    = (EditText)findViewById(R.id.edtEmail);
        edtPhone    = (EditText)findViewById(R.id.edtPhone);

        layoutContentActCardCliente = (ConstraintLayout)findViewById(R.id.layoutContentActCardCliente);

        criarConexao();
    }

    private void criarConexao(){

        try{

            dadosOpenHelper = new DadosOpenHelper(this);

            conexao = dadosOpenHelper.getWritableDatabase();

            Snackbar.make(layoutContentActCardCliente, R.string.message_conexao_criada, Snackbar.LENGTH_LONG)
                    .setAction(R.string.action_ok, null).show();

            clienteRepositorio = new ClienteRepositorio(conexao);

        }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();


        }
    }
        private void confirmar(){

            cliente = new Cliente();

            if (validaCampos() == false){

                try{

                    clienteRepositorio.inserir(cliente);

                    finish();

                }catch (SQLException ex){

                    AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                    dlg.setTitle(R.string.title_erro);
                    dlg.setMessage(ex.getMessage());
                    dlg.setNeutralButton(R.string.action_ok, null);
                    dlg.show();


                }
            }

        }


        private boolean validaCampos(){

            boolean res = false;

            String nome     = edtNome.getText().toString();
            String endereco = edtEndereco.getText().toString();
            String email    = edtEmail.getText().toString();
            String telefone = edtPhone.getText().toString();

            cliente.nome     = nome;
            cliente.endereco = endereco;
            cliente.email    = email;
            cliente.phone    = telefone;

            if (res = isCampoVazio(nome)){
                edtNome.requestFocus();
            }
            else
                if (res = isCampoVazio(endereco)){
                    edtEndereco.requestFocus();
                }
                else
                    if (res = !isEmailValido(email)){
                        edtEmail.requestFocus();
                    }
                    else
                        if (res = isCampoVazio(telefone)){
                            edtPhone.requestFocus();
                        }
            if (res){

                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle(R.string.title_aviso);
                dlg.setMessage(R.string.message_campos_invalidos_brancos);
                dlg.setNeutralButton(R.string.action_ok, null);
                dlg.show();
            }

            return res;
        }

        private boolean isCampoVazio(String valor){

            boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
            return resultado;
        }

        private boolean isEmailValido(String email){

            boolean resultado = (!isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
            return resultado;
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_card_cliente, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch(id){

            case R.id.action_ok:

                confirmar();
                //Toast.makeText(this, "Botão OK Selecionado", Toast.LENGTH_SHORT).show();

                break;
            case R.id.action_cancelar:

                //Toast.makeText(this, "Botão Cancelar Selecionado", Toast.LENGTH_SHORT).show();

                finish();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
