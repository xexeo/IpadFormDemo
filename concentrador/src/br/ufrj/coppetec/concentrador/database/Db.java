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
		logger.info("Conexão com o BD estabelecida.");
	}

	public final void setConnection() throws Exception {
		Class.forName(this.driver);
		this.conn = DriverManager.getConnection(this.url);
	}

	public Connection getConnection() throws Exception {
		return this.conn;
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
			} catch (Exception e) {
				logger.error("Erro ao encerrar a transação com o BD.", e);
			}
		}
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (Exception e) {
				logger.error("Erro ao fechar a conexão com o BD.", e);
			}
		}

	}
}
