package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Operador 
{
	
		//----------------------------------------------------------------------------------------------------------------------------------
		// ATRIBUTOS
		//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Id del operador
		 */
		@JsonProperty(value="id")
		private long id;

		/**
		 * Nombre del operador
		 */
		@JsonProperty(value="nombre")
		private String nombre;
		
		/**
		 * Usuario del operador
		 */
		@JsonProperty(value="usuario")
		private String usuario;
		
		/**
		 * contrasena del operador
		 */
		@JsonProperty(value="contrasena")
		private String contrasena;
		
		/**
		 * Correo electronico del operador
		 */
		@JsonProperty(value="correoElectronico")
		private String correoElectronico;
		
		/**
		 * Numero de contacto del operador
		 */
		@JsonProperty(value="numeroContacto")
		private long numeroContacto;
		
		/**
		 * Dinero adquirido en el año actual por el operador
		 */
		@JsonProperty(value="dineroAnoActual")
		private double dineroAnoActual;
		
		/**
		 * Dinero adquirido en el año corrido por el operador
		 */
		@JsonProperty(value="dineroAnoCorrido")
		private double dineroAnoCorrido;
		
		/**
		 * Relacion con la universidad  del operador
		 */
		@JsonProperty(value="relacionUniversidad")
		private String relacionUniversidad;
		
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODO CONSTRUCTOR
		//----------------------------------------------------------------------------------------------------------------------------------
		
		public Operador (@JsonProperty(value="id")long Id,@JsonProperty(value="nombre")String Nombre, 
				@JsonProperty(value="usuario")String Usuario, @JsonProperty(value="contrasena")String Contrasena, 
				@JsonProperty(value="correoElectronico")String CorreoElectronico, 
				@JsonProperty(value="numeroContacto")long NumeroContacto, @JsonProperty(value="dineroAnoActual")double DineroAnoActual, 
				@JsonProperty(value="dineroAnoCorrido")double DineroAnoCorrido, @JsonProperty(value="relacionUniversidad") String RelacionUniversidad)
		{
			this.id = Id;
			this.nombre = Nombre;
			this.usuario = Usuario;
			this.contrasena = Contrasena;
			this.correoElectronico = CorreoElectronico;
			this.numeroContacto = NumeroContacto;
			this.dineroAnoActual =DineroAnoActual;
			this.dineroAnoCorrido = DineroAnoCorrido;
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


		public double getDineroAnoActual() 
		{
			return dineroAnoActual;
		}


		public void setDineroAnoActual(double dineroAnoActual) 
		{
			this.dineroAnoActual = dineroAnoActual;
		}


		public double getDineroAnoCorrido() 
		{
			return dineroAnoCorrido;
		}


		public void setDineroAnoCorrido(double dineroAnoCorrido) 
		{
			this.dineroAnoCorrido = dineroAnoCorrido;
		}


		public String getRelacionUniversidad() 
		{
			return relacionUniversidad;
		}


		public void setRelacionUniversidad(String relacionUniversidad) 
		{
			this.relacionUniversidad = relacionUniversidad;
		}
	

}
