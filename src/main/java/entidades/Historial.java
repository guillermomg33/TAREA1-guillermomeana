/**
* Clase Historial.java
*
* @author GUILLERMO MEANA GONZÁLEZ
* @version 1.0
*/

package entidades;

import java.util.LinkedHashSet;
import java.util.Set;

public class Historial {
	private Long numHistoria;
	private String descripcion;
	
	// Relación Historial - Alergias
	// Asociación (1 - n)
	// Un Historial puede tener 0, 1 o muchas Alergias
	private Set<Alergia> listaAlergias;

	public Historial(long numHistoria, String descripcion) {
		super();
		this.numHistoria = numHistoria;
		this.descripcion = descripcion;
		this.listaAlergias = new LinkedHashSet<>();
	}

	public long getNumHistoria() {
		return numHistoria;
	}

	public void setNumHistoria(long numHistoria) {
		this.numHistoria = numHistoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Alergia> getListaAlergias() {
		return listaAlergias;
	}

	public void setListaAlergias(Set<Alergia> listaAlergias) {
		this.listaAlergias = listaAlergias;
	}
}
