package br.senac.pi.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.pi.databasehelper.DatabaseHelper;
import br.senac.pi.model.Devedor;

public class DevedorDao {
	/*
	 * Classe utilizada para realizar o meio de campo entre o model e o banco de dados.
	 * Utilizado para não haver repetições no codigo.
	 */
	private DatabaseHelper helper;
	private SQLiteDatabase db;
	
	public DevedorDao(Context context){
		helper = new DatabaseHelper(context);
	}
	
	private SQLiteDatabase getDB(){
		/*
		 * utilizado para pegar instância do banco de dados
		 */
		if (db == null){
			db= helper.getWritableDatabase();
		}
		
		return db;
	}
	
	public void inserir(Devedor devedor){
		this.getDB();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.Devedores.NOME, devedor.getNome());
		values.put(DatabaseHelper.Devedores.EMAIL, devedor.getEmail());
		values.put(DatabaseHelper.Devedores.TELEFONE, devedor.getTelefone());
		
		//Realiza inserção no Banco de dados
		db.insert(DatabaseHelper.Devedores.TABELA, null, values);
		
	}
	
	public List<Devedor> listarDevedores(){
		this.getDB();
		List<Devedor> listaDevedores = new ArrayList<Devedor>();
		Cursor cursor = db.rawQuery("SELECT * FROM DEVEDOR;", null);
		if (cursor.moveToFirst()){
		 do{
			Devedor devedor = new Devedor();
			devedor.setId(cursor.getInt(0));
			devedor.setNome(cursor.getString(1));
			devedor.setEmail(cursor.getString(2));
			devedor.setTelefone(cursor.getString(3));
			listaDevedores.add(devedor);
		}while (cursor.moveToNext());
		}
		
		return listaDevedores;
	}
	
	
	public void atualizar(int id, Devedor devedor){
		this.getDB();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.Devedores.NOME, devedor.getNome());
		values.put(DatabaseHelper.Devedores.EMAIL, devedor.getEmail());
		values.put(DatabaseHelper.Devedores.TELEFONE, devedor.getTelefone());
		
		db.update(DatabaseHelper.Devedores.TABELA, values, "_id = ?", new String[]{Integer.toString(id)});
		
	}
	
	public void deletar(int id){
		this.getDB();
		
		db.delete(DatabaseHelper.Devedores.TABELA, "_id", new String[]{Integer.toString(id)});
	}
	
	public void closeDB(){
		db.close();
	}

}
