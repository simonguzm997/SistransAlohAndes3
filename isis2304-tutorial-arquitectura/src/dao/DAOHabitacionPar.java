package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.HabitacionPar;

public class DAOHabitacionPar 

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
			public DAOHabitacionPar() 
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
			public ArrayList<HabitacionPar> getHabitacionesPar() throws SQLException, Exception 
			{
				ArrayList<HabitacionPar> habitaciones = new ArrayList<HabitacionPar>();

				String sql = String.format("SELECT * FROM %1$s.HABITACION_PAR ", USUARIO);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) 
				{
					habitaciones.add(convertResultSetToHabitacionPar(rs));
				}
				return habitaciones;
			}
			
			
			public ArrayList<HabitacionPar> getHabitacionesParTop20() throws SQLException, Exception 
			{
				ArrayList<HabitacionPar> habitaciones = new ArrayList<HabitacionPar>();

				String sql = String.format("SELECT * FROM %1$s.HABITACION_PAR INNER JOIN "
						+ "(SELECT ID_HABITACION "
						+ "FROM %1$s.CONTRATO_HABITACION "
						+ "GROUP BY ID_HABITACION "
						+ "ORDER BY COUNT(ID_HABITACION) DESC) B "
						+ "ON %1$s.HABITACION_PAR.ID = B.ID_HABITACION "
						+ "WHERE ROWNUM <=20 ", USUARIO);
				
				
//				SELECT * FROM HABITACION_PAR INNER JOIN
//				(SELECT ID_HABITACION
//				FROM CONTRATO_HABITACION
//				GROUP BY ID_HABITACION
//				ORDER BY COUNT(ID_HABITACION) DESC) B
//				ON HABITACION_PAR.ID = B.ID_HABITACION
//				WHERE ROWNUM <=20;

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) 
				{
					habitaciones.add(convertResultSetToHabitacionPar(rs));
				}
				return habitaciones;
			}
			
			public HabitacionPar findHabitacionParById(int id) throws SQLException, Exception 
			{
				HabitacionPar habPar = null;

				String sql = String.format("SELECT * FROM %1$s.HABITACION_PAR WHERE ID = %2$d ", USUARIO, id); 

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				if(rs.next()) 
				{
					habPar = convertResultSetToHabitacionPar(rs);
				}

				return habPar;
			}
			
			
			
			
			
			public void addHabitacionPar(HabitacionPar habPar) throws SQLException, Exception 
			{
				
				String sql = String.format("INSERT INTO %1$s.HABITACION_PAR (ID, MENAJE, TIPO, PRECIO_MENSUAL, ID_OPERADOR ) "
						+ "VALUES (%2$s, '%3$s', '%4$s','%5$s', '%6$s')", 
											USUARIO, 
											habPar.getId(),
											habPar.getMenaje(),
											habPar.getTipo(),
											habPar.getPrecioMensual(),
											habPar.getOperadorHabPar().getId());
				System.out.println(sql);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();

			}
			
			
			public void deleteHabitacionPar(HabitacionPar habPar) throws SQLException, Exception
			{


				String sql = String.format("DELETE FROM %1$s.HABITACION_PAR WHERE ID = %2$d", USUARIO, habPar.getId());

				System.out.println(sql);
				
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}
			
			
			public void updateHabitacionPar(HabitacionPar habPar) throws SQLException, Exception
			{
				StringBuilder sql = new StringBuilder();
				sql.append(String.format("UPDATE %s.HABITACION_PAR SET ", USUARIO));
				
				sql.append(String.format("MENAJE = '%1$s' AND TIPO = '%2$s' "
						+ "AND PRECIO_MENSUAL = '%3$s' AND ID_OPERADOR = '%4$s'",
						habPar.getMenaje(),
						habPar.getTipo(),
						habPar.getPrecioMensual(),
						habPar.getOperadorHabPar().getId()));
				
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
			public HabitacionPar convertResultSetToHabitacionPar(ResultSet resultSet) throws SQLException 
			{
			
				
				int pId  = resultSet.getInt("ID");
				String pMenaje = resultSet.getString("MENAJE");
				double pPrecioMensual = resultSet.getDouble("PRECIO_MENSUAL");
				String pTipo = resultSet.getString("TIPO");
			

				HabitacionPar habPar= new HabitacionPar(pId, pMenaje, pPrecioMensual, pTipo);
				
				return habPar;
			}
	
	
	
	
	
}
