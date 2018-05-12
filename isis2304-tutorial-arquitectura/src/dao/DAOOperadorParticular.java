package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.OperadorParticular;

public class DAOOperadorParticular 
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
			public DAOOperadorParticular() 
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
			public ArrayList<OperadorParticular> getOperadoresParticulares() throws SQLException, Exception 
			{
				ArrayList<OperadorParticular> operadoresParticulares = new ArrayList<OperadorParticular>();

				String sql = String.format("SELECT * FROM %1$s.OPERADOR_PARTICULAR INNER JOIN %1$s.CLIENTE "
						+ "ON OPERADOR_PARTICULAR.ID_OPERADOR = CLIENTE.ID ", USUARIO);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) {
					operadoresParticulares.add(convertResultSetToOperadorParticular(rs));
				}
				return operadoresParticulares;
			}
			
			public OperadorParticular findOperadorParticularById(int id) throws SQLException, Exception 
			{
				OperadorParticular opPar = null;

				String sql = String.format("SELECT * FROM %1$s.OPERADOR_PARTICULAR INNER JOIN %1$s.CLIENTE " + 
						"ON OPERADOR_PARTICULAR.ID_OPERADOR = CLIENTE.ID WHERE ID_OPERADOR = %2$d", USUARIO, id); 

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				if(rs.next()) 
				{
					opPar = convertResultSetToOperadorParticular(rs);
				}

				return opPar;
			}
			
			
			public void addOperadorParticular(OperadorParticular operPart) throws SQLException, Exception 
			{

				String sql = String.format("INSERT INTO %1$s.OPERADOR_PARTICULAR (ID_OPERADOR, DINERO_ANO_ACTUAL, DINERO_ANO_CORRIDO, RELACION_UNIVERSIDAD) "
						+ "VALUES (%2$s, '%3$s', '%4$s', '%5$s')", 
											USUARIO, 
											operPart.getId(),
											operPart.getDineroAnoActual(),
											operPart.getDineroAnoCorrido(),
											operPart.getVinculoUniversidad());
				System.out.println(sql);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();

			}
			
			public void deleteOperadorParticular(OperadorParticular opPar) throws SQLException, Exception
			{

				String sql = String.format("DELETE FROM %1$s.OPERADOR_PARTICULAR WHERE ID_OPERADOR = %2$d", USUARIO, opPar.getId());

				System.out.println(sql);
				
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}
			
			public void updateOperadorParticular (OperadorParticular opPar) throws SQLException, Exception
			{
				StringBuilder sql = new StringBuilder();
				sql.append(String.format("UPDATE %s.OPERADOR_PARTICULAR SET ", USUARIO));
				
				sql.append(String.format("DINERO_ANO_ACTUAL = '%1$s'"
						+ " AND DINERO_ANO_CORRIDO = '%2$s'"
						+ "AND RELACION_UNIVERSIDAD = '%3$D'", 
						opPar.getDineroAnoActual(),
						opPar.getDineroAnoCorrido(),
						opPar.getVinculoUniversidad()));
				
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
			public OperadorParticular convertResultSetToOperadorParticular(ResultSet resultSet) throws SQLException 
			{
			
				
				int pId  = resultSet.getInt("ID_OPERADOR");
				String pNombre = resultSet.getString("NOMBRE");
				String pUsuario = resultSet.getString("USUARIO");
				String pContrasena = resultSet.getString("CONTRASENIA");
				String pCorreoElectronico = resultSet.getString("CORREO_ELECTRONICO");
				long pNumeroContacto  = resultSet.getLong("NUMERO_CONTACTO");
				double pDineroAnoActual = resultSet.getDouble("DINERO_ANO_ACTUAL");
				double pDineroAnoCorrido = resultSet.getDouble("DINERO_ANO_CORRIDO");
				String pVinculoUniversidad = resultSet.getString("RELACION_UNIVERSIDAD");

				OperadorParticular operadorParticular = new OperadorParticular(pId, pNombre, pUsuario, pContrasena, pCorreoElectronico, pNumeroContacto, pDineroAnoActual, pDineroAnoCorrido, pVinculoUniversidad);
				
				return operadorParticular;
			}
}
