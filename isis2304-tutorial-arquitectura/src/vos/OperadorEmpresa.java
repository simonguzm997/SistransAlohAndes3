package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class OperadorEmpresa extends Cliente

{

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Dinero adquirido en el año actual por el operador
	 */
	@JsonProperty(value="dineroAnoActual")
	private double dineroAnoActual;
	
	/**
	 * Dinero adquirido en el año corrido por el operador
	 */
	@JsonProperty(value="dineroAnoCorrido")
	private double dineroAnoCorrido;
	
	/**
	 * Lista de hoteles del operador
	 */
	@JsonProperty(value="hoteles")
	private ArrayList<Hotel> hoteles;
	
	/**
	 * Lista de hoteles del operador
	 */
	@JsonProperty(value="hostales")
	private ArrayList<Hostal> hostales;
	
	/**
	 * Lista de Viviendas universitarias del operador
	 */
	@JsonProperty(value="viviendasUniversitarias")
	private ArrayList<ViviendaUniversitaria> viviendasUniversitarias;
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	
	
	public OperadorEmpresa(@JsonProperty(value="id")int pId,@JsonProperty(value="nombre")String pNombre, @JsonProperty(value="usuario")String pUsuario, 
			@JsonProperty(value="contrasena")String pContrasena, @JsonProperty(value="correoElectronico")String pCorreoElectronico, 
			@JsonProperty(value="numeroContacto")long pNumeroContacto, @JsonProperty(value="dineroAnoActual")double pDineroAnoActual,  @JsonProperty(value="dineroAnoCorrido")double pDineroAnoCorrido ) 
	{
		super(pId, pNombre, pUsuario, pContrasena, pCorreoElectronico, pNumeroContacto);
		
		this.dineroAnoActual =pDineroAnoActual;
		this.dineroAnoCorrido = pDineroAnoCorrido;
		this.hoteles = new ArrayList<Hotel>();
		this.hostales = new ArrayList<Hostal>();
		this.viviendasUniversitarias = new ArrayList<ViviendaUniversitaria>();
	}


	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------
	
	public double getDineroAnoActual() {
		return dineroAnoActual;
	}

	public void setDineroAnoActual(double dineroAnoActual) {
		this.dineroAnoActual = dineroAnoActual;
	}

	public double getDineroAnoCorrido() {
		return dineroAnoCorrido;
	}

	public void setDineroAnoCorrido(double dineroAnoCorrido) {
		this.dineroAnoCorrido = dineroAnoCorrido;
	}



	public ArrayList<Hotel> getHoteles() {
		return hoteles;
	}



	public void setHoteles(ArrayList<Hotel> hoteles) {
		this.hoteles = hoteles;
	}



	public ArrayList<Hostal> getHostales() {
		return hostales;
	}



	public void setHostales(ArrayList<Hostal> hostales) {
		this.hostales = hostales;
	}



	public ArrayList<ViviendaUniversitaria> getViviendasUniversitarias() {
		return viviendasUniversitarias;
	}



	public void setViviendasUniversitarias(ArrayList<ViviendaUniversitaria> viviendasUniversitarias) {
		this.viviendasUniversitarias = viviendasUniversitarias;
	}
	

}
