package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Apartamento;

public class DAOApartamento 
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
			public DAOApartamento() 
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
			public ArrayList<Apartamento> getApartamentos() throws SQLException, Exception 
			{
				ArrayList<Apartamento> apartamentos = new ArrayList<Apartamento>();

				String sql = String.format("SELECT * FROM %1$s.APARTAMENTO ", USUARIO);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) 
				{
					apartamentos.add(convertResultSetToApartamento(rs));
				}
				return apartamentos;
			}
			
			
			public ArrayList<Apartamento> getApartamentosTop20() throws SQLException, Exception 
			{
				ArrayList<Apartamento> apartamentos = new ArrayList<Apartamento>();

				String sql = String.format("SELECT * FROM %1$s.APARTAMENTO INNER JOIN " + 
						"				(SELECT ID_APARTAMENTO " + 
						"				FROM %1$s.CONTRATO_APARTAMENTO " + 
						"				GROUP BY ID_APARTAMENTO " + 
						"				ORDER BY COUNT(ID_APARTAMENTO) DESC) B " + 
						"				ON %1$s.APARTAMENTO.ID = B.ID_APARTAMENTO " + 
						"				WHERE ROWNUM <=20 ", USUARIO);
				
//				SELECT * FROM %1$s.APARTAMENTO INNER JOIN
//				(SELECT ID_APARTAMENTO
//				FROM %1$s.CONTRATO_APARTAMENTO
//				GROUP BY ID_APARTAMENTO
//				ORDER BY COUNT(ID_APARTAMENTO) DESC) B
//				ON %1$s.APARTAMENTO.ID = B.ID_APARTAMENTO
//				WHERE ROWNUM <=20;
				

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) 
				{
					apartamentos.add(convertResultSetToApartamento(rs));
				}
				return apartamentos;
			}
			
			
			
			public Apartamento findApartamentoById(int id) throws SQLException, Exception 
			{
				Apartamento apto = null;

				String sql = String.format("SELECT * FROM %1$s.APARTAMENTO WHERE ID = %2$d ", USUARIO, id); 

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				if(rs.next()) 
				{
					apto = convertResultSetToApartamento(rs);
				}

				return apto;
			}
			
			
			public void addApartamento(Apartamento apto) throws SQLException, Exception 
			{
				
				String sql = String.format("INSERT INTO %1$s.APARTAMENTO (ID, NOMBRE_EDIFICIO, DIRECCION, PRECIO_MENSUAL, MENAJE, ID_OPERADOR ) "
						+ "VALUES (%2$s, '%3$s', '%4$s','%5$s', '%6$s', '%7$s')", 
											USUARIO, 
											apto.getId(),
											apto.getNombreEdificio(),
											apto.getDireccion(),
											apto.getPrecioMensual(),
											apto.getMenaje(),
											apto.getOperadorApartamento().getId());
				System.out.println(sql);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();

			}
			
			
//			public void deleteApartamento(Apartamento apto) throws SQLException, Exception
//			{
//
//
//				String sql = String.format("DELETE FROM %1$s.APARTAMENTO WHERE ID = %2$d", USUARIO, apto.getId());
//
//				System.out.println(sql);
//				
//				PreparedStatement prepStmt = conn.prepareStatement(sql);
//				recursos.add(prepStmt);
//				prepStmt.executeQuery();
//			}
//			
			public void deleteApartamento(int apto) throws SQLException, Exception
			{


				String sql = String.format("DELETE FROM %1$s.APARTAMENTO WHERE ID = %2$d", USUARIO, apto);

				System.out.println(sql);
				
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}
			
			
			public void updateApartamento (Apartamento apto) throws SQLException, Exception
			{
				StringBuilder sql = new StringBuilder();
				sql.append(String.format("UPDATE %s.APARTAMENTO SET ", USUARIO));
				
				sql.append(String.format("ID_OPERADOR = '%1$s' AND NOMBRE_EDIFICIO = '%2$s' AND DIRECCION = '%3$s' "
						+ "AND PRECIO_MENSUAL = '%4$s' AND MENAJE = '%5$s'", 
						apto.getOperadorApartamento().getId(),
						apto.getNombreEdificio(),
						apto.getDireccion(),
						apto.getPrecioMensual(),
						apto.getMenaje()));
				
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
			public Apartamento convertResultSetToApartamento(ResultSet resultSet) throws SQLException 
			{
			
				
				int pId  = resultSet.getInt("ID");
				String pNombreEdificio = resultSet.getString("NOMBRE_EDIFICIO");
				String pDireccion = resultSet.getString("DIRECCION");
				double pPrecioMensual = resultSet.getDouble("PRECIO_MENSUAL");
				String pMenaje = resultSet.getString("MENAJE");

				Apartamento apartamento = new Apartamento(pId, pNombreEdificio, pDireccion, pPrecioMensual, pMenaje);
				
				return apartamento;
			}

}
