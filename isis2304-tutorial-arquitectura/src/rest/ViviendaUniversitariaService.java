package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohAndesTransactionManager;
import vos.ViviendaUniversitaria;

@Path("viviendasUniversitarias")
public class ViviendaUniversitariaService {

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
	public Response getVivUniversitarias() 
	{
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

			List<ViviendaUniversitaria> vivUniversitarias;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			vivUniversitarias = tm.getAllViviendasUniversitarias();
			return Response.status(200).entity(vivUniversitarias).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getViviendaUniversitariaById( @PathParam( "id" ) int id )
	{
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			
			ViviendaUniversitaria viviendaUniversitaria = tm.getViviendaUniversitariaById(id);
			return Response.status( 200 ).entity( viviendaUniversitaria ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	@POST 
	@Produces({MediaType.APPLICATION_JSON})
	public Response addViviendaUniversitaria (ViviendaUniversitaria universitaria)
	{
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());


			tm.addViviendaUniversitaria(universitaria);
			return Response.status(200).entity(universitaria).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}

	@PUT
	@Produces({MediaType.APPLICATION_JSON})
	public Response udpdateViviendaUniversitaria(ViviendaUniversitaria universitaria)
	{
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			
			tm.updateViviendaUniversitaria(universitaria);
			return Response.status( 200 ).entity( universitaria).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}


	@DELETE 
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteViviendaUniversitaria(ViviendaUniversitaria vivUniversitaria)
	{
		try{
			AlohAndesTransactionManager dt = new AlohAndesTransactionManager(getPath());
			dt.deleteViviendaUniversitaria(vivUniversitaria);
			return Response.status(200).entity(vivUniversitaria).build();
		}
		catch (Exception e)
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
}


