/**
* Clase Secretario.java
*
* @author GUILLERMO MEANA GONZÁLEZ
* @version 1.0
*/

package entidades;

public class Secretariado extends Empleado {
	private String aniosExperiencia;

	public Secretariado(String experiencia) {
		super();
		this.aniosExperiencia = experiencia;
	}

	public String getAniosExperiencia() {
		return aniosExperiencia;
	}

	public void setAniosExperiencia(String aniosExperiencia) {
		this.aniosExperiencia = aniosExperiencia;
	}
}
