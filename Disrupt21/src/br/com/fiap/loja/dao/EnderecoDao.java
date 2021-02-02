package br.com.fiap.loja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.loja.endereco.Endereco;
import br.com.fiap.loja.jdbc.ConnectionFactory;
import br.com.fiap.loja.jdbc.exception.JDBCException;

public class EnderecoDao {
		
		private Connection conn;
		
		public EnderecoDao() throws JDBCException {
			try {
				this.conn = new ConnectionFactory().getConnection();
			} catch (SQLException e) {
				throw new JDBCException();
			}
		}
		
		public void adiciona(Endereco endereco) throws SQLException, JDBCException {
			this.conecta();
			try {
				
				String sqlAdicionaEnd = "insert into tb_endereco(cep, logradouro, numero, "
						+ "complemento, bairro, cidade, estado) values (?,?,?,?,?,?,?)";
				PreparedStatement stmt = this.conn.prepareStatement(sqlAdicionaEnd);
				
				stmt.setLong(1, endereco.getCep());
				stmt.setString(2, endereco.getLogradouro());
				stmt.setString(3, endereco.getNumero());
				stmt.setString(4, endereco.getComplemento());
				stmt.setString(5, endereco.getBairro());
				stmt.setString(6, endereco.getCidade());
				stmt.setString(7, endereco.getEstado());
				
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			this.desconecta();
		}
		
		public List<Endereco> consulta() throws SQLException, JDBCException{
			this.conecta();
			
			try {
				List<Endereco> end = new ArrayList<Endereco>();
				PreparedStatement stmt = this.conn.prepareStatement("select "
						+ "* from tb_endereco");
				
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					Integer cep = rs.getInt("cep");
					String logradouro = rs.getString("logradouro");
					String numero = rs.getString("numero");
					String complemento = rs.getString("complemento");
					String bairro = rs.getString("bairro");
					String cidade = rs.getString("cidade");
					String estado = rs.getString("estado");
					
					
					end.add(new Endereco(logradouro, numero, complemento, bairro, cidade,
							estado, cep));
				}
				rs.close();
				stmt.close();
				this.desconecta();
				return end;
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

