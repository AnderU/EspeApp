package clases;

import java.io.Serializable;

import com.app.pescaderiaespe.MainActivity;

import bd.MySQLiteHelper;



@SuppressWarnings("serial")
public class Compra  implements Serializable{
	
	private Integer id, idProveedor;
	
	public Integer getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	private String cif, fecha;
	
	private double iva, impuestos;
	
	@Override
	public String toString() {
		
		MySQLiteHelper db = new MySQLiteHelper(MainActivity.main);
		return fecha+" - "+db.getProv(idProveedor).getNombre();
	}

	public Compra()
	{
		iva=10.0;
		impuestos=4.0;
	}

	public double getIva() {
		return iva;
	}



	public void setIva(double iva) {
		this.iva = iva;
	}



	public double getImpuestos() {
		return impuestos;
	}



	public void setImpuestos(double impuestos) {
		this.impuestos = impuestos;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	

}
