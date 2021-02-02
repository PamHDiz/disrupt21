package br.com.fiap.loja.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.fiap.loja.jdbc.exception.JDBCException;

public class ConnectionFactory {
	
	public Connection getConnection() throws JDBCException, SQLException{
		System.out.println("conectando ...");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new JDBCException();
		}
		
		return DriverManager.getConnection("jdbc:oracle:thin:rm85644@oracle.fiap.com.br:1521"
				+ ":ORCL",
				"", "");
	}

}
