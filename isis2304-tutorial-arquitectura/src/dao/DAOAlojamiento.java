package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Alojamiento;

public class DAOAlojamiento 
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
		
		public DAOAlojamiento() 
		{
			
			recursos = new ArrayList<Object>();
		}

		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE COMUNICACION CON LA BASE DE DATOS
		//----------------------------------------------------------------------------------------------------------------------------------

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
		public ArrayList<Alojamiento> getAlojamientos() throws SQLException, Exception {
			ArrayList<Alojamiento> alojas = new ArrayList<Alojamiento>();

			//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			String sql = String.format("SELECT * FROM ALOJAMIENTOS WHERE ROWNUM <= 50");

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				alojas.add(convertResultSetToAlojamiento(rs));
			}
			return alojas;
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
		public Alojamiento findAlojamientoById(Long id) throws SQLException, Exception 
		{
			Alojamiento aloja = null;

			String sql = String.format("SELECT * FROM %1$s.ALOJAMIENTOS WHERE ID = %2$d", USUARIO, id); 

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();
			if(rs.next()) {
				aloja = convertResultSetToAlojamiento(rs);
			}
			return aloja;
		}
		
		public Alojamiento findAlojamientoByIdOperador(Long id) throws SQLException, Exception 
		{
			Alojamiento aloja = null;

			String sql = String.format("SELECT * FROM %1$s.ALOJAMIENTOS WHERE IDOPERADOR = %2$d", USUARIO, id); 

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();
			if(rs.next()) {
				aloja = convertResultSetToAlojamiento(rs);
			}
			return aloja;
		}
		
		/**
		 * Metodo que agregar la informacion de un nuevo bebedor en la Base de Datos a partir del parametro ingresado<br/>
		 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
		 * @param bebedor Bebedor que desea agregar a la Base de Datos
		 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
		 * @throws Exception Si se genera un error dentro del metodo.
		 */
		public void addAlojamiento(Alojamiento aloja) throws SQLException, Exception {

			String sql = String.format("INSERT INTO %1$s.ALOJAMIENTOS (ID, NOMBRE, DIRECCION,CALIFICACION,HORAAPERTURA,HORACIERRE,NIT,CAPACIDAD,TIPO,ESTADO,DIASUSADOS,DESCRIPCIONSEGURO,VALORSEGURO,NUMHABITACIONES,MENAJE,IDOPERADOR) VALUES (%2$s, '%3$s', '%4$s', %5$s,'%6$s','%7$s',%8$s,%9$s,'%10$s','%11$s',%12$s,'%13$s',%14$s,%15$s,'%16$s',%17$s)", 
										USUARIO,
										aloja.getId(),
										aloja.getNombre(),
										aloja.getDireccion(),
										aloja.getCalificacion(),
										aloja.getHoraApertura(),
										aloja.getHoraCierre(),
										aloja.getNIT(),
										aloja.getCapacidad(),
										aloja.getTipo(),
										aloja.getEstado(),
										aloja.getDiasUsados(),
										aloja.getDescripcionSeguro(),
										aloja.getValorSeguro(),
										aloja.getNumHabitaciones(),
										aloja.getMenaje(),
										aloja.getIdOperador());			
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();

		}
		/**
		 * Metodo que actualiza la informacion del bebedor en la Base de Datos que tiene el identificador dado por parametro<br/>
		 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
		 * @param bebedor Bebedor que desea actualizar a la Base de Datos
		 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
		 * @throws Exception Si se genera un error dentro del metodo.
		 */
		public void updateAlojamiento(Alojamiento aloja) throws SQLException, Exception {

			StringBuilder sql = new StringBuilder();
			sql.append (String.format ("UPDATE %s.ALOJAMIENTOS ", USUARIO));
			sql.append (String.format (
					"SET NOMBRE = '%1$s', DIRECCION = '%2$s', CALIFICACION = %3$s, HORAAPERTURA =  '%4$s',HORACIERRE = '%5$s' , NIT = %6$s , CAPACIDAD = %7$s , TIPO = '%8$s' , ESTADO  = '%9$s' , DIASUSADOS = %10$s , DESCRIPCIONSEGURO = '%11$s' , VALORSEGURO =  %12$s, NUMHABITACIONES = %13$s ,  MENAJE = '%14$s'  , IDOPERADOR = %15$s  ",
					aloja.getNombre(),aloja.getDireccion(),aloja.getCalificacion(),aloja.getHoraApertura(),aloja.getHoraCierre(),aloja.getNIT(),aloja.getCapacidad(),aloja.getTipo(),aloja.getEstado(),aloja.getDiasUsados(),aloja.getDescripcionSeguro(),aloja.getValorSeguro(),aloja.getNumHabitaciones(),aloja.getMenaje(),aloja.getIdOperador()));
			sql.append ("WHERE ID = " + aloja.getId ());
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}

		
		/**
		 * Metodo que actualiza la informacion del bebedor en la Base de Datos que tiene el identificador dado por parametro<br/>
		 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
		 * @param bebedor Bebedor que desea actualizar a la Base de Datos
		 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
		 * @throws Exception Si se genera un error dentro del metodo.
		 */
		public void deleteAlojamiento(Alojamiento aloja) throws SQLException, Exception {

			String sql = String.format("DELETE FROM %1$s.ALOJAMIENTOS WHERE ID = %2$d", USUARIO, aloja.getId());
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
		public Alojamiento convertResultSetToAlojamiento(ResultSet resultSet) throws SQLException 
		{
			int id = resultSet.getInt("ID");
			long NIT = resultSet.getLong("NIT");
			String nombre = resultSet.getString("NOMBRE");
			String direccion = resultSet.getString("DIRECCION");
			double calificacion = resultSet.getDouble("CALIFICACION");
			String horaApertura = resultSet.getString("HORAAPERTURA");
			String horaCierre = resultSet.getString("HORACIERRE");
			String tipo = resultSet.getString("TIPO");
			int capacidad  = resultSet.getInt("CAPACIDAD");
			String estado = resultSet.getString("ESTADO");
			int diasUsados = resultSet.getInt("DIASUSADOS");
			String descripcionSeguro = resultSet.getString("DESCRIPCIONSEGURO");
			double valorSeguro = resultSet.getDouble("VALORSEGURO");
			int numHabitaciones = resultSet.getInt("NUMHABITACIONES");
			String menaje = resultSet.getString("MENAJE");
			long idOperador = resultSet.getLong("IDOPERADOR");
		
			Alojamiento aloja = new Alojamiento(id, NIT, nombre, direccion, calificacion, horaApertura, horaCierre, capacidad, tipo, estado, diasUsados, descripcionSeguro, valorSeguro, numHabitaciones, menaje, idOperador);
			return aloja;
		
		}
		
		
		

}
