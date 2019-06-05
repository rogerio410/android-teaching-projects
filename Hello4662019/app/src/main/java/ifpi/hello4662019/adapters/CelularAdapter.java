package ifpi.hello4662019.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ifpi.hello4662019.R;
import ifpi.hello4662019.modelos.Celular;

public class CelularAdapter
        extends RecyclerView.Adapter<CelularAdapter.CelularViewHolder> {


    private List<Celular> celulares;
    private Context context;

    public CelularAdapter(List<Celular> celulares, Context context){

        this.celulares = celulares;
        this.context = context;
    }

    @NonNull
    @Override
    public CelularViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int tipo) {

        LayoutInflater inflater = LayoutInflater.from(this.context);

        View view = inflater.inflate(R.layout.item_celular, viewGroup, false);

        CelularViewHolder viewHolder = new CelularViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CelularViewHolder celularViewHolder, int pos) {

        Celular celular = celulares.get(pos);

        celularViewHolder.txtCelularMarca.setText(celular.getMarca());
        celularViewHolder.txtCelularModelo.setText(celular.getModelo());
        celularViewHolder.txtCelularValor.setText("R$ " + celular.getValor());

        // Mudar a cor do fundo

        if (celular.getMarca().equalsIgnoreCase("Apple")){
            celularViewHolder.itemView.setBackgroundColor(Color.parseColor("#ffc1e3"));celularViewHolder.itemView.setBackgroundColor(Color.parseColor("#ffc1e3"));
        }else {
            celularViewHolder.itemView.setBackgroundColor(Color.parseColor("#ffc1e3"));celularViewHolder.itemView.setBackgroundColor(Color.parseColor("#4fc3f7"));
        }

    }

    @Override
    public int getItemCount() {
        return celulares.size();
    }

    // View Holder
    class CelularViewHolder
            extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView txtCelularMarca, txtCelularModelo, txtCelularValor;


        public CelularViewHolder(@NonNull View itemView) {
            super(itemView);

            // binding das views
            txtCelularMarca = itemView.findViewById(R.id.txt_celular_marca);
            txtCelularModelo = itemView.findViewById(R.id.txt_celular_modelo);
            txtCelularValor = itemView.findViewById(R.id.txt_celular_valor);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Snackbar.make(v, "Clicou", Snackbar.LENGTH_LONG).show();
//                }
//            });
        }



        @Override
        public void onClick(View v) {
            int posicao = getAdapterPosition();
            Celular celular = celulares.get(posicao);
            //Snackbar.make(v, "Clicou no: " + celular.getModelo(), Snackbar.LENGTH_LONG).show();

            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setTitle("Seleção de Celular");
            builder.setMessage("Você clicou em " + celular.getModelo());
            builder.setPositiveButton("Ok", null);
            builder.setNegativeButton("CANCELAR", null);
            builder.setNeutralButton("NADA", null);

            builder.create().show();
        }

        @Override
        public boolean onLongClick(View v) {
            int posicao = getAdapterPosition();
            Celular celular = celulares.get(posicao);
            Snackbar.make(v, "Clicou no: " + celular.getModelo(), Snackbar.LENGTH_LONG).show();
            return true;
        }
    }
}











