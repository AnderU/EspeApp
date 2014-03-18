package clases;

import java.io.Serializable;

@SuppressWarnings("serial")
public class proveedor implements Serializable{

	@Override
	public String toString() {
		return this.Nombre;
	}
	private Integer id;
	private String Cif, Nombre;
	
	public proveedor()
	{

			id=0;
			Cif="";
			Nombre="Proveedor";
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCif() {
		return Cif;
	}
	public void setCif(String cif) {
		Cif = cif;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	
}
