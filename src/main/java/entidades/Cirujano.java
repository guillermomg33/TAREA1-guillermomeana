/**
* Clase Cirujano.java
*
* @author GUILLERMO MEANA GONZÁLEZ
* @version 1.0
*/

package entidades;

import enums.Especialidad;

import java.util.Set;

public class Cirujano extends Empleado {
	private Set<Especialidad> especialidad;

	public Cirujano(Long id, String nombre, String apellidos, String telefono, String email, String nifNie, String direccion, Set<Especialidad> especialidad) {
		super(id, nombre, apellidos, telefono, email, nifNie, direccion);
		this.especialidad = especialidad;
	}

	public Set<Especialidad> getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Set<Especialidad> especialidad) {
		this.especialidad = especialidad;
	}
}
