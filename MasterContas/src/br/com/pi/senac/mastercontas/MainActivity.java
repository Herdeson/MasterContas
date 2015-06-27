package br.com.pi.senac.mastercontas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void selecionaOpcao(View view){
		switch (view.getId()) {
		case R.id.cadDevedor:
			startActivity(new Intent(this, DevedorActivity.class));
			break;
		case R.id.listDevedores:
			startActivity(new Intent(this, ListaDevedoresActivity.class));
			break;

		default:
			break;
		}
	}

}
