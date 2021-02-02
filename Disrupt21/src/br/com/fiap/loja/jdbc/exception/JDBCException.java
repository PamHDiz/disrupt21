package br.com.fiap.loja.jdbc.exception;

public class JDBCException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public JDBCException() {
		this("Algo deu errado. Não foi possível conectar ao "
				+ "banco de dados.");
	}

	public JDBCException(String msg) {
		super(msg);
	}
	
	

}
