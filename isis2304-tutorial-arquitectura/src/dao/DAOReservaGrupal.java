package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vos.ReservaGrupal;
import vos.Reserva;

public class DAOReservaGrupal 
{



	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTANTES
	//----------------------------------------------------------------------------------------------------------------------------------
			
			/**
			 * Constante para indicar el usuario Oracle del estudiante
			 */
			
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
			public DAOReservaGrupal() 
			{
				recursos = new ArrayList<Object>();
			}
			
			
			//----------------------------------------------------------------------------------------------------------------------------------
			// METODOS DE COMUNICACION CON LA BASE DE DATOS
			//----------------------------------------------------------------------------------------------------------------------------------

			
			
			 
				public ArrayList<ReservaGrupal> getReservasGrupales() throws SQLException, Exception 
				{
					ArrayList<ReservaGrupal> reservas = new ArrayList<ReservaGrupal>();

					String sql = String.format("SELECT * FROM %1$s.RESERVA_GRUPAL ", USUARIO);

					PreparedStatement prepStmt = conn.prepareStatement(sql);
					recursos.add(prepStmt);
					ResultSet rs = prepStmt.executeQuery();

					while (rs.next()) 
					{
						reservas.add(convertResultSetToReservaGrupal(rs));
					}
					return reservas;
				}
				
				public void addReservaGrupal(ReservaGrupal res) throws SQLException, Exception 
				{
					
					
					int i =0;
					while (i<res.getReservas().size())
					{
					String sql = String.format("INSERT INTO %1$s.RESERVA_GRUPAL (ID_RESERVAGRUPAL , ID_RESERVAU ) "
							+ " VALUES (%2$s, '%3$s')", 
												USUARIO, 
												res.getId(),
												res.getReservas().get(i).getId());
					System.out.println(sql);

					PreparedStatement prepStmt = conn.prepareStatement(sql);
					recursos.add(prepStmt);
					prepStmt.executeQuery();
					i++;
					}

				}
				
//				public void deleteReservaGrupal(ReservaGrupal reservaGrupal) throws SQLException, Exception
//				{
//
//					String sql = String.format("DELETE FROM %1$s.RESERVA_GRUPAL WHERE ID_RESERVAGRUPAL = %2$d", USUARIO, reservaGrupal.getId());
//
//					System.out.println(sql);
//					
//					PreparedStatement prepStmt = conn.prepareStatement(sql);
//					recursos.add(prepStmt);
//					prepStmt.executeQuery();
//				}
//				
//				
				public void deleteReservaGrupal(int reservaGrupal) throws SQLException, Exception
				{


					String sql = String.format("DELETE FROM %1$s.RESERVA_GRUPAL WHERE ID_RESERVAGRUPAL = %2$d", USUARIO, reservaGrupal);

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
				public ReservaGrupal convertResultSetToReservaGrupal(ResultSet resultSet) throws SQLException 
				{
				
					
					int pId  = resultSet.getInt("ID_RESERVAGRUPAL");
			
					

					ReservaGrupal reserva = new ReservaGrupal(pId);
					
					return reserva;
				}
		
		
	
	
}
