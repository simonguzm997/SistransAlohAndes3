package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReservaGrupal
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
	 * Habitaciones del alojamiento
	 */
	@JsonProperty(value="reservas")
	private List<Reserva> reservas;
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	
	public ReservaGrupal (@JsonProperty(value="id") long Id)
	{
		this.id = Id;
					
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------


	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public List<Reserva> getReservas() {
		return reservas;
	}



	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}



	

	
	
	
}
