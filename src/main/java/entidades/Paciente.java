package entidades;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class Paciente {
	private Long id;
	private String nombre;
	private LocalDate fechaNac;
	private String telefono;
	private String email;
	private String direccion;
	private String nifNie;
	
	// Con esto modificamos el 'n' o 'muchos' de multiplicación
	// Un '1' se haría con una variable simple
	// Set es preferible para evitar duplicados
	private Set<Tratamiento> listaTratamientos;
	// En caso de carga ligera trabajaríamos con Long
	// private Set<Long> listaIdsTratamientos; o similar

	// Relación Paciente - Historial (1 - 1)
	// Un paciente tiene un único historial
	private Historial historial;
	
	public Paciente() {}

	public Paciente(Long id, String nombre, LocalDate fechaNac, String telefono, String email, String direccion, String nifNie, Historial historial) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaNac = fechaNac;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.nifNie = nifNie;
		this.listaTratamientos = new LinkedHashSet<>();
		this.historial = historial;
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

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNifNie() {
		return nifNie;
	}

	public void setNifNie(String nifNie) {
		this.nifNie = nifNie;
	}

	public Set<Tratamiento> getListaTratamientos() {
		return listaTratamientos;
	}

	public void setListaTratamientos(Set<Tratamiento> listaTratamientos) {
		this.listaTratamientos = listaTratamientos;
	}

	public Historial getHistorial() {
		return historial;
	}

	public void setHistorial(Historial historial) {
		this.historial = historial;
	}
	
	
}
