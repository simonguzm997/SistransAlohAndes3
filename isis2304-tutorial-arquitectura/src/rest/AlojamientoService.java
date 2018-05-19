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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.node.ArrayNode;

import tm.AlohAndesTransactionManager;
import vos.Alojamiento;

@Path("alojamientos")
public class AlojamientoService {
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

	/**
	 * Metodo GET que trae a todos los bebedores en la Base de datos. <br/>
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/bebedores <br/>
	 * @return	<b>Response Status 200</b> - JSON que contiene a todos los bebedores que estan en la Base de Datos <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */			
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getBebedores() {

		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

			List<Alojamiento> alojas;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			alojas = tm.getAllBebedores();
			return Response.status(200).entity(alojas).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}

	/**
	 * Metodo GET que trae al bebedor en la Base de Datos con el ID dado por parametro <br/>
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/bebedores/{id} <br/>
	 * @return	<b>Response Status 200</b> - JSON Bebedor que contiene al bebedor cuyo ID corresponda al parametro <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getBebedorById( @PathParam( "id" ) Long id )
	{
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );

			Alojamiento aloja = tm.getAlojamientoById(id);
			return Response.status( 200 ).entity( aloja ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	/**
	 * Metodo GET que trae a todos los bebedores en la Base de datos. <br/>
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/bebedores <br/>
	 * @return	<b>Response Status 200</b> - JSON que contiene a todos los bebedores que estan en la Base de Datos <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */			
	@GET
	@Path("MayorIngreso")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getMayorRecaudacion() {

		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

			ArrayNode fechas;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			fechas = tm.getFechasMayorIngreso();
			return Response.status(200).entity(fechas).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	/**
	 * Metodo GET que trae a todos los bebedores en la Base de datos. <br/>
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/bebedores <br/>
	 * @return	<b>Response Status 200</b> - JSON que contiene a todos los bebedores que estan en la Base de Datos <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */			
	@GET
	@Path("MayorDemanda")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getMayorDemanda() {

		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

			ArrayNode fechas;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			fechas = tm.getFechasMayorDemanda();
			return Response.status(200).entity(fechas).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	/**
	 * Metodo GET que trae a todos los bebedores en la Base de datos. <br/>
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/bebedores <br/>
	 * @return	<b>Response Status 200</b> - JSON que contiene a todos los bebedores que estan en la Base de Datos <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */			
	@GET
	@Path("MenorDemanda")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getMenorDemanda() {

		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());

			ArrayNode fechas;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			fechas = tm.getFechasMenorDemanda();
			return Response.status(200).entity(fechas).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	/**
	 * Metodo que recibe un bebedor en formato JSON y lo agrega a la Base de Datos <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se agrega a la Base de datos la informacion correspondiente al bebedor. <br/>
	 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/bebedores <br/>
	 * @param aloja JSON con la informacion del bebedor que se desea agregar
	 * @return	<b>Response Status 200</b> - JSON que contiene al bebedor que ha sido agregado <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	//TODO Requerimiento 3A: Identifique e implemente la anotacion correcta para la realizacion del metodo

	//TODO Requerimiento 3B: Identifique e implemente las anotaciones que indican el tipo de contenido que produce Y consume el metodo 

	@POST
	@Path( "agregarAlojamiento" )
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBebedor(Alojamiento aloja) {

		//TODO Requerimiento 3C: Implemente el metodo a partir de los ejemplos anteriores y utilizando el Transaction Manager de Parranderos 
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());
			tm.addAlojamiento(aloja);
			return Response.status(200).entity(aloja).build();
		} catch (Exception e) {
			// TODO: handle exception
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}


	/**
	 * Metodo que recibe un bebedor en formato JSON y lo agrega a la Base de Datos <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se actualiza la Base de datos con la informacion correspondiente al bebedor.<br/>
	 * @param aloja JSON con la informacion del bebedor que se desea agregar
	 * @return	<b>Response Status 200</b> - JSON que contiene al bebedor que se desea modificar <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	//TODO Requerimiento 5A: Identifique e implemente la anotacion correcta para la realizacion del metodo

	//TODO Requerimiento 5B: Identifique e implemente las anotaciones que indican el tipo de contenido que produce Y consume el metodo 

	@PUT
	@Path ("updateAlojamiento")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBebedor(Alojamiento aloja) {
		//TODO Requerimiento 5B: Implemente el metodo a partir de los ejemplos anteriores y utilizando el Transaction Manager de Parranderos 
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());
			tm.updateAlojamiento(aloja);
			return Response.status(200).entity(aloja).build();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}

	/**
	 * Metodo que recibe un bebedor en formato JSON y lo agrega a la Base de Datos <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se actualiza la Base de datos con la informacion correspondiente al bebedor.<br/>
	 * @param aloja JSON con la informacion del bebedor que se desea agregar
	 * @return	<b>Response Status 200</b> - JSON que contiene al bebedor que se desea modificar <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	//TODO Requerimiento 5A: Identifique e implemente la anotacion correcta para la realizacion del metodo

	//TODO Requerimiento 5B: Identifique e implemente las anotaciones que indican el tipo de contenido que produce Y consume el metodo 

	@PUT
	@Path ("habilitarAlojamiento"  + "/{id: \\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response habilitarAlojamiento(@PathParam( "id" ) Long id) {
		//TODO Requerimiento 5B: Implemente el metodo a partir de los ejemplos anteriores y utilizando el Transaction Manager de Parranderos 
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());
			Alojamiento aloja = tm.getAlojamientoById(id);
			if (aloja.getEstado().equals("DISPONIBLE")) {
				Exception e1 = new Exception("El alojamiento ya se encuentra disponible.");
				throw e1;
			}
			else
			{
				tm.updateAlojamientoHabilitar(aloja);
			}
			return Response.status(200).entity(aloja).build();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}

	/**
	 * Metodo que recibe un bebedor en formato JSON y lo elimina de la Base de Datos <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se elimina de la Base de datos al bebedor con la informacion correspondiente.<br/>
	 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/bebedores <br/>
	 * @param aloja JSON con la informacion del bebedor que se desea eliminar
	 * @return	<b>Response Status 200</b> - JSON que contiene al bebedor que se desea eliminar <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	//TODO Requerimiento 6A: Identifique e implemente la anotacion correcta para la realizacion del metodo

	//TODO Requerimiento 6B: Identifique e implemente las anotaciones que indican el tipo de contenido que produce Y consume el metodo 

	@DELETE
	@Path ("deleteAlojamiento")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBebedor(Alojamiento aloja) {
		//TODO Requerimiento 6C: Implemente el metodo a partir de los ejemplos anteriores y utilizando el Transaction Manager de Parranderos 
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());
			tm.deleteAlojamiento(aloja);
			return Response.status(200).entity(aloja).build();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}


}
