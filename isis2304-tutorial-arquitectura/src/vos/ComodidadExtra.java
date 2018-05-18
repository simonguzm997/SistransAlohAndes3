package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ComodidadExtra 
{
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Id de la comodidad extra
	 */
	@JsonProperty(value="id")
	private long id;

	
	/**
	 * Nombre de la comodidad extra
	 */
	@JsonProperty(value="nombre")
	private String nombre;
		
	/**
	 * Valor de la comodidad extra
	 */
	@JsonProperty(value="costo")
	private Double costo;
	
	/**
	 * Descripcion de la comodidad extra
	 */
	@JsonProperty(value="descripcion")
	private String descripcion;

	/**
	 * Id de la comodidad extra
	 */
	@JsonProperty(value="idHabitacion")
	private long idHabitacion;

	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	
	public ComodidadExtra (@JsonProperty(value="id")long pId, @JsonProperty(value="nombre")String pNombre,
			@JsonProperty(value="costo")Double pCosto, @JsonProperty(value="descripcion")String pDescripcion,
			@JsonProperty(value="idHabitacion")long pIdHabitacion)
	{
		this.id = pId;
		this.nombre = pNombre;
		this.costo = pCosto;
		this.descripcion = pDescripcion;
		this.idHabitacion = pIdHabitacion;
	}

	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------



	public long getId() 
	{
		return id;
	}



	public void setId(long id)
	{
		this.id = id;
	}



	public String getNombre() 
	{
		return nombre;
	}



	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}



	public Double getCosto() 
	{
		return costo;
	}



	public void setCosto(Double costo)
	{
		this.costo = costo;
	}



	public String getDescripcion() 
	{
		return descripcion;
	}



	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}


	public long getIdHabitacion() {
		return idHabitacion;
	}


	public void setIdHabitacion(long idHabitacion) 
	{
		this.idHabitacion = idHabitacion;
	}

	
	

}
