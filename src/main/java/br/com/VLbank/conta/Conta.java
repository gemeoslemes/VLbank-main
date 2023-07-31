package br.com.VLbank.conta;

import br.com.VLbank.model.Cliente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "conta")
@NoArgsConstructor
@AllArgsConstructor
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nrocli;
	
	@Column(name = "agencia")
	private Integer agencia = 7199;
	
	@Column(name = "numero_conta")
	private String numeroConta;
	
	@Column(name = "dac")
	private Integer dac;
	
	@Column(name = "banco")
	private Integer banco = 037;
	
	@Column(name = "status")
	private Boolean status;
	
	@NotBlank(message = "Campo não pode ser nulo")
	@Column(name = "tipo")
	private String tipo;
	
	@ManyToOne
	@JoinColumn(name = "nrocli_cliente", nullable = false, referencedColumnName = "nrocli")
	private Cliente cliente;

	public Long getNrocli() {
		return nrocli;
	}

	public void setNrocli(Long nrocli) {
		this.nrocli = nrocli;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroContaSelecionada) {
		this.numeroConta = numeroContaSelecionada;
	}

	public Integer getDac() {
		return dac;
	}

	public void setDac(Integer dac) {
		this.dac = dac;
	}

	public Integer getBanco() {
		return banco;
	}

	public void setBanco(Integer banco) {
		this.banco = banco;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Conta [nrocli=" + nrocli + ", agencia=" + agencia + ", numeroConta=" + numeroConta + ", dac=" + dac
				+ ", banco=" + banco + ", status=" + status + ", tipo=" + tipo + ", cliente=" + cliente + "]";
	}
	
	
	
}
