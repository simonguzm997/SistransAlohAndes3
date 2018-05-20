package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Reserva;
import vos.ReservaGrupal;

public class DAOReservaGrupal
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
			
			public   DAOReservaGrupal() 
			{
				recursos = new ArrayList<Object>();
			}

			//----------------------------------------------------------------------------------------------------------------------------------
			// METODOS DE COMUNICACION CON LA BASE DE DATOS
			//----------------------------------------------------------------------------------------------------------------------------------
			
			
			public ReservaGrupal findReservaGrupalById(long id) throws SQLException, Exception 
			{
				ReservaGrupal reserva = null;

				String sql = String.format("SELECT * FROM %1$s.RESERVAGRUPAL WHERE ID = %2$d ", USUARIO, id); 

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				if(rs.next()) 
				{
					reserva = convertResultSetToReservaGrupal(rs);
				}

				return reserva;
			}

			


			public void addReservaGrupal(ReservaGrupal resGrup, int id) throws SQLException, Exception 
			{

				String sql = String.format("INSERT INTO %1$s.RESERVAGRUPAL (ID, IDRESUNITARIA ) "
						+ " VALUES ( %2$s , %3$s ) ", 
											USUARIO, 
											
											resGrup.getId(),
											id);
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
			
			
			private ReservaGrupal convertResultSetToReservaGrupal(ResultSet rs)  throws SQLException 
			{
				
				long pId  = rs.getInt("ID");
				ReservaGrupal pRes = new ReservaGrupal(pId);
				return pRes;
			}
	

}
