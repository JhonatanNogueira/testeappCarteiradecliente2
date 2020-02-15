package com.example.carteiradeclientes;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

public class ActCardCliente extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtEndereco;
    private EditText edtEmail;
    private EditText edtPhone;

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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_card_cliente, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
