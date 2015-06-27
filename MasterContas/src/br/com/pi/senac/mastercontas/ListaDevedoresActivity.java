package br.com.pi.senac.mastercontas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.pi.databasehelper.DatabaseHelper;
import br.com.pi.databasehelper.DatabaseHelper.Devedores;
import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleAdapter;

public class ListaDevedoresActivity extends ListActivity {
	private DatabaseHelper helper;
	private List<Map<String, Object>> devedores;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 
		helper = new DatabaseHelper(this);
		String[] from = new String[]{Devedores.NOME, Devedores.EMAIL};
		int[] to = new int[]{R.id.txtNomeDevedor,R.id.txtEmailDevedor};
		
		//Metodo lista devedores é responsavel para realizar o select no bancno associando as duas ultimas variaveis
		SimpleAdapter adapter = new SimpleAdapter(this, listaDevedores(), R.layout.lista_devedores, from , to);
		setListAdapter(adapter);
		
	}

	public List<Map<String, Object>> listaDevedores() {
		// TODO Auto-generated method stub
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query(Devedores.TABELA,null, null, null, null, null, null);
		cursor.moveToFirst();
		
		devedores = new ArrayList<Map<String,Object>>();
		
		for(int i = 0; i < cursor.getCount(); i++){
			Map<String, Object> devedor  = new HashMap<String, Object>();
			String nome = cursor.getString(1);
			String email = cursor.getString(3);
			
			devedor.put(Devedores.NOME, nome);
			devedor.put(Devedores.EMAIL, email);
			
			devedores.add(devedor);
			cursor.moveToNext();
		}
		
		
		return devedores;
	}


}
