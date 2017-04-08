package br.ufrj.coppetec.concentrador.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mangeli
 */
abstract class Db {

	private static Logger logger = LogManager.getLogger(Db.class);

	private String driver = "";
	private String url = null;
	private int timeout = 30;
	private Connection conn = null;
	private Statement statement = null;

	public Db(String driver, String url) throws Exception {
		this.driver = driver;
		this.url = url;
		setConnection();
		setStatement();
	}

	public Db(String driver) throws Exception {
		this.driver = driver;
		this.conn = null;
	}

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

	public void setConnection() throws Exception {
		this.setConnection(this.url);
	}

	public void setConnection(String url) throws Exception {
		logger.info("Driver: " + this.driver);
		Class.forName(this.driver);
		this.conn = DriverManager.getConnection(url);
		this.conn.setAutoCommit(false);
		logger.info("Conexão com o BD estabelecida.");
	}

	public Connection getConnection() throws Exception {
		return this.conn;
	}

	public void openTransaction() throws Exception {
		this.openTransaction(this.url);
	}

	public void openTransaction(String url) throws Exception {
		if (this.conn == null)
			setConnection(url);
		this.conn.setAutoCommit(false);
	}

	public void commit() throws Exception {
		this.conn.commit();
	}

	public final void setStatement() throws Exception {
		if (this.conn == null) {
			setConnection();
		}
		this.statement = conn.createStatement();
		this.statement.setQueryTimeout(this.timeout);
	}

	public Statement getStatement() {
		return this.statement;
	}

	public int executeStatement(String instruction) throws SQLException {
		return this.statement.executeUpdate(instruction);
	}

	public ResultSet executeQuery(String instruction) throws SQLException {
		return this.statement.executeQuery(instruction);
	}

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
