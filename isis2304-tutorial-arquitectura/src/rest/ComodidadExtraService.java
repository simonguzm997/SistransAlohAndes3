package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohAndesTransactionManager;
import vos.ComodidadExtra;

@Path("comodidades")
public class ComodidadExtraService {

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Atributo que usa la anotacion @Context para tener el ServletContext de la conexion actual.
	 */
	@Context
	private ServletContext context;

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE INICIALIZACION
	//----------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Metodo que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}


	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS REST
	//----------------------------------------------------------------------------------------------------------------------------------

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getComodidades() 
	{
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

			List<ComodidadExtra> comodidades;
			comodidades= tm.getAllComodidadExtras();
			return Response.status(200).entity(comodidades).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}


	@DELETE 
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteComodidad(ComodidadExtra comodidades)
	{
		try{
			AlohAndesTransactionManager dt = new AlohAndesTransactionManager(getPath());
			dt.deleteComodidadExtra(comodidades);
			return Response.status(200).entity(comodidades).build();
		}
		catch (Exception e)
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
}
