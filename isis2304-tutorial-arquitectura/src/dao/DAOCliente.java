package dao;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.*;


public class DAOCliente 
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
	 * Metodo constructor de la clase DAOBebedor <br/>
	*/
	public DAOCliente() 
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
	public ArrayList<Cliente> getClientes() throws SQLException, Exception 
	{
		
		System.out.println("------ENTRO  A GET ALL DAO");
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
		String sql = String.format("SELECT * FROM %1$s.CLIENTE ", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		System.out.println("prepstm sys" + prepStmt);
		
		System.out.println("rs sysout" + rs);
		
		
		
		while (rs.next()) 
		{
			clientes.add(convertResultSetToCliente(rs));
		}
		
		System.out.println("------SALGO  A GET ALL DAO");
		
		return clientes;
	}
	
	public Cliente findClienteById(Integer id) throws SQLException, Exception 
	{
		Cliente cliente = null;

		String sql = String.format("SELECT * FROM %1$s.CLIENTE WHERE ID = %2$d ", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) 
		{
			cliente = convertResultSetToCliente(rs);
		}

		return cliente;
	}
	
	public void addCliente(Cliente cliente) throws SQLException, Exception 
	{

		String sql = String.format("INSERT INTO %1$s.CLIENTE (ID, USUARIO, NOMBRE, CORREO_ELECTRONICO, CONTRASENIA, NUMERO_CONTACTO) "
				+ "VALUES ( %2$s , '%3$s' , '%4$s' , '%5$s' , '%6$s' , '%7$s' ) ", 
									USUARIO, 
									cliente.getId(),
									cliente.getUsuario(),
									cliente.getNombre(),
									cliente.getCorreoElectronico(),
									cliente.getContrasena(),
									cliente.getNumeroContacto());
		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	public void deleteCliente(Cliente cliente) throws SQLException, Exception
	{

		String sql = String.format("DELETE FROM %1$s.CLIENTE WHERE ID = %2$d", USUARIO, cliente.getId());

		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void updateCliente (Cliente cliente) throws SQLException, Exception
	{
		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.CLIENTE SET ", USUARIO));
		
		sql.append(String.format("USUARIO = '%1$s' AND NOMBRE = '%2$s' AND CORREO_ELECTRONICO = '%3$s' "
				+ "AND CONTRASENIA = '%4$s' AND NUMERO_CONTACTO = '%5$s'", 
				cliente.getUsuario(),
				cliente.getNombre(),
				cliente.getCorreoElectronico(),
				cliente.getContrasena(),
				cliente.getNumeroContacto()));
		
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
	public Cliente convertResultSetToCliente(ResultSet resultSet) throws SQLException 
	{
		System.out.println("------ENTRE A CONVERT RESULTSET");
		
		int pId  = resultSet.getInt("ID");
		System.out.println( pId);
		String pNombre = resultSet.getString("NOMBRE");
		System.out.println(pNombre);
		String pUsuario = resultSet.getString("USUARIO");
		System.out.println(pUsuario);
		String pContrasena = resultSet.getString("CONTRASENIA");
		System.out.println(pContrasena);
		String pCorreoElectronico = resultSet.getString("CORREO_ELECTRONICO");
		System.out.println(pCorreoElectronico);
		long pNumeroContacto  = resultSet.getLong("NUMERO_CONTACTO");
		System.out.println(pNumeroContacto);

		Cliente cliente = new Cliente(pId, pNombre, pUsuario, pContrasena, pCorreoElectronico, pNumeroContacto);
		
		System.out.println("------SALGO A CONVERT RESULTSET");
		
		
		return cliente;
		
		
	}
	
	

}
