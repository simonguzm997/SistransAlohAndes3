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
import vos.Habitacion;

@Path("habitaciones")
public class HabitacionService 
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
		public Response getHabitacions() 
		{
			try {
				AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

				List<Habitacion> habitacions;
				//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				habitacions = tm.getAllHabitacions();
				return Response.status(200).entity(habitacions ).build();
			} 
			catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
		}
		
		
		@GET
		@Path( "{id: \\d+}" )
		@Produces( { MediaType.APPLICATION_JSON } )
		public Response getHabitacionById( @PathParam( "id" ) int id )
		{
			try{
				AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
				
				Habitacion apto = tm.getHabitacionById(id);
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
		public Response getHabitacionsTop20() 
		{
			try {
				AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

				List<Habitacion> habitacions;
				//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				habitacions = tm.getAllHabitacionTop20();
				return Response.status(200).entity(habitacions).build();
			} 
			catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
		}
		
		
		@POST 
		@Produces({MediaType.APPLICATION_JSON})
		public Response addHabitacion (Habitacion apto)
		{
			try {
				AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());


				tm.addHabitacion(apto);
				return Response.status(200).entity(apto).build();
			} 
			catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
		}
		
		@PUT
		@Produces({MediaType.APPLICATION_JSON})
		public Response udpdateApartameto(Habitacion apto)
		{
			try{
				AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
				
				tm.updateHabitacion(apto);
				return Response.status( 200 ).entity( apto).build( );			
			}
			catch( Exception e )
			{
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
		}

		
		@PUT
		@Path("habilitarHabitacion"+"/{id: \\d+}" )
		@Produces({MediaType.APPLICATION_JSON})
		public Response habilitarHabitacion(@PathParam ("id") int id)
		{
			try{
				AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
				Habitacion hab = tm.getHabitacionById(id);
				if (hab.getEstado().equals("DISPONIBLE")) {
					Exception e1 = new Exception("La habitación ya se encuentra disponible");
					throw e1;
				}else
				{
					tm.updateHabitacionHabilitar(hab);

				}
				return Response.status( 200 ).entity( hab).build( );			
			}
			catch( Exception e )
			{
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
		}
		
		@GET
		@Path( "/filterRFC4" )
		@Produces({ MediaType.APPLICATION_JSON })
		public Response getHabitacionIntTiempo(@QueryParam("Inicio")String f1, @QueryParam("Fin")String f2,
				@QueryParam("Req1")String r1, @QueryParam("Req2")String r2) 
		{
			try {
				AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

				List<Habitacion> habitaciones;
				habitaciones = tm.getAllHabitacionesRFC4(f1, f2, r1, r2);
				return Response.status(200).entity(habitaciones).build();
			} 
			catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
		}
		
		
		
		
		
	
}
