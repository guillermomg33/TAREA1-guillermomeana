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
	
	// Cada enfermero tiene un informe propio de Tratamientos
	private Set listaTratamientos;

	public Enfermeria(Categoria categoria, Set listaTratamientos) {
		super();
		this.categoria = categoria;
		this.listaTratamientos = listaTratamientos;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Set getListaTratamientos() {
		return listaTratamientos;
	}

	public void setListaTratamientos(Set listaTratamientos) {
		this.listaTratamientos = listaTratamientos;
	}
}
