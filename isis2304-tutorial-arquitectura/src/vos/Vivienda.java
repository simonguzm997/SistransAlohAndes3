package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class Vivienda 
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Direccion de la vivienda  
	 */
	@JsonProperty(value="direccion")
	private String direccion;
	
	/**
	 * Menaje de la vivienda  
	 */
	@JsonProperty(value="menaje")
	private String menaje;
	
	
	/**
	 * habitaciones par de la vivienda 
	 */
	@JsonProperty(value="habitaciones")
	private ArrayList<HabitacionPar> habitaciones;
	
	/**
	 * operador del Viv
	 */
	@JsonProperty(value="operadorVivienda")
	private OperadorParticular operadorVivienda;
	
	
	
	
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	
	public Vivienda(@JsonProperty(value="direccion") String pDireccion, @JsonProperty(value="menaje") String pMenaje) 
	{
		this.direccion = pDireccion;
		this.menaje = pMenaje;
		this.habitaciones = new ArrayList<HabitacionPar>();
		this.operadorVivienda = null;
	}


	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------



	public String getDireccion() {
		return direccion;
	}

	public OperadorParticular getOperadorVivienda() {
		return operadorVivienda;
	}

	public void setOperadorVivienda(OperadorParticular operadorVivienda) {
		this.operadorVivienda = operadorVivienda;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getMenaje() {
		return menaje;
	}

	public void setMenaje(String menaje) {
		this.menaje = menaje;
	}

	public ArrayList<HabitacionPar> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(ArrayList<HabitacionPar> habitaciones) {
		this.habitaciones = habitaciones;
	}
	
	
	

}
