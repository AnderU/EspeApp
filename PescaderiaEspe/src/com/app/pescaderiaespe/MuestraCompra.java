package com.app.pescaderiaespe;


import java.text.DecimalFormat;
import java.util.List;

import bd.MySQLiteHelper;

import clases.Compra;
import clases.DetalleCompras;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MuestraCompra extends Activity {

	
    private Compra miCompra;
    private List<DetalleCompras> Compras;
    private	TextView iva;
    private	TextView impuestos;
    private	TextView total;
    
	public void borrarCompra()
	{
		MySQLiteHelper db = new MySQLiteHelper(this);
	   	 Intent intent2 = new Intent(this, Compras.class);
	   	 Compra o1= new Compra();

	   	 o1.setId(miCompra.getId());
	   	 db.deleteComp(o1);
	   	 db.deleteCompPorIdCompra(miCompra.getId());
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent2);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_muestra_compra);
		
		 MySQLiteHelper db = new MySQLiteHelper(this);
		 
		miCompra = (Compra) getIntent().getSerializableExtra("compra");
		Compras = db.getAllDetCompPorCompra(miCompra.getId());
		
		this.setTitle(miCompra.toString());
		TableLayout tabla = (TableLayout) findViewById(R.id.tablaResumen);
		iva = (TextView) findViewById(R.id.textIVAD);
		impuestos = (TextView) findViewById(R.id.textImpuestosD);
		total = (TextView) findViewById(R.id.textTOTALD);

		ActionBar ab = getActionBar(); 
        ab.setDisplayHomeAsUpEnabled(true);

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
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.muestra_compra, menu);
		return true;
	}
	
	 @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        
        case R.id.action_deleteC:
        	AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
       	 
            // Setting Dialog Title
            alertDialog.setTitle("Atención");
     
            // Setting Dialog Message
            alertDialog.setMessage("¿Está seguro de que desea borrar esta compra?");
     
            // Setting Icon to Dialog
            alertDialog.setIcon(R.drawable.ic_action_discard);
     
            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {
     
                borrarCompra();

           
            }});
     
            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                
            }});
     
            // Showing Alert Message
            alertDialog.show();
           return true;
  
        case android.R.id.home:
        	 Intent intent1 = new Intent(this, Compras.class);
             intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             startActivity(intent1);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

}
