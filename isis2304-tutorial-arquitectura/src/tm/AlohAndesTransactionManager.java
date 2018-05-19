package tm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import dao.DAOAlojamiento;
import dao.DAOCliente;
import dao.DAOComodidadExtra;
import dao.DAOHabitacion;
import dao.DAOOperador;
import dao.DAOReserva;
import vos.Alojamiento;
import vos.Cliente;
import vos.ComodidadExtra;
import vos.Habitacion;
import vos.Operador;
import vos.Reserva;

public class AlohAndesTransactionManager 
{
	//----------------------------------------------------------------------------------------------------------------------------------
		// CONSTANTES
		//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Constante que contiene el path relativo del archivo que tiene los datos de la conexion
		 */
		private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

		/**
		 * Atributo estatico que contiene el path absoluto del archivo que tiene los datos de la conexion
		 */
		private static String CONNECTION_DATA_PATH;
		
		/**
		 * Constatne que representa el numero maximo de Bebedores que pueden haber en una ciudad
		 */
		private final static Integer CANTIDAD_MAXIMA = 345;

		//----------------------------------------------------------------------------------------------------------------------------------
		// ATRIBUTOS
		//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
		 */
		private String user;

		/**
		 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
		 */
		private String password;

		/**
		 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
		 */
		private String url;

		/**
		 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
		 */
		private String driver;
		
		/**
		 * Atributo que representa la conexion a la base de datos
		 */
		private Connection conn;

		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE CONEXION E INICIALIZACION
		//----------------------------------------------------------------------------------------------------------------------------------
		
		public AlohAndesTransactionManager(String contextPathP) 
		{
			
			try {
				CONNECTION_DATA_PATH = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
				initializeConnectionData();
			} 
			catch (ClassNotFoundException e) {			
				e.printStackTrace();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private void initializeConnectionData() throws IOException, ClassNotFoundException 
		{

			FileInputStream fileInputStream = new FileInputStream(new File(AlohAndesTransactionManager.CONNECTION_DATA_PATH));
			Properties properties = new Properties();
			
			properties.load(fileInputStream);
			fileInputStream.close();
			
			this.url = properties.getProperty("url");
			this.user = properties.getProperty("usuario");
			this.password = properties.getProperty("clave");
			this.driver = properties.getProperty("driver");
			
		}
		
		private Connection darConexion() throws SQLException 
		{
			System.out.println("[ALOHANDES APP] Attempting Connection to: " + url + " - By User: " + user);
			return DriverManager.getConnection(url, user, password);
		}
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS TRANSACCIONALES
		//----------------------------------------------------------------------------------------------------------------------------------
		
		/**
		 * Metodo que modela la transaccion que retorna todos los bebedores de la base de datos. <br/>
		 * @return List<Bebedor> - Lista de bebedores que contiene el resultado de la consulta.
		 * @throws Exception -  Cualquier error que se genere durante la transaccion
		 */
		public List<Alojamiento> getAllBebedores() throws Exception {
			DAOAlojamiento daoAloja = new DAOAlojamiento();
			List<Alojamiento> alojamientos;
			try 
			{
				this.conn = darConexion();
				daoAloja.setConn(conn);
				
				//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				alojamientos = daoAloja.getAlojamientos();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoAloja.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return alojamientos;
		}
		//-------------------------
		
		//OPERADORES
		
		//***************************
		/**
		 * Metodo que modela la transaccion que retorna todos los bebedores de la base de datos. <br/>
		 * @return List<Bebedor> - Lista de bebedores que contiene el resultado de la consulta.
		 * @throws Exception -  Cualquier error que se genere durante la transaccion
		 */
		public List<Operador> getAllOperadores() throws Exception {
			DAOOperador daoOperador = new DAOOperador();
			List<Operador> operadores;
			try 
			{
				this.conn = darConexion();
				daoOperador.setConn(conn);
				
				//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				operadores = daoOperador.getOperadores();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOperador.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return operadores;
		}
		
		/**
		 * Metodo que modela la transaccion que retorna todos los bebedores de la base de datos. <br/>
		 * @return List<Bebedor> - Lista de bebedores que contiene el resultado de la consulta.
		 * @throws Exception -  Cualquier error que se genere durante la transaccion
		 */
		public List<Operador> getOperadoresByDinero() throws Exception {
			DAOOperador daoOperador = new DAOOperador();
			List<Operador> operadores;
			try 
			{
				this.conn = darConexion();
				daoOperador.setConn(conn);
				
				//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				operadores = daoOperador.getOperadores();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOperador.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return operadores;
		}
		
		/**
		 * Metodo que modela la transaccion que agrega un bebedor a la base de datos. <br/>
		 * <b> post: </b> se ha agregado el bebedor que entra como parametro <br/>
		 * @param bebedor - el bebedor a agregar. bebedor != null
		 * @throws Exception - Cualquier error que se genere agregando el bebedor
		 */
		public void addAlojamiento(Alojamiento  aloja) throws Exception 
		{
			
			DAOAlojamiento daoAlojamiento = new DAOAlojamiento( );
			try
			{
				//TODO Requerimiento 3D: Obtenga la conexion a la Base de Datos (revise los metodos de la clase)
				this.conn= darConexion();
				//TODO Requerimiento 3E: Establezca la conexion en el objeto DAOBebedor (revise los metodos de la clase DAOBebedor)
				daoAlojamiento.setConn(conn);
				daoAlojamiento.addAlojamiento(aloja);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoAlojamiento.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		/**
		 * Metodo que modela la transaccion que busca el bebedor en la base de datos que tiene el ID dado por parametro. <br/>
		 * @param name -id del bebedor a buscar. id != null
		 * @return Bebedor - Bebedor que se obtiene como resultado de la consulta.
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public Alojamiento getAlojamientoById(Long id) throws Exception {
			DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
			Alojamiento aloja7 = null;
			try 
			{
				this.conn = darConexion();
				daoAlojamiento.setConn(conn);
				aloja7 = daoAlojamiento.findAlojamientoById(id);
				if(aloja7 == null)
				{
					throw new Exception("El bebedor con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoAlojamiento.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return aloja7;
		}
		
		/**
		 * Metodo que modela la transaccion que actualiza en la base de datos al bebedor que entra por parametro.<br/>
		 * Solamente se actualiza si existe el bebedor en la Base de Datos <br/>
		 * <b> post: </b> se ha actualizado el bebedor que entra como parametro <br/>
		 * @param aloja - Bebedor a actualizar. bebedor != null
		 * @throws Exception - Cualquier error que se genere actualizando al bebedor.
		 */
		public void updateAlojamiento(Alojamiento aloja) throws Exception 
		{
			DAOAlojamiento daoAlojamiento = new DAOAlojamiento( );
			try
			{
				this.conn = darConexion();
				daoAlojamiento.setConn( conn );
				daoAlojamiento.updateAlojamiento(aloja);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoAlojamiento.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		public void updateAlojamientoHabilitar(Alojamiento aloja) throws Exception 
		{
			DAOAlojamiento daoAlojamiento = new DAOAlojamiento( );
			try
			{
				this.conn = darConexion();
				daoAlojamiento.setConn( conn );
				//TODO Requerimiento 5C: Utilizando los Metodos de DaoBebedor, verifique que exista el bebedor con el ID dado en el parametro. 
				//						 Si no existe un bebedor con el ID ingresado, lance una excepcion en donde se explique lo sucedido
				//						 De lo contrario, se actualiza la informacion del bebedor de la Base de Datos
				daoAlojamiento.habilitarAlojamiento(aloja);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoAlojamiento.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		/**
		 * Metodo que modela la transaccion que elimina de la base de datos al bebedor que entra por parametro. <br/>
		 * Solamente se actualiza si existe el bebedor en la Base de Datos <br/>
		 * <b> post: </b> se ha eliminado el bebedor que entra por parametro <br/>
		 * @param Bebedor - bebedor a eliminar. bebedor != null
		 * @throws Exception - Cualquier error que se genere eliminando al bebedor.
		 */
		public void deleteAlojamiento(Alojamiento aloja) throws Exception 
		{
			DAOAlojamiento daoAlojamiento = new DAOAlojamiento( );
			try
			{
				this.conn = darConexion();
				daoAlojamiento.setConn( conn );
				//TODO Requerimiento 6D: Utilizando los Metodos de DaoBebedor, verifique que exista el bebedor con el ID dado en el parametro. 
				//						 Si no existe un bebedor con el ID ingresado, lance una excepcion en donde se explique lo sucedido
				//						 De lo contrario, se elimina la informacion del bebedor de la Base de Datos
				daoAlojamiento.deleteAlojamiento(aloja);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoAlojamiento.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		//Alojamientos
		
		/**
		 * Metodo que modela la transaccion que agrega un bebedor a la base de datos. <br/>
		 * <b> post: </b> se ha agregado el bebedor que entra como parametro <br/>
		 * @param bebedor - el bebedor a agregar. bebedor != null
		 * @throws Exception - Cualquier error que se genere agregando el bebedor
		 */
		public void addOperador(Operador  operador) throws Exception 
		{
			
			DAOOperador daoOperador = new DAOOperador( );
			try
			{
				//TODO Requerimiento 3D: Obtenga la conexion a la Base de Datos (revise los metodos de la clase)
				this.conn= darConexion();
				//TODO Requerimiento 3E: Establezca la conexion en el objeto DAOBebedor (revise los metodos de la clase DAOBebedor)
				daoOperador.setConn(conn);
				daoOperador.addOperador(operador);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOperador.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		/**
		 * Metodo que modela la transaccion que busca el bebedor en la base de datos que tiene el ID dado por parametro. <br/>
		 * @param name -id del bebedor a buscar. id != null
		 * @return Bebedor - Bebedor que se obtiene como resultado de la consulta.
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public Operador getOperadorById(Long id) throws Exception {
			DAOOperador daoOperador = new DAOOperador();
			Operador operador = null;
			try 
			{
				this.conn = darConexion();
				daoOperador.setConn(conn);
				operador = daoOperador.findOperadorById(id);
				if(operador == null)
				{
					throw new Exception("El bebedor con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOperador.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return operador;
		}
		
		/**
		 * Metodo que modela la transaccion que actualiza en la base de datos al bebedor que entra por parametro.<br/>
		 * Solamente se actualiza si existe el bebedor en la Base de Datos <br/>
		 * <b> post: </b> se ha actualizado el bebedor que entra como parametro <br/>
		 * @param operador - Bebedor a actualizar. bebedor != null
		 * @throws Exception - Cualquier error que se genere actualizando al bebedor.
		 */
		public void updateOperador(Operador operador) throws Exception 
		{
			DAOOperador daoOperador = new DAOOperador( );
			try
			{
				this.conn = darConexion();
				daoOperador.setConn( conn );
				daoOperador.updateOperador(operador);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOperador.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		/**
		 * Metodo que modela la transaccion que elimina de la base de datos al bebedor que entra por parametro. <br/>
		 * Solamente se actualiza si existe el bebedor en la Base de Datos <br/>
		 * <b> post: </b> se ha eliminado el bebedor que entra por parametro <br/>
		 * @param Bebedor - bebedor a eliminar. bebedor != null
		 * @throws Exception - Cualquier error que se genere eliminando al bebedor.
		 */
		public void deleteOperador(Operador operador) throws Exception 
		{
			DAOOperador daoOperador = new DAOOperador( );
			try
			{
				this.conn = darConexion();
				daoOperador.setConn( conn );
				//TODO Requerimiento 6D: Utilizando los Metodos de DaoBebedor, verifique que exista el bebedor con el ID dado en el parametro. 
				//						 Si no existe un bebedor con el ID ingresado, lance una excepcion en donde se explique lo sucedido
				//						 De lo contrario, se elimina la informacion del bebedor de la Base de Datos
				daoOperador.deleteOperador(operador);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOperador.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		
		
		
		
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// HABITACION 
		//----------------------------------------------------------------------------------------------------------------------------------

		public List<Habitacion> getAllHabitacions() throws Exception 
		{
			
			
			DAOHabitacion daoHabitacion = new DAOHabitacion();
			List<Habitacion> habitacions;
			try 
			{
				this.conn = darConexion();
				daoHabitacion.setConn(conn);
				
				habitacions = daoHabitacion.getHabitaciones();
				int i = 0;
				while (i<habitacions.size())
				{
					Habitacion temp = habitacions.get(i);
					
					temp.setComodidadesExtra(getAllComodidadExtrasByIdHabitacion(temp.getId()));
				}
				
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHabitacion.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			System.out.println("------SALGO  A GET ALL TM");
			
			return habitacions;
		}
		
		public Habitacion getHabitacionById(int id) throws Exception
		{
			DAOHabitacion daoHabitacion = new DAOHabitacion();
			Habitacion habitacion = null;
			try 
			{
				this.conn = darConexion();
				daoHabitacion.setConn(conn);
				habitacion = daoHabitacion.findHabitacionById(id);
				if(habitacion == null)
				{
					throw new Exception("El habitacion con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
				else 
				{
					
					habitacion.setComodidadesExtra(getAllComodidadExtrasByIdHabitacion(id));
				}
				
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHabitacion.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return habitacion;
		}
		
		public void addHabitacion(Habitacion habitacion) throws Exception 
		{
			
			DAOHabitacion daoHabitacion = new DAOHabitacion();
			try
			{
				this.conn = darConexion();
				daoHabitacion.setConn(conn);
				daoHabitacion.addHabitacion(habitacion);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHabitacion.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		public void updateHabitacion(Habitacion habitacion) throws Exception 
		{
			DAOHabitacion daoHabitacion = new DAOHabitacion();
			
			try
			{
				this.conn = darConexion();
				daoHabitacion.setConn( conn );
				Habitacion pHabitacion = daoHabitacion.findHabitacionById(habitacion.getId());
				if (pHabitacion == null)
				{
					Exception e =new Exception (" El habitacion que quiere actualizar no existe en la base de datos");
					throw e;
				}
				else
				{
					daoHabitacion.updateHabitacion(habitacion);
				}

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHabitacion.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		public void updateHabitacionHabilitar(Habitacion habitacion) throws Exception 
		{
			DAOHabitacion daoHabitacion = new DAOHabitacion();
			
			try
			{
				this.conn = darConexion();
				daoHabitacion.setConn( conn );
				Habitacion pHabitacion = daoHabitacion.findHabitacionById(habitacion.getId());
				if (pHabitacion == null)
				{
					Exception e =new Exception (" El habitacion que quiere actualizar no existe en la base de datos");
					throw e;
				}
				else
				{
					daoHabitacion.habilitarHabitacion(habitacion);
				}

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHabitacion.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
				
		public void deleteHabitacion(Habitacion habitacion) throws Exception 
		{
			DAOHabitacion daoHabitacion = new DAOHabitacion();
			try
			{
				this.conn = darConexion();
				daoHabitacion.setConn( conn );
				daoHabitacion.deleteHabitacion(habitacion);
			
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHabitacion.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		
		
		public List<Habitacion> getAllHabitacionsByIdAlojamiento(long id) throws Exception 
		{
			
			
			DAOHabitacion daoHabitacion = new DAOHabitacion();
			List<Habitacion> habitacions;
			try 
			{
				this.conn = darConexion();
				daoHabitacion.setConn(conn);
				
				habitacions = daoHabitacion.getHabitacionesByIdAlojamiento(id);
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHabitacion.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			
			
			return habitacions;
		}
		
		
		
		
		
		
		public List<Habitacion> getAllHabitacionTop20() throws Exception 
		{
			DAOHabitacion daoHabitacion = new DAOHabitacion();
			List<Habitacion> habitaciones;
			try 
			{
				this.conn = darConexion();
				daoHabitacion.setConn(conn);
				
				habitaciones = daoHabitacion.getHabitacionesTop20();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHabitacion.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return habitaciones;
		}
		
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// COMODIDAD EXTRA
		//----------------------------------------------------------------------------------------------------------------------------------

		
		public List<ComodidadExtra> getAllComodidadExtras() throws Exception 
		{
			
			
			DAOComodidadExtra daoComodidadExtra = new DAOComodidadExtra();
			List<ComodidadExtra> comodidadExtras;
			try 
			{
				this.conn = darConexion();
				daoComodidadExtra.setConn(conn);
				
				comodidadExtras = daoComodidadExtra.getComodidadExtraes();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoComodidadExtra.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			
			
			return comodidadExtras;
		}
		
		public ComodidadExtra getComodidadExtraById(int id) throws Exception
		{
			DAOComodidadExtra daoComodidadExtra = new DAOComodidadExtra();
			ComodidadExtra comodidadExtra = null;
			try 
			{
				this.conn = darConexion();
				daoComodidadExtra.setConn(conn);
				comodidadExtra = daoComodidadExtra.findComodidadExtraById(id);
				if(comodidadExtra == null)
				{
					throw new Exception("El comodidadExtra con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoComodidadExtra.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return comodidadExtra;
		}
		
		public void addComodidadExtra(ComodidadExtra comodidadExtra) throws Exception 
		{
			
			DAOComodidadExtra daoComodidadExtra = new DAOComodidadExtra();
			try
			{
				this.conn = darConexion();
				daoComodidadExtra.setConn(conn);
				daoComodidadExtra.addComodidadExtra(comodidadExtra);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoComodidadExtra.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		public void updateComodidadExtra(ComodidadExtra comodidadExtra) throws Exception 
		{
			DAOComodidadExtra daoComodidadExtra = new DAOComodidadExtra();
			
			try
			{
				this.conn = darConexion();
				daoComodidadExtra.setConn( conn );
				ComodidadExtra pComodidadExtra = daoComodidadExtra.findComodidadExtraById(comodidadExtra.getId());
				if (pComodidadExtra.getNombre() == null)
				{
					Exception e =new Exception (" El comodidadExtra que quiere actualizar no existe en la base de datos");
					throw e;
				}
				else
				{
					daoComodidadExtra.updateComodidadExtra(comodidadExtra);
				}

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoComodidadExtra.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
				
		public void deleteComodidadExtra(ComodidadExtra comodidadExtra) throws Exception 
		{
			DAOComodidadExtra daoCliete = new DAOComodidadExtra();
			try
			{
				this.conn = darConexion();
				daoCliete.setConn( conn );
				daoCliete.deleteComodidadExtra(comodidadExtra);
			
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoCliete.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		public List<ComodidadExtra> getAllComodidadExtrasByIdHabitacion(long id) throws Exception 
		{
			
			
			DAOComodidadExtra daoComodidadExtra = new DAOComodidadExtra();
			List<ComodidadExtra> comodidadExtras;
			try 
			{
				this.conn = darConexion();
				daoComodidadExtra.setConn(conn);
				
				comodidadExtras = daoComodidadExtra.findComodidadExtraByIdHabitacion(id);
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoComodidadExtra.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			
			
			return comodidadExtras;
		}
		
		
		
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// RESERVAS
		//----------------------------------------------------------------------------------------------------------------------------------

		public List<Reserva> getAllReservas() throws Exception 
		{
			
			
			DAOReserva daoReserva = new DAOReserva();
			List<Reserva> reservas;
			try 
			{
				this.conn = darConexion();
				daoReserva.setConn(conn);
				
				reservas = daoReserva.getReservaes();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReserva.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			
			
			return reservas;
		}
		
		public Reserva getReservaById(int id) throws Exception
		{
			DAOReserva daoReserva = new DAOReserva();
			Reserva reserva = null;
			try 
			{
				this.conn = darConexion();
				daoReserva.setConn(conn);
				reserva = daoReserva.findReservaById(id);
				if(reserva == null)
				{
					throw new Exception("El reserva con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReserva.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return reserva;
		}
		
		public void addReserva(Reserva reserva) throws Exception 
		{
			
			DAOReserva daoReserva = new DAOReserva();
			try
			{
				this.conn = darConexion();
				daoReserva.setConn(conn);
				daoReserva.addReserva(reserva);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReserva.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		public void updateReserva(Reserva reserva) throws Exception 
		{
			DAOReserva daoReserva = new DAOReserva();
			
			try
			{
				this.conn = darConexion();
				daoReserva.setConn( conn );
				Reserva pReserva = daoReserva.findReservaById(reserva.getId());
				if (pReserva == null)
				{
					Exception e =new Exception (" El reserva que quiere actualizar no existe en la base de datos");
					throw e;
				}
				else
				{
					daoReserva.updateReserva(reserva);
				}

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReserva.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
				
		public void deleteReserva(Reserva reserva) throws Exception 
		{
			DAOReserva daoCliete = new DAOReserva();
			try
			{
				this.conn = darConexion();
				daoCliete.setConn( conn );
				daoCliete.deleteReserva(reserva);
			
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoCliete.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		
		public List<Reserva> getAllReservasByIdHabitacion(long id) throws Exception 
		{
			
			
			DAOReserva daoReserva = new DAOReserva();
			List<Reserva> reservas;
			try 
			{
				this.conn = darConexion();
				daoReserva.setConn(conn);
				
				reservas = daoReserva.findReservaByIdHabitacion(id);
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReserva.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			
			
			return reservas;
		}
		
		
		public List<Reserva> getAllReservasByIdCliente(long id) throws Exception 
		{
			
			
			DAOReserva daoReserva = new DAOReserva();
			List<Reserva> reservas;
			try 
			{
				this.conn = darConexion();
				daoReserva.setConn(conn);
				
				reservas = daoReserva.findReservaByIdCliente(id);
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReserva.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			
			
			return reservas;
		}
		
		
		//Cliente
		/**
		 * Metodo que optiene los operadores de la base de datos
		 * @return la lista de los operadores en Formato Json de la base de datos
		 * @throws Exception Si hay algun error
		 */
		public List<Cliente> getClientes () throws Exception
		{
			DAOCliente daoCliente = new DAOCliente();
			List<Cliente> ofertas;
			try {
				this.conn = darConexion();
				daoCliente.setConn(conn);
				ofertas = daoCliente.getClientes();
				
			}catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoCliente.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return ofertas;
		}
		
		/**
		 * Metodo que modela la transaccion que busca el bebedor en la base de datos que tiene el ID dado por parametro. <br/>
		 * @param name -id del bebedor a buscar. id != null
		 * @return Bebedor - Bebedor que se obtiene como resultado de la consulta.
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public Cliente getClienteById(Long id) throws Exception {
			DAOCliente daoCliente = new DAOCliente(); 
			Cliente cliente = null;
			try 
			{
				this.conn = darConexion();
				daoCliente.setConn(conn);
				cliente = daoCliente.findClienteById(id);
				if(cliente == null)
				{
					throw new Exception("El bebedor con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoCliente.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return cliente;
		}
		
		/**
		 * Metodo que optiene los operadores de la base de datos
		 * @return la lista de los operadores en Formato Json de la base de datos
		 * @throws Exception Si hay algun error
		 */
		public List<Cliente> getClientesFrecuentes () throws Exception
		{
			DAOCliente daoCliente = new DAOCliente();
			List<Cliente> ofertas;
			try {
				this.conn = darConexion();
				daoCliente.setConn(conn);
				ofertas = daoCliente.getClientesFrecuentes();
				
			}catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoCliente.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return ofertas;
		}
		
		/**
		 * Metodo que modela la transaccion que agrega un operador a la base de datos. <br/>
		 * <b> post: </b> se ha agregado el operador que entra como parametro <br/>
		 * @param bebedor - el operador a agregar. operador != null
		 * @throws Exception - Cualquier error que se genere agregando el bebedor
		 */
		public void addCliente(Cliente cliente) throws Exception 
		{
			DAOCliente daoCliente = new DAOCliente();
			try
			{
				this.conn= darConexion();
				daoCliente.setConn(conn);
				daoCliente.addCliente(cliente);
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoCliente.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		public void updateCliente(Cliente cliente) throws Exception 
		{
			DAOCliente daoCliente = new DAOCliente( );
			try
			{
				this.conn = darConexion();
				daoCliente.setConn( conn );
				//TODO Requerimiento 5C: Utilizando los Metodos de DaoBebedor, verifique que exista el bebedor con el ID dado en el parametro. 
				//						 Si no existe un bebedor con el ID ingresado, lance una excepcion en donde se explique lo sucedido
				//						 De lo contrario, se actualiza la informacion del bebedor de la Base de Datos
				daoCliente.updateCliente(cliente);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoCliente.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		/**
		 * Metodo que modela la transaccion que elimina de la base de datos al bebedor que entra por parametro. <br/>
		 * Solamente se actualiza si existe el bebedor en la Base de Datos <br/>
		 * <b> post: </b> se ha eliminado el bebedor que entra por parametro <br/>
		 * @param Bebedor - bebedor a eliminar. bebedor != null
		 * @throws Exception - Cualquier error que se genere eliminando al bebedor.
		 */
		public void deleteCliente(Cliente cliente) throws Exception 
		{
			DAOCliente daoCliente = new DAOCliente();
			try
			{
				this.conn = darConexion();
				daoCliente.setConn( conn );


					daoCliente.deleteCliente(cliente);
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoCliente.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		
		
		
}

		
		
		
		
		
		
   
   

		


