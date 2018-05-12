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
import vos.OperadorEmpresa;

@Path("operadoresEmpresa")
public class OperadorEmpresaService 
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
	public Response getOperadoresEmpresa() 
	{
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

			List<OperadorEmpresa> operadoresEmpresa;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			operadoresEmpresa = tm.getAllOperadoresEmpresas();
			return Response.status(200).entity(operadoresEmpresa).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getOperadorEmpresaById( @PathParam( "id" ) int id )
	{
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			
			OperadorEmpresa operadorEmpresa = tm.getOperadorEmpresaById(id);
			return Response.status( 200 ).entity( operadorEmpresa ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	public Response addOperadorEmpresa(OperadorEmpresa nuevo)
	{
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			
			tm.addOperadorEmpresa(nuevo);
			return Response.status( 200 ).entity( nuevo ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		
	}
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateOperadorEmpresa(OperadorEmpresa operador)
	{
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			
			tm.updateOperadorEmpresa(operador);
			return Response.status( 200 ).entity( operador).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}


	@DELETE 
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteOperadoresEmpresa(OperadorEmpresa operador)
	{
		try{
			AlohAndesTransactionManager dt = new AlohAndesTransactionManager(getPath());
			dt.deleteOperadorEmpresa(operador);
			return Response.status(200).entity(operador).build();
		}
		catch (Exception e)
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
}
