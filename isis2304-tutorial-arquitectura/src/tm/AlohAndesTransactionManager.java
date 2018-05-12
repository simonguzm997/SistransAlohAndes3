package tm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import dao.DAOAlojado;
import dao.DAOApartamento;
import dao.DAOCliente;
import dao.DAOComodidaExtra;
import dao.DAOContrato;
import dao.DAOHabitacionEmp;
import dao.DAOHabitacionPar;
import dao.DAOHostal;
import dao.DAOHotel;
import dao.DAOOperadorEmpresa;
import dao.DAOOperadorParticular;
import dao.DAOReserva;
import dao.DAOReservaGrupal;
import dao.DAOServicioIncluido;
import dao.DAOVivienda;
import dao.DAOViviendaEsporadica;
import dao.DAOViviendaUniversitaria;
import vos.Alojado;
import vos.Apartamento;
import vos.Cliente;
import vos.ComodidadExtra;
import vos.Contrato;
import vos.HabitacionEmp;
import vos.HabitacionPar;
import vos.Hostal;
import vos.Hotel;
import vos.OperadorEmpresa;
import vos.OperadorParticular;
import vos.ReservaGrupal;
import vos.Reservas;
import vos.ServicioIncluido;
import vos.Vivienda;
import vos.ViviendaEsporadica;
import vos.ViviendaUniversitaria;

public class AlohAndesTransactionManager 
{
	//----------------------------------------------------------------------------------------------------------------------------------
		// CONSTANTES
		//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Constante que contiene el path relativo del archivo que tiene los datos de la conexion
		 */
		private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

		/**
		 * Atributo estatico que contiene el path absoluto del archivo que tiene los datos de la conexion
		 */
		private static String CONNECTION_DATA_PATH;
		
		/**
		 * Constatne que representa el numero maximo de Bebedores que pueden haber en una ciudad
		 */
		private final static Integer CANTIDAD_MAXIMA = 345;

		//----------------------------------------------------------------------------------------------------------------------------------
		// ATRIBUTOS
		//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
		 */
		private String user;

		/**
		 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
		 */
		private String password;

		/**
		 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
		 */
		private String url;

		/**
		 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
		 */
		private String driver;
		
		/**
		 * Atributo que representa la conexion a la base de datos
		 */
		private Connection conn;

		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE CONEXION E INICIALIZACION
		//----------------------------------------------------------------------------------------------------------------------------------
		
		public AlohAndesTransactionManager(String contextPathP) 
		{
			
			try {
				CONNECTION_DATA_PATH = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
				initializeConnectionData();
			} 
			catch (ClassNotFoundException e) {			
				e.printStackTrace();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private void initializeConnectionData() throws IOException, ClassNotFoundException 
		{

			FileInputStream fileInputStream = new FileInputStream(new File(AlohAndesTransactionManager.CONNECTION_DATA_PATH));
			Properties properties = new Properties();
			
			properties.load(fileInputStream);
			fileInputStream.close();
			
			this.url = properties.getProperty("url");
			this.user = properties.getProperty("usuario");
			this.password = properties.getProperty("clave");
			this.driver = properties.getProperty("driver");
			
		}
		
		private Connection darConexion() throws SQLException 
		{
			System.out.println("[ALOHANDES APP] Attempting Connection to: " + url + " - By User: " + user);
			return DriverManager.getConnection(url, user, password);
		}
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS TRANSACCIONALES
		//----------------------------------------------------------------------------------------------------------------------------------
		
		
		
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// CLIENTES
		//----------------------------------------------------------------------------------------------------------------------------------
		
		
		public List<Cliente> getAllClientes() throws Exception 
		{
			System.out.println("------ENTRO  A GET ALL TM");
			
			DAOCliente daoCliente = new DAOCliente();
			List<Cliente> clientes;
			try 
			{
				this.conn = darConexion();
				daoCliente.setConn(conn);
				
				clientes = daoCliente.getClientes();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoCliente.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			System.out.println("------SALGO  A GET ALL TM");
			
			return clientes;
		}
		
		public Cliente getClienteById(int id) throws Exception
		{
			DAOCliente daoCliente = new DAOCliente();
			Cliente cliente = null;
			try 
			{
				this.conn = darConexion();
				daoCliente.setConn(conn);
				cliente = daoCliente.findClienteById(id);
				if(cliente == null)
				{
					throw new Exception("El cliente con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoCliente.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return cliente;
		}
		
		public void addCliente(Cliente cliente) throws Exception 
		{
			
			DAOCliente daoCliente = new DAOCliente();
			try
			{
				this.conn = darConexion();
				daoCliente.setConn(conn);
				daoCliente.addCliente(cliente);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoCliente.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		public void updateCliente(Cliente cliente) throws Exception 
		{
			DAOCliente daoCliente = new DAOCliente();
			
			try
			{
				this.conn = darConexion();
				daoCliente.setConn( conn );
				Cliente pCliente = daoCliente.findClienteById(cliente.getId());
				if (pCliente.getNombre() == null)
				{
					Exception e =new Exception (" El cliente que quiere actualizar no existe en la base de datos");
					throw e;
				}
				else
				{
					daoCliente.updateCliente(cliente);
				}

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoCliente.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
				
		public void deleteCliente(Cliente cliente) throws Exception 
		{
			DAOCliente daoCliete = new DAOCliente();
			try
			{
				this.conn = darConexion();
				daoCliete.setConn( conn );
				daoCliete.deleteCliente(cliente);
			
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoCliete.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// ALOJADOS
		//----------------------------------------------------------------------------------------------------------------------------------
		
		public List<Alojado> getAllAlojados() throws Exception 
		{
			DAOAlojado daoAlojado = new DAOAlojado();
			List<Alojado> alojados;
			try 
			{
				this.conn = darConexion();
				daoAlojado.setConn(conn);
				
				alojados = daoAlojado.getAlojados();
				
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoAlojado.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return alojados;
		}
		
		public Alojado getAlojadoById(int idAlojado) throws Exception
		{
			DAOAlojado daoAlojado = new DAOAlojado();
			Alojado alojado = null;
			try 
			{
				this.conn = darConexion();
				daoAlojado.setConn(conn);
				alojado = daoAlojado.findAlojadoById(idAlojado);
				List<Reservas> reservas;
				System.out.println("cree reservas");
				reservas = getAllReservassAlojado(idAlojado);
				alojado.setReservas( reservas);
				List <Contrato> contratos;
				contratos = getAllContratosAlojado(idAlojado);
				alojado.setContratos(contratos);
				if(alojado == null)
				{
					throw new Exception("El alojado con el id = " + idAlojado + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoAlojado.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return alojado;
		}
		
		public void addAlojado(Alojado alojado) throws Exception 
		{
			
			DAOAlojado daoAlojado = new DAOAlojado();
			try
			{
				this.conn = darConexion();
				daoAlojado.setConn(conn);
				daoAlojado.addAlojado(alojado);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoAlojado.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		public void updateAlojado(Alojado alojado) throws Exception 
		{
			DAOAlojado daoAlojado= new DAOAlojado();
			
			try
			{
				this.conn = darConexion();
				daoAlojado.setConn( conn );
				Alojado pAlojado = daoAlojado.findAlojadoById(alojado.getId());
				if (pAlojado.getNombre() == null)
				{
					Exception e =new Exception (" El Alojado que quiere actualizar no existe en la base de datos");
					throw e;
				}
				else
				{
					daoAlojado.updateAlojado(alojado);
				}

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoAlojado.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		public void deleteAlojado(Alojado alojado) throws Exception 
		{
			DAOAlojado daoAlojadi = new DAOAlojado();
			try
			{
				this.conn = darConexion();
				daoAlojadi.setConn( conn );
				//Cliente = daoCliete.Buscarid();
				daoAlojadi.deleteAlojado(alojado);
			
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoAlojadi.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// OPERADORES DE EMPRESA
		//----------------------------------------------------------------------------------------------------------------------------------
		
		
		public List<OperadorEmpresa> getAllOperadoresEmpresas() throws Exception 
		{
			DAOOperadorEmpresa daoOperadorEmpresa = new DAOOperadorEmpresa();
			List<OperadorEmpresa> operadoresEmpresa;
			try 
			{
				this.conn = darConexion();
				daoOperadorEmpresa .setConn(conn);
				
				operadoresEmpresa = daoOperadorEmpresa.getOperadoresEmpresa();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOperadorEmpresa.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return operadoresEmpresa;
		}
		
		public OperadorEmpresa getOperadorEmpresaById(int id) throws Exception
		{
			DAOOperadorEmpresa daoOperadorEmpresa = new DAOOperadorEmpresa();
			OperadorEmpresa operadorEmpresa = null;
			try 
			{
				this.conn = darConexion();
				daoOperadorEmpresa.setConn(conn);
				operadorEmpresa = daoOperadorEmpresa.findOperadorEmpresaById(id);
				if(operadorEmpresa == null)
				{
					throw new Exception("El operadorEmpresa con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOperadorEmpresa.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return operadorEmpresa;
		}
		
		public void addOperadorEmpresa(OperadorEmpresa opEmp) throws Exception 
		{
			
			DAOOperadorEmpresa daoOpEmp = new DAOOperadorEmpresa();
			try
			{
				this.conn = darConexion();
				daoOpEmp .setConn(conn);
				daoOpEmp.addOperadorEmpresa(opEmp);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOpEmp.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		public void updateOperadorEmpresa(OperadorEmpresa opEmp) throws Exception 
		{
			DAOOperadorEmpresa daoOperadorEmpresa= new DAOOperadorEmpresa();
			
			try
			{
				this.conn = darConexion();
				daoOperadorEmpresa.setConn( conn );
				OperadorEmpresa pOpEmp = daoOperadorEmpresa.findOperadorEmpresaById(opEmp.getId());
				if (pOpEmp.getNombre() == null)
				{
					Exception e =new Exception (" El operador de empresa que quiere actualizar no existe en la base de datos");
					throw e;
				}
				else
				{
					daoOperadorEmpresa.updateOperadorEmpresa(opEmp);
				}

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOperadorEmpresa.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		
		public void deleteOperadorEmpresa(OperadorEmpresa opEMp) throws Exception 
		{
			DAOOperadorEmpresa daoOpEmp = new DAOOperadorEmpresa();
			try
			{
				this.conn = darConexion();
				daoOpEmp.setConn( conn );
				//Cliente = daoCliete.Buscarid();
				daoOpEmp.deleteOperadorEmpresa(opEMp);;
			
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOpEmp.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// OPERADORES PARTICULARES
		//----------------------------------------------------------------------------------------------------------------------------------
		
		
		public List<OperadorParticular> getAllOperadoresParticulares() throws Exception 
		{
			DAOOperadorParticular daoOperadorParticular = new DAOOperadorParticular();
			List<OperadorParticular> operadoresParticulares;
			try 
			{
				this.conn = darConexion();
				daoOperadorParticular.setConn(conn);
				
				operadoresParticulares = daoOperadorParticular.getOperadoresParticulares();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOperadorParticular.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return operadoresParticulares;
		}
		
		public OperadorParticular getOperadorParticularById(int id) throws Exception
		{
			DAOOperadorParticular daoOperadorParticular = new DAOOperadorParticular();
			OperadorParticular operadorParticular = null;
			try 
			{
				this.conn = darConexion();
				daoOperadorParticular.setConn(conn);
				operadorParticular = daoOperadorParticular.findOperadorParticularById(id);
				if(operadorParticular == null)
				{
					throw new Exception("El operadorParticular con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOperadorParticular.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return operadorParticular;
		}
		
		public void addOperadorParticular(OperadorParticular opPart) throws Exception 
		{
			
			DAOOperadorParticular daoOpPart = new DAOOperadorParticular();
			try
			{
				this.conn = darConexion();
				daoOpPart .setConn(conn);
				daoOpPart.addOperadorParticular(opPart);;

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOpPart.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		public void updateOperadorParticular(OperadorParticular opPart) throws Exception 
		{
			DAOOperadorParticular daoOperadorParticular= new DAOOperadorParticular();
			
			try
			{
				this.conn = darConexion();
				daoOperadorParticular.setConn( conn );
				OperadorParticular pOpPart = daoOperadorParticular.findOperadorParticularById(opPart.getId());
				if (pOpPart.getNombre() == null)
				{
					Exception e =new Exception (" El operador particular que quiere actualizar no existe en la base de datos");
					throw e;
				}
				else
				{
					daoOperadorParticular.updateOperadorParticular(opPart);
				}

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOperadorParticular.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		
		
		public void deleteOperadorParticular(OperadorParticular opPar) throws Exception 
		{
			DAOOperadorParticular daoOpPar = new DAOOperadorParticular();
			try
			{
				this.conn = darConexion();
				daoOpPar.setConn( conn );
				//Cliente = daoCliete.Buscarid();
				daoOpPar.deleteOperadorParticular(opPar);
			
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOpPar.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// HOTELES
		//----------------------------------------------------------------------------------------------------------------------------------
		
		
		public List<Hotel> getAllHoteles() throws Exception 
		{
			DAOHotel daoHotel = new DAOHotel();
			List<Hotel> hoteles;
			try 
			{
				this.conn = darConexion();
				daoHotel.setConn(conn);
				
				hoteles = daoHotel.getHoteles();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHotel.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return hoteles;
		}
		
		public Hotel getHotelById(int id) throws Exception
		{
			DAOHotel daoHotel = new DAOHotel();
			Hotel hotel = null;
			try 
			{
				this.conn = darConexion();
				daoHotel.setConn(conn);
				hotel = daoHotel.findHotelById(id);
				if(hotel == null)
				{
					throw new Exception("El hotel con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHotel.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return hotel;
		}
		
		
		public List<Alojado> getAlojadosFrecuentesHotel (int nitHotel) throws Exception 
		{
			DAOHotel daoHotel = new DAOHotel();
			List<Alojado> alojados;
			try 
			{
				this.conn = darConexion();
				daoHotel.setConn(conn);
				
				alojados = daoHotel.getAlojadosFrecuentes(nitHotel);
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHotel.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return alojados;
		}
		
		public void addHotel(Hotel hotel) throws Exception 
		{
			
			DAOHotel daoHotel = new DAOHotel();
			try
			{
				this.conn = darConexion();
				daoHotel.setConn(conn);
				daoHotel.addHotel(hotel);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHotel.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		public void updateHotel(Hotel hotel) throws Exception 
		{
			DAOHotel daoHotel= new DAOHotel();
			
			try
			{
				this.conn = darConexion();
				daoHotel.setConn( conn );
				Hotel pHotel = daoHotel.findHotelById(hotel.getNIT());
				if (pHotel.getNombre() == null)
				{
					Exception e =new Exception (" El hotel que quiere actualizar no existe en la base de datos");
					throw e;
				}
				else
				{
					daoHotel.updateHotel(pHotel);
				}

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHotel.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		
		public void deleteHoteles(Hotel hotel) throws Exception 
		{
			DAOHotel daoHotel = new DAOHotel();
			try
			{
				this.conn = darConexion();
				daoHotel.setConn( conn );
				//Cliente = daoCliete.Buscarid();
				daoHotel.deleteHotel(hotel);
			
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHotel.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// HOTALES
		//----------------------------------------------------------------------------------------------------------------------------------
		
		
		public List<Hostal> getAllHostales() throws Exception 
		{
			DAOHostal daoHostal = new DAOHostal();
			List<Hostal> hostales;
			try 
			{
				this.conn = darConexion();
				daoHostal.setConn(conn);
				
				hostales = daoHostal.getHostales();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHostal.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return hostales;
		}
		
		public Hostal getHostalById(int id) throws Exception
		{
			DAOHostal daoHostal = new DAOHostal();
			Hostal hostal = null;
			try 
			{
				this.conn = darConexion();
				daoHostal.setConn(conn);
				hostal = daoHostal.findHostalById(id);
				if(hostal == null)
				{
					throw new Exception("El hostal con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHostal.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return hostal;
		}
		
		public void addHostal(Hostal hostal) throws Exception 
		{
			
			DAOHostal daoHostal = new DAOHostal();
			try
			{
				this.conn = darConexion();
				daoHostal.setConn(conn);
				daoHostal.addHostal(hostal);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHostal.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		public void updateHostal(Hostal hostal) throws Exception 
		{
			DAOHostal daoHostal= new DAOHostal();
			
			try
			{
				this.conn = darConexion();
				daoHostal.setConn( conn );
				Hostal pHostal = daoHostal.findHostalById(hostal.getNIT());
				if (pHostal.getNombre() == null)
				{
					Exception e =new Exception (" El hostal que quiere actualizar no existe en la base de datos");
					throw e;
				}
				else
				{
					daoHostal.updateHostal(pHostal);
				}

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHostal.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}

		
		public void deleteHostales(Hostal hostal) throws Exception 
		{
			DAOHostal daoHostal = new DAOHostal();
			try
			{
				this.conn = darConexion();
				daoHostal.setConn( conn );
				//Cliente = daoCliete.Buscarid();
				daoHostal.deleteHostal(hostal);
			
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHostal.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// APARTAMENTOS
		//----------------------------------------------------------------------------------------------------------------------------------
		
		
		public List<Apartamento> getAllApartamentos() throws Exception 
		{
			DAOApartamento daoApartamento = new DAOApartamento();
			List<Apartamento> apartamentos;
			try 
			{
				this.conn = darConexion();
				daoApartamento.setConn(conn);
				
				apartamentos = daoApartamento.getApartamentos();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoApartamento.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return apartamentos;
		}
		
		public List<Apartamento> getAllApartamentosTop20() throws Exception 
		{
			DAOApartamento daoApartamento = new DAOApartamento();
			List<Apartamento> apartamentos;
			try 
			{
				this.conn = darConexion();
				daoApartamento.setConn(conn);
				
				apartamentos = daoApartamento.getApartamentosTop20();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoApartamento.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return apartamentos;
		}
		
		public Apartamento getApartamentoById(int id) throws Exception
		{
			DAOApartamento daoApartamento = new DAOApartamento();
			Apartamento apartamento = null;
			try 
			{
				this.conn = darConexion();
				daoApartamento.setConn(conn);
				apartamento = daoApartamento.findApartamentoById(id);
				if(apartamento == null)
				{
					throw new Exception("El apartamento con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoApartamento.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return apartamento;
		}
		
		
		public void addApartamento(Apartamento apto) throws Exception 
		{
			
			DAOApartamento daoApto = new DAOApartamento();
			try
			{
				this.conn = darConexion();
				daoApto.setConn(conn);
				daoApto.addApartamento(apto);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoApto.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		public void updateApartamento(Apartamento apto) throws Exception 
		{
			DAOApartamento daoApartamento= new DAOApartamento();
			
			try
			{
				this.conn = darConexion();
				daoApartamento.setConn( conn );
				Apartamento pApartamento = daoApartamento.findApartamentoById(apto.getId());
				if (pApartamento.getDireccion() == null)
				{
					Exception e =new Exception (" El apto que quiere actualizar no existe en la base de datos");
					throw e;
				}
				else
				{
					daoApartamento.updateApartamento(pApartamento);
				}

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoApartamento.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
//		public void deleteApartamento(Apartamento apto) throws Exception 
//		{
//			DAOApartamento daoApto = new DAOApartamento();
//			try
//			{
//				this.conn = darConexion();
//				daoApto.setConn( conn );
//				daoApto.deleteApartamento(apto);;
//			
//			}
//			catch (SQLException sqlException) {
//				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
//				sqlException.printStackTrace();
//				throw sqlException;
//			} 
//			catch (Exception exception) {
//				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
//				exception.printStackTrace();
//				throw exception;
//			} 
//			finally {
//				try {
//					daoApto.cerrarRecursos();
//					if(this.conn!=null){
//						this.conn.close();					
//					}
//				}
//				catch (SQLException exception) {
//					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
//					exception.printStackTrace();
//					throw exception;
//				}
//			}	
//		}
		
		
		public void deleteApartamento(int idapto) throws Exception 
		{
			DAOApartamento daoApto = new DAOApartamento();
			try
			{
				this.conn = darConexion();
				daoApto.setConn( conn );
				daoApto.deleteApartamento(idapto);;
			
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoApto.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// VIVIENDAS
		//----------------------------------------------------------------------------------------------------------------------------------
		
		
		
		public List<Vivienda> getAllViviendas() throws Exception 
		{
			DAOVivienda daoVivienda = new DAOVivienda();
			List<Vivienda> viviendas;
			try 
			{
				this.conn = darConexion();
				daoVivienda.setConn(conn);
				
				viviendas = daoVivienda.getViviendas();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoVivienda.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return viviendas;
		}
		
		public Vivienda getViviendaById(int id) throws Exception
		{
			DAOVivienda daoVivienda = new DAOVivienda();
			Vivienda vivienda = null;
			try 
			{
				this.conn = darConexion();
				daoVivienda.setConn(conn);
				vivienda = daoVivienda.findViviendaById(id);
				if(vivienda == null)
				{
					throw new Exception("El vivienda con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoVivienda.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return vivienda;
		}
		
		public void addVivienda(Vivienda vivienda) throws Exception 
		{
			
			DAOVivienda daoVivienda = new DAOVivienda();
			try
			{
				this.conn = darConexion();
				daoVivienda.setConn(conn);
				daoVivienda.addVivienda(vivienda);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoVivienda.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		public void updateVivienda(Vivienda vivienda) throws Exception 
		{
			DAOVivienda daoVivienda = new DAOVivienda();
			
			try
			{
				this.conn = darConexion();
				daoVivienda.setConn( conn );
				Vivienda pVivienda = daoVivienda.findViviendaById(vivienda.getOperadorVivienda().getId());
				if (pVivienda.getDireccion() == null)
				{
					Exception e =new Exception (" El vivienda que quiere actualizar no existe en la base de datos");
					throw e;
				}
				else
				{
					daoVivienda.updateVivienda(vivienda);
				}

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoVivienda.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		public void deleteVivienda(Vivienda vivienda) throws Exception 
		{
			DAOVivienda daoViv = new DAOVivienda();
			try
			{
				this.conn = darConexion();
				daoViv.setConn( conn );
				daoViv.deleteVivienda(vivienda);
			
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoViv.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// VIVIENDAS ESPORADICAS
		//----------------------------------------------------------------------------------------------------------------------------------
		
		
		public List<ViviendaEsporadica> getAllViviendasEsporadicas() throws Exception 
		{
			DAOViviendaEsporadica  daoViviendaEsporadica = new DAOViviendaEsporadica();
			List<ViviendaEsporadica> viviendasEsporadicas;
			try 
			{
				this.conn = darConexion();
				daoViviendaEsporadica.setConn(conn);
				
				viviendasEsporadicas = daoViviendaEsporadica.getViviendasEsporadicas();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoViviendaEsporadica.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return viviendasEsporadicas;
		}
		
		public ViviendaEsporadica getViviendaEsporadicaById(int id) throws Exception
		{
			DAOViviendaEsporadica daoViviendaEsporadica = new DAOViviendaEsporadica();
			ViviendaEsporadica viviendaEsporadica = null;
			try 
			{
				this.conn = darConexion();
				daoViviendaEsporadica.setConn(conn);
				viviendaEsporadica = daoViviendaEsporadica.findViviendaEsporadicaById(id);
				if(viviendaEsporadica == null)
				{
					throw new Exception("El viviendaEsporadica con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoViviendaEsporadica.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return viviendaEsporadica;
		}
		
		public void addViviendaEsporadica(ViviendaEsporadica viviendaEspo) throws Exception 
		{
			
			DAOViviendaEsporadica daoViviendaEspo = new DAOViviendaEsporadica();
			try
			{
				this.conn = darConexion();
				daoViviendaEspo.setConn(conn);
				daoViviendaEspo.addViviendaEsporadica(viviendaEspo);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoViviendaEspo.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		public void updateViviendaEsporadica(ViviendaEsporadica viviendaEsporadica) throws Exception 
		{
			DAOViviendaEsporadica daoViviendaEsporadica = new DAOViviendaEsporadica();
			
			try
			{
				this.conn = darConexion();
				daoViviendaEsporadica.setConn( conn );
				ViviendaEsporadica pViviendaEsporadica = daoViviendaEsporadica.findViviendaEsporadicaById(viviendaEsporadica.getOperadorVivEspo().getId());
				if (pViviendaEsporadica.getDireccion() == null)
				{
					Exception e =new Exception (" El viviendaEsporadica que quiere actualizar no existe en la base de datos");
					throw e;
				}
				else
				{
					daoViviendaEsporadica.updateViviendaEsporadica(viviendaEsporadica);
				}

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoViviendaEsporadica.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		public void deleteViviendaEsporadica(ViviendaEsporadica viviendaEspo) throws Exception 
		{
			DAOViviendaEsporadica daoVivEspo = new DAOViviendaEsporadica();
			try
			{
				this.conn = darConexion();
				daoVivEspo.setConn( conn );
				daoVivEspo.deleteViviendaEsporadica(viviendaEspo);
			
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoVivEspo.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// VIVIENDAS UNIVERSITARIAS
		//----------------------------------------------------------------------------------------------------------------------------------
		
		
		
		public List<ViviendaUniversitaria> getAllViviendasUniversitarias() throws Exception 
		{
			DAOViviendaUniversitaria  daoViviendaUniversitaria = new DAOViviendaUniversitaria();
			List<ViviendaUniversitaria> viviendasUniversitarias;
			try 
			{
				this.conn = darConexion();
				daoViviendaUniversitaria.setConn(conn);
				
				viviendasUniversitarias = daoViviendaUniversitaria.getViviendasUniversitarias();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoViviendaUniversitaria.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return viviendasUniversitarias;
		}
		
		public ViviendaUniversitaria getViviendaUniversitariaById(int id) throws Exception
		{
			DAOViviendaUniversitaria daoViviendaUniversitaria = new DAOViviendaUniversitaria();
			ViviendaUniversitaria viviendaUniversitaria = null;
			try 
			{
				this.conn = darConexion();
				daoViviendaUniversitaria.setConn(conn);
				viviendaUniversitaria = daoViviendaUniversitaria.findViviendaUniversitariaById(id);
				if(viviendaUniversitaria == null)
				{
					throw new Exception("El viviendaUniversitaria con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoViviendaUniversitaria.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return viviendaUniversitaria;
		}
		
		public void addViviendaUniversitaria(ViviendaUniversitaria viviendaUni) throws Exception 
		{
			
			DAOViviendaUniversitaria daoViviendaUni = new DAOViviendaUniversitaria();
			try
			{
				this.conn = darConexion();
				daoViviendaUni.setConn(conn);
				daoViviendaUni.addViviendaUniversitaria(viviendaUni);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoViviendaUni.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		public void updateViviendaUniversitaria(ViviendaUniversitaria viviendaUniversitaria) throws Exception 
		{
			DAOViviendaUniversitaria daoViviendaUniversitaria = new DAOViviendaUniversitaria();
			
			try
			{
				this.conn = darConexion();
				daoViviendaUniversitaria.setConn( conn );
				ViviendaUniversitaria pViviendaUniversitaria = daoViviendaUniversitaria.findViviendaUniversitariaById(viviendaUniversitaria.getOperadorVivUni().getId());
				if (pViviendaUniversitaria.getNombre() == null)
				{
					Exception e =new Exception (" El viviendaUniversitaria que quiere actualizar no existe en la base de datos");
					throw e;
				}
				else
				{
					daoViviendaUniversitaria.updateViviendaUniversitaria(viviendaUniversitaria);
				}

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoViviendaUniversitaria.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		public void deleteViviendaUniversitaria(ViviendaUniversitaria viviendaUni) throws Exception 
		{
			DAOViviendaUniversitaria daoVivUni = new DAOViviendaUniversitaria();
			try
			{
				this.conn = darConexion();
				daoVivUni.setConn( conn );
				daoVivUni.deleteViviendaUniversitaria(viviendaUni);
			
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoVivUni.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// RESERVAS
		//----------------------------------------------------------------------------------------------------------------------------------
		
		public List<Reservas> getAllReservass() throws Exception 
		{
			DAOReserva daoReservas = new DAOReserva();
			List<Reservas> reservass;
			try 
			{
				this.conn = darConexion();
				daoReservas.setConn(conn);
				
				reservass = daoReservas.getReservas();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReservas.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return reservass;
		}
		
		
		public List<Reservas> getAllReservassAlojado(int idAlojado) throws Exception 
		{
			System.out.println("entre a reservas alojado");
			DAOReserva daoReservas = new DAOReserva();
			List<Reservas> reservass;
			try 
			{
				this.conn = darConexion();
				daoReservas.setConn(conn);
				
				reservass = daoReservas.getReservasFromAlojado(idAlojado);
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReservas.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return reservass;
		}
		
		
		
		public void addReservas(Reservas reservas) throws Exception 
		{
			
			DAOReserva daoReservas = new DAOReserva();
			try
			{
				this.conn = darConexion();
				daoReservas.setConn(conn);
				daoReservas.addReserva(reservas);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReservas.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		public Reservas getReservaById(int id) throws Exception
		{
			DAOReserva daoReserva = new DAOReserva();
			Reservas reserva = null;
			try 
			{
				this.conn = darConexion();
				daoReserva.setConn(conn);
				reserva = daoReserva.findReservasById(id);
				if(reserva == null)
				{
					throw new Exception("El reserva con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReserva.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return reserva;
		}
		
		public void updateReservas(Reservas reservas) throws Exception 
		{
			System.out.println("entre update TM");
			DAOReserva daoReservas = new DAOReserva();
			
			try
			{
				System.out.println("entre al try");
				this.conn = darConexion();
				daoReservas.setConn( conn );
				Reservas pReservas = daoReservas.findReservasById(reservas.getId());
				if (pReservas.getFechaFin() == null)
				{
					Exception e =new Exception (" El reservas que quiere actualizar no existe en la base de datos");
					throw e;
				}
				else
				{
					daoReservas.updateReserva(reservas);
				}

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReservas.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
				
		public void deleteReservas(Reservas reservas) throws Exception 
		{
			DAOReserva daoCliete = new DAOReserva();
			try
			{
				this.conn = darConexion();
				daoCliete.setConn( conn );
				daoCliete.deleteReserva(reservas);
			
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoCliete.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
	
		
		
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// CONTRATOS
		//----------------------------------------------------------------------------------------------------------------------------------
		
		
		public List<Contrato> getAllContratos() throws Exception 
		{
			DAOContrato daoContrato = new DAOContrato();
			List<Contrato> contratos;
			try 
			{
				this.conn = darConexion();
				daoContrato.setConn(conn);

				contratos = daoContrato.getContratos();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoContrato.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return contratos;
		}
		
		
		public List<Contrato> getAllContratosAlojado(int idAlojado) throws Exception 
		{
			System.out.println("entre a contratos alojado");
			DAOContrato daoContratos = new DAOContrato();
			List<Contrato> contratos;
			try 
			{
				this.conn = darConexion();
				daoContratos.setConn(conn);
				
				contratos = daoContratos.getContratosFromAlojado(idAlojado);
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoContratos.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return contratos;
		}
		
		
		public Contrato getContratoById(int id) throws Exception
		{
			DAOContrato daoContrato = new DAOContrato();
			Contrato contrato = null;
			try 
			{
				this.conn = darConexion();
				daoContrato.setConn(conn);
				contrato = daoContrato.findContratoById(id);
				if(contrato == null)
				{
					throw new Exception("El contrato con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoContrato.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return contrato;
		}
		

		public void addContrato(Contrato contrato) throws Exception 
		{
			DAOContrato daoContrato = new DAOContrato();
			try
			{
				this.conn = darConexion();
				daoContrato.setConn(conn);
				daoContrato.addContrato(contrato);
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoContrato.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}

		public void updateContrato(Contrato contrato) throws Exception 
		{
			DAOContrato daoContrato = new DAOContrato();

			try
			{
				this.conn = darConexion();
				daoContrato.setConn( conn );
				Contrato pContrato = daoContrato.findContratoById(contrato.getId());
				if (pContrato == null)
				{
					Exception e =new Exception (" El contrato que quiere actualizar no existe en la base de datos");
					throw e;
				}
				else
				{
					daoContrato.updateContrato(contrato);
				}

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoContrato.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}

		public void deleteContrato(Contrato contrato) throws Exception 
		{
			DAOContrato daoContrato = new DAOContrato();
			try
			{
				this.conn = darConexion();
				daoContrato.setConn( conn );
				daoContrato.deleteContrato(contrato);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoContrato.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// COMODIDADES EXTRA
		//----------------------------------------------------------------------------------------------------------------------------------
		
		
		public List<ComodidadExtra> getAllComodidadExtras() throws Exception 
		{
			DAOComodidaExtra daoComodidadExtra = new DAOComodidaExtra();
			List<ComodidadExtra> comodidadExtras;
			try 
			{
				this.conn = darConexion();
				daoComodidadExtra.setConn(conn);
				
				comodidadExtras = daoComodidadExtra.getComodidadesExtra();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoComodidadExtra.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return comodidadExtras;
		}
		
		public void addComodidadExtra(ComodidadExtra comodidadExtra) throws Exception 
		{
			
			DAOComodidaExtra daoComodidadExtra = new DAOComodidaExtra();
			try
			{
				this.conn = darConexion();
				daoComodidadExtra.setConn(conn);
				daoComodidadExtra.addComodidadExtra(comodidadExtra);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoComodidadExtra.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		public void updateComodidadExtra(ComodidadExtra comodidadExtra) throws Exception 
		{
			DAOComodidaExtra daoComodidadExtra = new DAOComodidaExtra();
			
			try
			{
				this.conn = darConexion();
				daoComodidadExtra.setConn( conn );
				ComodidadExtra pComodidadExtra = daoComodidadExtra.findComodidadExtraById(comodidadExtra.getNombre(), comodidadExtra.getHabEmp().getId());
				if (pComodidadExtra.getNombre() == null)
				{
					Exception e =new Exception (" El comodidadExtra que quiere actualizar no existe en la base de datos");
					throw e;
				}
				else
				{
					daoComodidadExtra.updateComodidadExtra(comodidadExtra);
				}

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoComodidadExtra.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
				
		public void deleteComodidadExtra(ComodidadExtra comodidadExtra) throws Exception 
		{
			DAOComodidaExtra daoCliete = new DAOComodidaExtra();
			try
			{
				this.conn = darConexion();
				daoCliete.setConn( conn );
				daoCliete.deleteComodidadExtra(comodidadExtra);
			
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoCliete.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// HABITACIONES DE EMPRESA
		//----------------------------------------------------------------------------------------------------------------------------------
		
		
		public List<HabitacionEmp> getAllHabitacionEmps() throws Exception 
		{
			DAOHabitacionEmp daoHabitacionEmp = new DAOHabitacionEmp();
			List<HabitacionEmp> habitacionEmps;
			try 
			{
				this.conn = darConexion();
				daoHabitacionEmp.setConn(conn);
				
				habitacionEmps = daoHabitacionEmp.getHabitacionesEmp();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHabitacionEmp.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return habitacionEmps;
		}
		
		public List<HabitacionEmp> getAllHabitacionEmpsTop20() throws Exception 
		{
			DAOHabitacionEmp daoHabitacionEmp = new DAOHabitacionEmp();
			List<HabitacionEmp> habitacionEmps;
			try 
			{
				this.conn = darConexion();
				daoHabitacionEmp.setConn(conn);
				
				habitacionEmps = daoHabitacionEmp.getHabitacionesEmpTop20();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHabitacionEmp.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return habitacionEmps;
		}
		
		
		public List<HabitacionEmp> getAllHabitacionEmpsIntTiempo( String fInicio, String fFin) throws Exception 
		{
			DAOHabitacionEmp daoHabitacionEmp = new DAOHabitacionEmp();
			List<HabitacionEmp> habitacionEmps;
			try 
			{
				this.conn = darConexion();
				daoHabitacionEmp.setConn(conn);
				
				habitacionEmps = daoHabitacionEmp.getHabitacionesEmpIntTiempo(fInicio, fFin);
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHabitacionEmp.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return habitacionEmps;
		}
		
		
		
		public HabitacionEmp getHabitacionEmpById(int id) throws Exception
		{
			DAOHabitacionEmp daoHabitacionEmp = new DAOHabitacionEmp();
			HabitacionEmp habitacionEmp = null;
			try 
			{
				this.conn = darConexion();
				daoHabitacionEmp.setConn(conn);
				habitacionEmp = daoHabitacionEmp.findHabitacionEmpById(id);
				if(habitacionEmp == null)
				{
					throw new Exception("El habitacionEmp con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHabitacionEmp.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return habitacionEmp;
		}
		
		public void addHabitacionEmp(HabitacionEmp habitacionEmp) throws Exception 
		{
			
			DAOHabitacionEmp daoHabitacionEmp = new DAOHabitacionEmp();
			try
			{
				this.conn = darConexion();
				daoHabitacionEmp.setConn(conn);
				daoHabitacionEmp.addHabitacionEmp(habitacionEmp);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHabitacionEmp.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		public void updateHabitacionEmp(HabitacionEmp habitacionEmp) throws Exception 
		{
			DAOHabitacionEmp daoHabitacionEmp = new DAOHabitacionEmp();
			
			try
			{
				this.conn = darConexion();
				daoHabitacionEmp.setConn( conn );
				HabitacionEmp pHabitacionEmp = daoHabitacionEmp.findHabitacionEmpById(habitacionEmp.getId());
				if (pHabitacionEmp.getTipo() == null)
				{
					Exception e =new Exception (" El habitacionEmp que quiere actualizar no existe en la base de datos");
					throw e;
				}
				else
				{
					daoHabitacionEmp.updateHabitacionEmp(habitacionEmp);
				}

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHabitacionEmp.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
				
		public void deleteHabitacionEmp(HabitacionEmp habitacionEmp) throws Exception 
		{
			DAOHabitacionEmp daoCliete = new DAOHabitacionEmp();
			try
			{
				this.conn = darConexion();
				daoCliete.setConn( conn );
				daoCliete.deleteHabitacionEmp(habitacionEmp);
			
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoCliete.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// HABITACIONES PARTICULARES
		//----------------------------------------------------------------------------------------------------------------------------------
		
		
		public List<HabitacionPar> getAllHabitacionPars() throws Exception 
		{
			DAOHabitacionPar daoHabitacionPar = new DAOHabitacionPar();
			List<HabitacionPar> habitacionPars;
			try 
			{
				this.conn = darConexion();
				daoHabitacionPar.setConn(conn);
				
				habitacionPars = daoHabitacionPar.getHabitacionesPar();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHabitacionPar.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return habitacionPars;
		}
		
		public List<HabitacionPar> getAllHabitacionParsTop20() throws Exception 
		{
			DAOHabitacionPar daoHabitacionPar = new DAOHabitacionPar();
			List<HabitacionPar> habitacionPars;
			try 
			{
				this.conn = darConexion();
				daoHabitacionPar.setConn(conn);
				
				habitacionPars = daoHabitacionPar.getHabitacionesParTop20();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHabitacionPar.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return habitacionPars;
		}
		
		public HabitacionPar getHabitacionParById(int id) throws Exception
		{
			DAOHabitacionPar daoHabitacionPar = new DAOHabitacionPar();
			HabitacionPar habitacionPar = null;
			try 
			{
				this.conn = darConexion();
				daoHabitacionPar.setConn(conn);
				habitacionPar = daoHabitacionPar.findHabitacionParById(id);
				if(habitacionPar == null)
				{
					throw new Exception("El habitacionPar con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHabitacionPar.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return habitacionPar;
		}
		
		public void addHabitacionPar(HabitacionPar habitacionPar) throws Exception 
		{
			
			DAOHabitacionPar daoHabitacionPar = new DAOHabitacionPar();
			try
			{
				this.conn = darConexion();
				daoHabitacionPar.setConn(conn);
				daoHabitacionPar.addHabitacionPar(habitacionPar);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHabitacionPar.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		public void updateHabitacionPar(HabitacionPar habitacionPar) throws Exception 
		{
			DAOHabitacionPar daoHabitacionPar = new DAOHabitacionPar();
			
			try
			{
				this.conn = darConexion();
				daoHabitacionPar.setConn( conn );
				HabitacionPar pHabitacionPar = daoHabitacionPar.findHabitacionParById(habitacionPar.getId());
				if (pHabitacionPar.getTipo() == null)
				{
					Exception e =new Exception (" El habitacionPar que quiere actualizar no existe en la base de datos");
					throw e;
				}
				else
				{
					daoHabitacionPar.updateHabitacionPar(habitacionPar);
				}

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoHabitacionPar.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
				
		public void deleteHabitacionPar(HabitacionPar habitacionPar) throws Exception 
		{
			DAOHabitacionPar daoCliete = new DAOHabitacionPar();
			try
			{
				this.conn = darConexion();
				daoCliete.setConn( conn );
				daoCliete.deleteHabitacionPar(habitacionPar);
			
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoCliete.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// SERVICIOS INCLUIDOS 
		//----------------------------------------------------------------------------------------------------------------------------------
		
		public List<ServicioIncluido> getAllServicioIncluidos() throws Exception 
		{
			DAOServicioIncluido daoServicioIncluido = new DAOServicioIncluido();
			List<ServicioIncluido> servicioIncluidos;
			try 
			{
				this.conn = darConexion();
				daoServicioIncluido.setConn(conn);
				
				servicioIncluidos = daoServicioIncluido.getServiciosIncluidos();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoServicioIncluido.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return servicioIncluidos;
		}
		
		public void addServicioIncluido(ServicioIncluido servicioIncluido) throws Exception 
		{
			
			DAOServicioIncluido daoServicioIncluido = new DAOServicioIncluido();
			try
			{
				this.conn = darConexion();
				daoServicioIncluido.setConn(conn);
				daoServicioIncluido.addServicioIncluido(servicioIncluido);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoServicioIncluido.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		

				
		public void deleteServicioIncluido(ServicioIncluido servicioIncluido) throws Exception 
		{
			DAOServicioIncluido daoCliete = new DAOServicioIncluido();
			try
			{
				this.conn = darConexion();
				daoCliete.setConn( conn );
				daoCliete.deleteServicioIncluido(servicioIncluido);
			
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoCliete.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		
		public void addReservaGrupal(ReservaGrupal reservaGrupal) throws Exception 
		{
			
			DAOReservaGrupal daoReservaGrupal = new DAOReservaGrupal();
			try
			{
				this.conn = darConexion();
				daoReservaGrupal.setConn(conn);
				daoReservaGrupal.addReservaGrupal(reservaGrupal);
				//this.conn.setAutoCommit(autoCommit);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReservaGrupal.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		public void deleteReservaGrupal(int idreservaGrupal) throws Exception 
		{
			DAOReservaGrupal daoApto = new DAOReservaGrupal();
			try
			{
				this.conn = darConexion();
				daoApto.setConn( conn );
				daoApto.deleteReservaGrupal(idreservaGrupal);
			
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoApto.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		
	
	

}

		
		
		
		
		
		
   
   

		


