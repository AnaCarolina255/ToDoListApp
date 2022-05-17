package br.senai.sp.cotia.todolistapp.fragment;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.text.SimpleDateFormat;
import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.databinding.FragmentDetalheTarefaBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;
public class DetalheTarefaFragment extends Fragment {
    // variável para a tarefa a ser detalhada
    private Tarefa tarefa;
    private FragmentDetalheTarefaBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // instancia o binding
        binding = FragmentDetalheTarefaBinding.inflate(inflater, container, false);
        // verifica se existe algo sendo passado no bundle
        if (getArguments() != null){
            // recupero a tarefa
            tarefa = (Tarefa) getArguments().getSerializable("tarefa");
            // popula os campos com as informações da tarefa
            binding.tvContentTitulo.setText(tarefa.getTitulo());
            binding.tvContentDescricao.setText(tarefa.getDescricao());
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            binding.tvContentData.setText(formatador.format(tarefa.getDataPrevista()));
            // se a tarefa estiver concluída
            if (tarefa.isConcluida()){
                binding.tvContentTitulo.setBackgroundColor(getResources().getColor(R.color.verdinho));
            }else{
                binding.tvContentTitulo.setBackgroundColor(getResources().getColor(R.color.amarelin));
            }
        }
        // retorna a view raiz do binding
        return binding.getRoot();
    }

}