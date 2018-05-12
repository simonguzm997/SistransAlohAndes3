package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vos.Hostal;

public class DAOHostal 
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
			public DAOHostal() 
			{
				recursos = new ArrayList<Object>();
			}
			
			
			//----------------------------------------------------------------------------------------------------------------------------------
			// METODOS DE COMUNICACION CON LA BASE DE DATOS
			//----------------------------------------------------------------------------------------------------------------------------------

			

			/**
			 * Metodo que obtiene la informacion de todos los clientes en la Base de Datos <br/>
			 * @return
			 * @throws SQLException
			 * @throws Exception
			 */
			public ArrayList<Hostal> getHostales() throws SQLException, Exception 
			{
				ArrayList<Hostal> hostales = new ArrayList<Hostal>();

				String sql = String.format("SELECT * FROM %1$s.HOSTALES ", USUARIO);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) 
				{
					hostales.add(convertResultSetToHostal(rs));
				}
				return hostales;
			}
			
			public Hostal findHostalById(int nit) throws SQLException, Exception 
			{
				Hostal hostal = null;

				String sql = String.format("SELECT * FROM %1$s.HOSTALES WHERE NIT = %2$d ", USUARIO, nit); 

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				if(rs.next()) 
				{
					hostal = convertResultSetToHostal(rs);
				}

				return hostal;
			}
			
			public void addHostal(Hostal hostal) throws SQLException, Exception 
			{
				
				String sql = String.format("INSERT INTO %1$s.HOSTALES (NIT, NOMBRE, DIRECCION, HORA_APERTURA, HORA_CIERRE, ID_OPERADOR) "
						+ "VALUES (%2$s, '%3$s', '%4$s', '%5$s', '%6$s', '%7$s')", 
											USUARIO, 
											hostal.getNIT(),
											hostal.getNombre(),
											hostal.getDireccion(),
											hostal.getHoraApertura(),
											hostal.getHoraCierre(),
											hostal.getOperadorHostal().getId());
				System.out.println(sql);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();

			}
			
			
			public void deleteHostal(Hostal hostal) throws SQLException, Exception
			{


				String sql = String.format("DELETE FROM %1$s.HOSTALES WHERE NIT = %2$d", USUARIO, hostal.getNIT());

				System.out.println(sql);
				
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}
			
			
			public void updateHostal (Hostal hostal) throws SQLException, Exception
			{
				StringBuilder sql = new StringBuilder();
				sql.append(String.format("UPDATE %s.HOSTALES SET ", USUARIO));
				
				sql.append(String.format("NOMBRE = '%1$s' AND DIRECCION = '%2$s' "
						+ "AND HORA_APERTURA = '%3$s' AND HORA_CIERRE = '%4$s' "
						+ "AND ID_OPERADOR = '%5$s'", 
						hostal.getNombre(),
						hostal.getDireccion(),
						hostal.getHoraApertura(),
						hostal.getHoraCierre(),
						hostal.getOperadorHostal().getId()));
				
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
			public Hostal convertResultSetToHostal(ResultSet resultSet) throws SQLException 
			{
			
				
				int pNIT  = resultSet.getInt("NIT");
				String pNombre = resultSet.getString("NOMBRE");
				String pDireccion = resultSet.getString("DIRECCION");
				Date pHoraApertura = resultSet.getDate("HORA_APERTURA");
				Date pHoraCierre = resultSet.getDate("HORA_CIERRE");
				

				Hostal hostal = new Hostal(pNIT, pNombre, pDireccion, pHoraApertura, pHoraCierre);
				
				return hostal;
			}

}
