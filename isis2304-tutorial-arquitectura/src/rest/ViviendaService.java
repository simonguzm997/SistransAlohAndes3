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
import vos.Vivienda;

@Path("viviendas")
public class ViviendaService {
	
	
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
		public Response getViviendas() 
		{
			try {
				AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

				List<Vivienda> viviendas;
				//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				viviendas = tm.getAllViviendas();
				return Response.status(200).entity(viviendas).build();
			} 
			catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
		}
		
		@GET
		@Path( "{id: \\d+}" )
		@Produces( { MediaType.APPLICATION_JSON } )
		public Response getViviendaById( @PathParam( "id" ) int id )
		{
			try{
				AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
				
				Vivienda vivienda = tm.getViviendaById(id);
				return Response.status( 200 ).entity( vivienda ).build( );			
			}
			catch( Exception e )
			{
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
		}
		
		@POST 
		@Produces({MediaType.APPLICATION_JSON})
		public Response addVivienda(Vivienda nueva)
		{
			try {
				AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());


				tm.addVivienda(nueva);
				return Response.status(200).entity(nueva).build();
			} 
			catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
		}
		
		@PUT
		@Produces({MediaType.APPLICATION_JSON})
		public Response udpdateVivienda(Vivienda vivienda)
		{
			try{
				AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
				
				tm.updateVivienda(vivienda);
				return Response.status( 200 ).entity( vivienda).build( );			
			}
			catch( Exception e )
			{
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
		}


		@DELETE 
		@Produces({MediaType.APPLICATION_JSON})
		public Response deleteVivienda(Vivienda vivienda)
		{
			try{
				AlohAndesTransactionManager dt = new AlohAndesTransactionManager(getPath());
				dt.deleteVivienda(vivienda);
				return Response.status(200).entity(vivienda).build();
			}
			catch (Exception e)
			{
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
		}
}
