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
import vos.Cliente;

@Path("clientes")
public class ClienteService 
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
	public Response getClientes() 
	{
		
		System.out.println("------ENTRO  A GET ALL SERVICE");
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

			List<Cliente> clientes;
			clientes= tm.getAllClientes();
			return Response.status(200).entity(clientes).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getClienteById( @PathParam( "id" ) int id )
	{
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			
			Cliente cliente = tm.getClienteById(id);
			return Response.status( 200 ).entity( cliente ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

	@POST
	@Produces({MediaType.APPLICATION_JSON})
	public Response addCliente(Cliente nuevo)
	{
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			
			tm.addCliente(nuevo);
			return Response.status( 200 ).entity( nuevo ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		
	}
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateCliente(Cliente cliente)
	{
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			
			tm.updateCliente(cliente);
			return Response.status( 200 ).entity( cliente).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

	@DELETE 
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteCliente(Cliente cliente)
	{
		try{
			AlohAndesTransactionManager dt = new AlohAndesTransactionManager(getPath());
			dt.deleteCliente(cliente);
			return Response.status(200).entity(cliente).build();
		}
		catch (Exception e)
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
}
