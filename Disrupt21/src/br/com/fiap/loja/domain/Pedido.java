package br.com.fiap.loja.domain;

import java.util.Random;

public class Pedido {
	
	private long ide;
	private Cliente cliente;
	private double numeroPedido;
	private int quantidade;
	private double frete;
	private double totalPago;
	
	public Pedido() {
		
	}
	
	public Pedido(long ide, Cliente cliente, int quantidade) {
		this.ide = ide;
		this.cliente = cliente;
		this.quantidade = quantidade;
		this.numeroPedido = numeroPedidoCriado();
		this.frete = valorFrete();
		this.totalPago = valorAPagar();
	}
	
	public long getIde() {
		return ide;
	}

	public void setIde(long ide) {
		this.ide = ide;
	}

	public double numeroPedidoCriado() {
		Random random = new Random();
		double numeroRandom = random.nextDouble();
		
		return this.numeroPedido = Math.round(numeroRandom * 1000);
	}
	
	public double valorFrete() {
		if(this.cliente.getEndereco().getEstado() == "São Paulo") {
			this.frete = 22.50;
		} else {
			this.frete = 22.50 +(0.1 * 22.50);
		}
		return this.frete;
	}
	
	public double valorAPagar() {
		double preco = 450.0;
		this.totalPago = (preco * this.quantidade) + this.frete;
		return totalPago;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public double getNumeroPedido() {
		return numeroPedido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public double getFrete() {
		return frete;
	}

	public double getTotalPago() {
		return totalPago;
	}
	
}
