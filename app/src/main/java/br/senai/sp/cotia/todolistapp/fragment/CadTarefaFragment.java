package br.senai.sp.cotia.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.database.AppDatabase;
import br.senai.sp.cotia.todolistapp.databinding.FragmentCadTarefaBinding;
import br.senai.sp.cotia.todolistapp.databinding.FragmentPrincipalBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;

public class CadTarefaFragment extends Fragment {
    private FragmentCadTarefaBinding binding;
    //variável para o datepicker
    DatePickerDialog datePicker;
    //variáveis para o dia, mês e ano
    int year, month, day;
    //variável para a data atual
    Calendar dataAtual;
    //variável para a data formatada
    String dataEscolhida = "";
    //variável para acessar a database
    AppDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // instanciar a appdatabase
        database = AppDatabase.getDatabase(getActivity());

        //instancia o binding
        binding = FragmentCadTarefaBinding.inflate(inflater, container, false);

        //instancia a data atual
        dataAtual = Calendar.getInstance();

        //descobre o dia, mês e ano atuais
        year = dataAtual.get(Calendar.YEAR);
        month = dataAtual.get(Calendar.MONTH);
        day = dataAtual.get(Calendar.DAY_OF_MONTH);

        //instanciar o datepicker
        datePicker = new DatePickerDialog(getContext(), (view, ano, mes, dia) -> {
            //cai aqui toda vez que clica no OK do DatePickerDialog
            // passa para as variáveis globais
            year = ano;
            month = mes;
            day = dia;
            //formata a String da dataEscolhida
            dataEscolhida = String.format("%02d/%02d/%04d", day, month + 1, year);
            //jogar a String no botão
            binding.btData.setText(dataEscolhida);
        },year, month, day);

        //listener do botão de data
        binding.btData.setOnClickListener(v -> {

            //abre o DatePicker
            datePicker.show();
        });

        // listener do botão salvar
        binding.btSalvar.setOnClickListener(v -> {
            // validar os campos
            if (binding.editTitulo.getText().toString().isEmpty()){
                binding.editTitulo.setError(getString(R.string.inf_titulo));
                binding.editTitulo.requestFocus();
            }else if (dataEscolhida.isEmpty()){
                Toast.makeText(getContext(), R.string.inf_data, Toast.LENGTH_SHORT).show();
            }else{
                //criar um objeto tarefa
                Tarefa tarefa = new Tarefa();
                //popular a tarefa
                tarefa.setTitulo(binding.editTitulo.getText().toString());
                tarefa.setDescricao(binding.editDescricao.getText().toString());
                //cria um calendar e popula com a data que foi selecionada
                //Calendar.get instance() -> está pegando a data atual
                Calendar dataRealizacao = Calendar.getInstance();
                dataRealizacao.set(year, month, day);
                //passar para a tarefa os milissegundos da data
                tarefa.setDataPrevista(dataRealizacao.getTimeInMillis());
                //criar um Calendar para a data atual
                Calendar dataAtual = Calendar.getInstance();
                tarefa.setDataCriacao(dataAtual.getTimeInMillis());
                //salvar a tarefa no BD
                new InsertTarefa().execute(tarefa);
            }
        });


        //Inflate the layout for this fragment
        return binding.getRoot();
    }

    //classe para a Task de inserir Tarefa
    private class InsertTarefa extends AsyncTask<Tarefa, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.w("PASSOU", "no OnPreExecute");
        }

        @Override
        protected String doInBackground(Tarefa... tarefas) {
            Log.w("PASSOU", "no DoInBackground");
            //extrair a tarefa do vetor
            Tarefa t = tarefas[0];
            try {
                //tenta inserir
                database.getTarefaDao().insert(t);
                //retorna ok caso tenha dado erro
                return "ok";
            }catch (Exception e){
                e.printStackTrace();
                //retorna a mensagem de errdo caso tenha dado erro
                return e.getMessage();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Log.w("PASSOU", "no OnProgressUpdate");
        }

        @Override
        protected void onPostExecute(String resultado) {
            if (resultado.equals("ok")){
                Log.w("RESULTADO", "IUPIIIII");
            }else{
                Log.w("RESULTADO", resultado);
                Toast.makeText(getContext(), "DEU RUIM "+resultado, Toast.LENGTH_SHORT).show();
            }
        }
    }
}