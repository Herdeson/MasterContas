package br.com.pi.senac.mastercontas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import br.com.pi.databasehelper.DatabaseHelper;
import br.com.pi.databasehelper.DatabaseHelper.Devedores;
import br.senac.pi.dao.DevedorDao;
import br.senac.pi.model.Devedor;

public class ListaDevedoresActivity extends ListActivity implements OnItemClickListener, OnClickListener {

	private List<Map<String, Object>> devedores;
	
	DevedorDao dao = new DevedorDao(this);
	
	private AlertDialog alertDialog;
	private AlertDialog alertConfirma;

	
	private int devedorSelecionado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 
	
		String[] from = new String[]{Devedores.NOME, Devedores.EMAIL};
		int[] to = new int[]{R.id.txtNomeDevedor,R.id.txtEmailDevedor};
		
		//Metodo lista devedores é responsavel para realizar o select no bancno associando as duas ultimas variaveis
		SimpleAdapter adapter = new SimpleAdapter(this, listaDevedores(), R.layout.lista_devedores, from , to);
		setListAdapter(adapter);
		
		getListView().setOnItemClickListener(this);
		this.alertDialog = criaAlertDialog();
		this.alertConfirma = ciraConfirmacaoDialog();
		
	}






	public List<Map<String, Object>> listaDevedores() {
		// TODO Auto-generated method stub
		devedores = new ArrayList<Map<String, Object>>();
		List<Devedor> listaDevedores = dao.listarDevedores();
		for(Devedor devedor: listaDevedores){
			Map<String, Object> itemDevedor = new HashMap<String, Object>();
			
			String nome = devedor.getNome();
			String email = devedor.getEmail();
			
			itemDevedor.put(DatabaseHelper.Devedores.NOME, nome);
			itemDevedor.put(DatabaseHelper.Devedores.EMAIL, email);
			
			devedores.add(itemDevedor);
		}
		
		return devedores;
		
		
/*		SQLiteDatabase db = helper.getReadableDatabase();
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
		
		
		return devedores;*/
	}

	@Override
	public void onClick(DialogInterface dialog, int item) {
		// TODO Auto-generated method stub
		switch (item) {
		case 0:
			startActivity(new Intent(this, DevedorActivity.class));
			break;
		case 1:
			startActivity(new Intent(this, DevedorActivity.class));
			break;
		case 2:
			alertConfirma.show();
			break;
		case DialogInterface.BUTTON_POSITIVE:
			devedores.remove(devedorSelecionado);
			getListView().invalidateViews();
			this.dao.deletar(devedorSelecionado);
			break;
		case DialogInterface.BUTTON_NEGATIVE:
			alertConfirma.dismiss();
			break;
		default:
			break;
		

		}
		
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int positivo, long id) {
		// TODO Auto-generated method stub
		this.devedorSelecionado = positivo;
		alertDialog.show();
	}
	
	private AlertDialog criaAlertDialog() {
		// TODO Auto-generated method stub
		final CharSequence[] items ={
				getString(R.string.novo_devedor),
				getString(R.string.editar_devedor),
				getString(R.string.remover_devedor)
		};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.acoes_devedor);
		builder.setItems(items, this);
		return builder.create();
	} 
	
	@Override
	protected void onDestroy(){
		dao.closeDB();
		super.onDestroy();
	}

	private AlertDialog ciraConfirmacaoDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.configma_exclusao_devedor);
		builder.setPositiveButton(R.string.sim_confirmacao, this);
		builder.setNegativeButton(R.string.nao_confirmacao, this);
		
		return builder.create();
	}


}
