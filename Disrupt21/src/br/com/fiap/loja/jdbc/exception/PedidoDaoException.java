package br.com.fiap.loja.jdbc.exception;

public class PedidoDaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public PedidoDaoException() {
		this("Problemas ao gerar o pedido");	
	}

	public PedidoDaoException(String msg) {
		super(msg);
	}

}
