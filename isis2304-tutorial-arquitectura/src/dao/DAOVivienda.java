package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Vivienda;

public class DAOVivienda
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
			public DAOVivienda() 
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
			public ArrayList<Vivienda> getViviendas() throws SQLException, Exception 
			{
				ArrayList<Vivienda> viviendas = new ArrayList<Vivienda>();

				String sql = String.format("SELECT * FROM %1$s.VIVIENDA ", USUARIO);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) 
				{
					viviendas.add(convertResultSetToVivienda(rs));
				}
				return viviendas;
			}
			
			public Vivienda findViviendaById(int id) throws SQLException, Exception 
			{
				Vivienda vivienda = null;

				String sql = String.format("SELECT * FROM %1$s.VIVIENDA WHERE ID_OPERADOR = %2$d ", USUARIO, id); 

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				if(rs.next()) 
				{
					vivienda = convertResultSetToVivienda(rs);
				}

				return vivienda;
			}
			
			
			
			public void addVivienda(Vivienda vivienda) throws SQLException, Exception 
			{
				
				String sql = String.format("INSERT INTO %1$s.VIVIENDA (DIRECCION, MENAJE, ID_OPERADOR) "
						+ "VALUES (%2$s, '%3$s', '%4$s')", 
											USUARIO, 
											vivienda.getDireccion(),
											vivienda.getMenaje(),
											vivienda.getOperadorVivienda().getId());
				System.out.println(sql);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();

			}
			
			public void deleteVivienda(Vivienda vivienda) throws SQLException, Exception
			{


				String sql = String.format("DELETE FROM %1$s.VIVIENDA WHERE ID_OPERADOR = %2$d", USUARIO, vivienda.getOperadorVivienda().getId());

				System.out.println(sql);
				
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}
			
			
			public void updateVivienda (Vivienda vivienda) throws SQLException, Exception
			{
				StringBuilder sql = new StringBuilder();
				sql.append(String.format("UPDATE %s.VIVIENDA SET ", USUARIO));
				
				sql.append(String.format("DIRECCION = '%1$s' AND MENAJE = '%2$s' ", 
						vivienda.getDireccion(),
						vivienda.getMenaje()));
				
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
			public Vivienda convertResultSetToVivienda(ResultSet resultSet) throws SQLException 
			{
			
				
				
				String pDireccion = resultSet.getString("DIRECCION");
				String pMenaje = resultSet.getString("MENAJE");

				Vivienda vivienda = new Vivienda(pDireccion, pMenaje);
				
				return vivienda;
			}
	
}
