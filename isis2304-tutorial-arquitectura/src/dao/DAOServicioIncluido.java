package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ServicioIncluido;

public class DAOServicioIncluido 
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
			public DAOServicioIncluido ()
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
			public ArrayList<ServicioIncluido> getServiciosIncluidos() throws SQLException, Exception 
			{
				ArrayList<ServicioIncluido> servInc = new ArrayList<ServicioIncluido>();

				String sql = String.format("SELECT * FROM %1$s.SERVICIO_INCLUIDO ", USUARIO);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) 
				{
					servInc.add(convertResultSetToServicioIncluido(rs));
				}
				return servInc;
			}
			
			
			public ServicioIncluido findServicioIncluidoById(String pNombre, Long id_Contrato) throws SQLException, Exception 
			{
				ServicioIncluido servInc = null;

				String sql = String.format("SELECT * FROM %1$s.SERVICIO_INCLUIDO WHERE NOMBRE = %2$d "
						+ "AND ID_CONTRATO = %3$d   ", USUARIO, pNombre, id_Contrato); 
				
				

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				if(rs.next()) 
				{
					servInc = convertResultSetToServicioIncluido(rs);
				}

				return servInc;
			}
			
			
			
			public void addServicioIncluido (ServicioIncluido servInc) throws SQLException, Exception 
			{
				
				String sql = String.format("INSERT INTO %1$s.SERVICIO_INCLUIDO (NOMBRE, ID_CONTRATO) "
						+ "VALUES (%2$s, '%3$s')", 
											USUARIO, 
											servInc.getNombre(),
											servInc.getContrato().getId());
				System.out.println(sql);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();

			}
			
			
			//no tiene update por que no se modifica
			
			
			public void deleteServicioIncluido(ServicioIncluido servInc) throws SQLException, Exception
			{


				String sql = String.format("DELETE FROM %1$s.SERVICIO_INCLUIDO WHERE NOMBRE = %2$d "
						+ "AND ID_CONTRATO = %3$d   ", USUARIO, 
						servInc.getNombre(),
						servInc.getContrato().getId());

				System.out.println(sql);
				
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}
			
//			
//			public void updateComodidadExtra (ComodidadExtra comExtra) throws SQLException, Exception
//			{
//				StringBuilder sql = new StringBuilder();
//				sql.append(String.format("UPDATE %s.COMODIDADES_EXTRA SET ", USUARIO));
//				
//				sql.append(String.format("VALOR = '%1$s'", 
//						comExtra.getValor()));
//				
//				System.out.println(sql);
//				
//				PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
//				recursos.add(prepStmt);
//				prepStmt.executeQuery();
//				
//			}
			
			
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
			public ServicioIncluido convertResultSetToServicioIncluido (ResultSet resultSet) throws SQLException 
			{
			
				String pNombre = resultSet.getString("NOMBRE");
				
				ServicioIncluido serInc = new ServicioIncluido(pNombre);
				return serInc;
			}

	
	
}
