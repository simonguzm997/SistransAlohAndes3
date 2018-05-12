package vos;

import java.util.Date;
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
		
//		/**
//		 * Nombre del alojamiento de la reserva
//		 */
//		@JsonProperty(value="nomAlojamiento")
//		private String nomAlojamiento;

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
		
		/**
		 * habitacion emp de la reserva
		 */
		@JsonProperty(value="reservas")
		private List <Reservas> reservas;
		
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODO CONSTRUCTOR
		//----------------------------------------------------------------------------------------------------------------------------------
			
		public ReservaGrupal(@JsonProperty(value="id")int pId)
		{
			
			this.id = pId;
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

		public List<Reservas> getReservas() {
			return reservas;
		}

		public void setReservas(List<Reservas> reservas) {
			this.reservas = reservas;
		}
		
		
		
		
	
	

}
