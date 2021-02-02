package br.com.fiap.loja.main;

import java.sql.SQLException;

import br.com.fiap.loja.dao.PedidoDao;
import br.com.fiap.loja.domain.Cliente;
import br.com.fiap.loja.domain.Pedido;
import br.com.fiap.loja.endereco.Endereco;
import br.com.fiap.loja.jdbc.exception.JDBCException;
import br.com.fiap.loja.jdbc.exception.PedidoDaoException;

public class Main {
	
	public static void main(String[] args) throws JDBCException, SQLException, PedidoDaoException {
		
		Endereco end = new Endereco();
		end.setLogradouro("Rua Colônia da Glória");
		end.setNumero("453");
		end.setComplemento("Apto 123b");
		end.setBairro("Vila Mariana");
		end.setCidade("São Paulo");
		end.setEstado("São Paulo");
		end.setCep(04113001);
		
		
		Cliente cl = new Cliente(111, "Pamella H. Diz", "222.222.222-22", 
				"pamhdiz@gmail.com", "11 964980242", end);
		
		System.out.println(cl.getEndereco().getLogradouro());
		
		Pedido ped = new Pedido(232 ,cl, 1);
		System.out.println(ped.getFrete());	
		System.out.println(ped.getTotalPago());
		
		PedidoDao pedidoDao = new PedidoDao();
		pedidoDao.adiciona(ped);
	}
	
	


}
