package com.app.pescaderiaespe;

import java.util.ArrayList;
import java.util.List;

import bd.MySQLiteHelper;

import clases.Compra;
import clases.DetalleCompras;
import clases.genero;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddCompra extends Activity {

	private Button btSig;
	private Button btGuardar;
	
    private Compra miCompra;
    private ArrayList<DetalleCompras> Compras;
    
    private String proveedor;
    
    private Spinner spGenero;
    
    private EditText cantidad;
    private EditText precio;
    private TextView proveedorET;
	
    public void fBtSig()
    {
    	     
    	boolean error=false;
    	
        if ( this.spGenero.getSelectedItemPosition()==0 || cantidad.getText().toString().equals("")
            	|| precio.getText().toString().equals(""))    	   
            	   error=true;
    	
    	if (!error)
    	{
	        DetalleCompras aux= new DetalleCompras();
	        aux.setCantidad(Double.parseDouble(cantidad.getText().toString()));
	        aux.setPrecio(Double.parseDouble(precio.getText().toString()));
	        aux.setIdGenero(((genero)spGenero.getSelectedItem()).getId());
	        Compras.add(aux);        
	        
	    	Intent intent = new Intent(this, AddCompra.class);
	    	intent.putExtra("proveedor", proveedor);
	    	intent.putExtra("compra", miCompra);
	    	intent.putExtra("detalle", Compras);
	        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        startActivity(intent);  
    	}
    	else
    	{
    		
    		Toast.makeText(getApplicationContext(), "Rellene los datos obligatorios", Toast.LENGTH_SHORT).show();
    	}
    	
    }
    
    public void fBtGuardar()
    {
        
    	 boolean error=false;
        if ( this.spGenero.getSelectedItemPosition()==0 || cantidad.getText().toString().equals("")
            	|| precio.getText().toString().equals("") )    	   
            	   error=true;
        
        if (!error)
        {
	        DetalleCompras aux= new DetalleCompras();
	        aux.setCantidad(Double.parseDouble(cantidad.getText().toString()));
	        aux.setPrecio(Double.parseDouble(precio.getText().toString()));
	        aux.setIdGenero(((genero)spGenero.getSelectedItem()).getId());
	        Compras.add(aux);        
	        	
	    	Intent intent = new Intent(this, ConfirmaCompra.class);
	    	//intent.putExtra("proveedor", proveedor);
	    	intent.putExtra("compra", miCompra);
	    	intent.putExtra("detalle", Compras);
	        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        startActivity(intent); 
        }
        else
        {
        	Toast.makeText(getApplicationContext(), "Rellene los datos obligatorios", Toast.LENGTH_SHORT).show();
        }
    }
    
    public void loadSpinnerData() {
        // database handler
    	 MySQLiteHelper db = new MySQLiteHelper(this);
 
       
     // Spinner Drop down elements
        List<genero> lables1 = db.getAllGens();
        lables1.add(0,new genero());
 
        // Creating adapter for spinner
        ArrayAdapter<genero> dataAdapter1 = new ArrayAdapter<genero>(this, android.R.layout.simple_spinner_item, lables1);
 
        // Drop down layout style - list view with radio button
        dataAdapter1
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        spGenero.setAdapter(dataAdapter1);
        
    }    
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_compra);
		
		miCompra = (Compra) getIntent().getSerializableExtra("compra");
		Compras = (ArrayList<DetalleCompras>) getIntent().getSerializableExtra("detalle");
		proveedor = (String) getIntent().getSerializableExtra("proveedor");
		
		//
        this.spGenero = (Spinner) findViewById(R.id.spGenero);
        this.cantidad = (EditText) findViewById(R.id.txtCant);
        this.precio = (EditText) findViewById(R.id.txtPrecio);
        this.proveedorET = (TextView) findViewById(R.id.txtProv);
        
        proveedorET.setText(proveedor);
        loadSpinnerData();
        
		this.btSig = (Button) findViewById(R.id.btSigAdd);
		this.btGuardar = (Button) findViewById(R.id.btguardar);

		
		btSig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            		fBtSig();
            }
        });
		
		btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	fBtGuardar();
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_compra, menu);
		return true;
	}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action buttons
        switch(item.getItemId()) {
        
        case R.id.action_cancel: 
        	
           	Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            MainActivity.main.finish();
            startActivity(intent);
        	
        	return true;
        
        default:
            return super.onOptionsItemSelected(item);
        }
    }

}
