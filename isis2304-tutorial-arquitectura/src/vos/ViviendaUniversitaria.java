package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class ViviendaUniversitaria 
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * NIT de la vivienda universitaria
	 */
	@JsonProperty(value="NIT")
	private int NIT;

	/**
	 * Nombre de la vivienda universitaria 
	 */
	@JsonProperty(value="nombre")
	private String nombre;
	
	/**
	 * Direccion de la vivienda universitaria 
	 */
	@JsonProperty(value="direccion")
	private String direccion;
	
	/**
	 * Capacidad de la vivienda universitaria
	 */
	@JsonProperty(value="capacidad")
	private int capacidad;

	/**
	 * Habitaciones de la vivienda universitaria 
	 */
	@JsonProperty(value="habitacionesViviendaUniversitaria")
	private ArrayList<HabitacionEmp> habitacionesViviendaUniversitaria;
	
	
	/**
	 * operador del VivUni
	 */
	@JsonProperty(value="operadorVivUni")
	private OperadorEmpresa operadorVivUni;
	
	
	
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
		
	
	public ViviendaUniversitaria(@JsonProperty(value="NIT")int pNIT, @JsonProperty(value="nombre")String pNombre, 
			@JsonProperty(value="direccion") String pDireccion, @JsonProperty(value="capacidad") int pCapacidad) 
	{

		this.NIT = pNIT;
		this.nombre = pNombre;
		this.direccion = pDireccion;
		this.capacidad = pCapacidad;
		this.habitacionesViviendaUniversitaria = new ArrayList<HabitacionEmp>();
		this.operadorVivUni = null;
	}


	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------

	
	
	public int getNIT() {
		return NIT;
	}


	public OperadorEmpresa getOperadorVivUni() {
		return operadorVivUni;
	}


	public void setOperadorVivUni(OperadorEmpresa operadorVivUni) {
		this.operadorVivUni = operadorVivUni;
	}


	public void setNIT(int nIT) {
		NIT = nIT;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public int getCapacidad() {
		return capacidad;
	}


	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}


	public ArrayList<HabitacionEmp> getHabitacionesViviendaUniversitaria() {
		return habitacionesViviendaUniversitaria;
	}


	public void setHabitacionesViviendaUniversitaria(ArrayList<HabitacionEmp> habitacionesViviendaUniversitaria) {
		this.habitacionesViviendaUniversitaria = habitacionesViviendaUniversitaria;
	}
	
	
	
	
	
	
	
	



}
