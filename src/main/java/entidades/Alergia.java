/**
* Clase Alergia.java
*
* @author GUILLERMO MEANA GONZÁLEZ
* @version 1.0
*/

package entidades;

public class Alergia {
	private Long id;
	private String nombre;
	
	// Relación Alergia - Medicamento
	// Dependencia (1 - 1)
	// Una alergia tiene que tener un medicamento
	// Y un medicamento solo puede tratar una sóla alergia
	// Codificamos el '1' con una variable
	private Medicamento medicamento;

	public Alergia(long id, String nombre, Medicamento medicamento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.medicamento = medicamento;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}
}
