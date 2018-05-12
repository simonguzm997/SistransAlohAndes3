package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class Apartamento
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Id del apartamento
	 */
	@JsonProperty(value="id")
	private int id;

	/**
	 * Nombre del edifico
	 */
	@JsonProperty(value="nombreEdificio")
	private String nombreEdificio;
	
	/**
	 * Direccion del edifico 
	 */
	@JsonProperty(value="direccion")
	private String direccion;
	
	/**
	 * precio mensual del apartamento
	 */
	@JsonProperty(value="precioMensual")
	private double precioMensual;
	
	/**
	 * Menaje del apartamento 
	 */
	@JsonProperty(value="menaje")
	private String menaje;
	
	/**
	 * contratos   del apartamento
	 */
	@JsonProperty(value="contratosApartamento")
	private ArrayList<Contrato> contratosApartamento;
	
	/**
	 * operador del apt
	 */
	@JsonProperty(value="operadorApartamento")
	private OperadorParticular operadorApartamento;
	
	
	

	

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	
	public Apartamento(@JsonProperty(value="id") int pId, @JsonProperty(value="nombreEdificio") String pNombreEdificio, 
			@JsonProperty(value="direccion") String pDireccion, @JsonProperty(value="precioMensual") double pPrecioMensual, 
			@JsonProperty(value="menaje") String pMenaje) 
	{
		this.id = pId;
		this.nombreEdificio = pNombreEdificio;
		this.direccion = pDireccion;
		this.precioMensual = pPrecioMensual;
		this.menaje = pMenaje;
		this.contratosApartamento = new ArrayList<Contrato>();
		this.operadorApartamento = null;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------

	public int getId() {
		return id;
	}

	public OperadorParticular getOperadorApartamento() {
		return operadorApartamento;
	}

	public void setOperadorApartamento(OperadorParticular operadorApartamento) {
		this.operadorApartamento = operadorApartamento;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreEdificio() {
		return nombreEdificio;
	}

	public void setNombreEdificio(String nombreEdificio) {
		this.nombreEdificio = nombreEdificio;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getPrecioMensual() {
		return precioMensual;
	}

	public void setPrecioMensual(double precioMensual) {
		this.precioMensual = precioMensual;
	}

	public String getMenaje() {
		return menaje;
	}

	public void setMenaje(String menaje) {
		this.menaje = menaje;
	}

	public ArrayList<Contrato> getContratosApartamento() {
		return contratosApartamento;
	}

	public void setContratosApartamento(ArrayList<Contrato> contratosApartamento) {
		this.contratosApartamento = contratosApartamento;
	}
	
	
	

	
	
	
}
