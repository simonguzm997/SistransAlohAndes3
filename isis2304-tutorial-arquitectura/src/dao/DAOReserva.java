package dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Reserva;

public class DAOReserva 
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
		
		public   DAOReserva() 
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
		public ArrayList<Reserva> getReservaes() throws SQLException, Exception 
		{
			ArrayList<Reserva> reservaes = new ArrayList<Reserva>();

			String sql = String.format("SELECT * FROM %1$s.RESERVAS ", USUARIO);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();
			

			while (rs.next()) {
				reservaes.add(convertResultSetToReserva(rs));
			}
			return reservaes;
		}
		
		
		public Reserva findReservaById(long id) throws SQLException, Exception 
		{
			Reserva reserva = null;

			String sql = String.format("SELECT * FROM %1$s.RESERVAS WHERE ID = %2$d ", USUARIO, id); 

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.next()) 
			{
				reserva = convertResultSetToReserva(rs);
			}

			return reserva;
		}
		
		
		public ArrayList<Reserva> findReservaByIdHabitacion(long id) throws SQLException, Exception 
		{
			
			ArrayList<Reserva> reservaes = new ArrayList<Reserva>();

			String sql = String.format("SELECT * FROM %1$s.RESERVAS WHERE IDHABITACION = %2$d ", USUARIO, id); 

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				reservaes.add(convertResultSetToReserva(rs));
			}

			return reservaes;
		}
		
		public ArrayList<Reserva> findReservaByIdCliente(long id) throws SQLException, Exception 
		{
			ArrayList<Reserva> reservaes = new ArrayList<Reserva>();

			String sql = String.format("SELECT * FROM %1$s.RESERVAS WHERE IDCLIENTE = %2$d ", USUARIO, id); 

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				reservaes.add(convertResultSetToReserva(rs));
			}

			return reservaes;
		}
		
		public ArrayList<Reserva> findReservasDeGrupal(long id) throws SQLException, Exception 
		{
			ArrayList<Reserva> reservaes = new ArrayList<Reserva>();

			String sql = String.format("SELECT %1$s.RESERVAS.* FROM %1$s.RESERVAS "
					+ " INNER JOIN %1$s.RESERVAGRUPAL "
					+ " ON %1$s.RESERVAS.ID = %1$s.RESERVAGRUPAL.IDRESUNITARIA"
					+ " WHERE %1$s.RESERVAGRUPAL.ID = %2$s. ", USUARIO, id); 

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				reservaes.add(convertResultSetToReserva(rs));
			}

			return reservaes;
		}
		
		public void addReserva(Reserva reserva) throws SQLException, Exception 
		{

			String sql = String.format("INSERT INTO %1$s.RESERVAS (ID, CANTPERSONAS, FECHAINICIO, FECHAFIN, VALOR, ESTADO, "
					+ " IDHABITACION, IDCLIENTE ) "
					+ " VALUES ( reserva_sequence.nextval, %2$s , '%3$s' , '%4$s' , %5$s , '%6$s' , %7$s , %8$s ) ", 
										USUARIO, 
										
										reserva.getCantPersonas(),
										reserva.getFechaInicio(),
										reserva.getFechaFin(),
										reserva.getValor(),
										reserva.getEstado(),
										reserva.getIdHabitacion(),
										reserva.getIdCliente());
			System.out.println(sql);
			
			

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();

		}
		
		
		public int addReservaDeGrupal(Reserva reserva) throws SQLException, Exception 
		{
			System.out.println("entre a ResDeGrupal");
		
			String sql2 = String.format("CREATE TABLE RS "
					+ " (Id NUMBER (10, 0) NOT NULL, " + 
					"CONSTRAINT PK_RSTEMP PRIMARY KEY (Id )) ");
			
			System.out.println(sql2);
			
			String sql3 = String.format(" INSERT INTO RS (ID)  VALUES (reserva_sequence.nextval) ");
			System.out.println(sql3);
			String sql4 = String.format(" SELECT * FROM RS ");
			
			System.out.println(sql4);

			
			PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
			PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
			PreparedStatement prepStmt4 = conn.prepareStatement(sql4);
			
			System.out.println("prepared statements!");
			
			recursos.add(prepStmt2);
			prepStmt2.executeQuery();
			recursos.add(prepStmt3);
			prepStmt3.executeQuery();
			recursos.add(prepStmt4);
			
			System.out.println("aded prepared staementsss");
			
			//prepStmt2.executeQuery();
			System.out.println("execute stm2");
			//prepStmt3.executeQuery();
			System.out.println("execute stm3");
			
			ResultSet rs = prepStmt4.executeQuery();
			System.err.println("rs con stm 4");
			
			int idS =0;
			while (rs.next()) 
			{
				 idS = rs.getInt("ID");
			}
					
			
			System.out.println("EL IDDDD   " + idS);
			
			String sql = String.format("INSERT INTO %1$s.RESERVAS (ID, CANTPERSONAS, FECHAINICIO, FECHAFIN, VALOR, ESTADO, "
					+ " IDHABITACION, IDCLIENTE ) "
					+ " VALUES ( %2$s , %3$s , '%4$s' ,'%5$s' , %6$s , '%7$s' , %8$s , %9$s) ", 
										USUARIO, 
										idS,
										reserva.getCantPersonas(),
										reserva.getFechaInicio(),
										reserva.getFechaFin(),
										reserva.getValor(),
										reserva.getEstado(),
										reserva.getIdHabitacion(),
										reserva.getIdCliente());
		
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			System.out.println("yaas");
		
			String sql5 = String.format(" DROP TABLE RS ");
			System.out.println(sql5);
			PreparedStatement prepStmt5 = conn.prepareStatement(sql5);
			System.out.println("prepared sql5");
			recursos.add(prepStmt5);
			prepStmt5.executeQuery();
			System.out.println("ejecuta sql5");
			
			
			
		return idS;	

		}
		
		
		public void updateReserva (Reserva reserva) throws SQLException, Exception
		{
			StringBuilder sql = new StringBuilder();
			sql.append(String.format("UPDATE %s.RESERVAS  ", USUARIO));
			
			sql.append(String.format(" SET CANTPERSONAS = %1$s , FECHAINICIO = '%2$s', FECHAFIN = '%3$s' , "
					+ "VALOR = %4$s , ESTADO = '%5$s', IDHABITACION = %6$s , IDCLIENTE = %7$s ",
					reserva.getCantPersonas(),
					reserva.getFechaInicio(),
					reserva.getFechaFin(),
					reserva.getValor(),
					reserva.getEstado(),
					reserva.getIdHabitacion(),
					reserva.getIdCliente()));
			
			sql.append (" WHERE ID = " + reserva.getId ());
			
			System.out.println(sql);
			
			PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
		}
		
		public void cancelarReserva (Reserva reserva) throws SQLException, Exception
		{
			StringBuilder sql = new StringBuilder();
			sql.append(String.format("UPDATE %s.RESERVAS  ", USUARIO));
			double nuevoValor = (reserva.getValor()/100)*30;
			sql.append(String.format(" SET CANTPERSONAS = '%1$s' , FECHAINICIO = '%2$s', FECHAFIN = '%3$s' , "
					+ "VALOR = '%4$s' , ESTADO = 'Cancelada', IDHABITACION = '%6$s' , IDCLIENTE = '%7$s' ",
					
					reserva.getCantPersonas(),
					reserva.getFechaInicio(),
					reserva.getFechaFin(),
					nuevoValor,
					reserva.getEstado(),
					reserva.getIdHabitacion(),
					reserva.getIdCliente()));
			
			sql.append (" WHERE ID = " + reserva.getId ());
			
			System.out.println(sql);
			
			PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
		}
		

		public void deleteReserva(Reserva reserva) throws SQLException, Exception
		{

			String sql = String.format("DELETE FROM %1$s.RESERVAS WHERE ID = %2$d", USUARIO, reserva.getId());

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
		public Reserva convertResultSetToReserva(ResultSet resultSet) throws SQLException 
		{
		
			
			long pId  = resultSet.getInt("ID");
			int pCantPersonas = resultSet.getInt("CANTPERSONAS");
			String pFechaInicio = resultSet.getString("FECHAINICIO");
			String pFechaFin = resultSet.getString("FECHAFIN");
			double pValor = resultSet.getDouble("VALOR");
			String pEstado = resultSet.getString("ESTADO");
			long pIdHabitacion  = resultSet.getInt("IDHABITACION");
			long pIdCliente  = resultSet.getInt("IDCLIENTE");
			
			
			Reserva r = new Reserva(pId, pCantPersonas, pFechaInicio, pFechaFin, pValor, pEstado, pIdHabitacion, pIdCliente);
			return r;
		
		}
	
}
