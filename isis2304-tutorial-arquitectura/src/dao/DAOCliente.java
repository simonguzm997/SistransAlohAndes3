package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.codehaus.jackson.node.ArrayNode;

import vos.Cliente;

public class DAOCliente {

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
	public DAOCliente() {
		recursos = new ArrayList<Object>();
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE COMUNICACION CON LA BASE DE DATOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo que obtiene la informacion de todos los bebedores en la Base de Datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * @return	lista con la informacion de todos los bebedores que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<Cliente> getClientes() throws SQLException, Exception {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
		String sql = String.format("SELECT * FROM CLIENTES WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			clientes.add(convertResultSetToCliente(rs));
		}
		return clientes;
	}
	

	/**
	 * Metodo que obtiene la informacion del bebedor en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
	 * @param id el identificador del bebedor
	 * @return la informacion del bebedor que cumple con los criterios de la sentecia SQL
	 * 			Null si no existe el bebedor conlos criterios establecidos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public Cliente findClienteById(Long id) throws SQLException, Exception 
	{
		Cliente cliente = null;

		String sql = String.format("SELECT * FROM %1$s.CLIENTES WHERE ID = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			cliente = convertResultSetToCliente(rs);
		}


		return cliente;
	}
	
	/**
	 * Metodo que obtiene la informacion de todos los bebedores en la Base de Datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * @return	lista con la informacion de todos los bebedores que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<Cliente> getClientesFrecuentes() throws SQLException, Exception {
		ArrayList<Cliente> ClientesFrecuentes = new ArrayList<Cliente>();

		String sql = " SELECT * FROM " +
				" (select a.IDcliente,cantidad from " +
				" (select IDCliente , count (idAlojamiento) as cantidad from " +
				" (select IdCliente, IDHABITACION " +
				" from reservas " +
				" group by  (IDCLIENTE, IDHABITACION) " +
				" ) inner join " +
				" habitaciones on habitaciones.id = idhabitacion " +
				" group by idCliente) A inner join " +
				" (select MAX (fecha) as Max, idcliente from " +
				" (SELECT (to_date(FECHAFIN, 'dd/mm/yyyy') - to_date(FECHAINICIO, 'dd/mm/yyyy') ) AS FECHA, idcliente " +
				"     FROM RESERVAS) " +
				"     group by idcliente) B " +
				"     on A.IDcliente = B.Idcliente) C INNER JOIN CLIENTES " +
				"     ON C.IDCLIENTE = CLIENTES.ID where cantidad>=3";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			ClientesFrecuentes.add(convertResultSetToCliente(rs));
		}
		return ClientesFrecuentes;
	}
	
	
	public ArrayList<Cliente> getClientesRFC10(long idAlojamiento, String fecha1, String fecha2, String orderby) throws SQLException, Exception {
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		String sql = String.format(" SELECT %1$s.CLIENTES.*  "
				+ " FROM (select * from ( "
				+ "select * from %1$s.habitaciones " + 
				" where idAlojamiento = %2$d ) B "
				+ "inner join %1$s.reservas "
				+ " on b.id = %1$s.reservas.IDHABITACION "
				+ " WHERE FECHAINICIO > to_date('%3$s','DD-MON-YY') "
				+ " AND FECHAFIN < to_date('%4$s','DD-MON-YY')) C "
				+ " INNER JOIN %1$s.CLIENTES "
				+ " ON C.IDCLIENTE= %1$s.CLIENTES.ID "
				+ " ORDER BY %5$s ",
				
				USUARIO, idAlojamiento, fecha1, fecha2, orderby);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			clientes.add(convertResultSetToCliente(rs));
		}
		return clientes;
	}
	
	
	public ArrayList<Cliente> getClientesRFC11(long idAlojamiento, String fecha1, String fecha2, String orderby) throws SQLException, Exception {
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		String sql = String.format(" SELECT %1$s.CLIENTES.*  "
				+ " FROM (select * from ( "
				+ "select * from %1$s.habitaciones " + 
				" where idAlojamiento = %2$d ) B "
				+ "inner join %1$s.reservas "
				+ " on b.id = %1$s.reservas.IDHABITACION "
				+ " WHERE FECHAFIN < to_date('%3$s','DD-MON-YY') "
				+ " OR FECHAINICIO > to_date('%4$s','DD-MON-YY')) C "
				+ " INNER JOIN %1$s.CLIENTES "
				+ " ON C.IDCLIENTE= %1$s.CLIENTES.ID "
				+ " ORDER BY %5$s ",
				
				
				USUARIO, idAlojamiento, fecha1, fecha2, orderby);
		
		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			clientes.add(convertResultSetToCliente(rs));
		}
		return clientes;
	}
	
	
	
	
	
	
	
	/**
	 * Metodo que obtiene la informacion de todos los bebedores en la Base de Datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * @return	lista con la informacion de todos los bebedores que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public Cliente getDineroCliente(Cliente cli) throws SQLException, Exception {
		String sql1 = " SELECT SUM (FECHA) AS NOCHESQUEDADAS FROM ( " +
				" SELECT (to_date(FECHAFIN, 'dd/mm/yyyy') - to_date(FECHAINICIO, 'dd/mm/yyyy') ) AS FECHA,IDCLIENTE "  +
				"     FROM RESERVAS where RESERVAS.IDCLIENTE= "+cli.getId()+ " ) ";

		PreparedStatement prepStmt = conn.prepareStatement(sql1);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while(rs.next()) {
			cli.setDiasUsado(rs.getInt("NOCHESQUEDADAS"));

		}
		return cli;
	}
	
	/**
	 * Metodo que agregar la informacion de un nuevo bebedor en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param cliente Bebedor que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addCliente(Cliente cliente) throws SQLException, Exception {

		String sql = String.format("INSERT INTO %1$s.CLIENTES (ID, NOMBRE, USUARIO, CONTRASENA, CORREOELECTRONICO,NUMEROCONTACTO,RELACIONUNIVERSIDAD) VALUES (%2$s, '%3$s', '%4$s', '%5$s', '%6$s', %7$s ,'%8$s')", 
									USUARIO, 
									cliente.getId(), 
									cliente.getNombre(),
									cliente.getUsuario(), 
									cliente.getContrasena(),
									cliente.getCorreoElectronico(),
									cliente.getNumeroContacto(),
									cliente.getRelacionUniversidad());
		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que actualiza la informacion del bebedor en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param cliente Bebedor que desea actualizar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void updateCliente(Cliente cliente) throws SQLException, Exception {

		StringBuilder sql = new StringBuilder();
		sql.append (String.format ("UPDATE %s.CLIENTES ", USUARIO));
		sql.append (String.format (
				"SET NOMBRE = '%1$s', USUARIO = '%2$s', CONTRASENA = '%3$s', CORREOELECTRONICO = '%4$s', NUMEROCONTACTO =%5$s,  RELACIONUNIVERSIDAD='%6$s'  ",
				cliente.getNombre (), cliente.getUsuario(),
				cliente.getContrasena (), cliente.getCorreoElectronico(), cliente.getNumeroContacto(),cliente.getRelacionUniversidad()));
		sql.append ("WHERE ID = " + cliente.getId ());
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que actualiza la informacion del bebedor en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param cliente Bebedor que desea actualizar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void deleteCliente(Cliente cliente) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.CLIENTES WHERE ID = %2$d", USUARIO, cliente.getId());

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
	public void setConn(Connection connection){
		this.conn = connection;
	}
	
	/**
	 * Metodo que cierra todos los recursos que se encuentran en el arreglo de recursos<br/>
	 * <b>Postcondicion: </b> Todos los recurso del arreglo de recursos han sido cerrados.
	 */
	public void cerrarRecursos() {
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
	 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla BEBEDORES) en una instancia de la clase Bebedor.
	 * @param resultSet ResultSet con la informacion de un bebedor que se obtuvo de la base de datos.
	 * @return Bebedor cuyos atributos corresponden a los valores asociados a un registro particular de la tabla BEBEDORES.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public Cliente convertResultSetToCliente(ResultSet resultSet) throws SQLException {
		long Id = resultSet.getLong("ID");
		String Nombre = resultSet.getString("NOMBRE");
		String Usuario = resultSet.getString("USUARIO");
		String Contrasena = resultSet.getString("CONTRASENA");
		String CorreoElectronico = resultSet.getString("CORREOELECTRONICO");
		long NumeroContacto = resultSet.getLong("NUMEROCONTACTO");
		String RelacionUniversidad = resultSet.getString("RELACIONUNIVERSIDAD");
		
		Cliente cli = new Cliente(Id, Nombre, Usuario, Contrasena, CorreoElectronico, NumeroContacto, RelacionUniversidad);
		return cli;
	}
}
