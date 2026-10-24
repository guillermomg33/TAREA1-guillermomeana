/**
* Clase Cita.java
*
* @author GUILLERMO MEANA GONZÁLEZ
* @version 1.0
*/

package entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Cita {
	// Es abstracta ya que hay 2 tipos de citas
	// Revisiones e intervenciones
	private Long id;
	private LocalDateTime fechaHora;
	
	// Formato para LocalDateTime
	public static final DateTimeFormatter FORMATO_FECHA_HORA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	public Cita(long id, LocalDateTime fechaHora) {
		super();
		this.id = id;
		this.fechaHora = fechaHora;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public static DateTimeFormatter getFormatoFechaHora() {
		return FORMATO_FECHA_HORA;
	}
}
