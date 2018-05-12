package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vos.Alojado;
import vos.Hotel;

public class DAOHotel 
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
			public DAOHotel() 
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
			public ArrayList<Hotel> getHoteles() throws SQLException, Exception 
			{
				ArrayList<Hotel> hoteles = new ArrayList<Hotel>();

				String sql = String.format("SELECT * FROM %1$s.HOTELES ", USUARIO);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) 
				{
					hoteles.add(convertResultSetToHotel(rs));
				}
				return hoteles;
			}
			
			public ArrayList<Alojado> getAlojadosFrecuentes(int pNIT) throws SQLException, Exception 
			{
				ArrayList<Alojado> alojados = new ArrayList<Alojado>();

				String sql = String.format("SELECT * FROM "
						+ "((SELECT * FROM %1$s.ALOJADOS INNER JOIN "
						+ "%1$s.CLIENTE ON %1$s.ALOJADOS.IDENTIFICACION_CLIENTE = %1$s.CLIENTE.ID)) A "
						+ "INNER JOIN ((SELECT ID_ALOJADO, VECES FROM "
						+ "(SELECT ID_ALOJADO, NIT_HOTEL,  COUNT (%1$s.RESERVAS.ID_ALOJADO) AS VECES "
						+ "from %1$s.RESERVAS INNER JOIN %1$s.HABITACIONES_HOtel "
						+ "ON %1$s.HABITACIONES_HOTEL.ID_HABITACION = %1$s.RESERVAS.ID_HABITACION "
						+ "GROUP BY ID_ALOJADO, NIT_HOTEL) "
						+ "WHERE NIT_HOTEL = %2$s)) B "
						+ "ON A.IDENTIFICACION_CLIENTE = B.ID_ALOJADO  "
						+ "WHERE VECES >=1 ", 
						USUARIO, pNIT);
				
//				%1$s
//				SELECT * FROM
//				((SELECT * FROM ALOJADOS INNER JOIN
//				CLIENTE ON ALOJADOS.IDENTIFICACION_CLIENTE = CLIENTE.ID)) A 
//				INNER JOIN ((SELECT ID_ALOJADO, VECES FROM
//				(SELECT ID_ALOJADO, NIT_HOTEL,  COUNT (RESERVAS.ID_ALOJADO) AS VECES 
//				from RESERVAS INNER JOIN HABITACIONES_HOtel
//				ON HABITACIONES_HOTEL.ID_HABITACION = RESERVAS.ID_HABITACION
//				GROUP BY ID_ALOJADO, NIT_HOTEL)
//				WHERE NIT_HOTEL = 145698563)) B
//				ON A.IDENTIFICACION_CLIENTE = B.ID_ALOJADO 
//				WHERE VECES >=3;

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) {
					alojados.add(convertResultSetToAlojado(rs));
				}
				return alojados;
			}
			
			public Hotel findHotelById(int nit) throws SQLException, Exception 
			{
				Hotel hotel = null;

				String sql = String.format("SELECT * FROM %1$s.HOTELES WHERE NIT = %2$d ", USUARIO, nit); 

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				if(rs.next()) 
				{
					hotel = convertResultSetToHotel(rs);
				}

				return hotel;
			}
			
			public void addHotel(Hotel hotel) throws SQLException, Exception 
			{
				
				String restaurante = "T";
				if (hotel.isRestaurante()==false)
					restaurante = "F";
				String piscina = "T";
				if (hotel.isPiscina()==false)
					piscina = "F";
				String parqueadero = "T";
				if (hotel.isParqueadero()==false)
					parqueadero = "F";
				String Wifi = "T";
				if (hotel.isWifi()==false)
					Wifi = "F";
				
				String sql = String.format("INSERT INTO %1$s.HOTELES (NIT, NOMBRE, DIRECCION, CALIFICACION, HORA_APERTURA, HORA_CIERRE, RESTAURANTE, PISCINA, PARQUEADERO, WIFI, ID_OPERADOR) "
						+ "VALUES (%2$s, '%3$s', '%4$s', %5$s, '%6$s', '%7$s', '%8$s', '%9$s', '%10$s', '%11$s', %12$s)", 
											USUARIO, 
											hotel.getNIT(),
											hotel.getNombre(),
											hotel.getDireccion(),
											hotel.getCalificacion(),
											hotel.getHoraApertura(),
											hotel.getHoraCierre(),
											restaurante,
											piscina,
											parqueadero,
											Wifi,
											hotel.getOperadorHotel().getId());
				System.out.println(sql);

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();

			}
			
			
			public void deleteHotel(Hotel hotel) throws SQLException, Exception
			{


				String sql = String.format("DELETE FROM %1$s.HOTELES WHERE NIT = %2$d", USUARIO, hotel.getNIT());

				System.out.println(sql);
				
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}
			
			
			public void updateHotel (Hotel hotel) throws SQLException, Exception
			{
				String restaurante = "T";
				if (hotel.isRestaurante()==false)
					restaurante = "F";
				String piscina = "T";
				if (hotel.isPiscina()==false)
					piscina = "F";
				String parqueadero = "T";
				if (hotel.isParqueadero()==false)
					parqueadero = "F";
				String Wifi = "T";
				if (hotel.isWifi()==false)
					Wifi = "F";
				
				StringBuilder sql = new StringBuilder();
				sql.append(String.format("UPDATE %s.HOTELES SET ", USUARIO));
				
				sql.append(String.format("NOMBRE = '%1$s' AND DIRECCION = '%2$s' "
						+ "AND CALIFICACION = '%3$s' AND HORA_APERTURA = '%4$s' "
						+ "AND HORA_CIERRE = '%5$s' AND RESTAURANTE = '%6$s' "
						+ "AND PISCINA = '%7$s' AND PARQUEADERO = '%8$s' "
						+ "AND WIFI = '%9$s' AND ID_OPERADOR = '%10$s' ", 
						hotel.getNombre(),
						hotel.getDireccion(),
						hotel.getCalificacion(),
						hotel.getHoraApertura(),
						hotel.getHoraCierre(),
						restaurante,
						piscina,
						parqueadero,
						Wifi,
						hotel.getOperadorHotel().getId()));
				
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
			public Hotel convertResultSetToHotel(ResultSet resultSet) throws SQLException 
			{
			
				
				int pNIT  = resultSet.getInt("NIT");
				String pNombre = resultSet.getString("NOMBRE");
				String pDireccion = resultSet.getString("DIRECCION");
				int pCalificacion  = resultSet.getInt("CALIFICACION");				
				Date pHoraApertura = resultSet.getDate("HORA_APERTURA");
				Date pHoraCierre = resultSet.getDate("HORA_CIERRE");
				boolean pRestaurante = false;
				if (resultSet.getString("RESTAURANTE").equals("T"))
					pRestaurante = true;
				
				boolean pPiscina = false;
				if (resultSet.getString("RESTAURANTE").equals("T"))
					pPiscina = true;
				
				boolean pParqueadero = false;
				if (resultSet.getString("RESTAURANTE").equals("T"))
					pParqueadero = true;
				
				boolean pWifi = false;
				if (resultSet.getString("RESTAURANTE").equals("T"))
					pWifi = true;
					
				
				Hotel hotel = new Hotel(pNIT, pNombre, pDireccion, pCalificacion, pHoraApertura, pHoraCierre, pRestaurante, pPiscina, pParqueadero, pWifi);
				
				return hotel;
			}
			
			public Alojado convertResultSetToAlojado(ResultSet resultSet) throws SQLException 
			{

				
				int pId  = resultSet.getInt("IDENTIFICACION_CLIENTE");
				String pNombre = resultSet.getString("NOMBRE");
				String pUsuario = resultSet.getString("USUARIO");
				String pContrasena = resultSet.getString("CONTRASENIA");
				String pCorreoElectronico = resultSet.getString("CORREO_ELECTRONICO");
				//int pNumeroContacto  = resultSet.getInt("NUMERO_CONTACTO");
				String pVinculoUniversidad = resultSet.getString("VINCULO_UNIVERSIDAD");

				Alojado alojado = new Alojado(pId, pNombre, pUsuario, pContrasena, pCorreoElectronico, 123, pVinculoUniversidad);
				
				
				return alojado;
			}



				
			
}
