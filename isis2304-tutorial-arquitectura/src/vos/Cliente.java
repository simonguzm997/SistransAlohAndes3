package vos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Cliente 
{
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Id del cliente
	 */
	@JsonProperty(value="id")
	private long id;

	/**
	 * Nombre del cliente
	 */
	@JsonProperty(value="nombre")
	private String nombre;
	
	/**
	 * Usuario del cliente
	 */
	@JsonProperty(value="usuario")
	private String usuario;
	
	/**
	 * contrasena del cliente
	 */
	@JsonProperty(value="contrasena")
	private String contrasena;
	
	/**
	 * Correo electronico del cliente
	 */
	@JsonProperty(value="correoElectronico")
	private String correoElectronico;
	
	/**
	 * Numero de contacto del cliente
	 */
	@JsonProperty(value="numeroContacto")
	private long numeroContacto;
	
	
	/**
	 * Relacion con la universidad  del cliente
	 */
	@JsonProperty(value="relacionUniversidad")
	private String relacionUniversidad;
	
	/**
	 * Dias reservados por el cliente
	 */
	@JsonProperty(value="diasUsado")
	private int diasUsado;
	
	/**
	 * Dinero pagado por el cliente
	 */
	@JsonProperty(value="dineroPagado")
	private double dineroPagado;
	
	/**
	 *Reservas del cliente
	 */
	@JsonProperty(value="reservas")
	private List<Reserva> reservas;
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	
	
	public Cliente(@JsonProperty(value="id")long Id,@JsonProperty(value="nombre")String Nombre, 
			@JsonProperty(value="usuario")String Usuario, @JsonProperty(value="contrasena")String Contrasena, 
			@JsonProperty(value="correoElectronico")String CorreoElectronico, 
			@JsonProperty(value="numeroContacto")long NumeroContacto, @JsonProperty(value="relacionUniversidad") String RelacionUniversidad) 
	{
		
		this.id = Id;
		this.nombre = Nombre;
		this.usuario = Usuario;
		this.contrasena = Contrasena;
		this.correoElectronico = CorreoElectronico;
		this.numeroContacto = NumeroContacto;
		this.relacionUniversidad = RelacionUniversidad;
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


	public String getNombre()
	{
		return nombre;
	}


	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}


	public String getUsuario()
	{
		return usuario;
	}


	public void setUsuario(String usuario) 
	{
		this.usuario = usuario;
	}


	public String getContrasena()
	{
		return contrasena;
	}


	public void setContrasena(String contrasena)
	{
		this.contrasena = contrasena;
	}


	public String getCorreoElectronico() 
	{
		return correoElectronico;
	}


	public void setCorreoElectronico(String correoElectronico) 
	{
		this.correoElectronico = correoElectronico;
	}


	public long getNumeroContacto() 
	{
		return numeroContacto;
	}


	public void setNumeroContacto(long numeroContacto) 
	{
		this.numeroContacto = numeroContacto;
	}


	public String getRelacionUniversidad()
	{
		return relacionUniversidad;
	}


	public void setRelacionUniversidad(String relacionUniversidad) 
	{
		this.relacionUniversidad = relacionUniversidad;
	}


	public int getDiasUsado()
	{
		return diasUsado;
	}


	public void setDiasUsado(int diasUsado)
	{
		this.diasUsado = diasUsado;
	}


	public double getDineroPagado() 
	{
		return dineroPagado;
	}


	public void setDineroPagado(double dineroPagado) 
	{
		this.dineroPagado = dineroPagado;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) 
	{

		this.reservas = reservas;
		
	}
	
	
	

	
}
