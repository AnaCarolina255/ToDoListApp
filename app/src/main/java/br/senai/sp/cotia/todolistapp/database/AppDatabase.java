package br.senai.sp.cotia.todolistapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.senai.sp.cotia.todolistapp.model.Tarefa;

//falar qual é a entidade
@Database(entities = {Tarefa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    // atributo para acessar a database
    private static AppDatabase database;
    // atributo para TarefaDao
    public abstract TarefaDao getTarefaDao();

    //método para acessar o atributo que acessa a database
    public static AppDatabase getDatabase(Context context){
        // verificar se não foi instanciada
        if (database == null){
            // instancia a database
            database = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "todolist").build();
        }
        //retorna a database
        return database;
    }

}
