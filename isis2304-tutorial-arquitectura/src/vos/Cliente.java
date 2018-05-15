package vos;

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
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	
	
	public Cliente(@JsonProperty(value="id")long pId,@JsonProperty(value="nombre")String pNombre, 
			@JsonProperty(value="usuario")String pUsuario, @JsonProperty(value="contrasena")String pContrasena, 
			@JsonProperty(value="correoElectronico")String pCorreoElectronico, 
			@JsonProperty(value="numeroContacto")long pNumeroContacto, @JsonProperty(value="relacionUniversidad") String pRelacionUniversidad) 
	{
		
		this.id = pId;
		this.nombre = pNombre;
		this.usuario = pUsuario;
		this.contrasena = pContrasena;
		this.correoElectronico = pCorreoElectronico;
		this.numeroContacto = pNumeroContacto;
		this.relacionUniversidad = pRelacionUniversidad;
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
	
	
	
	
	
	

	
	
	
	
	
	
	
}
