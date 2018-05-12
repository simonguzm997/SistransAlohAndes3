package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vos.Alojado;
import vos.Reservas;

public class DAOReserva
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
			public DAOReserva() 
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
			public ArrayList<Reservas> getReservas() throws SQLException, Exception 
			{
				ArrayList<Reservas> reservas = new ArrayList<Reservas>();

				String sql = String.format("SELECT * FROM %1$s.RESERVAS ", USUARIO);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) 
				{
					reservas.add(convertResultSetToReserva(rs));
				}
				return reservas;
			}
			
			public ArrayList<Reservas> getReservasFromAlojado(int idAlojado) throws SQLException, Exception 
			{
				ArrayList<Reservas> reservas = new ArrayList<Reservas>();

				String sql = String.format("SELECT * FROM %1$s.RESERVAS WHERE ID_ALOJADO = %2$d ", USUARIO, idAlojado);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) 
				{
					reservas.add(convertResultSetToReserva(rs));
				}
				return reservas;
			}
			
			public Reservas findReservasById(int id) throws SQLException, Exception 
			{
				Reservas reservas = null;

				String sql = String.format("SELECT * FROM %1$s.RESERVAS WHERE ID = %2$d ", USUARIO, id); 

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				if(rs.next()) 
				{
					reservas = convertResultSetToReserva(rs);
				}

				return reservas;
			}
			
			
			

			
			
			public void addReserva(Reservas res) throws SQLException, Exception 
			{
				
				String sql = String.format("INSERT INTO %1$s.RESERVAS (ID, CANT_PERSONAS, FECHA_INICIO, FECHA_FIN, VALOR, ID_HABITACION, ID_ALOJADO ) "
						+ " VALUES (%2$s, '%3$s', '%4$s','%5$s', '%6$s', '%7$s', '%8$s')", 
											USUARIO, 
											res.getId(),
											res.getCantPersonas(),
											res.getFechaInicio(),
											res.getFechaFin(),
											res.getValor(),
											res.getHabitacionReservada().getId(),
											res.getAlojado().getId());
				System.out.println(sql);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();

			}
			
			public void deleteReserva(Reservas res) throws SQLException, Exception {

				String sql = String.format("DELETE FROM %1$s.RESERVAS WHERE ID = %2$d", USUARIO, res.getId());

				System.out.println(sql);
				
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}
			
			public void updateReserva (Reservas res) throws SQLException, Exception
			{
				System.out.println("entre al updateDAO");
				String pEstado = "T";
				System.out.println("linea2");
				if (!res.isEstado())
					pEstado = "F";
				System.out.println("linea 3");
				StringBuilder sql = new StringBuilder();
				sql.append(String.format("UPDATE %s.RESERVAS SET ", USUARIO));
				System.out.println(res.getFechaFin());
				sql.append(String.format("CANT_PERSONAS = '%1$s', FECHA_INICIO = ''%2$s'', "
						+ " FECHA_FIN = ''%3$s'' , VALOR = '%4$s' , "
						+ " ID_HABITACION = '%5$s' , ID_ALOJADO = '%6$s' , "
						+ " ESTADO = ''%7$s'', WHERE ID_ALOJADO = '%6$s'   ", 
						res.getCantPersonas(),
						res.getFechaInicio(),
						res.getFechaFin(),
						res.getValor(),
						res.getHabitacionReservada().getId(),
						res.getAlojado().getId(),
						pEstado));
				
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
			public Reservas convertResultSetToReserva(ResultSet resultSet) throws SQLException 
			{
			
				
				int pId  = resultSet.getInt("ID");
				int pCantPersonas  = resultSet.getInt("CANT_PERSONAS");
				Date pFechaInicio = resultSet.getDate("FECHA_INICIO");
				Date pFechaFin = resultSet.getDate("FECHA_FIN");
				double pValor = resultSet.getDouble("VALOR");
				String estado = resultSet.getString("ESTADO");
				boolean pEstado = false;
				if (estado.equals("T"))
				{
					pEstado = true;
				}
				

				Reservas reserva = new Reservas(pId, pCantPersonas, pFechaInicio, pFechaFin, pValor, pEstado);
				
				return reserva;
			}
	
	
	
	
	
	
	
	
}
