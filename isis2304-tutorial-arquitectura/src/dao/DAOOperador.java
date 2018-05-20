package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Operador;

public class DAOOperador 
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
		 * Metodo constructor de la clase DAOOperador <br/>
		*/
		
		public DAOOperador() 
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
		public ArrayList<Operador> getOperadores() throws SQLException, Exception 
		{
			ArrayList<Operador> operadores = new ArrayList<Operador>();

			String sql = String.format("SELECT * FROM %1$s.OPERADORES ", USUARIO);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				operadores.add(convertResultSetToOperador(rs));
			}
			return operadores;
		}
		
		
		public Operador findOperadorById(long id) throws SQLException, Exception 
		{
			Operador operador = null;

			String sql = String.format("SELECT * FROM %1$s.OPERADORES WHERE ID = %2$d ", USUARIO, id); 

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.next()) 
			{
				operador = convertResultSetToOperador(rs);
			}

			return operador;
		}
		
		public Operador findMejorOperadorPorSemana(int i) throws SQLException, Exception 
		{
			Operador operador = null;
			String sql = " SELECT * FROM OPERADORES WHERE ID=( " +
					" SELECT IDOPERADOR FROM ( " +
					" SELECT MAX (OCUPACION) MAXIMO, IDOPERADOR FROM( " +
					" SELECT COUNT (IDOPERADOR) OCUPACION, IDOPERADOR  FROM ( " +
					" select to_char(FECHAINICIO, 'WW')semana , RESERVAS.ID IDRESERVA ,VALOR, IDHABITACION IDHABITACION,ALOJAMIENTOS.IDOPERADOR IDOPERADOR " +
					" from RESERVAS " +
					" INNER JOIN HABITACIONES " +
					" ON HABITACIONES.ID=reservas.IDHABITACION " +
					" INNER JOIN ALOJAMIENTOS " +
					" ON ALOJAMIENTOS.ID=HABITACIONES.IDALOJAMIENTO) " +
					" WHERE SEMANA = "+i +
					" group by IDOPERADOR ORDER BY OCUPACION DESC) " +
					" group by IDOPERADOR " +
					" ORDER BY MAXIMO DESC) " +
					" WHERE ROWNUM <=1) ";
							
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.next()) 
			{
				operador = convertResultSetToOperador(rs);
			}

			return operador;
		}
		
		
		public ArrayList<Operador> getOperadoresByDinero () throws SQLException
		{

			ArrayList<Operador> operadores = new ArrayList<Operador>();

			String sql = String.format("SELECT ID,NOMBRE,DINEROANOACTUAL,DINEROANORECORRIDO FROM %1$s.OPERADORES WHERE ROWNUM <= 50 ORDER BY DESC DINEROCORRIDO ", USUARIO);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				operadores.add(convertResultSetToOperador(rs));
			}
			return operadores;
		}
		
		public void addOperador(Operador operador) throws SQLException, Exception 
		{

			String sql = String.format("INSERT INTO %1$s.OPERADORES (ID, NOMBRE, USUARIO, CONTRASENA, CORREOELECTRONICO, NUMEROCONTACTO, "
					+ " RELACIONUNIVERSIDAD, DINEROANOACTUAL, DINEROANOCORRIDO ) "
					+ " VALUES ( %2$s , '%3$s' , '%4$s' , '%5$s' , '%6$s' , '%7$s' , '%8$s' , %9$s, %10$s ) ", 
										USUARIO, 
										operador.getId(),
										operador.getNombre(),
										operador.getUsuario(),
										operador.getContrasena(),
										operador.getCorreoElectronico(),
										operador.getNumeroContacto(),
										operador.getRelacionUniversidad(),
										operador.getDineroAnoActual(),
										operador.getDineroAnoCorrido());
			System.out.println(sql);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();

		}
		
		
		public void updateOperador (Operador operador) throws SQLException, Exception
		{
			StringBuilder sql = new StringBuilder();
			sql.append(String.format("UPDATE %s.OPERADORES  ", USUARIO));
			
			sql.append(String.format(" SET NOMBRE = '%1$s' , CONTRASENA = '%2$s', CORREOELECTRONICO = '%3$s' , "
					+ "NUMEROCONTACTO = '%4$s' , RELACIONUNIVERSIDAD = '%5$s' , "
					+ "DINEROANOACTUAL = %6$s , DINEROANOCORRIDO = %7$s ",
					
					operador.getNombre(),
					operador.getContrasena(),
					operador.getCorreoElectronico(),
					operador.getNumeroContacto(),
					operador.getRelacionUniversidad(),
					operador.getDineroAnoActual(),
					operador.getDineroAnoCorrido()));
			sql.append (" WHERE ID = " + operador.getId ());
			
			System.out.println(sql);
			
			PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
		}
		

		public void deleteOperador(Operador operador) throws SQLException, Exception
		{

			String sql = String.format("DELETE FROM %1$s.OPERADORES WHERE ID = %2$d", USUARIO, operador.getId());

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
		public Operador convertResultSetToOperador(ResultSet resultSet) throws SQLException 
		{
		
			
			long pId  = resultSet.getInt("ID");
			String pNombre = resultSet.getString("NOMBRE");
			String pUsuario = resultSet.getString("USUARIO");
			String pContrasena = resultSet.getString("CONTRASENA");
			String pCorreoElectronico = resultSet.getString("CORREOELECTRONICO");
			long pNumeroContacto  = resultSet.getLong("NUMEROCONTACTO");			
			double pDineroAnoActual = resultSet.getDouble("DINEROANOACTUAL");
			double pDineroAnoCorrido = resultSet.getDouble("DINEROANOCORRIDO");
			String pRelacionUniversidad = resultSet.getString("RELACIONUNIVERSIDAD");

			Operador operador = new Operador(pId, pNombre, pUsuario, pContrasena, pCorreoElectronico, pNumeroContacto, pDineroAnoActual, pDineroAnoCorrido, pRelacionUniversidad);
			return operador;
		}
		
}
