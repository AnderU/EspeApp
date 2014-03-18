package com.app.pescaderiaespe;

import bd.MySQLiteHelper;
import clases.proveedor;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MuestraProveedor extends Activity {

	private TextView cif; 
	private TextView nombre;
	private TextView identificador;
	private Integer id;
	
	public void borrarProv()
	{
		MySQLiteHelper db = new MySQLiteHelper(this);
	   	 Intent intent2 = new Intent(this, Proveedores.class);
	   	 proveedor o1= new proveedor();
	   	 o1.setCif(cif.getText().toString());
	   	 o1.setNombre(nombre.getText().toString());
	   	 o1.setId(id);
	   	 db.deleteProv(o1);
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent2);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_muestra_proveedor);
		
		ActionBar ab = getActionBar(); 
        ab.setDisplayHomeAsUpEnabled(true);
		
		proveedor mProv = (proveedor) getIntent().getSerializableExtra("mProv");
		
		cif=(TextView)findViewById(R.id.textMCif);
		nombre=(TextView)findViewById(R.id.textMNombre);
		identificador=(TextView)findViewById(R.id.textMId);
		
		identificador.setText(mProv.getId().toString());
		cif.setText(mProv.getCif());
		nombre.setText(mProv.getNombre());
		id=mProv.getId();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.muestra_proveedor, menu);
		return true;
	}
	   @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Take appropriate action for each action item click
	        switch (item.getItemId()) {

	        case R.id.action_edit:
	        	 Intent intent1 = new Intent(this, AddProveedor.class);
	        	 proveedor o= new proveedor();
	        	 
	        	 o.setId(Integer.parseInt(identificador.getText().toString()));
	        	 o.setCif(cif.getText().toString());
	        	 o.setNombre(nombre.getText().toString());
	        	 o.setId(id);
	        	 intent1.putExtra("mProvEdit", o);
	        	 intent1.putExtra("modoEditar", true);
	             intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	             startActivity(intent1);
	            return true;
	        
	        case R.id.action_delete:
	        	AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
	        	 
	            // Setting Dialog Title
	            alertDialog.setTitle("Atención");
	     
	            // Setting Dialog Message
	            alertDialog.setMessage("¿Está seguro de que desea borrar este proveedor?");
	     
	            // Setting Icon to Dialog
	            alertDialog.setIcon(R.drawable.ic_action_discard);
	     
	            // Setting Positive "Yes" Button
	            alertDialog.setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog,int which) {
	     
	                borrarProv();
	
	           
	            }});
	     
	            // Setting Negative "NO" Button
	            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int which) {
	                
	            }});
	     
	            // Showing Alert Message
	            alertDialog.show();
	        	
	            return true;
	        
	        case android.R.id.home:
	        	 Intent intent = new Intent(this, Proveedores.class);
	             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	             startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }

}
	        
