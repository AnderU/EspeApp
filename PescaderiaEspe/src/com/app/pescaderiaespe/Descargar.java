package com.app.pescaderiaespe;

import clases.Compra;
import clases.DetalleCompras;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;


import bd.MySQLiteHelper;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Descargar extends Activity {

	private Button descargar;
	private Button descargaryenviar;
	private TextView ultimaDescarga;
	private ListView ficheros;
	private ArrayAdapter<String> dataAdapter1;
	
	
	//Drawer
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mOptions;
    
    Integer[] imageId = {
  	      R.drawable.ic_basket,
  	      R.drawable.ic_eye ,
  	      R.drawable.ic_action_download,
  	      R.drawable.ic_action_person,
  	      R.drawable.ic_fish,
  	      R.drawable.ic_action_settings
  	      
  	  };
    
	public void loadListView()
    {    	 
     	File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File (sdCard.getAbsolutePath() + "/excels");
        File[] listOfFiles = directory.listFiles();
        LinkedList<String> lables1 = new LinkedList<String>();
        for (int i=0; i<listOfFiles.length; i++)
        {
            
        	lables1.add(listOfFiles[i].toString().substring(directory.toString().length()+1,listOfFiles[i].toString().length() ));
        }       
 
        // Creating adapter for spinner
        dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lables1);
 
        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_list_item_1);
 
        // attaching data adapter to spinner
        ficheros.setAdapter(dataAdapter1);
    }
    
    public void borrarComp()
    {
    	 MySQLiteHelper db = new MySQLiteHelper(this);
    	SQLiteDatabase db1 = db.getWritableDatabase();
    	db1.execSQL("DELETE FROM compras");
    	db1.execSQL("DELETE FROM detallecompras");
    }
    
    public void borrarFicheros()
    {
    	AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
   	 
        // Setting Dialog Title
        alertDialog.setTitle("Atención");
 
        // Setting Dialog Message
        alertDialog.setMessage("¿Está seguro de que desea borrar los ficheros excel?");
 
        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.ic_action_discard);
 
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
 

                File sdCard = Environment.getExternalStorageDirectory();
                File directory = new File (sdCard.getAbsolutePath() + "/excels");
                File[] listOfFiles = directory.listFiles();
                for (int i = 0; i < listOfFiles.length; i++) {
                	
                	listOfFiles[i].delete();

                  }
                loadListView();

       
        }});
 
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            
        }});
 
        // Showing Alert Message
        alertDialog.show();
    }
    
    public void borrar()
    {
    	AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
   	 
        // Setting Dialog Title
        alertDialog.setTitle("Atención");
 
        // Setting Dialog Message
        alertDialog.setMessage("¿Está seguro de que desea borrar las compras existentes?");
 
        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.ic_action_discard);
 
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
 
            borrarComp();

       
        }});
 
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            
        }});
 
        // Showing Alert Message
        alertDialog.show();
    }
    
	public void itemListClick(String file)
	{
		
    	Intent intent = new Intent();
    	intent.setAction(android.content.Intent.ACTION_VIEW);
    	File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File (sdCard.getAbsolutePath() + "/excels");
    	intent.setDataAndType(Uri.parse("file://"+directory+"/"+file), "application/vnd.ms-excel");
    	startActivity(intent); 
	}
    
    @SuppressLint("SimpleDateFormat")
	public void enviar()
    {
    	MySQLiteHelper db = new MySQLiteHelper(this);
    	
    	
    	clases.Configuracion conf =db.getConf(1);
     
    	
    	
    	//descargar()
    	
    	Intent email = new Intent(Intent.ACTION_SEND);
   
		  email.putExtra(Intent.EXTRA_EMAIL, new String[]{conf.getEmail()});

		  email.putExtra(Intent.EXTRA_SUBJECT, "Excel Compras Pescaderia");
		  
		  Date fecha= new Date();
		  SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		  email.putExtra(Intent.EXTRA_TEXT, "Email generado el "+df.format(fecha));
		  email.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + descargar()));
		  //need this to prompts email client only
		  email.setType("message/rfc822");
		 
		  startActivity(Intent.createChooser(email, "Choose an Email client :"));
    	
    }
	
	@SuppressLint("SimpleDateFormat")
	public String descargar()
	{
    	MySQLiteHelper db = new MySQLiteHelper(this);
    	
    	List<Compra> compras=db.getAllComp();
    	List<DetalleCompras> detCompras;
    	LinkedList<LinkedList<Label>> labels= new LinkedList<LinkedList<Label>>();
    	int fila=0;
    	
    	for (int i=0; i<compras.size(); i++)
    	{
    		labels.add(new LinkedList<Label>());

    		//Compra
    		labels.get(fila).add(new Label(0,fila,"0"));
    		labels.get(fila).add(new Label(1,fila,compras.get(i).getId().toString()));
    		labels.get(fila).add(new Label(2,fila,compras.get(i).getIdProveedor().toString()));
    		labels.get(fila).add(new Label(3,fila,compras.get(i).getFecha()));    		
    		labels.get(fila).add(new Label(4,fila,Double.toString(compras.get(i).getIva())));
    		labels.get(fila).add(new Label(5,fila,Double.toString(compras.get(i).getImpuestos())));
    		fila++;
    		
    		//detalleCompra
    		detCompras=db.getAllDetCompPorCompra(compras.get(i).getId());
    		for (int j=0; j<detCompras.size(); j++)
    		{
    			labels.add(new LinkedList<Label>());
    			
        		labels.get(fila).add(new Label(0,fila,"1"));
        		labels.get(fila).add(new Label(1,fila,detCompras.get(j).getIdCompra().toString()));
        		labels.get(fila).add(new Label(2,fila,detCompras.get(j).getIdGenero().toString()));
        		labels.get(fila).add(new Label(3,fila,detCompras.get(j).getCantidad().toString()));    		
        		labels.get(fila).add(new Label(4,fila,detCompras.get(j).getPrecio().toString()));
        		labels.get(fila).add(new Label(5,fila,""));
    			
    			fila++;
    		}    		
    	
    	}
    	
    	SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
    	Date fecha= new Date();
    	
    	String Fnamexls= df.format(fecha)+"_"+db.getSecuenciaArchivos(1) + ".xls";
    	db.updateSecuenciaArchivos(1, db.getSecuenciaArchivos(1)+1);
    	
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File (sdCard.getAbsolutePath() + "/excels");
        directory.mkdirs();
        File file = new File(directory, Fnamexls);

        WorkbookSettings wbSettings = new WorkbookSettings();

      wbSettings.setLocale(new Locale("es", "ES"));

      WritableWorkbook workbook;
      try {
          workbook = Workbook.createWorkbook(file, wbSettings);

          WritableSheet sheet = workbook.createSheet("Compras", 0);
          //x,y

          try {
        	  for (int i=0; i<labels.size(); i++)
        	  {
        		  for (int j=0; j<labels.get(i).size(); j++)
        			  sheet.addCell(labels.get(i).get(j));
        	  }

                    } catch (RowsExceededException e) {
                           // TODO Auto-generated catch block
                           e.printStackTrace();
                    } catch (WriteException e) {
                           // TODO Auto-generated catch block
                           e.printStackTrace();
                    }
      

          workbook.write();
          try {
                           workbook.close();
                           SimpleDateFormat df1= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                           db.updateUltimaDescarga(1,df1.format(fecha));
                           Toast.makeText(getApplicationContext(), "Fihero excel "+file+" generado", Toast.LENGTH_LONG).show();
                           loadListView();
                    } catch (WriteException e) {
                           // TODO Auto-generated catch block
                           e.printStackTrace();
                    }
          //createExcel(excelSheet);
      } catch (IOException e) {
          // TODO Auto-generated catch block
          
    	  e.printStackTrace();
      }
      
      return file.toString();
		
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_descargar);
		
		//DRAWER
		
		mTitle = mDrawerTitle = getTitle();
        mOptions = getResources().getStringArray(R.array.options_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layoutD);
        mDrawerList = (ListView) findViewById(R.id.left_drawerD);
        
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        CustomList adapter = new CustomList(Descargar.this, mOptions, imageId);
            
        mDrawerList.setAdapter(adapter); 
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.action_settings,  /* "open drawer" description for accessibility */
                R.string.proveedores  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		MySQLiteHelper db = new MySQLiteHelper(this);
			
	
		this.ultimaDescarga = (TextView) findViewById(R.id.textUltD);
		this.ficheros = (ListView) findViewById(R.id.listViewFicheros);
		this.loadListView();
		ficheros.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

              String o = (String) ficheros.getItemAtPosition(position);
              itemListClick(o);
              
            }

          });
		ultimaDescarga.setText("Última descarga: "+db.getUltimaDescarga(1));
		
		
		
        this.descargar = (Button) findViewById(R.id.btDescarga);
        this.descargaryenviar = (Button) findViewById(R.id.btEnviar);
        descargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            		
            	descargar();
   	
            }
        });
        descargaryenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            	enviar();
            	
            	
   	
            }
        });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.descargar, menu);
		return true;
	}
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
		   
		   
	       if (mDrawerToggle.onOptionsItemSelected(item)) {
	           return true;
	       }
	        // Take appropriate action for each action item click
	        switch (item.getItemId()) {

	        case R.id.action_borra:
	        		this.borrar();
	        	return true;
	        case R.id.action_borraFicheros:
        		this.borrarFicheros();
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }
	   /* The click listener for ListView in the navigation drawer */
	   private class DrawerItemClickListener implements ListView.OnItemClickListener {
	       @Override
	       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	           selectItem(position);
	       }
	   }

	   private void selectItem(int position) {
	       // update the main content by replacing fragments
	       // update selected item and title, then close the drawer
	      
	       Intent intent;
	       switch (position) {
	        case 0:
	            // search action
	        	intent = new Intent(this, MainActivity.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	            finish();
	            MainActivity.main.finish();
	            startActivity(intent);
	        break;
	        case 1:
	        	intent = new Intent(this, Compras.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	            finish();
	            startActivity(intent);
	        break; 
	        case 2:
	        	intent = new Intent(this, Descargar.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	            finish();
	            startActivity(intent);
	        break; 
	        case 3:
	        	intent = new Intent(this, Proveedores.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	            finish();
	            startActivity(intent);
	        break; 
	        case 4:
	        	intent = new Intent(this, com.app.pescaderiaespe.Genero.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	            finish();
	            startActivity(intent);
	        break; 
	        case 5:
	        	intent = new Intent(this, Configuracion.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	            finish();
	            startActivity(intent);
	        break;

	       }
	       mDrawerLayout.closeDrawer(mDrawerList);
	   }

	   @Override
	   public void setTitle(CharSequence title) {
	       mTitle = title;
	       getActionBar().setTitle(mTitle);
	   }

	   /**
	    * When using the ActionBarDrawerToggle, you must call it during
	    * onPostCreate() and onConfigurationChanged()...
	    */

	   @Override
	   protected void onPostCreate(Bundle savedInstanceState) {
	       super.onPostCreate(savedInstanceState);
	       // Sync the toggle state after onRestoreInstanceState has occurred.
	       mDrawerToggle.syncState();
	   }

	   @Override
	   public void onConfigurationChanged(Configuration newConfig) {
	       super.onConfigurationChanged(newConfig);
	       // Pass any configuration change to the drawer toggls
	       mDrawerToggle.onConfigurationChanged(newConfig);
	   }

}
