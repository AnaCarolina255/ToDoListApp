package br.senai.sp.cotia.todolistapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.databinding.FragmentDetalheTarefaBinding;
import br.senai.sp.cotia.todolistapp.databinding.FragmentPrincipalBinding;

public class DetalheTarefaFragment extends Fragment {
    private FragmentDetalheTarefaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instancia o binding
        binding = FragmentDetalheTarefaBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalhe_tarefa, container, false);
    }
}