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
import vos.Apartamento;

@Path("apartamentos")
public class ApartamentoService 
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
	public Response getApartamentos() 
	{
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

			List<Apartamento> apartamentos;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			apartamentos = tm.getAllApartamentos();
			return Response.status(200).entity(apartamentos ).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getApartamentoById( @PathParam( "id" ) int id )
	{
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			
			Apartamento apto = tm.getApartamentoById(id);
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	@GET
	@Path( "/top20" )
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getApartamentosTop20() 
	{
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

			List<Apartamento> apartamentos;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			apartamentos = tm.getAllApartamentosTop20();
			return Response.status(200).entity(apartamentos).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	
	@POST 
	@Produces({MediaType.APPLICATION_JSON})
	public Response addApartamento (Apartamento apto)
	{
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());


			tm.addApartamento(apto);
			return Response.status(200).entity(apto).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON})
	public Response udpdateApartameto(Apartamento apto)
	{
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			
			tm.updateApartamento(apto);
			return Response.status( 200 ).entity( apto).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

	
	@DELETE
	@Path( "{id: \\d+}" )
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteAlojado( @PathParam( "id" ) int id)
	{
		try{
			
			
			AlohAndesTransactionManager dt = new AlohAndesTransactionManager(getPath());
	
			dt.deleteApartamento(id);
			return Response.status(200).entity( "hola").build();
		}
		catch (Exception e)
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	

//	@DELETE 
//	@Produces({MediaType.APPLICATION_JSON})
//	public Response deleteAlojado(Apartamento apartamento)
//	{
//		try{
//			
//			
//			AlohAndesTransactionManager dt = new AlohAndesTransactionManager(getPath());
//	
//			dt.deleteApartamento(apartamento);
//			return Response.status(200).entity(apartamento).build();
//		}
//		catch (Exception e)
//		{
//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
//		}
//	}
}
