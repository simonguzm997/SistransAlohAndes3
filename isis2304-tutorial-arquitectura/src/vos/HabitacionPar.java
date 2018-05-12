package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class HabitacionPar 
{
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Id de la habitacion particular
	 */
	@JsonProperty(value="id")
	private int id;

	
	/**
	 * Menaje de la habitacion particular
	 */
	@JsonProperty(value="menaje")
	private String menaje;

	/**
	 * precio mensual de la habitacion particular
	 */
	@JsonProperty(value="precioMensual")
	private double precioMensual;
	
	/**
	 * tipo de la habitacion particular
	 */
	@JsonProperty(value="tipo")
	private String tipo;
	
	
	/**
	 * operador del apt
	 */
	@JsonProperty(value="operadorHabPar")
	private OperadorParticular operadorHabPar;
	


	/**
	 * contratos   de la habitacion particular
	 */
	@JsonProperty(value="contratosHabitacionPar")
	private ArrayList<Contrato> contratosHabitacionPar;
	
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	public HabitacionPar(@JsonProperty(value="id")int pId, @JsonProperty(value="menaje") String pMenaje,
			@JsonProperty(value="precioMensual") double pPrecioMensual , @JsonProperty(value="tipo")String pTipo) 
	{
		this.id = pId;
		this.menaje = pMenaje;
		this.precioMensual = pPrecioMensual;
		this.tipo = pTipo;
		this.contratosHabitacionPar = new ArrayList<Contrato>();
		this.operadorHabPar= null;
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

	public String getMenaje() {
		return menaje;
	}



	public void setMenaje(String menaje) {
		this.menaje = menaje;
	}

	public double getPrecioMensual() {
		return precioMensual;
	}

	public void setPrecioMensual(double precioMensual) {
		this.precioMensual = precioMensual;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public ArrayList<Contrato> getContratosHabitacionPar() {
		return contratosHabitacionPar;
	}


	public void setContratosHabitacionPar(ArrayList<Contrato> contratosHabitacionPar) {
		this.contratosHabitacionPar = contratosHabitacionPar;
	}
	
	public OperadorParticular getOperadorHabPar() {
		return operadorHabPar;
	}


	public void setOperadorHabPar(OperadorParticular operadorHabPar) {
		this.operadorHabPar = operadorHabPar;
	}
	
	
	
	
	

}
