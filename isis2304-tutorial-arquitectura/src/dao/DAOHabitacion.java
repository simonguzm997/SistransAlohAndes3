package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vos.Habitacion;



public class DAOHabitacion 
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
	 * Metodo constructor de la clase DAOCliente <br/>
	*/
	
	public   DAOHabitacion() 
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
	public ArrayList<Habitacion> getHabitaciones() throws SQLException, Exception 
	{
		ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();

		String sql = String.format("SELECT * FROM %1$s.HABITACIONES ", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			habitaciones.add(convertResultSetToHabitacion(rs));
		}
		return habitaciones;
	}
	
	
	public Habitacion findHabitacionById(long id) throws SQLException, Exception 
	{
		Habitacion habitacion = null;

		String sql = String.format("SELECT * FROM %1$s.HABITACIONES WHERE ID = %2$d ", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) 
		{
			habitacion = convertResultSetToHabitacion(rs);
		}

		return habitacion;
	}
	
	
	public ArrayList<Habitacion> getHabitacionesByIdAlojamiento(long id) throws SQLException, Exception 
	{
		ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();

		String sql = String.format("SELECT * FROM %1$s.HABITACIONES WHERE ID = %2$d ", USUARIO, id); 
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			habitaciones.add(convertResultSetToHabitacion(rs));
		}
		return habitaciones;
	}
	
	
	public void addHabitacion(Habitacion habitacion) throws SQLException, Exception 
	{

		String sql = String.format("INSERT INTO %1$s.HABITACIONES (ID, TIPO, PRECIOBASEDIA, CAPACIDAD, NUMERO, ESTADO, "
				+ " IDALOJAMIENTO ) "
				+ " VALUES ( %2$s , '%3$s' , %4$s , %5$s , %6$s , '%7$s' , %8$s) ", 
									USUARIO, 
									habitacion.getId(),
									habitacion.getTipo(),
									habitacion.getPrecioBaseDia(),
									habitacion.getCapacidad(),
									habitacion.getNumero(),
									habitacion.getEstado(),
									habitacion.getIdAlojamiento());
		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	
	public void updateHabitacion (Habitacion habitacion) throws SQLException, Exception
	{
		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.HABITACIONES  ", USUARIO));
		
		sql.append(String.format(" SET TIPO = '%1$s' , PRECIOBASEDIA = %2$s, CAPACIDAD = %3$s , "
				+ "NUMERO = %4$s , ESTADO = '%5$s' , "
				+ "IDALOJAMIENTO = %6$s  ",
				
				habitacion.getTipo(),
				habitacion.getPrecioBaseDia(),
				habitacion.getCapacidad(),
				habitacion.getNumero(),
				habitacion.getEstado(),
				habitacion.getIdAlojamiento()));
		sql.append (" WHERE ID = " + habitacion.getId ());
		
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}
	
	public void habilitarHabitacion (Habitacion habitacion) throws SQLException, Exception
	{
		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.HABITACIONES  ", USUARIO));
		
		sql.append(String.format(" SET TIPO = '%1$s' , PRECIOBASEDIA = %2$s, CAPACIDAD = %3$s , "
				+ "NUMERO = %4$s , ESTADO = 'DISPONIBLE' , "
				+ "IDALOJAMIENTO = %6$s  ",
				
				habitacion.getTipo(),
				habitacion.getPrecioBaseDia(),
				habitacion.getCapacidad(),
				habitacion.getNumero(),
				habitacion.getEstado(),
				habitacion.getIdAlojamiento()));
		sql.append (" WHERE ID = " + habitacion.getId ());
		
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}
	

	public void deleteHabitacion(Habitacion habitacion) throws SQLException, Exception
	{

		String sql = String.format("DELETE FROM %1$s.HABITACIONES WHERE ID = %2$d", USUARIO, habitacion.getId());

		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	
	public List<Habitacion> getHabitacionesTop20() throws SQLException, Exception 
	{
		ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();

		String sql = String.format(" select * from habitaciones  " + 
				"				inner join (select IDHABITACION " + 
				"				FROM RESERVAS " + 
				"				GROUP BY IDHABITACION " + 
				"				ORDER BY COUNT (IDHABITACION) DESC) B " + 
				"				ON habitaciones.ID = B.IDHABITACION " + 
				"				WHERE ROWNUM <=20 ", USUARIO);
	
			
//		select * from habitaciones 
//		inner join (select IDHABITACION
//		FROM RESERVAS
//		GROUP BY IDHABITACION
//		ORDER BY COUNT (IDHABITACION) DESC) B
//		ON habitaciones.ID = B.IDHABITACION
//		WHERE ROWNUM <=20;


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			habitaciones.add(convertResultSetToHabitacion(rs));
		}
		return habitaciones;
		
		
		
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
	public Habitacion convertResultSetToHabitacion(ResultSet resultSet) throws SQLException 
	{
		
		
		long pId  = resultSet.getInt("ID");
		String pTipo = resultSet.getString("TIPO");
		double pPrecioBaseDia = resultSet.getDouble("PRECIOBASEDIA");
		int pCapacidad = resultSet.getInt("CAPACIDAD");
		int pNumero = resultSet.getInt("NUMERO");
		String pEstado = resultSet.getString("ESTADO");
		long pIdAlojamiento  = resultSet.getInt("IDALOJAMIENTO");
		
		Habitacion r = new Habitacion(pId, pTipo, pPrecioBaseDia, pCapacidad, pNumero, pEstado, pIdAlojamiento);
		return r;
	
	}


	
	
}
