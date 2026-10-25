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
	// No tiene atributos únicos, solo está para diferenciarlo de Intervencion

	public Revision(long id, LocalDateTime fechaHora, String anotacion) {
		super(id, fechaHora, anotacion);
	}
}