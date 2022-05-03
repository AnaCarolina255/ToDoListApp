package br.senai.sp.cotia.todolistapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.model.Tarefa;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>{
    //lista de tarefas
    private List<Tarefa> tarefas;
    //variável para o Context
    private Context context;

    //construtor para receber os valores
    public TarefaAdapter(List<Tarefa> lista, Context contexto){
        this.tarefas = lista;
        this.context = contexto;
    }


    @NonNull
    @Override
    //desenha cada item do RecyclerView
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //infla o layout do adapter
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_tarefas, parent, false);
        // retorna um novo ViewHolder com a view
        return new TarefaViewHolder(view);
    }

    @Override
    //criar o elemento visível
    //é chamado para cada item da lista
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        //Calendar.getInstance().getTimeInMillis();
        //obtém a tarefa pela position
        Tarefa t = tarefas.get(position);
        //distribuir as informações da tarefa no Holder
        holder.tvTitulo.setText(t.getTitulo());
        //se estiver concluída
        if (t.isConcluida()){
            holder.tvStatus.setText(R.string.concluida);
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.verdinho));
        }else {
            holder.tvStatus.setText(R.string.aberta);
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.amarelin));
        }

        //formata a data de long pra string
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvData.setText(formatador.format(t.getDataPrevista()));
    }

    @Override
    // retorna a quantidade de elementos a serem exibidos na lista
    public int getItemCount() {
        if (tarefas != null) {
            return tarefas.size();
        }
        return 0;
    }

    class TarefaViewHolder extends RecyclerView.ViewHolder{
        // variáveis para acessar os components do xml
        TextView tvTitulo, tvData, tvStatus;

        //Classe ViewHolder para mapear os componentes do xml
        public TarefaViewHolder(View view){
            //chama o construtor da superclasse
            super(view);
            // passar para as variáveis os componentes do xml
            tvTitulo = view.findViewById(R.id.tv_titulo);
            tvData = view.findViewById(R.id.tv_data);
            tvStatus = view.findViewById(R.id.tv_status);
        }
    }
}
