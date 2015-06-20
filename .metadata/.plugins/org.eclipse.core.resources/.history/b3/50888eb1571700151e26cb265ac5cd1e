package com.senac.pi.calc2;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	private EditText entradaUm;
	private EditText entradaDois;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        this.entradaUm = (EditText) findViewById(R.id.entradaUm);
        this.entradaDois= (EditText) findViewById(R.id.entradaDois);
    }


   public void somarNum(View v){
	   float pn = Float.parseFloat(this.entradaUm.getText().toString());
	   float sn = Float.parseFloat(this.entradaDois.getText().toString());
	   
	   float resultado =  pn + sn;
	   Toast.makeText(getBaseContext(), "A Soma é:" + resultado, Toast.LENGTH_LONG).show();
	   
   }
   
   public void subtrai(View v){
	   float pn = Float.parseFloat(this.entradaUm.getText().toString());
	   float sn = Float.parseFloat(this.entradaDois.getText().toString());
	   
	   float resultado =  pn - sn;
	   //Toast.makeText(getBaseContext(), "A Subtração é:" + resultado, Toast.LENGTH_LONG).show();
	   
	   AlertDialog.Builder infoResultado = new AlertDialog.Builder(MainActivity.this);
	   infoResultado.setTitle("Resultado");
	   infoResultado.setMessage("A Subtração é: "+resultado);
	   infoResultado.setNeutralButton("Ok", null);
	   infoResultado.show();
	   
   }
}
