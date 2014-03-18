package com.app.pescaderiaespe;


import java.util.List;

import bd.MySQLiteHelper;

import clases.proveedor;
import android.os.Bundle;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class Proveedores extends Activity {

	
	private EditText inputSearch;
	private ArrayAdapter<proveedor> dataAdapter1;
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
    
	//Objetos en pantalla
	private ListView lstProv;
	
	public void loadListView()
    {
        // database handler
    	 MySQLiteHelper db = new MySQLiteHelper(this);
    	 
        // Spinner Drop down elements
        List<proveedor> lables1 = db.getAllProvs();
 
        // Creating adapter for spinner
        dataAdapter1 = new ArrayAdapter<proveedor>(this, android.R.layout.simple_list_item_1, lables1);
 
        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_list_item_1);
 
        // attaching data adapter to spinner
        lstProv.setAdapter(dataAdapter1);
    }
	
	public void itemListClick(proveedor prov)
	{
		
        Intent intent = new Intent(this, MuestraProveedor.class);
        intent.putExtra("mProv", prov);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_proveedores);

		inputSearch = (EditText) findViewById(R.id.inputSearch);		
		
        inputSearch.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
				// When user changed the Text
				Proveedores.this.dataAdapter1.getFilter().filter(cs);	
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub							
			}
		});
		mTitle = mDrawerTitle = getTitle();
        mOptions = getResources().getStringArray(R.array.options_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layoutP);
        mDrawerList = (ListView) findViewById(R.id.left_drawerP);
        
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        CustomList adapter = new CustomList(Proveedores.this, mOptions, imageId);
            
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

        
        
        lstProv=(ListView)findViewById(R.id.listView1);
        loadListView();
        
        lstProv.setClickable(true);
        lstProv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

          @Override
          public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

            proveedor o = (proveedor) lstProv.getItemAtPosition(position);
            itemListClick(o);
            
          }

        });
        lstProv.setTextFilterEnabled(true);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.proveedores, menu);
			
		
		return super.onCreateOptionsMenu(menu);
	}

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	   
       if (mDrawerToggle.onOptionsItemSelected(item)) {
           return true;
       }
        // Take appropriate action for each action item click
        switch (item.getItemId()) {

        case R.id.action_add:
        	Intent intent1 = new Intent(this, AddProveedor.class);
        	intent1.putExtra("modoEditar", false);
            startActivity(intent1);
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
