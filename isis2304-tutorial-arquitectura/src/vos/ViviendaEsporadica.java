package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class ViviendaEsporadica 
{
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Direccion de la vivienda Esporadica 
	 */
	@JsonProperty(value="direccion")
	private String direccion;
	
	/**
	 * Numero de habitaciones de la vivienda esporadica
	 */
	@JsonProperty(value="numHabitaciones")
	private int numHabitaciones;
	
	/**
	 * Menaje de la vivienda Esporadica 
	 */
	@JsonProperty(value="menaje")
	private String menaje;
	
	/**
	 * Costo de la vivienda Esporadica 
	 */
	@JsonProperty(value="costo")
	private double costo;
	
	/**
	 * Dias usados de la vivienda esporadica
	 */
	@JsonProperty(value="diasUsados")
	private int diasUsados;
	
	/**
	 * Descripcion del seguro de la vivienda Esporadica 
	 */
	@JsonProperty(value="descripcionSeguro")
	private String descripcionSeguro;
	
	/**
	 * valor del seguro de la vivienda Esporadica 
	 */
	@JsonProperty(value="valorSeguro")
	private double valorSeguro;
	
	
	
	/**
	 * contratos   de la vivienda esporadica 
	 */
	@JsonProperty(value="contratosViviendaEspo")
	private ArrayList<Contrato> contratosViviendaEspo;
	
	/**
	 * operador del VivEspo
	 */
	@JsonProperty(value="operadorVivEspo")
	private OperadorParticular operadorVivEspo;
	
	
	
	
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	
	public ViviendaEsporadica(@JsonProperty(value="direccion") String pDireccion, @JsonProperty(value="numHabitaciones") int pNumHabitaciones, 
			@JsonProperty(value="menaje") String pMenaje, @JsonProperty(value="costo") double pCosto, 
			@JsonProperty(value="diasUsados") int pDiasUsados, @JsonProperty(value="descripcionSeguro") String pDescripcionSeguro, 
			@JsonProperty(value="valorSeguro") double pValorSeguro) 
	{
		
		this.direccion = pDireccion;
		this.numHabitaciones = pNumHabitaciones;
		this.menaje = pMenaje;
		this.costo = pCosto;
		this.diasUsados = pDiasUsados;
		this.descripcionSeguro = pDescripcionSeguro;
		this.valorSeguro = pValorSeguro;
		this.contratosViviendaEspo = new ArrayList<Contrato>();
		this.operadorVivEspo = null;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------


	public String getDireccion() {
		return direccion;
	}

	public OperadorParticular getOperadorVivEspo() {
		return operadorVivEspo;
	}

	public void setOperadorVivEspo(OperadorParticular operadorVivEspo) {
		this.operadorVivEspo = operadorVivEspo;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getNumHabitaciones() {
		return numHabitaciones;
	}

	public void setNumHabitaciones(int numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}

	public String getMenaje() {
		return menaje;
	}

	public void setMenaje(String menaje) {
		this.menaje = menaje;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public int getDiasUsados() {
		return diasUsados;
	}

	public void setDiasUsados(int diasUsados) {
		this.diasUsados = diasUsados;
	}

	public String getDescripcionSeguro() {
		return descripcionSeguro;
	}

	public void setDescripcionSeguro(String descripcionSeguro) {
		this.descripcionSeguro = descripcionSeguro;
	}

	public double getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(double valorSeguro) 
	{
		this.valorSeguro = valorSeguro;
	}
	

	public ArrayList<Contrato> getContratosViviendaEspo() {
		return contratosViviendaEspo;
	}

	public void setContratosViviendaEspo(ArrayList<Contrato> contratosViviendaEspo) 
	{
		this.contratosViviendaEspo = contratosViviendaEspo;
	}


	
	
	
	

}
