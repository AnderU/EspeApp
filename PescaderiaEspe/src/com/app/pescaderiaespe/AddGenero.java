package com.app.pescaderiaespe;

import bd.MySQLiteHelper;
import clases.genero;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddGenero extends Activity {

	private EditText id;
	private EditText nombre;
	private boolean editar;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_genero);
		ActionBar ab = getActionBar(); 
        ab.setDisplayHomeAsUpEnabled(true);

        
        id = (EditText) findViewById(R.id.txtCant);
        nombre = (EditText) findViewById(R.id.txtPrecio);
        
        genero mGenEdit = (genero) getIntent().getSerializableExtra("mGenEdit");
        
        editar= (Boolean) getIntent().getSerializableExtra("modoEditar");
        
        if (mGenEdit!=null)
        {
        	id.setText(mGenEdit.getId().toString());
        	nombre.setText(mGenEdit.getNombre());
        	
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_genero, menu);
		return true;
	}
	
   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {

        case android.R.id.home:
        	if (!editar)
        	{
	       	 	Intent intent = new Intent(this, Genero.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
        	}
        	else
        	{
        		Intent intent = new Intent(this, MuestraGenero.class);
        		genero gen= new genero();
        		gen.setNombre(nombre.getText().toString());
        		gen.setId(Integer.parseInt(id.getText().toString()));
        		intent.putExtra("mGen", gen);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
        	}

            return true;
        case R.id.action_add_genero:
        	boolean error=false;
        	
            if ( nombre.getText().toString().equals("")
                	|| id.getText().toString().equals("") )    	   
                	   error=true;
        	
        	if (!error)
        	{
	        	if (!editar)
	        	{
	            	MySQLiteHelper db = new MySQLiteHelper(this);
	            	genero aux= new genero();
	            	aux.setId(Integer.parseInt(id.getText().toString()));
	            	aux.setNombre(nombre.getText().toString());
	            	db.addGenero(aux);
	           	 	Intent intent1 = new Intent(this, Genero.class);
	                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                startActivity(intent1);
	        	}
	        	else
	        	{
	            	MySQLiteHelper db = new MySQLiteHelper(this);
	            	genero aux= new genero();
	            	aux.setNombre(nombre.getText().toString());
	            	aux.setId(Integer.parseInt(id.getText().toString()));
	            	db.updateGen(aux);
	           	 	Intent intent1 = new Intent(this, Genero.class);
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
