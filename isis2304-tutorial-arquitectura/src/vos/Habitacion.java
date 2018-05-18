package vos;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Habitacion 
{

	
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Id de la habitacion de emp
	 */
	@JsonProperty(value="id")
	private long id;

	/**
	 * tipo de la habitacion
	 */
	@JsonProperty(value="tipo")
	private String tipo;
	
	/**
	 * Precio base dia de la habitacion
	 */
	@JsonProperty(value="precioBaseDia")
	private Double precioBaseDia;
	
	/**
	 * Capacidad de la habitacion de emp
	 */
	@JsonProperty(value="capacidad")
	private int capacidad;
	
	/**
	 * Numero de la habitacion de emp
	 */
	@JsonProperty(value="numero")
	private int numero;
	
	/**
	 * Estado de la habitacion
	 */
	@JsonProperty(value="estado")
	private String estado;
	
	/**
	 * ID del alojamiento donde se encuentra la habitacion
	 */
	@JsonProperty(value="idHabitacion")
	private long idHabitacion;
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
		


	public Habitacion(@JsonProperty(value="id")long pId,@JsonProperty(value="tipo")String pTipo,
			@JsonProperty(value="precioBaseDia")Double pPrecioBaseDia, @JsonProperty(value="capacidad")int pCapacidad,
			@JsonProperty(value="numero")int pNumero, @JsonProperty(value="estado")String pEstado) 
	{
		this.id = pId;
		this.tipo = pTipo;
		this.precioBaseDia = pPrecioBaseDia;
		this.capacidad = pCapacidad;
		this.numero = pNumero;
		this.estado = pEstado;
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


	public String getTipo() 
	{
		return tipo;
	}


	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}


	public Double getPrecioBaseDia() 
	{
		return precioBaseDia;
	}


	public void setPrecioBaseDia(Double precioBaseDia) 
	{
		this.precioBaseDia = precioBaseDia;
	}


	public int getCapacidad() 
	{
		return capacidad;
	}


	public void setCapacidad(int capacidad) 
	{
		this.capacidad = capacidad;
	}


	public int getNumero() 
	{
		return numero;
	}


	public void setNumero(int numero) 
	{
		this.numero = numero;
	}


	public String getEstado() 
	{
		return estado;
	}


	public void setEstado(String estado) 
	{
		this.estado = estado;
	}
	

}
