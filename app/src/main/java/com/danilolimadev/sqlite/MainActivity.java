package com.danilolimadev.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {

            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(nome VARCHAR, idade INT(3) ) "); //Executa algo na linguagem SQL

            //Inserir dados
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Danilo', 22)");   //Inserindo dentro do banco de dados no campo nome e idade os valores
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Ana', 20)");

            Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas", null); //O cursor permite que percorra os dados gravados

            int indiceColunaNome = cursor.getColumnIndex("nome");   //Recuperar qual o índice dessa coluna
            int indiceColunaIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();

            while (cursor != null) {

                Log.i("RESULTADO - nome: ", cursor.getString(indiceColunaNome));
                Log.i("RESULTADO - idade: ", cursor.getString(indiceColunaIdade));
                cursor.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
