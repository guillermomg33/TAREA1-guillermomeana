/**
* Clase Medicamento.java
*
* @author GUILLERMO MEANA GONZÁLEZ
* @version 1.0
*/

package entidades;

public class Medicamento {
	private Long id;
	private String nombre;
	private String principioActivo;
	private double dosisMax;
	
	// Relaciones con Alergia, pero a la hora de codificar
	// aquí no tenemos que poner nada
	
	public Medicamento(long id, String nombre, String principioActivo,
			double dosisMax) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.principioActivo = principioActivo;
		this.dosisMax = dosisMax;
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

	public String getPrincipioActivo() {
		return principioActivo;
	}

	public void setPrincipioActivo(String principioActivo) {
		this.principioActivo = principioActivo;
	}

	public double getDosisMax() {
		return dosisMax;
	}

	public void setDosisMax(double dosisMax) {
		this.dosisMax = dosisMax;
	}
}
