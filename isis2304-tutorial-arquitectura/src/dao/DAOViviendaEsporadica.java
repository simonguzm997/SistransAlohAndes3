package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ViviendaEsporadica;

public class DAOViviendaEsporadica 
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
			public DAOViviendaEsporadica() 
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
			public ArrayList<ViviendaEsporadica> getViviendasEsporadicas() throws SQLException, Exception 
			{
				ArrayList<ViviendaEsporadica> viviendasEspo = new ArrayList<ViviendaEsporadica>();

				String sql = String.format("SELECT * FROM %1$s.VIVIENDA_ESPORADICA ", USUARIO);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) 
				{
					viviendasEspo.add(convertResultSetToViviendaEsporadica(rs));
				}
				return viviendasEspo;
			}
			
			public ViviendaEsporadica findViviendaEsporadicaById(int id) throws SQLException, Exception 
			{
				ViviendaEsporadica vivEspo = null;

				String sql = String.format("SELECT * FROM %1$s.VIVIENDA_ESPORADICA "
						+ "WHERE ID_OPERADOR = %2$d ", USUARIO, id); 

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				if(rs.next()) 
				{
					vivEspo = convertResultSetToViviendaEsporadica(rs);
				}

				return vivEspo;
			}
			
			
			public void addViviendaEsporadica(ViviendaEsporadica vivEspo) throws SQLException, Exception 
			{
				
				String sql = String.format("INSERT INTO %1$s.VIVIENDA_ESPORADICA (ID_OPERADOR, NUMERO_DE_HABITACIONES, DIRECCION, MENAJE, COSTO, DIAS_USADA, DESCRIPCION_SEGURO, VALOR_SEGURO) "
						+ "VALUES (%2$s, '%3$s', '%4$s', '%5$s', '%6$s', '%7$s', '%8$s', '%9$s')", 
											USUARIO, 
											vivEspo.getOperadorVivEspo().getId(),
											vivEspo.getNumHabitaciones(),
											vivEspo.getDireccion(),
											vivEspo.getMenaje(),
											vivEspo.getCosto(),
											vivEspo.getDiasUsados(),
											vivEspo.getDescripcionSeguro(),
											vivEspo.getValorSeguro());
				System.out.println(sql);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();

			}
			
			public void deleteViviendaEsporadica(ViviendaEsporadica viviendaE) throws SQLException, Exception
			{


				String sql = String.format("DELETE FROM %1$s.VIVIENDA_ESPORADICA WHERE ID_OPERADOR = %2$d", USUARIO, viviendaE.getOperadorVivEspo().getId());

				System.out.println(sql);
				
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}
			
			
			public void updateViviendaEsporadica (ViviendaEsporadica viviendaE) throws SQLException, Exception
			{

				StringBuilder sql = new StringBuilder();
				sql.append(String.format("UPDATE %s.VIVIENDA_ESPORADICA SET ", USUARIO));
				
				sql.append(String.format("NUMERO_DE_HABITACIONES = '%1$s' AND DIRECCION = '%2$s' "
						+ "AND MENAJE = '%3$s' AND COSTO = '%4$s' "
						+ "AND DIAS_USADA = '%5$s' AND DESCRIPCION_SEGURO = '%6$s'"
						+ "AND VALOR_SEGURO = '%7$s'", 
						viviendaE.getNumHabitaciones(),
						viviendaE.getDireccion(),
						viviendaE.getMenaje(),
						viviendaE.getCosto(),
						viviendaE.getDiasUsados(),
						viviendaE.getDescripcionSeguro(),
						viviendaE.getValorSeguro()));
				
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
			public ViviendaEsporadica convertResultSetToViviendaEsporadica(ResultSet resultSet) throws SQLException 
			{
			
				
				
				String pDireccion = resultSet.getString("DIRECCION");
				int pNumHabitaciones  = resultSet.getInt("NUMERO_DE_HABITACIONES");
				String pMenaje = resultSet.getString("MENAJE");
				double pCosto = resultSet.getDouble("COSTO");
				int pDiasUsados  = resultSet.getInt("DIAS_USADA");
				String pDescripcionSeguro = resultSet.getString("DESCRIPCION_SEGURO");
				double pValorSeguro = resultSet.getDouble("VALOR_SEGURO");

				
				ViviendaEsporadica vivEspo = new ViviendaEsporadica(pDireccion, pNumHabitaciones, pMenaje, pCosto, pDiasUsados, pDescripcionSeguro, pValorSeguro);
				
				return vivEspo;
			}
	
	
}
