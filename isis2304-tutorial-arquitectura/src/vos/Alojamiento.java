package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Alojamiento 
{

	
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------


	/**
	 * Id del alojamiento
	 */
	@JsonProperty(value="id")
	private long id;

	
	/**
	 * NIT del alojamiento
	 */
	@JsonProperty(value="NIT")
	private long NIT;

	/**
	 * Nombre del alojamiento
	 */
	@JsonProperty(value="nombre")
	private String nombre;
	
	/**
	 * Direccion del alojamiento 
	 */
	@JsonProperty(value="direccion")
	private String direccion;
	
	/**
	 * Calificacion del alojamiento
	 */
	@JsonProperty(value="calificacion")
	private int calificacion;
	
	/**
	 * Hora de apertura del alojamiento 
	 */
	@JsonProperty(value="horaApertura")
	private String horaApertura;
	
	/**
	 * Hora de cirre del alojamiento 
	 */
	@JsonProperty(value="horaCierre")
	private String horaCierre;

	/**
	 * Capacidad del alojamiento
	 */
	@JsonProperty(value="capacidad")
	private int capacidad;
	
	
	/**
	 * tipo de alojamiento
	 */
	@JsonProperty(value="tipo")
	private String tipo;
	
	/**
	 * estado del alojamiento
	 */
	@JsonProperty(value="estado")
	private String estado;
	
	/**
	 * Dias usados del alojamiento
	 */
	@JsonProperty(value="diasUsados")
	private int diasUsados;
	
	/**
	 * Descripcion del seguro del alojamiento
	 */
	@JsonProperty(value="descripcionSeguro")
	private String descripcionSeguro;
	
	/**
	 * valor del seguro del alojamiento
	 */
	@JsonProperty(value="valorSeguro")
	private double valorSeguro;
	
	/**
	 * Numero de habitaciones del alojamiento
	 */
	@JsonProperty(value="numHabitaciones")
	private int numHabitaciones;
	
	/**
	 * Menaje del alojamiento
	 */
	@JsonProperty(value="menaje")
	private String menaje;
	
	
	
	public Alojamiento(@JsonProperty(value="id") long pId, @JsonProperty(value="NIT")long pNIT, 
			@JsonProperty(value="nombre")String pNombre, @JsonProperty(value="direccion") String pDireccion, 
			@JsonProperty(value="calificacion")int pCalificacion, @JsonProperty(value="horaApertura") String pHoraApertura,
			@JsonProperty(value="horaCierre") String pHoraCierre, @JsonProperty(value="capacidad")int pCapacidad,
			@JsonProperty(value="tipo")String pTipo, @JsonProperty(value="estado")String pEstado,
			@JsonProperty(value="diasUsados") int pDiasUsados, @JsonProperty(value="descripcionSeguro") String pDescripcionSeguro,
			@JsonProperty(value="valorSeguro") double pValorSeguro, @JsonProperty(value="numHabitaciones") int pNumHabitaciones, 
			@JsonProperty(value="menaje") String pMenaje) 
	{
	
		this.id = pId;
		this.NIT = pNIT;
		this.nombre = pNombre;
		this.direccion = pDireccion;
		this.calificacion = pCalificacion;
		this.horaApertura = pHoraApertura;
		this.horaCierre = pHoraCierre;
		this.capacidad = pCapacidad;
		this.tipo = pTipo;
		this.estado = pEstado;
		this.diasUsados = pDiasUsados;
		this.descripcionSeguro = pDescripcionSeguro;
		this.valorSeguro = pValorSeguro;
		this.numHabitaciones = pNumHabitaciones;
		this.menaje = pMenaje;
	}



	public long getId()
	{
		return id;
	}



	public void setId(long id) 
	{
		this.id = id;
	}



	public long getNIT() 
	{
		return NIT;
	}



	public void setNIT(long nIT) 
	{
		NIT = nIT;
	}



	public String getNombre() 
	{
		return nombre;
	}



	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}



	public String getDireccion() 
	{
		return direccion;
	}



	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}



	public int getCalificacion()
	{
		return calificacion;
	}



	public void setCalificacion(int calificacion) 
	{
		this.calificacion = calificacion;
	}



	public String getHoraApertura()
	{
		return horaApertura;
	}



	public void setHoraApertura(String horaApertura)
	{
		this.horaApertura = horaApertura;
	}



	public String getHoraCierre() 
	{
		return horaCierre;
	}



	public void setHoraCierre(String horaCierre)
	{
		this.horaCierre = horaCierre;
	}



	public int getCapacidad() 
	{
		return capacidad;
	}



	public void setCapacidad(int capacidad)
	{
		this.capacidad = capacidad;
	}



	public String getTipo() 
	{
		return tipo;
	}



	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}



	public String getEstado() 
	{
		return estado;
	}



	public void setEstado(String estado)
	{
		this.estado = estado;
	}



	public int getDiasUsados() {
		
		return diasUsados;
	}



	public void setDiasUsados(int diasUsados) 
	{
		this.diasUsados = diasUsados;
	}



	public String getDescripcionSeguro() 
	{
		return descripcionSeguro;
	}



	public void setDescripcionSeguro(String descripcionSeguro) 
	{
		this.descripcionSeguro = descripcionSeguro;
	}



	public double getValorSeguro()
	{
		return valorSeguro;
	}



	public void setValorSeguro(double valorSeguro) 
	{
		this.valorSeguro = valorSeguro;
	}



	public int getNumHabitaciones() 
	{
		return numHabitaciones;
	}



	public void setNumHabitaciones(int numHabitaciones) 
	{
		this.numHabitaciones = numHabitaciones;
	}



	public String getMenaje() 
	{
		return menaje;
	}



	public void setMenaje(String menaje)
	{
		this.menaje = menaje;
	}



	
	
}