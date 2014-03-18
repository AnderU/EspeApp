package com.app.pescaderiaespe;

import bd.MySQLiteHelper;
import clases.proveedor;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


public class AddProveedor extends Activity {

	private EditText cif;
	private EditText nombre;
	private EditText identificador;
	private Integer id;
	private boolean editar;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_proveedor);
		ActionBar ab = getActionBar(); 
        ab.setDisplayHomeAsUpEnabled(true);
 
        cif = (EditText) findViewById(R.id.id);
        nombre = (EditText) findViewById(R.id.nombre);
        identificador = (EditText) findViewById(R.id.idid);
        
        proveedor mProvEdit = (proveedor) getIntent().getSerializableExtra("mProvEdit");
        
        editar= (Boolean) getIntent().getSerializableExtra("modoEditar");
        
        if (mProvEdit!=null)
        {
        	cif.setText(mProvEdit.getCif());
        	nombre.setText(mProvEdit.getNombre());
        	identificador.setText(mProvEdit.getId().toString());
        	id=mProvEdit.getId();
        }
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_proveedor, menu);
		return true;
	}
   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        case android.R.id.home:
        	if (!editar)
        	{
	       	 	Intent intent = new Intent(this, Proveedores.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
        	}
        	else
        	{
        		Intent intent = new Intent(this, MuestraProveedor.class);
        		proveedor prov= new proveedor();
        		prov.setId(Integer.parseInt(identificador.getText().toString()));
        		prov.setCif(cif.getText().toString());
        		prov.setNombre(nombre.getText().toString());
        		intent.putExtra("mProv", prov);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
        	}
           return true;
        case R.id.action_add_proveedor:
        	
        		boolean error=false;
        	
            if ( nombre.getText().toString().equals("")
                	|| cif.getText().toString().equals("") )    	   
                	   error=true;
        	
        	if (!error)
        	{
	        	if (!editar)
	        	{
		        	MySQLiteHelper db = new MySQLiteHelper(this);
		        	proveedor aux= new proveedor();
		        	aux.setId(Integer.parseInt(identificador.getText().toString()));
		        	aux.setCif(cif.getText().toString());
		        	aux.setNombre(nombre.getText().toString());
		        	db.addProveedor(aux);
		       	 	Intent intent1 = new Intent(this, Proveedores.class);
		            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		            startActivity(intent1);
	        	}
	        	else
	        	{
	            	MySQLiteHelper db = new MySQLiteHelper(this);
	            	proveedor aux= new proveedor();
	            	aux.setId(Integer.parseInt(identificador.getText().toString()));
	            	aux.setCif(cif.getText().toString());
	            	aux.setNombre(nombre.getText().toString());
	            	aux.setId(id);
	            	db.updateProv(aux);
	           	 	Intent intent1 = new Intent(this, Proveedores.class);
	                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                startActivity(intent1);	
	        	}
	        	Toast.makeText(getApplicationContext(), "Datos guardados", Toast.LENGTH_SHORT).show();
        	}
        	else
        		Toast.makeText(getApplicationContext(), "Rellene los datos obligatorios", Toast.LENGTH_SHORT).show();
           return true;

        default:
            return super.onOptionsItemSelected(item);
        }
    }

}
