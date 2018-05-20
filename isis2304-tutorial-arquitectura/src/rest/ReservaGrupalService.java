package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
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

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;

import tm.AlohAndesTransactionManager;
import vos.Habitacion;
import vos.Reserva;
import vos.ReservaGrupal;
@Path("reservaGrupal")
public class ReservaGrupalService 
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
	
	
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getReservaGrupalById( @PathParam( "id" ) long id )
	{
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			
			ReservaGrupal resGrup = tm.getReservaGrupalById(id);
			return Response.status( 200 ).entity( resGrup ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@POST
	@Path ("/RF7")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getHabitacionIntTiempo(@QueryParam("IdGrup")long idGrup, @QueryParam("Cantidad")int cantRes,
			@QueryParam("TipoHab")String tipoHab, @QueryParam("FechaInicio")String fInicio, 
			@QueryParam("FechaFin")String fFin, @QueryParam("iDCliente")long idCliente,
			@QueryParam("dias")int dias ) 
	{
		
		System.out.println(idGrup+"--"+cantRes+"--"+tipoHab+"--"+fInicio+"--"+fFin+"--"+idCliente+"--"+dias);
		try {
			System.out.println("antes de crear TM");
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

			System.out.println("despues de crear TM y antes de llamar a addResGrupal");
			
			tm.addReservaGrupal((long) idGrup, (int)cantRes, tipoHab, fInicio, fFin, (long)idCliente, (int)dias);
			System.out.println("despues de addReservaGrupal");
			return Response.status(200).entity(tm.getReservaGrupalById(idGrup)).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	
	
	
	
	@PUT
	@Path ("/cancelarReservaGrupal")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancelarReserva(@QueryParam("IdGrup")long idGrup) 
	{
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());
			
			ObjectMapper mapper = new ObjectMapper();
			ArrayNode arrayNode = mapper.createArrayNode();
			double multa =tm.cancelarReservaGrupal(idGrup);
			
			org.codehaus.jackson.node.ObjectNode obj1 = mapper.createObjectNode();
			obj1.put("El valor de su multa es de: ", multa);
			arrayNode.add(obj1);
			
			
			return Response.status(200).entity(arrayNode).build();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
}
