package br.com.pi.senac.mastercontas;

import br.com.pi.databasehelper.DatabaseHelper;
import br.senac.pi.dao.DevedorDao;
import br.senac.pi.model.Devedor;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DevedorActivity extends Activity {
	private DatabaseHelper helper;
	private EditText editNome , editeMail, editTelefone;
	
	//Refatorando
	Devedor devedor = new Devedor();
	DevedorDao dao = new DevedorDao(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_devedor);
		
		editNome = (EditText) findViewById(R.id.nomeDevedor);
		editeMail = (EditText) findViewById(R.id.email);
		editTelefone = (EditText) findViewById(R.id.telefone);
		
		helper = new DatabaseHelper(this);
	}
	
	public void cadastrarDevedor(View view){
		
		devedor.setNome(editNome.getText().toString());
		devedor.setEmail(editeMail.getText().toString());
		devedor.setTelefone(editTelefone.getText().toString());
		
		dao.inserir(devedor);
		
		startActivity(new Intent(this, ListaDevedoresActivity.class));
		
		/*
		SQLiteDatabase db = helper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("nome", editNome.getText().toString());
		values.put("email", editeMail.getText().toString());
		values.put("telefone", editTelefone.getText().toString());
		
		long resultado = db.insert("devedor", null, values);
		
		if(resultado != -1){
			Toast.makeText(this, R.string.msg_sucesso, Toast.LENGTH_LONG).show();
			finish(); //Fechar a Tela de Cadastro
		} else {
			Toast.makeText(this, R.string.msg_error, Toast.LENGTH_LONG).show();
		}
		*/
	}
	
	@Override
	protected void onDestroy(){
		helper.close();
		super.onDestroy();
	}
	


}
