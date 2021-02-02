package br.com.fiap.loja.domain;

import java.util.ArrayList;
import java.util.List;


public class LojaVirtual {
	
	List<Pedido> pedido;
	private int totalPedidos;
	
	public LojaVirtual() {
		this.pedido = new ArrayList<Pedido>();
		if(this.pedido != null) {
			this.totalPedidos++;
		}
	}

	public List<Pedido> getPedidos() {
		return pedido;
	}

	public int getTotalPedidos() {
		return totalPedidos;
	}
	
}
