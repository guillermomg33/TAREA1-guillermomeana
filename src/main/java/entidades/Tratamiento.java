package entidades;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class Tratamiento {
	private Long id;
	private String nombre;
	private LocalDate fechaini = LocalDate.now();
	private boolean consentimiento = false;
	
	// Carga ligera
	// Lo 'mínimo', permite asociar de forma rápida Pa con Tr
	// ID foránea, ya que Tratamiento depende de Paciente
	private Long idpaciente;
	
	// Carga pesada
	// Nos permite acceder al Paciente entero desde Tr, e.g., acceder a su NIE
	// private Paciente paciente;
	
	// Con estos dos codificamos el '1' de multiplicidad
	
	public Tratamiento() {}
	
	// RELACIÓN Paciente - Tratamiento
	// Composición (1 - n)
	// Un Paciente puede tener muchos Tratamientos, pero éstos son individuales,
	// por lo que si se borra el Paciente se borran los Tratamientos

	// Relación Tratamientos - Cita
	// Composición (1 - n)
	// Un tratamiento puede tener muchas citas
	private Set<Cita> listaCitas;

	public Tratamiento(Long id, String nombre, LocalDate fechaini, boolean consentimiento, Long idpaciente) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaini = fechaini;
		this.consentimiento = consentimiento;
		this.idpaciente = idpaciente;
		this.listaCitas = new LinkedHashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaini() {
		return fechaini;
	}

	public void setFechaini(LocalDate fechaini) {
		this.fechaini = fechaini;
	}

	public boolean isConsentimiento() {
		return consentimiento;
	}

	public void setConsentimiento(boolean consentimiento) {
		this.consentimiento = consentimiento;
	}

	public Long getIdpaciente() {
		return idpaciente;
	}

	public void setIdpaciente(Long idpaciente) {
		this.idpaciente = idpaciente;
	}

	public Set<Cita> getListaCitas() {
		return listaCitas;
	}

	public void setListaCitas(Set<Cita> listaCitas) {
		this.listaCitas = listaCitas;
	}
	
	
}
