/**
* Clase Revision.java
*
* @author GUILLERMO MEANA GONZÁLEZ
* @version 1.0
*/

package entidades;

import java.time.LocalDateTime;

public class Revision extends Cita {
	// Extiende de Cita por lo que implementa id y fechaHora
	private String anotaciones;

	public Revision(long id, LocalDateTime fechaHora, String anotaciones) {
		super(id, fechaHora);
		this.anotaciones = anotaciones;
	}

	public String getAnotaciones() {
		return anotaciones;
	}

	public void setAnotaciones(String anotaciones) {
		this.anotaciones = anotaciones;
	}
	
	
}
