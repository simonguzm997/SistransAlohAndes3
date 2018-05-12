package vos;


import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Contrato 
{

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Id del contrato
	 */
	@JsonProperty(value="id")
	private int id;
	
	
	/**
	 * Valor del contrato
	 */
	@JsonProperty(value="valor")
	private Double valor;
	
	/**
	 * dia inicial del contrato
	 */
	@JsonProperty(value="diaInicio")
	private Date diaInicio;
	
	/**
	 * dia de terminacion del contrato
	 */
	@JsonProperty(value="diaFin")
	private Date diaFin;
	
	/**
	 * Alojado del contrato
	 */
	@JsonProperty(value="alojado")
	private Alojado alojado;
	
	/**
	 * servicios incluidos del contrato
	 */
	@JsonProperty(value="serviciosIncluidos")
	private ArrayList<ServicioIncluido> serviciosIncluidos;

	

	
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
		

	public Contrato(@JsonProperty(value="id")int pId, @JsonProperty(value="valor") double pValor,
			@JsonProperty(value="diaInicio") Date pDiaInicio, @JsonProperty(value="diaFin") Date pDiaFin) 
	{
		this.id = pId;
		this.valor = pValor;
		this.diaInicio = pDiaInicio;
		this.diaFin = pDiaFin;
		this.alojado = null;
		this.serviciosIncluidos = new ArrayList<ServicioIncluido>();
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getDiaInicio() {
		return diaInicio;
	}

	public void setDiaInicio(Date diaInicio) {
		this.diaInicio = diaInicio;
	}

	public Date getDiaFin() {
		return diaFin;
	}

	public void setDiaFin(Date diaFin) {
		this.diaFin = diaFin;
	}


	public Alojado getAlojado() {
		return alojado;
	}


	public void setAlojado(Alojado alojado) {
		this.alojado = alojado;
	}


	public ArrayList<ServicioIncluido> getServiciosIncluidos() {
		return serviciosIncluidos;
	}


	public void setServiciosIncluidos(ArrayList<ServicioIncluido> serviciosIncluidos) {
		this.serviciosIncluidos = serviciosIncluidos;
	}
	
	
	
	
	

}
