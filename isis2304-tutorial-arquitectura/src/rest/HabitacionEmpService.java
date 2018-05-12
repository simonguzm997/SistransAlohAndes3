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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohAndesTransactionManager;
import vos.HabitacionEmp;

@Path("habitacionesEmp")
public class HabitacionEmpService {

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
	public Response getHabitacionesEmp() 
	{
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

			List<HabitacionEmp> habitacionesEmp;
			habitacionesEmp= tm.getAllHabitacionEmps();
			return Response.status(200).entity(habitacionesEmp).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	@GET
	@Path( "/top20" )
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getHabitacionEmpsTop20() 
	{
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

			List<HabitacionEmp> habitacionEmps;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			habitacionEmps = tm.getAllHabitacionEmpsTop20();
			return Response.status(200).entity(habitacionEmps).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	@GET
	@Path( "/filterHora" )
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getHabitacionIntTiempo(@QueryParam("inicio")String fInicio, @QueryParam("fin")String fFin) 
	{
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

			List<HabitacionEmp> habitacionEmps;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			habitacionEmps = tm.getAllHabitacionEmpsIntTiempo(fInicio, fFin);
			return Response.status(200).entity(habitacionEmps).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getHabitacionEmpById( @PathParam( "id" ) int id )
	{
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			
			HabitacionEmp habitacionEmp = tm.getHabitacionEmpById(id);
			return Response.status( 200 ).entity( habitacionEmp ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

	@POST 
	@Produces({MediaType.APPLICATION_JSON})
	public Response addHabitacionEmp (HabitacionEmp habitacionEmp)
	{
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());


			tm.addHabitacionEmp(habitacionEmp);
			return Response.status(200).entity(habitacionEmp).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON})
	public Response udpdateApartameto(HabitacionEmp habitacionEmp)
	{
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			
			tm.updateHabitacionEmp(habitacionEmp);
			return Response.status( 200 ).entity( habitacionEmp).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}



	@DELETE 
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteHhabitacionEmp(HabitacionEmp habitacionEmp)
	{
		try{
			AlohAndesTransactionManager dt = new AlohAndesTransactionManager(getPath());
			dt.deleteHabitacionEmp(habitacionEmp);
			return Response.status(200).entity(habitacionEmp).build();
		}
		catch (Exception e)
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
}
