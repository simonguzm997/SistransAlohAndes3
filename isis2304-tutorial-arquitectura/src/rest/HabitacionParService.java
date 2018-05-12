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
import vos.HabitacionPar;

@Path("habitacionesPar")
public class HabitacionParService {

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
	public Response getHabitacionesPar() 
	{
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

			List<HabitacionPar> habitacionesPar;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			habitacionesPar = tm.getAllHabitacionPars();
			return Response.status(200).entity(habitacionesPar).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getHabitacionParById( @PathParam( "id" ) int id )
	{
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			
			HabitacionPar habitacionPar = tm.getHabitacionParById(id);
			return Response.status( 200 ).entity( habitacionPar ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "/top20" )
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getHabitacionParsTop20() 
	{
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

			List<HabitacionPar> habitacionPars;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			habitacionPars = tm.getAllHabitacionParsTop20();
			return Response.status(200).entity(habitacionPars).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	public Response addHabitacionPar(HabitacionPar nuevo)
	{
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			
			tm.addHabitacionPar(nuevo);
			return Response.status( 200 ).entity( nuevo ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		
	}
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateHabitacionPar(HabitacionPar habitacionPar)
	{
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			
			tm.updateHabitacionPar(habitacionPar);
			return Response.status( 200 ).entity( habitacionPar).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	



	@DELETE 
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteHabitacionEmp(HabitacionPar habitacionPar)
	{
		try{
			AlohAndesTransactionManager dt = new AlohAndesTransactionManager(getPath());
			dt.deleteHabitacionPar(habitacionPar);
			return Response.status(200).entity(habitacionPar).build();
		}
		catch (Exception e)
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
}

