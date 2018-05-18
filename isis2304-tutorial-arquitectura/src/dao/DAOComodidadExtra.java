package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ComodidadExtra;

public class DAOComodidadExtra 
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
	
	public  DAOComodidadExtra() 
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
	public ArrayList<ComodidadExtra> getComodidadExtraes() throws SQLException, Exception 
	{
		ArrayList<ComodidadExtra> comodidadExtraes = new ArrayList<ComodidadExtra>();

		String sql = String.format("SELECT * FROM %1$s.COMODIDADESEXTRA ", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			comodidadExtraes.add(convertResultSetToComodidadExtra(rs));
		}
		return comodidadExtraes;
	}
	
	
	public ComodidadExtra findComodidadExtraById(long id) throws SQLException, Exception 
	{
		ComodidadExtra comodidadExtra = null;

		String sql = String.format("SELECT * FROM %1$s.COMODIDADESEXTRA WHERE ID = %2$d ", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) 
		{
			comodidadExtra = convertResultSetToComodidadExtra(rs);
		}

		return comodidadExtra;
	}
	
	public void addComodidadExtra(ComodidadExtra comodidadExtra) throws SQLException, Exception 
	{

		String sql = String.format("INSERT INTO %1$s.COMODIDADESEXTRA (ID, NOMBRE, COSTO, DESCRIPCION, IDHABITACION ) "
				+ " VALUES ( %2$s , '%3$s' , '%4$s' , '%5$s' , '%6$s' ) ", 
									USUARIO, 
									comodidadExtra.getId(),
									comodidadExtra.getNombre(),
									comodidadExtra.getCosto(),
									comodidadExtra.getDescripcion(),
									comodidadExtra.getIdHabitacion());
		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	
	public void updateComodidadExtra (ComodidadExtra comodidadExtra) throws SQLException, Exception
	{
		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.HABITACIONES  ", USUARIO));
		
		sql.append(String.format(" SET NOMBRE = '%1$s' , COSTO = '%2$s', DESCRIPCION = '%3$s' , "
				+ "NUMERO = '%4$s' , ESTADO = '%5$s' , "
				+ "IDHABITACION = %6$s  ",
								
				comodidadExtra.getNombre(),
				comodidadExtra.getCosto(),
				comodidadExtra.getDescripcion(),
				comodidadExtra.getIdHabitacion()));
		sql.append (" WHERE ID = " + comodidadExtra.getId ());
		
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}
	

	public void deleteComodidadExtra(ComodidadExtra comodidadExtra) throws SQLException, Exception
	{

		String sql = String.format("DELETE FROM %1$s.HABITACIONES WHERE ID = %2$d", USUARIO, comodidadExtra.getId());

		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
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
	
		long pId  = resultSet.getInt("ID");
		String pNombre = resultSet.getString("NOMBRE");
		double pPrecioBaseDia = resultSet.getDouble("COSTO");
		String pDescripcion = resultSet.getString("DESCRIPCION");
		long pIdHabitacion  = resultSet.getInt("IDHABITACION");
		
		ComodidadExtra r = new ComodidadExtra(pId, pNombre, pPrecioBaseDia, pDescripcion, pIdHabitacion);
		return r;
	
	}
	
	
	

}
