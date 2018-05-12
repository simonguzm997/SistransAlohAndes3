package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vos.Contrato;

public class DAOContrato 
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
			public DAOContrato() 
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
			public ArrayList<Contrato> getContratos() throws SQLException, Exception 
			{
				ArrayList<Contrato> contratos = new ArrayList<Contrato>();

				String sql = String.format("SELECT * FROM %1$s.CONTRATO ", USUARIO);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) 
				{
					contratos.add(convertResultSetToContrato(rs));
				}
				return contratos;
			}
			
			
			public ArrayList<Contrato> getContratosFromAlojado(int idAlojado) throws SQLException, Exception 
			{
				ArrayList<Contrato> contratos = new ArrayList<Contrato>();

				String sql = String.format("SELECT * FROM %1$s.CONTRATO WHERE ID_ALOJADO = %2$d ", USUARIO, idAlojado);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) 
				{
					contratos.add(convertResultSetToContrato(rs));
				}
				return contratos;
			}
			
			public Contrato findContratoById(int id) throws SQLException, Exception 
			{
				Contrato contrato = null;

				String sql = String.format("SELECT * FROM %1$s.CONTRATO WHERE ID = %2$d ", USUARIO, id); 

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				if(rs.next()) 
				{
					contrato = convertResultSetToContrato(rs);
				}

				return contrato;
			}
			
			
			public void addContrato(Contrato con) throws SQLException, Exception 
			{
				
				String sql = String.format("INSERT INTO %1$s.CONTRATO (ID, DIA_INICIO, DIA_FIN, VALOR, ID_ALOJADO ) "
						+ "VALUES (%2$s, '%3$s', '%4$s','%5$s', '%6$s')", 
											USUARIO, 
											con.getId(),
											con.getDiaInicio(),
											con.getDiaFin(),
											con.getValor(),
											con.getAlojado().getId());
				System.out.println(sql);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();

			}
			
			public void deleteContrato(Contrato con) throws SQLException, Exception
			{

				String sql = String.format("DELETE FROM %1$s.CONTRATO WHERE ID = %2$d", USUARIO, con.getId());

				System.out.println(sql);
				
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}
			
			public void updateContrato (Contrato con) throws SQLException, Exception
			{
				StringBuilder sql = new StringBuilder();
				sql.append(String.format("UPDATE %s.CONTRATO SET ", USUARIO));
				
				sql.append(String.format("ID_ALOJADO = '%1$s' AND DIA_INICIO = '%2$s' AND DIA_FIN = '%3$s' "
						+ "AND VALOR = '%4$s'", 
						con.getAlojado().getId(),
						con.getDiaInicio(),
						con.getDiaFin(),
						con.getValor()));
				
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
			public Contrato convertResultSetToContrato(ResultSet resultSet) throws SQLException 
			{
			
				
				int pId  = resultSet.getInt("ID");
				double pValor = resultSet.getDouble("VALOR");
				Date pDiaInicio = resultSet.getDate("DIA_INICIO");
				Date pDiaFin = resultSet.getDate("DIA_FIN");

				Contrato contrato = new Contrato(pId, pValor, pDiaInicio, pDiaFin);
				return contrato;
			}
	
	
	
	
	
	
	
}
