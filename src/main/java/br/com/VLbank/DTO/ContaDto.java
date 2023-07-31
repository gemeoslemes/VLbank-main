package br.com.VLbank.DTO;

import java.util.ArrayList;
import java.util.List;

import br.com.VLbank.conta.Conta;
import br.com.VLbank.model.Cliente;
import br.com.VLbank.service.ContaService;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Table(name = "conta")
public class ContaDto {

	@Column(name = "agencia")
	private Integer agencia = 7199;
	
	@Column(name = "numero_conta")
	private List<String> numeroConta = new ArrayList<>();

	@Column(name = "dac")
	private Integer dac;

	@Column(name = "banco")
	private Integer banco = 25;

	@Column(name = "status")
	private Boolean status;

	@NotBlank(message = "Campo não pode ser nulo")
	@Column(name = "tipo")
	private String tipo;

	@ManyToOne
	@JoinColumn(name = "nrocli_cliente", nullable = false)
	private Cliente cliente;

	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public List<String> getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(List<String> numeroConta) {
		this.numeroConta = numeroConta;
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

	public Conta toConta(ContaService contaService) {
		Conta conta = new Conta();
		conta.setAgencia(this.agencia);
		conta.setDac(contaService.gerarDacs(this.numeroConta));
//		conta.setNumeroConta(this.numeroConta);
		conta.setBanco(this.banco);
		conta.setStatus(this.status = true);
		conta.setTipo(this.tipo);
		conta.setCliente(cliente);
		return conta;
	}

}
