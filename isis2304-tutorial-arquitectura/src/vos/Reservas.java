package vos;



import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Reservas 
{

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	
	/**
	 * Id de la reserva
	 */
	@JsonProperty(value="id")
	private int id;

	/**
	 * cantidad de personas de la reserva
	 */
	@JsonProperty(value="cantPersonas")
	private int cantPersonas;
	
	
	/**
	 * Fecha inicial de la reserva
	 */
	@JsonProperty(value="fechaInicio")
	private Date FechaInicio;
	
	/**
	 * Fecha de terminacion de la reserva 
	 */
	@JsonProperty(value="fechaFin")
	private Date FechaFin;
	
	/**
	 * Valor de la comodidad extra
	 */
	@JsonProperty(value="valor")
	private Double valor;
	
//	/**
//	 * Nombre del alojamiento de la reserva
//	 */
//	@JsonProperty(value="nomAlojamiento")
//	private String nomAlojamiento;

	/**
	 * Alojado de la reserva
	 */
	@JsonProperty(value="alojado")
	private Alojado alojado;
	
	/**
	 * habitacion emp de la reserva
	 */
	@JsonProperty(value="habitacionReservada")
	private HabitacionEmp habitacionReservada;
	
	/**
	 * Estado de la reserva
	 */
	@JsonProperty(value="estado")
	private boolean estado;
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
		
	public Reservas(@JsonProperty(value="id")int pId, @JsonProperty(value="cantPersonas") int pCantPersonas, 
			@JsonProperty(value="fechaInicio") Date pFechaInicio, @JsonProperty(value="fechaFin") Date pFechaFin, 
			@JsonProperty(value="valor") Double pValor, @JsonProperty(value="estado") boolean pEstado)
	{
		
		this.id = pId;
		this.cantPersonas = pCantPersonas;
		this.FechaInicio = pFechaInicio;
		this.FechaFin = pFechaFin;
		this.valor = pValor;
		this.estado = pEstado;
		this.alojado = null;
		this.habitacionReservada = null;
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

	public int getCantPersonas() {
		return cantPersonas;
	}

	public void setCantPersonas(int cantPersonas) {
		this.cantPersonas = cantPersonas;
	}

	public Date getFechaInicio() {
		return FechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		FechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return FechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		FechaFin = fechaFin;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}



	public Alojado getAlojado() {
		return alojado;
	}

	public void setAlojado(Alojado alojado) {
		this.alojado = alojado;
	}

	public HabitacionEmp getHabitacionReservada() {
		return habitacionReservada;
	}

	public void setHabitacionReservada(HabitacionEmp habitacionReservada) {
		this.habitacionReservada = habitacionReservada;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
	


}
