package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class HabitacionEmp 
{
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Id de la habitacion de emp
	 */
	@JsonProperty(value="id")
	private int id;

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
	 * comodidades extra  de la habitacion 
	 */
	@JsonProperty(value="comodidadesExtra")
	private ArrayList<ComodidadExtra> comodidadesExtra;
	
	
	

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
		

	
	public HabitacionEmp (@JsonProperty(value="id")int pId,@JsonProperty(value="tipo")String pTipo,
			@JsonProperty(value="precioBaseDia")Double pPrecioBaseDia, @JsonProperty(value="capacidad")int pCapacidad,
			@JsonProperty(value="numero")int pNumero)
	{
		this.id = pId;
		this.tipo = pTipo;
		this.precioBaseDia = pPrecioBaseDia;
		this.capacidad = pCapacidad;
		this.numero = pNumero;
		this.comodidadesExtra = new ArrayList<ComodidadExtra>();
		
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------

	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public String getTipo() {
		return tipo;
	}





	public void setTipo(String tipo) {
		this.tipo = tipo;
	}





	public Double getPrecioBaseDia() {
		return precioBaseDia;
	}





	public void setPrecioBaseDia(Double precioBaseDia) {
		this.precioBaseDia = precioBaseDia;
	}





	public int getCapacidad() {
		return capacidad;
	}





	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}




	public int getNumero() {
		return numero;
	}





	public void setNumero(int numero) {
		this.numero = numero;
	}

	public ArrayList<ComodidadExtra> getComodidadesExtra() {
		return comodidadesExtra;
	}

	public void setComodidadesExtra(ArrayList<ComodidadExtra> comodidadesExtra) {
		this.comodidadesExtra = comodidadesExtra;
	}
	
	
	
	

}
