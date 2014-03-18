package bd;

import java.util.LinkedList;
import java.util.List;

import clases.Compra;
import clases.Configuracion;
import clases.DetalleCompras;
import clases.genero;
import clases.proveedor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	// Database Version
    private static final int DATABASE_VERSION = 12;
    // Database Name
    private static final String DATABASE_NAME = "ProvDB";
   
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);	
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create book table
		String CREATE_PROV_TABLE = "CREATE TABLE proveedores ( " +
                "id INTEGER PRIMARY KEY , " + 
				"cif TEXT, "+
				"nombre TEXT )";
		
		String CREATE_GEN_TABLE = "CREATE TABLE generos ( " +
                "id INTEGER PRIMARY KEY , " + 
				"nombre TEXT )";
		
		String CREATE_COMPRA_TABLE = "CREATE TABLE compras ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                "idProveedor INTEGER , " + 
				"cif TEXT, "+
				"fecha TEXT, " +
				"impuestos REAL, "+
				"iva REAL )";
		
		String CREATE_DCOMPRA_TABLE = "CREATE TABLE detallecompras ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				"idGenero INTEGER, "+
				"idCompra INTEGER, " +
				"cantidad REAL, "+
				"precio REAL )";
		
		String CREATE_SECUENCIA_ARCHIVOS_TABLE = "CREATE TABLE secuenciaarchivos ( " +
                "id INTEGER PRIMARY KEY , " + 
				"valor INTEGER )";
		
		String CREATE_UD_TABLE = "CREATE TABLE ultimadescarga ( " +
                "id INTEGER PRIMARY KEY , " + 
				"fecha TEXT)";
		
		String CREATE_CONFIGURACION_TABLE = "CREATE TABLE configuracion ( " +
                "id INTEGER PRIMARY KEY , " + 
				"email TEXT, "+
                "iva REAL, "+
				"impuestos REAL )";
		
		// create books table
		db.execSQL(CREATE_PROV_TABLE);
		db.execSQL(CREATE_GEN_TABLE);
		db.execSQL(CREATE_COMPRA_TABLE);
		db.execSQL(CREATE_DCOMPRA_TABLE);
		db.execSQL(CREATE_SECUENCIA_ARCHIVOS_TABLE);
		db.execSQL(CREATE_UD_TABLE);
		db.execSQL(CREATE_CONFIGURACION_TABLE);
		
		db.execSQL("INSERT INTO secuenciaarchivos (id,valor) VALUES (1,1)");
		db.execSQL("INSERT INTO ultimadescarga (id,fecha) VALUES (1,' NUNCA')");
		db.execSQL("INSERT INTO configuracion (id,email,iva,impuestos) VALUES (1,'no-mail',10.0,4.0)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS proveedores");
        db.execSQL("DROP TABLE IF EXISTS generos");
        db.execSQL("DROP TABLE IF EXISTS compras");
        db.execSQL("DROP TABLE IF EXISTS detallecompras");
        db.execSQL("DROP TABLE IF EXISTS secuenciaarchivos");
        db.execSQL("DROP TABLE IF EXISTS ultimadescarga");
        db.execSQL("DROP TABLE IF EXISTS configuracion");
        // create fresh books table
        this.onCreate(db);
	}
	
	
	//---------------------------------------------------------------------
   
	/**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */
	
	// Books table name
    private static final String TABLE_PROVEEDORES = "proveedores";
    
    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_CIF = "cif";
    private static final String KEY_NOMBRE = "nombre";
    
    private static final String[] COLUMNS = {KEY_ID,KEY_CIF,KEY_NOMBRE};
    
	public void addProveedor(proveedor prov){
		Log.d("addProv", prov.toString());
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		 
		// 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_ID, prov.getId());
        values.put(KEY_CIF, prov.getCif()); // get title 
        values.put(KEY_NOMBRE, prov.getNombre()); // get author
 
        // 3. insert
        db.insert(TABLE_PROVEEDORES, // table
        		null, //nullColumnHack
        		values); // key/value -> keys = column names/ values = column values
        
        // 4. close
        db.close(); 
	}
	
	public proveedor getProv(int id){

		// 1. get reference to readable DB
		SQLiteDatabase db = this.getReadableDatabase();
		 
		// 2. build query
        Cursor cursor = 
        		db.query(TABLE_PROVEEDORES, // a. table
        		COLUMNS, // b. column names
        		" id = ?", // c. selections 
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
 
        // 4. build book object
        proveedor prov = new proveedor();
        prov.setId(Integer.parseInt(cursor.getString(0)));
        prov.setCif(cursor.getString(1));
        prov.setNombre(cursor.getString(2));

		Log.d("getProv("+id+")", prov.toString());

        // 5. return book
        return prov;
	}
	//--------------------------------------------------
	public proveedor getProvPorCif(String cif){

		// 1. get reference to readable DB
		SQLiteDatabase db = this.getReadableDatabase();
		 
		// 2. build query
        Cursor cursor = 
        		db.query(TABLE_PROVEEDORES, // a. table
        		COLUMNS, // b. column names
        		" cif = ?", // c. selections 
                new String[] { cif }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
 
        // 4. build book object
        proveedor prov = new proveedor();
        prov.setId(Integer.parseInt(cursor.getString(0)));
        prov.setCif(cursor.getString(1));
        prov.setNombre(cursor.getString(2));

		

        // 5. return book
        return prov;
	}
	// Get All Books
    public List<proveedor> getAllProvs() {
        List<proveedor> proveedores = new LinkedList<proveedor>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_PROVEEDORES+ " ORDER BY nombre";
 
    	// 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
 
        // 3. go over each row, build book and add it to list
        proveedor prov = null;
        if (cursor.moveToFirst()) {
            do {
            	prov = new proveedor();
            	prov.setId(Integer.parseInt(cursor.getString(0)));
            	prov.setCif(cursor.getString(1));
            	prov.setNombre(cursor.getString(2));

                // Add book to books
            	proveedores.add(prov);
            } while (cursor.moveToNext());
        }
        
		Log.d("getAllBooks()", proveedores.toString());

        // return books
        return proveedores;
    }
	
	 // Updating single book
    public int updateProv(proveedor prov) {

    	// 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
		// 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("cif", prov.getCif()); // get title 
        values.put("nombre", prov.getNombre()); // get author
 
        // 3. updating row
        int i = db.update(TABLE_PROVEEDORES, //table
        		values, // column/value
        		KEY_ID+" = ?", // selections
                new String[] { String.valueOf(prov.getId()) }); //selection args
        
        // 4. close
        db.close();
        
        return i;
        
    }

    // Deleting single book
    public void deleteProv(proveedor prov) {

    	// 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        
        // 2. delete
        db.delete(TABLE_PROVEEDORES,
        		KEY_ID+" = ?",
                new String[] { String.valueOf(prov.getId()) });
        
        // 3. close
        db.close();
        
		Log.d("deleteBook", prov.toString());

    }
    //------------------------------------------------------------------------------------------------------------------------
    
  //---------------------------------------------------------------------
    
 	/**
      * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
      */
 	
 	// Books table name
     private static final String TABLE_GENEROS = "generos";
     
     // Books Table Columns names
     private static final String KEY_NOMBREG = "nombre";
     
     private static final String[] COLUMNSG = {KEY_ID,KEY_NOMBRE};
     
 	public void addGenero(genero gen){
 		Log.d("addProv", gen.toString());
 		// 1. get reference to writable DB
 		SQLiteDatabase db = this.getWritableDatabase();
 		 
 		// 2. create ContentValues to add key "column"/value
         ContentValues values = new ContentValues();
         values.put(KEY_ID, gen.getId());
         values.put(KEY_NOMBREG, gen.getNombre()); // get title 
  
         // 3. insert
         db.insert(TABLE_GENEROS, // table
         		null, //nullColumnHack
         		values); // key/value -> keys = column names/ values = column values
         
         // 4. close
         db.close(); 
 	}
 	
 	public genero getGenero(int id){

 		// 1. get reference to readable DB
 		SQLiteDatabase db = this.getReadableDatabase();
 		 
 		// 2. build query
         Cursor cursor = 
         		db.query(TABLE_GENEROS, // a. table
         				COLUMNSG, // b. column names
         		" id = ?", // c. selections 
                 new String[] { String.valueOf(id) }, // d. selections args
                 null, // e. group by
                 null, // f. having
                 null, // g. order by
                 null); // h. limit
         
         // 3. if we got results get the first one
         if (cursor != null)
             cursor.moveToFirst();
  
         // 4. build book object
         genero gen = new genero();
         gen.setId(Integer.parseInt(cursor.getString(0)));
         gen.setNombre(cursor.getString(1));

 		Log.d("getProv("+id+")", gen.toString());

         // 5. return book
         return gen;
 	}
 	
 	// Get All Books
     public List<genero> getAllGens() {
         List<genero> generos = new LinkedList<genero>();

         // 1. build the query
         String query = "SELECT  * FROM " + TABLE_GENEROS+ " ORDER BY nombre";
  
     	// 2. get reference to writable DB
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(query, null);
  
         // 3. go over each row, build book and add it to list
         genero gen = null;
         if (cursor.moveToFirst()) {
             do {
             	gen = new genero();
             	gen.setId(Integer.parseInt(cursor.getString(0)));
             	gen.setNombre(cursor.getString(1));

                 // Add book to books
             	generos.add(gen);
             } while (cursor.moveToNext());
         }
         
 		Log.d("getAllBooks()", generos.toString());

         // return books
         return generos;
     }
 	
 	 // Updating single book
     public int updateGen(genero gen) {

     	// 1. get reference to writable DB
         SQLiteDatabase db = this.getWritableDatabase();
  
 		// 2. create ContentValues to add key "column"/value
         ContentValues values = new ContentValues();
         
         values.put("nombre", gen.getNombre()); // get author
  
         // 3. updating row
         int i = db.update(TABLE_GENEROS, //table
         		values, // column/value
         		KEY_ID+" = ?", // selections
                 new String[] { String.valueOf(gen.getId()) }); //selection args
         
         // 4. close
         db.close();
         
         return i;
         
     }

     // Deleting single book
     public void deleteGen(genero gen) {

     	// 1. get reference to writable DB
         SQLiteDatabase db = this.getWritableDatabase();
         
         // 2. delete
         db.delete(TABLE_GENEROS,
         		KEY_ID+" = ?",
                 new String[] { String.valueOf(gen.getId()) });
         
         // 3. close
         db.close();
         
 		Log.d("deleteBook", gen.toString());

     }
     
   //---------------------------------------------------------------------
     
 	/**
      * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
      */
 	
 	// Books table name
     private static final String TABLE_COMPRAS = "compras";
     
     // Books Table Columns names
     private static final String KEY_IDPROVEEDOR = "idProveedor";
     private static final String KEY_FECHA = "fecha";
     private static final String KEY_IMPUESTOS = "impuestos";
     private static final String KEY_IVA = "iva";
     
     private static final String[] COLUMNSC = {KEY_ID,KEY_IDPROVEEDOR,KEY_CIF,KEY_FECHA,KEY_IMPUESTOS,KEY_IVA};
     
 	public long addCompra(Compra comp){
 		Log.d("addProv", comp.toString());
 		// 1. get reference to writable DB
 		SQLiteDatabase db = this.getWritableDatabase();
 		 
 		// 2. create ContentValues to add key "column"/value
         ContentValues values = new ContentValues();
         values.put(KEY_IDPROVEEDOR, comp.getIdProveedor()); // get title 
         values.put(KEY_CIF, comp.getCif()); // get title 
         values.put(KEY_FECHA, comp.getFecha()); // get author
         values.put(KEY_IMPUESTOS, comp.getImpuestos()); // get author
         values.put(KEY_IVA, comp.getIva()); // get author
  
         // 3. insert
         long i=db.insert(TABLE_COMPRAS, // table
        		 null, //nullColumnHack
         		values); // key/value -> keys = column names/ values = column values
         
         
         // 4. close
         db.close(); 
         
         return i;
 	}
 	
 	public Compra getComp(int id){

 		// 1. get reference to readable DB
 		SQLiteDatabase db = this.getReadableDatabase();
 		 
 		// 2. build query
         Cursor cursor = 
         		db.query(TABLE_COMPRAS, // a. table
         		COLUMNSC, // b. column names
         		" id = ?", // c. selections 
                 new String[] { String.valueOf(id) }, // d. selections args
                 null, // e. group by
                 null, // f. having
                 null, // g. order by
                 null); // h. limit
         
         // 3. if we got results get the first one
         if (cursor != null)
             cursor.moveToFirst();
  
         // 4. build book object
         Compra comp = new Compra();
         comp.setId(Integer.parseInt(cursor.getString(0)));
         comp.setIdProveedor(Integer.parseInt(cursor.getString(1)));
         comp.setCif(cursor.getString(2));
         comp.setFecha(cursor.getString(3));
         comp.setImpuestos(Double.parseDouble(cursor.getString(4)));
         comp.setIva(Double.parseDouble(cursor.getString(5)));

 		Log.d("getProv("+id+")", comp.toString());

         // 5. return book
         return comp;
 	}
 	
 	// Get All Books
     public List<Compra> getAllComp() {
         List<Compra> compras = new LinkedList<Compra>();

         // 1. build the query
         String query = "SELECT  * FROM " + TABLE_COMPRAS;
  
     	// 2. get reference to writable DB
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(query, null);
  
         // 3. go over each row, build book and add it to list
         Compra comp = null;
         if (cursor.moveToFirst()) {
             do {
             	comp = new Compra();
                comp.setId(Integer.parseInt(cursor.getString(0)));
                comp.setIdProveedor(Integer.parseInt(cursor.getString(1)));
                comp.setCif(cursor.getString(2));
                comp.setFecha(cursor.getString(3));
                comp.setImpuestos(Double.parseDouble(cursor.getString(4)));
                comp.setIva(Double.parseDouble(cursor.getString(5)));

                 // Add book to books
             	compras.add(comp);
             } while (cursor.moveToNext());
         }
         
 		Log.d("getAllBooks()", compras.toString());

         // return books
         return compras;
     }
 	
 	 // Updating single book
     public int updateComp(Compra comp) {

     	// 1. get reference to writable DB
         SQLiteDatabase db = this.getWritableDatabase();
  
 		// 2. create ContentValues to add key "column"/value
         ContentValues values = new ContentValues();
         values.put(KEY_IDPROVEEDOR, comp.getIdProveedor()); // get title 
         values.put(KEY_CIF, comp.getCif()); // get title 
         values.put(KEY_FECHA, comp.getFecha()); // get author
         values.put(KEY_IMPUESTOS, comp.getImpuestos()); // get author
         values.put(KEY_IVA, comp.getIva()); 
  
         // 3. updating row
         int i = db.update(TABLE_COMPRAS, //table
         		values, // column/value
         		KEY_ID+" = ?", // selections
                 new String[] { String.valueOf(comp.getId()) }); //selection args
         
         // 4. close
         db.close();
         
         return i;
         
     }

     // Deleting single book
     public void deleteComp(Compra comp) {

     	// 1. get reference to writable DB
         SQLiteDatabase db = this.getWritableDatabase();
         
         // 2. delete
         db.delete(TABLE_COMPRAS,
         		KEY_ID+" = ?",
                 new String[] { String.valueOf(comp.getId()) });
         
         // 3. close
         db.close();
         
 		Log.d("deleteBook", comp.toString());

     }
     //------------------------------------------------------------------------------------------------------------------------
     //---------------------------------------------------------------------
     
  	/**
       * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
       */
  	
  	// Books table name
      private static final String TABLE_DCOMPRAS = "detallecompras";
      
      // Books Table Columns names
      private static final String KEY_IDGENERO = "idGenero";
      private static final String KEY_IDCOMPRA = "idCompra";
      private static final String KEY_CANTIDAD = "cantidad";
      private static final String KEY_PRECIO = "precio";
      
      private static final String[] COLUMNSDC = {KEY_ID,KEY_IDGENERO,KEY_IDCOMPRA,KEY_CANTIDAD,KEY_PRECIO};
      
  	public void addDetCompra(DetalleCompras detComp){
  		Log.d("addProv", detComp.toString());
  		// 1. get reference to writable DB
  		SQLiteDatabase db = this.getWritableDatabase();
  		 
  		// 2. create ContentValues to add key "column"/value
          ContentValues values = new ContentValues();
          values.put(KEY_IDGENERO, detComp.getIdGenero()); // get title 
          values.put(KEY_IDCOMPRA, detComp.getIdCompra()); // get author
          values.put(KEY_CANTIDAD, detComp.getCantidad()); // get author
          values.put(KEY_PRECIO, detComp.getPrecio()); // get author
   
          // 3. insert
          db.insert(TABLE_DCOMPRAS, // table
          		null, //nullColumnHack
          		values); // key/value -> keys = column names/ values = column values
          
          // 4. close
          db.close(); 
  	}
  	
  	public DetalleCompras getDetComp(int id){

  		// 1. get reference to readable DB
  		SQLiteDatabase db = this.getReadableDatabase();
  		 
  		// 2. build query
          Cursor cursor = 
          		db.query(TABLE_DCOMPRAS, // a. table
          		COLUMNSDC, // b. column names
          		" id = ?", // c. selections 
                  new String[] { String.valueOf(id) }, // d. selections args
                  null, // e. group by
                  null, // f. having
                  null, // g. order by
                  null); // h. limit
          
          // 3. if we got results get the first one
          if (cursor != null)
              cursor.moveToFirst();
   
          // 4. build book object
          DetalleCompras detComp = new DetalleCompras();
          detComp.setId(Integer.parseInt(cursor.getString(0)));
          detComp.setIdGenero(Integer.parseInt(cursor.getString(1)));
          detComp.setIdCompra(Integer.parseInt(cursor.getString(2)));
          detComp.setCantidad(Double.parseDouble(cursor.getString(3)));
          detComp.setPrecio(Double.parseDouble(cursor.getString(4)));

  		Log.d("getProv("+id+")", detComp.toString());

          // 5. return book
          return detComp;
  	}
  	
  	// Get All Books
    public List<DetalleCompras> getAllDetCompPorCompra(int idCompra) {
        List<DetalleCompras> detalleCompras = new LinkedList<DetalleCompras>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_DCOMPRAS+" WHERE "+KEY_IDCOMPRA+" ="+idCompra;
 
    	// 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
 
        // 3. go over each row, build book and add it to list
        DetalleCompras detComp = null;
        if (cursor.moveToFirst()) {
            do {
          	detComp = new DetalleCompras();
              detComp.setId(Integer.parseInt(cursor.getString(0)));
              detComp.setIdGenero(Integer.parseInt(cursor.getString(1)));
              detComp.setIdCompra(Integer.parseInt(cursor.getString(2)));
              detComp.setCantidad(Double.parseDouble(cursor.getString(3)));
              detComp.setPrecio(Double.parseDouble(cursor.getString(4)));

                // Add book to books
              detalleCompras.add(detComp);
            } while (cursor.moveToNext());
        }
        
		

        // return books
        return detalleCompras;
    }
  	
  	
  	// Get All Books
      public List<DetalleCompras> getAllDetComp() {
          List<DetalleCompras> detalleCompras = new LinkedList<DetalleCompras>();

          // 1. build the query
          String query = "SELECT  * FROM " + TABLE_DCOMPRAS;
   
      	// 2. get reference to writable DB
          SQLiteDatabase db = this.getWritableDatabase();
          Cursor cursor = db.rawQuery(query, null);
   
          // 3. go over each row, build book and add it to list
          DetalleCompras detComp = null;
          if (cursor.moveToFirst()) {
              do {
            	detComp = new DetalleCompras();
                detComp.setId(Integer.parseInt(cursor.getString(0)));
                detComp.setIdGenero(Integer.parseInt(cursor.getString(1)));
                detComp.setIdCompra(Integer.parseInt(cursor.getString(2)));
                detComp.setCantidad(Double.parseDouble(cursor.getString(3)));
                detComp.setPrecio(Double.parseDouble(cursor.getString(4)));

                  // Add book to books
                detalleCompras.add(detComp);
              } while (cursor.moveToNext());
          }
          
  		Log.d("getAllBooks()", detalleCompras.toString());

          // return books
          return detalleCompras;
      }
  	
  	 // Updating single book
      public int updatedetComp(DetalleCompras detComp) {

      	// 1. get reference to writable DB
          SQLiteDatabase db = this.getWritableDatabase();
   
  		// 2. create ContentValues to add key "column"/value
          ContentValues values = new ContentValues();
          values.put(KEY_IDGENERO, detComp.getIdGenero()); // get title 
          values.put(KEY_IDCOMPRA, detComp.getIdCompra()); // get author
          values.put(KEY_CANTIDAD, detComp.getCantidad()); // get author
          values.put(KEY_PRECIO, detComp.getPrecio()); // get author
   
          // 3. updating row
          int i = db.update(TABLE_DCOMPRAS, //table
          		values, // column/value
          		KEY_ID+" = ?", // selections
                  new String[] { String.valueOf(detComp.getId()) }); //selection args
          
          // 4. close
          db.close();
          
          return i;
          
      }

      // Deleting single book
      public void deleteComp(DetalleCompras detComp) {

      	// 1. get reference to writable DB
          SQLiteDatabase db = this.getWritableDatabase();
          
          // 2. delete
          db.delete(TABLE_DCOMPRAS,
          		KEY_ID+" = ?",
                  new String[] { String.valueOf(detComp.getId()) });
          
          // 3. close
          db.close();
          
  		Log.d("deleteBook", detComp.toString());

      }
      
      // Deleting single book
      public void deleteCompPorIdCompra(int idCompra) {

      	// 1. get reference to writable DB
          SQLiteDatabase db = this.getWritableDatabase();
          
          // 2. delete
          db.delete(TABLE_DCOMPRAS,
          		KEY_IDCOMPRA+" = ?",
                  new String[] { String.valueOf(idCompra) });
          
          // 3. close
          db.close();
          

      }
      //------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------
	   
    		/**
    	     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
    	     */
    		
    		// Books table name
    	    private static final String TABLE_SECUENCIA_ARCHIVOS = "secuenciaarchivos";
    	    
    	    // Books Table Columns names
    	    private static final String KEY_VALOR = "valor";
    	    
    	    private static final String[] COLUMNSSA = {KEY_ID,KEY_VALOR};
    	    

    		
    		public int getSecuenciaArchivos(int id){

    			// 1. get reference to readable DB
    			SQLiteDatabase db = this.getReadableDatabase();
    			 
    			// 2. build query
    	        Cursor cursor = 
    	        		db.query(TABLE_SECUENCIA_ARCHIVOS, // a. table
    	        		COLUMNSSA, // b. column names
    	        		" id = ?", // c. selections 
    	                new String[] { String.valueOf(id) }, // d. selections args
    	                null, // e. group by
    	                null, // f. having
    	                null, // g. order by
    	                null); // h. limit
    	        
    	        // 3. if we got results get the first one
    	        if (cursor != null)
    	            cursor.moveToFirst();
    	 
    	        // 4. build book object
    	      

    	        // 5. return book
    	        return cursor.getInt(1);
    		}
    		
    		
    		
    		 // Updating single book
    	    public int updateSecuenciaArchivos(int id, int valor) {

    	    	// 1. get reference to writable DB
    	        SQLiteDatabase db = this.getWritableDatabase();
    	 
    			// 2. create ContentValues to add key "column"/value
    	        ContentValues values = new ContentValues();
    	      
    	        values.put("valor",valor); // get author
    	 
    	        // 3. updating row
    	        int i = db.update(TABLE_SECUENCIA_ARCHIVOS, //table
    	        		values, // column/value
    	        		KEY_ID+" = ?", // selections
    	                new String[] { String.valueOf(id) }); //selection args
    	        
    	        // 4. close
    	        db.close();
    	        
    	        return i;
    	        
    	    }

    	    //------------------------------------------------------------------------------------------------------------------------
    	    
    	  //---------------------------------------------------------------------
    	    
    		/**
    	     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
    	     */
    		
    		// Books table name
    	    private static final String TABLE_UD = "ultimadescarga";
    	    
    	    // Books Table Columns names
    	    private static final String KEY_FECHAD = "fecha";
    	    
    	    private static final String[] COLUMNSUD = {KEY_ID,KEY_FECHAD};
    	    
    		public String getUltimaDescarga(int id){

    			// 1. get reference to readable DB
    			SQLiteDatabase db = this.getReadableDatabase();
    			 
    			// 2. build query
    	        Cursor cursor = 
    	        		db.query(TABLE_UD, // a. table
    	        		COLUMNSUD, // b. column names
    	        		" id = ?", // c. selections 
    	                new String[] { String.valueOf(id) }, // d. selections args
    	                null, // e. group by
    	                null, // f. having
    	                null, // g. order by
    	                null); // h. limit
    	        
    	        // 3. if we got results get the first one
    	        if (cursor != null)
    	            cursor.moveToFirst();
    	 
    	        // 4. build book object
    	       

    	        // 5. return book
    	        return cursor.getString(1);
    		}
    		 // Updating single book
    	    public int updateUltimaDescarga(int id,String fecha) {

    	    	// 1. get reference to writable DB
    	        SQLiteDatabase db = this.getWritableDatabase();
    	 
    			// 2. create ContentValues to add key "column"/value
    	        ContentValues values = new ContentValues();

    	        values.put("fecha", fecha); // get author
    	 
    	        // 3. updating row
    	        int i = db.update(TABLE_UD, //table
    	        		values, // column/value
    	        		KEY_ID+" = ?", // selections
    	                new String[] { String.valueOf(id) }); //selection args
    	        
    	        // 4. close
    	        db.close();
    	        
    	        return i;
    	        
    	    }


    	    //------------------------------------------------------------------------------------------------------------------------
    	  
 //---------------------------------------------------------------------
    	    
    		/**
    	     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
    	     */
    		
    		// Books table name
    	    private static final String TABLE_CONF = "configuracion";
    	    
    	    // Books Table Columns names
    	    private static final String KEY_EMAIL = "email";

    	    
    	    private static final String[] COLUMNSCONF = {KEY_ID,KEY_EMAIL,KEY_IVA,KEY_IMPUESTOS};
    	    
    		public Configuracion getConf(int id){

    			// 1. get reference to readable DB
    			SQLiteDatabase db = this.getReadableDatabase();
    			 
    			// 2. build query
    	        Cursor cursor = 
    	        		db.query(TABLE_CONF, // a. table
    	        				COLUMNSCONF, // b. column names
    	        		" id = ?", // c. selections 
    	                new String[] { String.valueOf(id) }, // d. selections args
    	                null, // e. group by
    	                null, // f. having
    	                null, // g. order by
    	                null); // h. limit
    	        
    	        // 3. if we got results get the first one
    	        if (cursor != null)
    	            cursor.moveToFirst();
    	 
    	        // 4. build book object
    	       Configuracion conf = new Configuracion();
    	       conf.setEmail(cursor.getString(1));
    	       conf.setIva(cursor.getDouble(2));
    	       conf.setImpuestos(cursor.getDouble(3));
    	       
    	        // 5. return book
    	        return conf;
    		}
    		 // Updating single book
    	    public int updateConf(int id,Configuracion conf) {

    	    	// 1. get reference to writable DB
    	        SQLiteDatabase db = this.getWritableDatabase();
    	 
    			// 2. create ContentValues to add key "column"/value
    	        ContentValues values = new ContentValues();

    	        values.put("email", conf.getEmail()); // get author
    	        values.put("iva", conf.getIva());
    	        values.put("impuestos", conf.getImpuestos());
    	        // 3. updating row
    	        int i = db.update(TABLE_CONF, //table
    	        		values, // column/value
    	        		KEY_ID+" = ?", // selections
    	                new String[] { String.valueOf(id) }); //selection args
    	        
    	        // 4. close
    	        db.close();
    	        
    	        return i;
    	        
    	    }


    	    //------------------------------------------------------------------------------------------------------------------------
    	     	
    	
}
