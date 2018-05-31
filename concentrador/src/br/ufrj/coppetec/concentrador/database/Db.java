package br.ufrj.coppetec.concentrador.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe abstrata que representa um banco de dados.
 *
 * @author ludes - PESC - COPPE - ufrj
 * @author Eduardo Mangeli
 * @author Marcelo Areas
 * @author Fabrício Pereira
 * @author Geraldo Xexéo
 */
abstract class Db {

	private static Logger logger = LogManager.getLogger(Db.class);

	private String driver = "";									///< Driver específico do banco de dados
	private String url = null;									///< URL para conexão com o banco
	private int timeout = 30;									///< Tempo em segundos de espera por uma operação até abortar 
	private Connection conn = null;								///< Referência para a conexão como banco
	private Statement statement = null;							///< Comando de _consulta_ ao banco de dados.

	/**
	 * Inicia a conexão com um banco de dados.
	 * @param driver		driver específico do banco
	 * @param url			url da conexão
	 * @throws Exception 
	 */
	public Db(String driver, String url) throws Exception {
		this.driver = driver;
		this.url = url;
		setConnection();
		setStatement();
	}

	/**
	 * Constrói um objeto para representar o banco de dados mas não inicia a conexão.
	 * @param driver		driver específico do banco
	 * @throws Exception 
	 */
	public Db(String driver) throws Exception {
		this.driver = driver;
		this.conn = null;
	}

	/**
	 * Encerra uma transação e retorna a conexão e o banco de dados ao estado anterior ao início da transação.
	 */
	public void rollback() {
		try {
			if (this.conn != null) {
				this.conn.rollback();
				logger.info("Rollback!");
			}
		} catch (Exception e) {
			logger.error("Erro ao fazer rollback.", e);
		}
	}

	/**
	 * Estabelece a conexão com o banco com a URL informada na construção do objeto.
	 * @throws Exception 
	 */
	public void setConnection() throws Exception {
		this.setConnection(this.url);
	}

	/**
	 * Estabalece a conexão com o banco com uma URL configurável.
	 * @param url			URL para conexão com o banco
	 * @throws Exception 
	 */
	public void setConnection(String url) throws Exception {
		logger.info("Driver: " + this.driver);
		Class.forName(this.driver);
		this.conn = DriverManager.getConnection(url);
		this.conn.setAutoCommit(false);
		logger.info("Conexão com o BD estabelecida.");
	}

	/**
	 * Retorna uma referência para a conexão com o banco.
	 * @return				referência para a conexão com o banco
	 * @throws Exception 
	 */
	public Connection getConnection() throws Exception {
		return this.conn;
	}

	/**
	 * Inicia uma transação com a URL informada na construção do objeto.
	 * @throws Exception 
	 */
	public void openTransaction() throws Exception {
		this.openTransaction(this.url);
	}

	/**
	 * Inicia uma transação com uma URL configurável.
	 * @param url			URL para conexão com o banco
	 * @throws Exception 
	 */
	public void openTransaction(String url) throws Exception {
		if (this.conn == null)
			setConnection(url);
		this.conn.setAutoCommit(false);
	}

	/**
	 * Executa o comando atual e persiste o estado do banco de dados.
	 * @throws Exception 
	 */
	public void commit() throws Exception {
		this.conn.commit();
	}

	/**
	 * Inicia a criação de um comando para o banco de dados.
	 * @throws Exception 
	 */
	public final void setStatement() throws Exception {
		if (this.conn == null) {
			setConnection();
		}
		this.statement = conn.createStatement();
		this.statement.setQueryTimeout(this.timeout);
	}

	/**
	 * Retorna uma referência para o comando atual para o banco de dados.
	 * @return referência para o comando
	 */
	public Statement getStatement() {
		return this.statement;
	}

	/**
	 * Executa um comando de modificação de dados.
	 * @param instruction		comando a ser executado
	 * @return					indicador do número de registros afetados
	 * @throws SQLException 
	 */
	public int executeStatement(String instruction) throws SQLException {
		return this.statement.executeUpdate(instruction);
	}

	/**
	 * Executa um comando de consulta de dados.
	 * @param instruction	comando a ser executado
	 * @return				resultado da consulta
	 * @throws SQLException 
	 */
	public ResultSet executeQuery(String instruction) throws SQLException {
		return this.statement.executeQuery(instruction);
	}

	/**
	 * Fecha a conexão dom o banco de dados.
	 */
	public void closeConnection() {
		if (this.statement != null) {
			try {
				this.statement.close();
				logger.info("Database statement closed.");
			} catch (Exception e) {
				logger.error("Erro ao encerrar a transação com o BD.", e);
			}
		}
		if (this.conn != null) {
			try {
				this.conn.close();
				logger.info("Database connection closed.");
			} catch (Exception e) {
				logger.error("Erro ao fechar a conexão com o BD.", e);
			}
		}

	}
}
