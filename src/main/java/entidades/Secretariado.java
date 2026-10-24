/**
* Clase Secretario.java
*
* @author GUILLERMO MEANA GONZÁLEZ
* @version 1.0
*/

package entidades;

public class Secretariado extends Empleado {
	private String experiencia;

	public Secretariado(String experiencia) {
		super();
		this.experiencia = experiencia;
	}

	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}
}
