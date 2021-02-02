package br.com.fiap.loja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.loja.domain.Cliente;
import br.com.fiap.loja.domain.Pedido;
import br.com.fiap.loja.jdbc.ConnectionFactory;
import br.com.fiap.loja.jdbc.exception.JDBCException;
import br.com.fiap.loja.jdbc.exception.PedidoDaoException;

public class PedidoDao {
		
		private Connection conn;
		
		public PedidoDao() throws JDBCException {
			try {
				this.conn = new ConnectionFactory().getConnection();
			} catch (SQLException e) {
				throw new JDBCException();
			}
		}
		
		public void adiciona(Pedido pedido) throws SQLException, JDBCException, PedidoDaoException {
			this.conecta();
			try {
				
				String sqlAdicionaPedido = "insert into tb_pedido(ide, cliente, numeroPedido, "
						+ "quantidade, frete, totalPago) values (?,?,?,?,?,?)";
				PreparedStatement stmt = this.conn.prepareStatement(sqlAdicionaPedido);
				
				stmt.setLong(1, pedido.getIde());
				stmt.setLong(2, pedido.getCliente().getId());
				stmt.setDouble(3, pedido.getNumeroPedido());
				stmt.setInt(4, pedido.getQuantidade());
				stmt.setDouble(5, pedido.getFrete());
				stmt.setDouble(6, pedido.getTotalPago());
				
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				this.desconecta();
				}
		}
		
		public List<Pedido> consulta() throws SQLException, JDBCException{
			this.conecta();
			
			try {
				List<Pedido> pedidos = new ArrayList<Pedido>();
				PreparedStatement stmt = this.conn.prepareStatement("select "
						+ "* from tb_pedido");
				
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					long ide = rs.getLong("ide");
					int quantidade = rs.getInt("quantidade");
					
					Cliente cl = (Cliente) new ClienteDao().consulta();
					pedidos.add(new Pedido(ide, cl, quantidade));
		
					
				}
				rs.close();
				stmt.close();
				this.desconecta();
				return pedidos;
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
