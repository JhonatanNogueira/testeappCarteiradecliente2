package com.example.carteiradeclientes;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carteiradeclientes.dominio.entidades.Cliente;

import java.util.List;


public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolderCliente> {

    private List<Cliente> dados;



    public ClienteAdapter(List<Cliente> dados) {
        this.dados = dados;
    }

    Activity activity;

    @NonNull
    @Override

    public ClienteAdapter.ViewHolderCliente onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View layout = inflater.inflate(R.layout.linha_clientes, parent, false);

        ViewHolderCliente holderCliente = new ViewHolderCliente(layout);

        return holderCliente;


       // public View getView(int posicao, View dados convertView;
       // convertView, ViewGroup parent) {
       //     LayoutInflater inflater = activity.getLayoutInflater();
      //      View layout = inflater.inflate(R.layout.linha_clientes, parent, false);
     //       ViewHolderCliente holderCliente = new ViewHolderCliente(layout);
     //       return holderCliente;
            //Activity activity;
            //return layout;
        //aaaaaperdidaço
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteAdapter.ViewHolderCliente holder, int position) {

        if ((dados != null) && (dados.size() > 0)) {

            Cliente cliente = dados.get(position);

            holder.txtNome.setText(cliente.nome);
            holder.txtPhone.setText(cliente.phone);

        }

    }
        public int getItemCount () {
            return dados.size();
        }

         public class ViewHolderCliente extends RecyclerView.ViewHolder {

            public TextView txtNome;
            public TextView txtPhone;

            public ViewHolderCliente(View itemView) {
                super(itemView);

                txtNome = itemView.findViewById(R.id.txtNome);
                txtPhone = itemView.findViewById(R.id.txtPhone);
            }
        }
}



