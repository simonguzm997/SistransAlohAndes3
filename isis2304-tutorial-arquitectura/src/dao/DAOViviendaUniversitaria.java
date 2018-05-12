package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ViviendaUniversitaria;

public class DAOViviendaUniversitaria 
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
			public DAOViviendaUniversitaria() 
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
			public ArrayList<ViviendaUniversitaria> getViviendasUniversitarias() throws SQLException, Exception 
			{
				ArrayList<ViviendaUniversitaria> vivUni = new ArrayList<ViviendaUniversitaria>();

				String sql = String.format("SELECT * FROM %1$s.VIVIENDAS_UNIVERSITARIAS ", USUARIO);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) 
				{
					vivUni.add(convertResultSetToViviendaUniversitaria(rs));
				}
				return vivUni;
			}
			
			public ViviendaUniversitaria findViviendaUniversitariaById(int nit) throws SQLException, Exception 
			{
				ViviendaUniversitaria vivUni = null;

				String sql = String.format("SELECT * FROM %1$s.VIVIENDAS_UNIVERSITARIAS WHERE NIT = %2$d ", USUARIO, nit); 

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				if(rs.next()) 
				{
					vivUni = convertResultSetToViviendaUniversitaria(rs);
				}

				return vivUni;
			}
			
			
			public void addViviendaUniversitaria(ViviendaUniversitaria vivUni) throws SQLException, Exception 
			{
				
				String sql = String.format("INSERT INTO %1$s.VIVIENDAS_UNIVERSITARIAS (NIT, NOMBRE, DIRECCION, CAPACIDAD, ID_OPERADOR) "
						+ "VALUES (%2$s, '%3$s', '%4$s', %5$s, %6$s) ", 
											USUARIO, 
											vivUni.getNIT(),
											vivUni.getNombre(),
											vivUni.getDireccion(),
											vivUni.getCapacidad(),
											vivUni.getOperadorVivUni().getId());
				System.out.println(sql);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();

			}
			
			public void deleteViviendaUniversitaria(ViviendaUniversitaria viviendaU) throws SQLException, Exception
			{


				String sql = String.format("DELETE FROM %1$s.VIVIENDAS_UNIVERSITARIAS WHERE NIT = %2$d ; commit;", USUARIO, viviendaU.getNIT());
				
				
				
				System.out.println(sql);
				
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}
			
			
			public void updateViviendaUniversitaria (ViviendaUniversitaria vivUni) throws SQLException, Exception
			{
				StringBuilder sql = new StringBuilder();
				sql.append(String.format("UPDATE %s.VIVIENDAS_UNIVERSITARIAS SET ", USUARIO));
				
				sql.append(String.format("NOMBRE = '%1$s' AND DIRECCION = '%2$s' "
						+ "AND CAPACIDAD = '%3$s' AND ID_OPERADOR = '%4$s'", 
						vivUni.getNombre(),
						vivUni.getDireccion(),
						vivUni.getCapacidad(),
						vivUni.getOperadorVivUni().getId()));
				
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
			public ViviendaUniversitaria convertResultSetToViviendaUniversitaria(ResultSet resultSet) throws SQLException 
			{
			
				
				int pNIT  = resultSet.getInt("NIT");
				String pNombre = resultSet.getString("NOMBRE");
				String pDireccion = resultSet.getString("DIRECCION");
				int pCapacidad  = resultSet.getInt("CAPACIDAD");
				
				ViviendaUniversitaria viviUni = new ViviendaUniversitaria(pNIT, pNombre, pDireccion, pCapacidad);
				
				return viviUni;
			}
	
	
}
