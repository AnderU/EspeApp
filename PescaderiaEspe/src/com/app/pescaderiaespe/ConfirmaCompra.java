package com.app.pescaderiaespe;

import java.text.DecimalFormat;
import java.util.ArrayList;

import bd.MySQLiteHelper;

import clases.Compra;
import clases.DetalleCompras;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmaCompra extends Activity {

	
    private Compra miCompra;
    private ArrayList<DetalleCompras> Compras;
    	
	private Button btConfirm;
	
    private	TextView iva;
    private	TextView impuestos;
    private	TextView total;
	
    public void fBtConfirm()
    {
    	 MySQLiteHelper db = new MySQLiteHelper(this);
    	 long idCompra=db.addCompra(miCompra);
 	    for (int i = 0; i <Compras.size(); i++) {
 	    	Compras.get(i).setIdCompra((int) idCompra);
 	    	db.addDetCompra(Compras.get(i));
	    }  		
 	   Toast.makeText(getApplicationContext(), "Compra guardada", Toast.LENGTH_SHORT).show();  	
    	Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent); 
    }
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirma_compra);
	
		
	 MySQLiteHelper db = new MySQLiteHelper(this);
		 
		miCompra = (Compra) getIntent().getSerializableExtra("compra");
		Compras = (ArrayList<DetalleCompras>) getIntent().getSerializableExtra("detalle");
		TableLayout tabla = (TableLayout) findViewById(R.id.tablaResumen);
		
		
		this.setTitle(miCompra.toString());
		iva = (TextView) findViewById(R.id.textIVAD);
		impuestos = (TextView) findViewById(R.id.textImpuestosD);
		total = (TextView) findViewById(R.id.textTOTALD);

		Double total=0.0;
	    for (int i = 0; i <Compras.size(); i++) {

	        TableRow row= new TableRow(this);
	        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
	        row.setLayoutParams(lp);
	        TextView genero = new TextView(this);
	        TextView cantidad = new TextView(this);
	        TextView precio = new TextView(this);

	        genero.setText(db.getGenero(Compras.get(i).getIdGenero()).getNombre());
	        cantidad.setText(Compras.get(i).getCantidad().toString());
	        precio.setText(Compras.get(i).getPrecio().toString());
	        total+=Compras.get(i).getCantidad()*Compras.get(i).getPrecio();
	        row.addView(genero);
	        row.addView(cantidad);
	        row.addView(precio);
	        tabla.addView(row,i+1);
	    }
	    DecimalFormat df= new DecimalFormat("0.00"); 
		total=total*(1+(miCompra.getImpuestos()/100))*(1+(miCompra.getIva()/100));
		iva.setText(Double.toString(miCompra.getIva()));
		impuestos.setText(Double.toString(miCompra.getImpuestos()));
		this.total.setText(df.format(total));
		

        this.btConfirm = (Button) findViewById(R.id.btnConfirmar);
        
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            		fBtConfirm();
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.confirma_compra, menu);
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
