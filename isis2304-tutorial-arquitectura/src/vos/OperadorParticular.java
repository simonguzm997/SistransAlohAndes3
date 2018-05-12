package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class OperadorParticular extends Cliente
{
	

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
	
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
	 * Vinculo con la universidad  del operador
	 */
	@JsonProperty(value="vinculoUniversidad")
	private String vinculoUniversidad;
	
	
	/**
	 * vivienda esporadica del operador
	 */
	@JsonProperty(value="viviendaEsporadica")
	private ViviendaEsporadica viviendaEsporadica;
	
	/**
	 * viviendas  del operador donde ofrece habitaciones
	 */
	@JsonProperty(value="viviendas")
	private ArrayList<Vivienda> viviendas;
	
	/**
	 * apartamentos  del operador 
	 */
	@JsonProperty(value="apartamentos")
	private ArrayList<Apartamento> apartamentos;

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	
	
	public OperadorParticular(@JsonProperty(value="id")int pId,@JsonProperty(value="nombre")String pNombre, @JsonProperty(value="usuario")String pUsuario, 
			@JsonProperty(value="contrasena")String pContrasena, @JsonProperty(value="correoElectronico")String pCorreoElectronico, 
			@JsonProperty(value="numeroContacto")long pNumeroContacto, @JsonProperty(value="dineroAnoActual")double pDineroAnoActual, 
			@JsonProperty(value="dineroAnoCorrido")double pDineroAnoCorrido, @JsonProperty(value="vinculoUniversidad")String pVinculoUniversidad ) 
	{
		super(pId, pNombre, pUsuario, pContrasena, pCorreoElectronico, pNumeroContacto);
		
		this.dineroAnoActual =pDineroAnoActual;
		this.dineroAnoCorrido = pDineroAnoCorrido;
		this.vinculoUniversidad = pVinculoUniversidad;
		this.viviendaEsporadica = null;
		this.viviendas = new ArrayList<Vivienda>();
		this.apartamentos = new ArrayList<Apartamento>();
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------
		

	public double getDineroAnoActual() {
		return dineroAnoActual;
	}


	public void setDineroAnoActual(double dineroAnoActual) {
		this.dineroAnoActual = dineroAnoActual;
	}


	public double getDineroAnoCorrido() {
		return dineroAnoCorrido;
	}


	public void setDineroAnoCorrido(double dineroAnoCorrido) {
		this.dineroAnoCorrido = dineroAnoCorrido;
	}


	public String getVinculoUniversidad() {
		return vinculoUniversidad;
	}


	public void setVinculoUniversidad(String vinculoUniversidad) {
		this.vinculoUniversidad = vinculoUniversidad;
	}

	public ViviendaEsporadica getViviendaEsporadica() {
		return viviendaEsporadica;
	}

	public void setViviendaEsporadica(ViviendaEsporadica viviendaEsporadica) {
		this.viviendaEsporadica = viviendaEsporadica;
	}

	public ArrayList<Vivienda> getViviendas() {
		return viviendas;
	}

	public void setViviendas(ArrayList<Vivienda> viviendas) {
		this.viviendas = viviendas;
	}

	public ArrayList<Apartamento> getApartamentos() {
		return apartamentos;
	}

	public void setApartamentos(ArrayList<Apartamento> apartamentos) {
		this.apartamentos = apartamentos;
	}
	
	
	
	
	

}
