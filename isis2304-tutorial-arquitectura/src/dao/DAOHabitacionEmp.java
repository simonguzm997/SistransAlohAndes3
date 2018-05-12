package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.HabitacionEmp;

public class DAOHabitacionEmp 
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
			public DAOHabitacionEmp() 
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
			public ArrayList<HabitacionEmp> getHabitacionesEmp() throws SQLException, Exception 
			{
				ArrayList<HabitacionEmp> habitaciones = new ArrayList<HabitacionEmp>();

				String sql = String.format("SELECT * FROM %1$s.HABITACIONES_EMP ", USUARIO);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) 
				{
					habitaciones.add(convertResultSetToHabitacionEmp(rs));
				}
				return habitaciones;
			}
			
			
			public ArrayList<HabitacionEmp> getHabitacionesEmpTop20() throws SQLException, Exception 
			{
				ArrayList<HabitacionEmp> habitaciones = new ArrayList<HabitacionEmp>();
				

				String sql = String.format("SELECT * FROM %1$s.HABITACIONES_EMP INNER JOIN "
						+ "(SELECT ID_HABITACION "
						+ "FROM RESERVAS "
						+ "GROUP BY ID_HABITACION "
						+ "ORDER BY COUNT(ID_HABITACION) DESC) B "
						+ "ON HABITACIONES_EMP.ID = B.ID_HABITACION "
						+ "WHERE ROWNUM <=20 ", USUARIO);
				
//				SELECT * FROM HABITACIONES_EMP INNER JOIN
//				(SELECT ID_HABITACION
//				FROM RESERVAS
//				GROUP BY ID_HABITACION
//				ORDER BY COUNT(ID_HABITACION) DESC) B
//				ON HABITACIONES_EMP.ID = B.ID_HABITACION
//				WHERE ROWNUM <=20;

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) 
				{
					habitaciones.add(convertResultSetToHabitacionEmp(rs));
				}
				return habitaciones;
			}
			
			public ArrayList<HabitacionEmp> getHabitacionesEmpIntTiempo(String fInicio, String fFin) throws SQLException, Exception 
			{
				ArrayList<HabitacionEmp> habitaciones = new ArrayList<HabitacionEmp>();
				

				String sql = String.format("SELECT * FROM %1$s.HABITACIONES_EMP "
						+ "WHERE ID NOT IN "
						+ "(SELECT ID_HABITACION FROM RESERVAS "
						+ "WHERE FECHA_INICIO > to_date('%2$s','DD-MON-YY')  "
						+ "AND FECHA_FIN < to_date('%3$s','DD-MON-YY')) "
						+ "AND DISPONIBILIDAD = 'SI' ", 
						USUARIO, fInicio, fFin );
		
				
//				SELECT * FROM HABITACIONES_EMP
//				WHERE ID NOT IN
//				(SELECT ID_HABITACION FROM RESERVAS
//				WHERE FECHA_INICIO > to_date('25-FEB-18','DD-MON-YY')
//				AND FECHA_FIN < to_date('21-APR-18','DD-MON-YY'))
//				AND DISPONIBILIDAD = 'SI';

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) 
				{
					habitaciones.add(convertResultSetToHabitacionEmp(rs));
				}
				return habitaciones;
			}
			
			
			
			public HabitacionEmp findHabitacionEmpById(int id) throws SQLException, Exception 
			{
				HabitacionEmp habEmp = null;

				String sql = String.format("SELECT * FROM %1$s.HABITACIONES_EMP WHERE ID = %2$d ", USUARIO, id); 

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				if(rs.next()) 
				{
					habEmp = convertResultSetToHabitacionEmp(rs);
				}

				return habEmp;
			}
			
			
			
			public void addHabitacionEmp(HabitacionEmp habEmp) throws SQLException, Exception 
			{
				
				String sql = String.format("INSERT INTO %1$s.HABITACIONES_EMP (ID, TIPO, PRECIO_BASE_DIA, CAPACIDAD, NUMERO ) "
						+ "VALUES (%2$s, '%3$s', '%4$s','%5$s', '%6$s')", 
											USUARIO, 
											habEmp.getId(),
											habEmp.getTipo(),
											habEmp.getPrecioBaseDia(),
											habEmp.getCapacidad(),
											habEmp.getNumero());
				System.out.println(sql);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();

			}
			
			
			public void deleteHabitacionEmp(HabitacionEmp habEmp) throws SQLException, Exception
			{


				String sql = String.format("DELETE FROM %1$s.HABITACIONES_EMP WHERE ID = %2$d", USUARIO, habEmp.getId());

				System.out.println(sql);
				
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}
			
			
			public void updateHabitacionEmp(HabitacionEmp habEmp) throws SQLException, Exception
			{
				StringBuilder sql = new StringBuilder();
				sql.append(String.format("UPDATE %s.HABITACIONES_EMP SET ", USUARIO));
				
				sql.append(String.format("TIPO = '%1$s' AND PRECIO_BASE_DIA = '%2$s' "
						+ "AND CAPACIDAD = '%3$s' AND NUMERO = '%4$s'",
						habEmp.getTipo(),
						habEmp.getPrecioBaseDia(),
						habEmp.getCapacidad(),
						habEmp.getNumero()));
				
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
			public HabitacionEmp convertResultSetToHabitacionEmp(ResultSet resultSet) throws SQLException 
			{
			
				
				int pId  = resultSet.getInt("ID");
				String pTipo = resultSet.getString("TIPO");
				double pPrecioBaseDia = resultSet.getDouble("PRECIO_BASE_DIA");
				int pCapacidad  = resultSet.getInt("CAPACIDAD");
				int pNumero  = resultSet.getInt("NUMERO");

				HabitacionEmp habEmp = new HabitacionEmp(pId, pTipo, pPrecioBaseDia, pCapacidad, pNumero);
				
				return habEmp;
			}
	
	
	
	

}
