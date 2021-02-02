package br.com.fiap.loja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.loja.domain.Cliente;
import br.com.fiap.loja.endereco.Endereco;
import br.com.fiap.loja.jdbc.ConnectionFactory;
import br.com.fiap.loja.jdbc.exception.JDBCException;



public class ClienteDao {
	
	private Connection conn;
	
	public ClienteDao() throws JDBCException {
		try {
			this.conn = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new JDBCException();
		}
	}
	
	public void adiciona(Cliente cliente) throws SQLException, JDBCException {
		this.conecta();
		try {
			
			String sqlAdicionaCliente = "insert into tb_cliente(id, cpf, nome, "
					+ "email, telefone) values (?,?,?,?,?)";
			PreparedStatement stmt = this.conn.prepareStatement(sqlAdicionaCliente);
			
			stmt.setLong(1, cliente.getId());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getNome());
			stmt.setString(4, cliente.getEmail());
			stmt.setString(5, cliente.getTelefone());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			this.desconecta();
			}
	}
	
	public List<Cliente> consulta() throws SQLException, JDBCException{
		this.conecta();
		
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();
			PreparedStatement stmt = this.conn.prepareStatement("select "
					+ "* from tb_cliente");
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				long id = rs.getLong("id");
				String cpf = rs.getString("cpf");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");
				Endereco endereco = (Endereco) rs.getObject(1);
				
				clientes.add(new Cliente(id, cpf,nome, email, telefone, endereco));
				
			}
			rs.close();
			stmt.close();
			this.desconecta();
			return clientes;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void conecta() throws SQLException, JDBCException {
		if(this.conn.isClosed()) {
			try {
				this.conn = new ConnectionFactory().getConnection();
			} catch (SQLException e) {
				throw new JDBCException();
			}
		}
	}
	
	
	private void desconecta() throws SQLException {
		if (!conn.isClosed())
			conn.close();
	}
	
}
