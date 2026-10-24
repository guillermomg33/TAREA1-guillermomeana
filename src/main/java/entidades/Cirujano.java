/**
* Clase Cirujano.java
*
* @author GUILLERMO MEANA GONZÁLEZ
* @version 1.0
*/

package entidades;

import enums.Especialidad;

public class Cirujano extends Empleado {
	private Especialidad especialidad;

	public Cirujano(Especialidad especialidad) {
		super();
		this.especialidad = especialidad;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
}
