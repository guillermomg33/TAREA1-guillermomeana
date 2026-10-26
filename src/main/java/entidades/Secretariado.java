/**
* Clase Secretario.java
*
* @author GUILLERMO MEANA GONZÁLEZ
* @version 1.0
*/

package entidades;

public class Secretariado extends Empleado {
	private int aniosExperiencia;

	public Secretariado(Long id, String nombre, String apellidos, String telefono, String email, String nifNie, String direccion, int aniosExperiencia) {
		super(id, nombre, apellidos, telefono, email, nifNie, direccion);
		this.aniosExperiencia = aniosExperiencia;
	}

	public int getAniosExperiencia() {
		return aniosExperiencia;
	}

	public void setAniosExperiencia(int aniosExperiencia) {
		this.aniosExperiencia = aniosExperiencia;
	}
}
