package vos;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Alojado extends Cliente
{

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Vinculo con la universidad  del alojado
	 */
	@JsonProperty(value="vinculoUniversidad")
	private String vinculoUniversidad;
	
	/**
	 * Reservas del alojado
	 */
	@JsonProperty(value="reservas")
	private List<Reservas> reservas;
	
	/**
	 * Reservas del alojado
	 */
	@JsonProperty(value="contratos")
	private List<Contrato> contratos;
	
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	
	
	public Alojado(@JsonProperty(value="id")int pId,@JsonProperty(value="nombre")String pNombre, @JsonProperty(value="usuario")String pUsuario, 
			@JsonProperty(value="contrasena")String pContrasena, @JsonProperty(value="correoElectronico")String pCorreoElectronico,
			@JsonProperty(value="numeroContacto")int pNumeroContacto, @JsonProperty(value="vinculoUniversidad")String pVinculoUniversidad) 
	{
		super(pId, pNombre, pUsuario, pContrasena, pCorreoElectronico, pNumeroContacto);
		this.vinculoUniversidad = pVinculoUniversidad;
		this.reservas = new ArrayList<Reservas>();
		this.contratos = new ArrayList<Contrato>();
	}



	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------

	public String getVinculoUniversidad() {
		return vinculoUniversidad;
	}

	public void setVinculoUniversidad(String vinculoUniversidad) {
		this.vinculoUniversidad = vinculoUniversidad;
	}



	public List<Reservas> getReservas() {
		return reservas;
	}



	public void setReservas(List<Reservas> reservas) {
		this.reservas = reservas;
	}



	public List<Contrato> getContratos() {
		return contratos;
	}



	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}
	
	
	
	
}
