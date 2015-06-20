package br.com.senac.pi.boaviagem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DashBoardActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstance){
		super.onCreate(savedInstance);
		setContentView(R.layout.dashboard);
	}
	
	public void selecionarOpcao(View view){
		TextView textView = (TextView) view;
		String opcao = "Opção: "+ textView.getText().toString();
		
		Toast.makeText(this, opcao, Toast.LENGTH_LONG).show();
	}
	

}
