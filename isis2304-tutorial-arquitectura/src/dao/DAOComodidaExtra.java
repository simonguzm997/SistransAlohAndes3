package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ComodidadExtra;

public class DAOComodidaExtra 
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
			public DAOComodidaExtra ()
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
			public ArrayList<ComodidadExtra> getComodidadesExtra() throws SQLException, Exception 
			{
				ArrayList<ComodidadExtra> comExtra = new ArrayList<ComodidadExtra>();

				String sql = String.format("SELECT * FROM %1$s.COMODIDADES_EXTRA ", USUARIO);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) 
				{
					comExtra.add(convertResultSetToComodidadExtra(rs));
				}
				return comExtra;
			}
			
			public ComodidadExtra findComodidadExtraById(String pNombre, int idHab) throws SQLException, Exception 
			{
				ComodidadExtra comExtra = null;

				String sql = String.format("SELECT * FROM %1$s.COMODIDADES_EXTRA WHERE NOMBRE = %2$d"
						+ " AND ID_HABITACION = %3$d   ", USUARIO, pNombre, idHab); 

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				if(rs.next()) 
				{
					comExtra = convertResultSetToComodidadExtra(rs);
				}

				return comExtra;
			}
			
			
			public void addComodidadExtra (ComodidadExtra comExtra) throws SQLException, Exception 
			{
				
				String sql = String.format("INSERT INTO %1$s.COMODIDADES_EXTRA (NOMBRE, VALOR, ID_HABITACION) "
						+ "VALUES (%2$s, '%3$s', '%4$s')", 
											USUARIO, 
											comExtra.getNombre(),
											comExtra.getValor(),
											comExtra.getHabEmp().getId());
				System.out.println(sql);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();

			}
			
			public void deleteComodidadExtra(ComodidadExtra comExtra) throws SQLException, Exception
			{


				String sql = String.format("DELETE FROM %1$s.COMODIDADES_EXTRA WHERE NOMBRE = %2$d "
						+ "AND ID_HABITACION = %3$d   ", USUARIO, 
						comExtra.getNombre(), comExtra.getHabEmp().getId());

				System.out.println(sql);
				
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}
			
			
			public void updateComodidadExtra (ComodidadExtra comExtra) throws SQLException, Exception
			{
				StringBuilder sql = new StringBuilder();
				sql.append(String.format("UPDATE %s.COMODIDADES_EXTRA SET ", USUARIO));
				
				sql.append(String.format("VALOR = '%1$s'", 
						comExtra.getValor()));
				
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
			public ComodidadExtra convertResultSetToComodidadExtra(ResultSet resultSet) throws SQLException 
			{
			
				String pNombre = resultSet.getString("NOMBRE");
				Double pValor = resultSet.getDouble("VALOR");
				
				// DUDA, CREAR LA HABITACION PARA ASIGNARLA? O AGREGAR EN TM
				
				ComodidadExtra comExtra = new ComodidadExtra(pNombre, pValor);
				
				return comExtra;
			}
	
	
	
	

}
