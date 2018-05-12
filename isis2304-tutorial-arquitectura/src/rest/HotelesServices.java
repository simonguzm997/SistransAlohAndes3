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
import vos.Alojado;
import vos.HabitacionEmp;
import vos.Hotel;

@Path("hoteles")
public class HotelesServices 
{
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
	public Response getHoteles() 
	{
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

			List<Hotel> hoteles;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			hoteles = tm.getAllHoteles();
			return Response.status(200).entity(hoteles).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getHotelById( @PathParam( "id" ) int id )
	{
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			
			Hotel hotel = tm.getHotelById(id);
			return Response.status( 200 ).entity( hotel ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	@GET
	@Path( "/Frecuentes" )
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAlojadosFrecuentes(@QueryParam("NIT")int pNIT) 
	{
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

			List<Alojado> alojados;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			alojados = tm.getAlojadosFrecuentesHotel(pNIT);
			return Response.status(200).entity(alojados).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	
	@POST 
	@Produces({MediaType.APPLICATION_JSON})
	public Response addHotel (Hotel hotel)
	{
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());


			tm.addHotel(hotel);
			return Response.status(200).entity(hotel).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON})
	public Response udpdateHotel(Hotel hotel)
	{
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			
			tm.updateHotel(hotel);
			return Response.status( 200 ).entity( hotel).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}


	@DELETE 
	@Produces({MediaType.APPLICATION_JSON})
	public Response deletehotel(Hotel hotel)
	{
		try{
			AlohAndesTransactionManager dt = new AlohAndesTransactionManager(getPath());
			dt.deleteHoteles(hotel);
			return Response.status(200).entity(hotel).build();
		}
		catch (Exception e)
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

}
