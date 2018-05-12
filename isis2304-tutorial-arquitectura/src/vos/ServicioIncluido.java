package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ServicioIncluido 
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Nombre del servicio incluido
	 */
	@JsonProperty(value="nombre")
	private String nombre;
	
	/**
	 * Nombre del servicio incluido
	 */
	@JsonProperty(value="contrato")
	private Contrato contrato;
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
			
	
	public ServicioIncluido (@JsonProperty(value="nombre")String pNombre)
	{
		this.nombre =pNombre;
		this.contrato = null;
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


	public Contrato getContrato() {
		return contrato;
	}


	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
	
	
	
	
	

}
