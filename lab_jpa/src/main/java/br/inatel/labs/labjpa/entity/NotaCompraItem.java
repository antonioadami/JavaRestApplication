package br.inatel.labs.labjpa.entity;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class NotaCompraItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private NotaCompra notaCompra;

	@ManyToOne
	private Produto produto;

	@NotNull
	@Positive
	private BigDecimal valorCompraProduto;

	@NotNull
	@Positive
	private Integer quantidade;
	
	// Construtores
	
	public NotaCompraItem() {}

	public NotaCompraItem(NotaCompra notaCompra, Produto produto, BigDecimal valorCompraProduto, Integer quantidade) {
		this.notaCompra = notaCompra;
		this.produto = produto;
		this.valorCompraProduto = valorCompraProduto;
		this.quantidade = quantidade;
	}
	
	// Acessores

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NotaCompra getNotaCompra() {
		return notaCompra;
	}

	public void setNotaCompra(NotaCompra notaCompra) {
		this.notaCompra = notaCompra;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getValorCompraProduto() {
		return valorCompraProduto;
	}

	public void setValorCompraProduto(BigDecimal valorCompraProduto) {
		this.valorCompraProduto = valorCompraProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotaCompraItem other = (NotaCompraItem) obj;
		return Objects.equals(id, other.id);
	}

	

	@Override
	public String toString() {
		return "NotaCompraItem [id=" + id + ", valorCompraProduto=" + valorCompraProduto + ", quantidade=" + quantidade
				+ "]";
	}

	public BigDecimal getCalculoTotalItem() {
	   return valorCompraProduto.multiply( BigDecimal.valueOf( quantidade ) );
	}


	
	
}
