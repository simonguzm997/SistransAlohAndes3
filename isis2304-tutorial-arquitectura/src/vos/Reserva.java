package vos;



import org.codehaus.jackson.annotate.JsonProperty;

public class Reserva 
{

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	
	/**
	 * Id de la reserva
	 */
	@JsonProperty(value="id")
	private long id;

	/**
	 * cantidad de personas de la reserva
	 */
	@JsonProperty(value="cantPersonas")
	private int cantPersonas;
	
	
	/**
	 * Fecha inicial de la reserva
	 */
	@JsonProperty(value="fechaInicio")
	private String FechaInicio;
	
	/**
	 * Fecha de terminacion de la reserva 
	 */
	@JsonProperty(value="fechaFin")
	private String FechaFin;
	
	/**
	 * Valor de la reserva 
	 */
	@JsonProperty(value="valor")
	private Double valor;
	


	/**
	 * Estado de la reserva
	 */
	@JsonProperty(value="estado")
	private String estado;
	
	
	
//	/**
//	 * Alojado de la reserva
//	 */
//	@JsonProperty(value="alojado")
//	private Alojado alojado;
//	
//	/**
//	 * habitacion emp de la reserva
//	 */
//	@JsonProperty(value="habitacionReservada")
//	private HabitacionEmp habitacionReservada;
//	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
		
	public Reserva(@JsonProperty(value="id")long pId, @JsonProperty(value="cantPersonas") int pCantPersonas, 
			@JsonProperty(value="fechaInicio") String pFechaInicio, @JsonProperty(value="fechaFin") String pFechaFin, 
			@JsonProperty(value="valor") Double pValor, @JsonProperty(value="estado") String pEstado)
	{
		
		this.id = pId;
		this.cantPersonas = pCantPersonas;
		this.FechaInicio = pFechaInicio;
		this.FechaFin = pFechaFin;
		this.valor = pValor;
		this.estado = pEstado;
		//this.alojado = null;
		//this.habitacionReservada = null;
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



	public int getCantPersonas() 
	{
		return cantPersonas;
	}



	public void setCantPersonas(int cantPersonas)
	{
		this.cantPersonas = cantPersonas;
	}



	public String getFechaInicio()
	{
		return FechaInicio;
	}



	public void setFechaInicio(String fechaInicio) 
	{
		FechaInicio = fechaInicio;
	}



	public String getFechaFin() 
	{
		return FechaFin;
	}



	public void setFechaFin(String fechaFin) 
	{
		FechaFin = fechaFin;
	}



	public Double getValor() 
	{
		return valor;
	}



	public void setValor(Double valor) 
	{
		this.valor = valor;
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
