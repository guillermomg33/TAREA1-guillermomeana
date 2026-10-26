/**
* Clase Enfermero.java
*
* @author GUILLERMO MEANA GONZÁLEZ
* @version 1.0
*/

package entidades;

import java.util.Set;

import enums.Categoria;

public class Enfermeria extends Empleado {
	private Categoria categoria;

	public Enfermeria(Long id, String nombre, String apellidos, String telefono, String email, String nifNie, String direccion, Categoria categoria) {
		super(id, nombre, apellidos, telefono, email, nifNie, direccion);
		this.categoria = categoria;
	}

	public Enfermeria(Categoria categoria) {
		super();
		this.categoria = categoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
