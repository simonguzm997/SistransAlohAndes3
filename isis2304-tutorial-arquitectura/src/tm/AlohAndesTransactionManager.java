package tm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import dao.DAOAlojamiento;
import dao.DAOOperador;
import vos.Alojamiento;
import vos.Operador;

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
		
		
}

		
		
		
		
		
		
   
   

		


