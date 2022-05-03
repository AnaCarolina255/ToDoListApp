package br.senai.sp.cotia.todolistapp.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.adapter.TarefaAdapter;
import br.senai.sp.cotia.todolistapp.database.AppDatabase;
import br.senai.sp.cotia.todolistapp.databinding.FragmentPrincipalBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;

public class PrincipalFragment extends Fragment {
    private FragmentPrincipalBinding binding;
    //variável para database
    private AppDatabase database;
    //variável para o Adapter
    private TarefaAdapter adapter;
    //variável para a Lista
    private List<Tarefa> tarefas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instancia o binding
        binding = FragmentPrincipalBinding.inflate(inflater, container, false);

        //clique no botão de adicionar tarefa
        binding.btnAddTarefa.setOnClickListener(v ->{
            NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_principalFragment_to_cadTarefaFragment);
        });

        return  binding.getRoot();
    }
}