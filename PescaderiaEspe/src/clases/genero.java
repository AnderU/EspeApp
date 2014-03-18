package clases;

import java.io.Serializable;

@SuppressWarnings("serial")
public class genero implements Serializable{
	
	private Integer id;
	private String nombre;
	
	public genero()
	{
		id=0;
		nombre="Género";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return nombre;
	}
	

}
