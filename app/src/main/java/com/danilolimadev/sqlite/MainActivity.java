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
            //bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(nome VARCHAR, idade INT(3) ) "); //Executa algo na linguagem SQL
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INT(3) ) "); //PRIMARY KEY indica que o campo nunca pode receber dois valores iguais ou seja, ele não pode se repetir, AUTOINCREMENT sempre vai incrementar 1 de forma automática
            //bancoDados.execSQL("DROP TABLE pessoas");   //Apagar a tabela pessoas


            //Inserir dados
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Danilo', 22)");   //Inserindo dentro do banco de dados no campo nome e idade os valores
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Ana', 20)");        //Uma vez que os dados foram salvos no dispositivos, não é necessário enviar novamente
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Mariana', 24)");
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Tiago', 18)");

            //bancoDados.execSQL("UPDATE pessoas SET idade = 28");    //Atualize na tabela pessoas  configure a idade para 28, desse modo atualiza todas as pessoas e coloca a idade 28
            //bancoDados.execSQL("UPDATE pessoas SET idade = 28 WHERE nome = 'Tiago'");   //Nesse caso só vai alterar a idade do Tiago
            //bancoDados.execSQL("UPDATE pessoas SET nome = 'Marcelo' WHERE nome = 'Tiago'"); //Nesse caso altera o nome

            //bancoDados.execSQL("DELETE from pessoas WHERE nome = 'Tiago'"); //Deleta da tabela pessoas onde o nome é igual a Tiago


            //Cursor cursor = bancoDados.rawQuery("SELECT nome, idade, id FROM pessoas", null); //O cursor permite que percorra os dados gravados
            Cursor cursor = bancoDados.rawQuery("SELECT * FROM pessoas", null); //* indica todos, sempre vai trazer todos os campos
            //Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas WHERE idade > 21", null);  //WHERE faz uma pesquisa, por exemplo trazendo os resultados somente com idade maior que 21
            //Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas WHERE idade > 21 AND nome = 'Danilo'", null);  //O AND indica mais de uma condição na pesquisa
            //Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas WHERE nome LIKE '%ni%'", null);  //LIKE é como, traz por exemplo pessoas que o nome possui ni com o % indicando que pode ter qualquer coisa a direita ou a esquerda, dependendo de onde foi colocado

            int indiceColunaNome = cursor.getColumnIndex("nome");   //Recuperar qual o índice dessa coluna
            int indiceColunaIdade = cursor.getColumnIndex("idade");
            int indiceColunaId = cursor.getColumnIndex("id");

            cursor.moveToFirst();

            while (cursor != null) {

                Log.i("RESULTADO - nome: ", cursor.getString(indiceColunaNome));
                Log.i("RESULTADO - idade: ", cursor.getString(indiceColunaIdade));
                Log.i("RESULTADO - id: ", cursor.getString(indiceColunaId));
                cursor.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
