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
	private int id;

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
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	

	public Cliente (@JsonProperty(value="id")int pId,@JsonProperty(value="nombre")String pNombre, @JsonProperty(value="usuario")String pUsuario, 
			@JsonProperty(value="contrasena")String pContrasena, @JsonProperty(value="correoElectronico")String pCorreoElectronico, @JsonProperty(value="numeroContacto")long pNumeroContacto )
	{
		this.id = pId;
		this.nombre = pNombre;
		this.usuario = pUsuario;
		this.contrasena = pContrasena;
		this.correoElectronico = pCorreoElectronico;
		this.numeroContacto = pNumeroContacto;
	
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public long getNumeroContacto() {
		return numeroContacto;
	}

	public void setNumeroContacto(long numeroContacto) {
		this.numeroContacto = numeroContacto;
	}
		
		
	
	
	
}
