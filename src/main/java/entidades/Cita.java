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
	private String anotacion;
	
	// Formato para LocalDateTime
	public static final DateTimeFormatter FORMATO_FECHA_HORA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	public Cita(long id, LocalDateTime fechaHora, String anotacion) {
		this.id = id;
		this.fechaHora = fechaHora;
		this.anotacion = anotacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getAnotacion() {
		return anotacion;
	}

	public void setAnotacion(String anotacion) {
		this.anotacion = anotacion;
	}
}
