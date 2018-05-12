package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ComodidadExtra 
{
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Nombre de la comodidad extra
	 */
	@JsonProperty(value="nombre")
	private String nombre;
		
	/**
	 * Valor de la comodidad extra
	 */
	@JsonProperty(value="valor")
	private Double valor;
	
	/**
	 * Hab de la comodidad
	 */
	@JsonProperty(value="habComextra")
	private HabitacionEmp habEmp;
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	
	public ComodidadExtra (@JsonProperty(value="nombre")String pNombre, @JsonProperty(value="valor")Double pValor)
	{
		this.nombre = pNombre;
		this.valor = pValor;
		this.habEmp = null;
	}
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}


	public HabitacionEmp getHabEmp() {
		return habEmp;
	}


	public void setHabEmp(HabitacionEmp habEmp) {
		this.habEmp = habEmp;
	}
	
	
	
	

}
