package vos;

import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Hostal 
{
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * NIT del Hostal
	 */
	@JsonProperty(value="NIT")
	private int NIT;

	/**
	 * Nombre del Hostal
	 */
	@JsonProperty(value="nombre")
	private String nombre;
	
	/**
	 * Direccion del Hostal 
	 */
	@JsonProperty(value="direccion")
	private String direccion;
	
	/**
	 * Hora de apertura del Hostal 
	 */
	@JsonProperty(value="horaApertura")
	private Date horaApertura;
	
	/**
	 * Hora de cirre del Hostal 
	 */
	@JsonProperty(value="horaCierre")
	private Date horaCierre;
	
	/**
	 * Habitaciones del hostal
	 */
	@JsonProperty(value="habitacionesHostal")
	private ArrayList<HabitacionEmp> habitacionesHostal;
	
	/**
	 * operador del hostal
	 */
	@JsonProperty(value="operadorHostal")
	private OperadorEmpresa operadorHostal;
	
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
			
	
	public Hostal (@JsonProperty(value="NIT")int pNIT, @JsonProperty(value="nombre")String pNombre, 
			@JsonProperty(value="direccion") String pDireccion, @JsonProperty(value="horaApertura") Date pHoraApertura,
			@JsonProperty(value="horaCierre") Date pHoraCierre )
	{
		this.NIT = pNIT;
		this.nombre = pNombre;
		this.direccion = pDireccion;
		this.horaApertura = pHoraApertura;
		this.horaCierre = pHoraCierre;
		this.habitacionesHostal = new ArrayList<HabitacionEmp>();
		this.operadorHostal = null;
		
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------

	
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

	public ArrayList<HabitacionEmp> getHabitacionesHostal() {
		return habitacionesHostal;
	}

	public void setHabitacionesHostal(ArrayList<HabitacionEmp> habitacionesHostal) {
		this.habitacionesHostal = habitacionesHostal;
	}

	public OperadorEmpresa getOperadorHostal() {
		return operadorHostal;
	}

	public void setOperadorHostal(OperadorEmpresa operadorHostal) {
		this.operadorHostal = operadorHostal;
	}
	
	
	
	
	

}
