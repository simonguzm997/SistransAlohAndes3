package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Alojado;

public class DAOAlojado 
{
	//----------------------------------------------------------------------------------------------------------------------------------
		// CONSTANTES
		//----------------------------------------------------------------------------------------------------------------------------------
		
		/**
		 * Constante para indicar el usuario Oracle del estudiante
		 */
		//TODO Requerimiento 1H: Modifique la constante, reemplazando al ususario PARRANDEROS por su ususario de Oracle
		public final static String USUARIO = "ISIS2304A111810";
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// ATRIBUTOS
		//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Arraylits de recursos que se usan para la ejecucion de sentencias SQL
		 */
		private ArrayList<Object> recursos;

		/**
		 * Atributo que genera la conexion a la base de datos
		 */
		private Connection conn;
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE INICIALIZACION
		//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Metodo constructor de la clase DAOAlojado <br/>
		*/
		public DAOAlojado() 
		{
			recursos = new ArrayList<Object>();
		}
		
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE COMUNICACION CON LA BASE DE DATOS
		//----------------------------------------------------------------------------------------------------------------------------------

		

		/**
		 * Metodo que obtiene la informacion de todos los alojados en la Base de Datos <br/>
		 * @return
		 * @throws SQLException
		 * @throws Exception
		 */
		public ArrayList<Alojado> getAlojados() throws SQLException, Exception 
		{
			ArrayList<Alojado> alojados = new ArrayList<Alojado>();

			String sql = String.format("SELECT * FROM %1$s.ALOJADOS INNER JOIN %1$s.CLIENTE "
					+ "ON ALOJADOS.IDENTIFICACION_CLIENTE = CLIENTE.ID ", USUARIO);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				alojados.add(convertResultSetToAlojado(rs));
			}
			return alojados;
		}
		
		public Alojado findAlojadoById(int id) throws SQLException, Exception 
		{
			Alojado alojado = null;

			String sql = String.format("SELECT * FROM %1$s.ALOJADOS INNER JOIN %1$s.CLIENTE " + 
										"ON ALOJADOS.IDENTIFICACION_CLIENTE = CLIENTE.ID WHERE ID = %2$d", USUARIO, id); 

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.next()) 
			{
				alojado = convertResultSetToAlojado(rs);
			}

			return alojado;
		}
		
		
		public void addAlojado(Alojado alojado) throws SQLException, Exception 
		{

			String sql = String.format("INSERT INTO %1$s.ALOJADOS (IDENTIFICACION_CLIENTE, VINCULO_UNIVERSIDAD) "
					+ "VALUES ( %2$s , '%3$s' )", 
										USUARIO, 
										alojado.getId(),
										alojado.getVinculoUniversidad());
			System.out.println(sql);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();

		}
		
		public void deleteAlojado(Alojado alojado) throws SQLException, Exception
		{

			String sql = String.format("DELETE FROM %1$s.ALOJADOS WHERE IDENTIFICACION_CLIENTE = %2$d", USUARIO, alojado.getId());

			System.out.println(sql);
			
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		
		public void updateAlojado (Alojado alojado) throws SQLException, Exception
		{
			StringBuilder sql = new StringBuilder();
			sql.append(String.format("UPDATE %s.ALOJADOS SET ", USUARIO));
			
			sql.append(String.format("NOMBRE = '%1$s'", alojado.getVinculoUniversidad()));
			
			System.out.println(sql);
			
			PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
		}
		
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS AUXILIARES
		//----------------------------------------------------------------------------------------------------------------------------------
		
		/**
		 * Metodo encargado de inicializar la conexion del DAO a la Base de Datos a partir del parametro <br/>
		 * <b>Postcondicion: </b> el atributo conn es inicializado <br/>
		 * @param connection la conexion generada en el TransactionManager para la comunicacion con la Base de Datos
		 */
		public void setConn(Connection connection)
		{
			this.conn = connection;
		}
		
		/**
		 * Metodo que cierra todos los recursos que se encuentran en el arreglo de recursos<br/>
		 * <b>Postcondicion: </b> Todos los recurso del arreglo de recursos han sido cerrados.
		 */
		public void cerrarRecursos() 
		{
			for(Object ob : recursos){
				if(ob instanceof PreparedStatement)
					try {
						((PreparedStatement) ob).close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
			}
		}
		
		/**
		 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla CLIENTE) en una instancia de la clase Bebedor.
		 * @param resultSet ResultSet con la informacion de un bebedor que se obtuvo de la base de datos.
		 * @return Bebedor cuyos atributos corresponden a los valores asociados a un registro particular de la tabla BEBEDORES.
		 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
		 */
		public Alojado convertResultSetToAlojado(ResultSet resultSet) throws SQLException 
		{
			
//			String sql = String.format("SELECT * FROM ALOJADOS INNER JOIN CLIENTE"
//					+ "ON ALOJADOS.IDENTIFICACION_CLIENTE = CLIENTE.ID WHERE ROWNUM <= 50", USUARIO);
//
//			PreparedStatement prepStmt = conn.prepareStatement(sql);
//			recursos.add(prepStmt);
//			ResultSet rs = prepStmt.executeQuery();

			
			
			
			int pId  = resultSet.getInt("IDENTIFICACION_CLIENTE");
			String pNombre = resultSet.getString("NOMBRE");
			String pUsuario = resultSet.getString("USUARIO");
			String pContrasena = resultSet.getString("CONTRASENIA");
			String pCorreoElectronico = resultSet.getString("CORREO_ELECTRONICO");
			//int pNumeroContacto  = resultSet.getInt("NUMERO_CONTACTO");
			String pVinculoUniversidad = resultSet.getString("VINCULO_UNIVERSIDAD");

			Alojado alojado = new Alojado(pId, pNombre, pUsuario, pContrasena, pCorreoElectronico, 123, pVinculoUniversidad);
			
			
			return alojado;
		}
		
		

}
