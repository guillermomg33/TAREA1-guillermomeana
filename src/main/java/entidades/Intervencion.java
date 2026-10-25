/**
* Clase Intervencion.java
*
* @author GUILLERMO MEANA GONZÁLEZ
* @version 1.0
*/

package entidades;

import java.time.LocalDateTime;

public class Intervencion extends Cita {
	// Lo mismo que Revision
	private int duracionMinutos;

	public Intervencion(long id, LocalDateTime fechaHora, int duracionMinutos, String anotacion) {
		super(id, fechaHora, anotacion);
		this.duracionMinutos = duracionMinutos;
	}

	public int getDuracionMinutos() {
		return duracionMinutos;
	}

	public void setDuracionMinutos(int duracionMinutos) {
		this.duracionMinutos = duracionMinutos;
	}
	
	
}
