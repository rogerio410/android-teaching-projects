package com.example.rogermac.helloobjectbox.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.rogermac.helloobjectbox.FormularioCelularActivity;
import com.example.rogermac.helloobjectbox.R;
import com.example.rogermac.helloobjectbox.modelo.Celular;

import java.util.List;

import io.objectbox.Box;

/**
 * Created by rogermac on 22/01/18.
 */

public class CelularRVAdapter extends RecyclerView.Adapter<CelularRVAdapter.ViewHolder> {


    private Context context;
    private List<Celular> celulares;
    private Box<Celular> celularBox;

    public CelularRVAdapter(Context context, List<Celular> celulares, Box<Celular> celularBox) {
        this.context = context;
        this.celulares = celulares;
        this.celularBox = celularBox;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCelularModelo;
        private TextView tvCelularMarca;

        public ViewHolder(View itemView) {
            super(itemView);

            tvCelularModelo = itemView.findViewById(R.id.tv_celular_modelo);
            tvCelularMarca = itemView.findViewById(R.id.tv_celular_marca);
        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_celular_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Celular celular = this.celulares.get(position);

        holder.tvCelularModelo.setText(celular.getModelo());
        holder.tvCelularMarca.setText(celular.getMarca());

        configurarClick(holder.itemView, celular, position);

    }

    @Override
    public int getItemCount() {
        return this.celulares.size();
    }

    private void configurarClick(View itemView, Celular celular, int position) {

        itemView.setOnLongClickListener((view ) -> {
            PopupMenu popup = new PopupMenu(context, view);
            popup.getMenuInflater().inflate(R.menu.popup_menu_lista_celular, popup.getMenu());

            popup.setOnMenuItemClickListener((item) -> {

                switch (item.getItemId()){
                    case R.id.opcao_editar:
                        acaoEditar(itemView, celular, position);
                        break;
                    case R.id.opcao_remover:
                        acaoRemover(itemView, celular, position);
                        break;
                }

                return false;
            });

            popup.show();

            return true;
        });

    }

    private void acaoRemover(View view, Celular celular, int position) {
        //remover da lista do recyclerView
        this.celulares.remove(celular);
        //remover do BD
        this.celularBox.remove(celular);

        //Reorganizar a lista
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());

        Snackbar.make(view, "Celular removido: " + celular.getModelo(), Snackbar.LENGTH_SHORT).show();
    }

    private void acaoEditar(View view, Celular celular, int position) {
        //Enviar o id do Celular selecionado
        Intent intent = new Intent(context, FormularioCelularActivity.class);
        intent.putExtra("idCelular", celular.getId());

        //Iniciar o formulario
        context.startActivity(intent);

        //Avisar aa intent que um item mudou.
        notifyItemChanged(position);

    }
}





