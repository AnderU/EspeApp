package com.app.pescaderiaespe;

import bd.MySQLiteHelper;
import clases.genero;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MuestraGenero extends Activity {

	private TextView id; 
	private TextView nombre;

	
	public void borrarGen()
	{
		MySQLiteHelper db = new MySQLiteHelper(this);
	   	 Intent intent2 = new Intent(this, Genero.class);
	   	 genero o1= new genero();
	   	 o1.setNombre(nombre.getText().toString());
	   	 o1.setId(Integer.parseInt(id.getText().toString()));
	   	 db.deleteGen(o1);
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent2);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_muestra_genero);
		
		ActionBar ab = getActionBar(); 
        ab.setDisplayHomeAsUpEnabled(true);
		
		genero mGen = (genero) getIntent().getSerializableExtra("mGen");
		
		
		nombre=(TextView)findViewById(R.id.textMNombreG);
		id=(TextView)findViewById(R.id.textMId);
		id.setText(mGen.getId().toString());
		nombre.setText(mGen.getNombre());		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.muestra_genero, menu);
		return true;
	}
	   @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Take appropriate action for each action item click
	        switch (item.getItemId()) {

	        case R.id.action_editG:
	        	 Intent intent1 = new Intent(this, AddGenero.class);
	        	 genero o= new genero();
	        	 o.setNombre(nombre.getText().toString());
	        	 o.setId(Integer.parseInt(id.getText().toString()));
	        	 intent1.putExtra("mGenEdit", o);
	        	 intent1.putExtra("modoEditar", true);
	             intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	             startActivity(intent1);
	            return true;
	        
	        case R.id.action_deleteG:
	        	AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
	        	 
	            // Setting Dialog Title
	            alertDialog.setTitle("Atención");
	     
	            // Setting Dialog Message
	            alertDialog.setMessage("¿Está seguro de que desea borrar este género?");
	     
	            // Setting Icon to Dialog
	            alertDialog.setIcon(R.drawable.ic_action_discard);
	     
	            // Setting Positive "Yes" Button
	            alertDialog.setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog,int which) {
	     
	                borrarGen();
	
	           
	            }});
	     
	            // Setting Negative "NO" Button
	            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int which) {
	                
	            }});
	     
	            // Showing Alert Message
	            alertDialog.show();
	        	
	            return true;
	        
	        case android.R.id.home:
	        	 Intent intent = new Intent(this, Genero.class);
	             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	             startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }

}
	        
