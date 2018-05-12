package vos;

import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Hotel 
{
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * NIT del Hotel
	 */
	@JsonProperty(value="NIT")
	private int NIT;

	/**
	 * Nombre del Hotel
	 */
	@JsonProperty(value="nombre")
	private String nombre;
	
	/**
	 * Direccion del Hotel 
	 */
	@JsonProperty(value="direccion")
	private String direccion;
	
	/**
	 * Calificacion del Hotel
	 */
	@JsonProperty(value="calificacion")
	private int calificacion;
	
	/**
	 * Hora de apertura del Hotel 
	 */
	@JsonProperty(value="horaApertura")
	private Date horaApertura;
	
	/**
	 * Hora de cirre del Hotel 
	 */
	@JsonProperty(value="horaCierre")
	private Date horaCierre;
	
	/**
	 * Booleano para saber si el hotel tiene restaurante 
	 */
	@JsonProperty(value="restaurante")
	private boolean restaurante;
	
	
	/**
	 * Booleano para saber si el hotel tiene piscina 
	 */
	@JsonProperty(value="piscina")
	private boolean piscina;
	
	/**
	 * Booleano para saber si el hotel tiene parqueadero 
	 */
	@JsonProperty(value="parqueadero")
	private boolean parqueadero;
	
	
	/**
	 * Booleano para saber si el hotel tiene Wifi 
	 */
	@JsonProperty(value="Wifi")
	private boolean Wifi;
	
	
	/**
	 * Habitaciones del hotel
	 */
	@JsonProperty(value="habitacionesHotel")
	private ArrayList<HabitacionEmp> habitacionesHotel;
	
	
	/**
	 * operador del Hotel
	 */
	@JsonProperty(value="operadorHotel")
	private OperadorEmpresa operadorHotel;
	
	
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
			
	
	public Hotel (@JsonProperty(value="NIT")int pNIT, @JsonProperty(value="nombre")String pNombre, 
			@JsonProperty(value="direccion") String pDireccion, @JsonProperty(value="calificacion")int pCalificacion, 
			@JsonProperty(value="horaApertura") Date pHoraApertura, @JsonProperty(value="horaCierre") Date pHoraCierre,
			@JsonProperty(value="restaurante")boolean pRestaurante, @JsonProperty(value="piscina")boolean pPiscina,
			@JsonProperty(value="parqueadero")boolean pParqueadero, @JsonProperty(value="Wifi")boolean pWifi)
	{
		this.NIT = pNIT;
		this.nombre = pNombre;
		this.direccion = pDireccion;
		this.calificacion = pCalificacion;
		this.horaApertura = pHoraApertura;
		this.horaCierre = pHoraCierre;	
		this.restaurante = pRestaurante;
		this.piscina = pPiscina;
		this.parqueadero = pParqueadero;
		this.Wifi = pWifi;
		this.habitacionesHotel = new ArrayList<HabitacionEmp>();	
		this.operadorHotel = null;
		
	}





	public int getNIT() {
		return NIT;
	}





	public void setNIT(int nIT) {
		NIT = nIT;
	}





	public String getNombre() {
		return nombre;
	}





	public void setNombre(String nombre) {
		this.nombre = nombre;
	}





	public String getDireccion() {
		return direccion;
	}





	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}





	public int getCalificacion() {
		return calificacion;
	}





	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}





	public Date getHoraApertura() {
		return horaApertura;
	}





	public void setHoraApertura(Date horaApertura) {
		this.horaApertura = horaApertura;
	}





	public Date getHoraCierre() {
		return horaCierre;
	}





	public void setHoraCierre(Date horaCierre) {
		this.horaCierre = horaCierre;
	}





	public boolean isRestaurante() {
		return restaurante;
	}





	public void setRestaurante(boolean restaurante) {
		this.restaurante = restaurante;
	}





	public boolean isPiscina() {
		return piscina;
	}





	public void setPiscina(boolean piscina) {
		this.piscina = piscina;
	}





	public boolean isParqueadero() {
		return parqueadero;
	}





	public void setParqueadero(boolean parqueadero) {
		this.parqueadero = parqueadero;
	}





	public boolean isWifi() {
		return Wifi;
	}





	public void setWifi(boolean wifi) {
		Wifi = wifi;
	}





	public ArrayList<HabitacionEmp> getHabitacionesHotel() {
		return habitacionesHotel;
	}





	public void setHabitacionesHotel(ArrayList<HabitacionEmp> habitacionesHotel) {
		this.habitacionesHotel = habitacionesHotel;
	}





	public OperadorEmpresa getOperadorHotel() {
		return operadorHotel;
	}





	public void setOperadorHotel(OperadorEmpresa operadorHotel) {
		this.operadorHotel = operadorHotel;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------

	
	
	
	
}
