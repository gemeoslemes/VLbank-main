package br.com.VLbank.model.seguranca;

import br.com.VLbank.conta.Conta;
import br.com.VLbank.model.Cliente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seguranca")
@NoArgsConstructor
@AllArgsConstructor
public class Seguranca {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nrocli;
	
	@Column(name = "agencia")
	private Integer agencia; 
	
	@Column(name = "numero_conta")
	private String numeroConta;
	
	@Column(name = "dac")
	private Integer dac;
	
	@Column(name = "senha")
	private String senha;
	
	@Column(name = "nrocli_conta")
	private Long nrocliConta;
	
	@Column(name = "nrocli_cliente")
	private Long nrocliCliente;

	public Long getNrocli() {
		return nrocli;
	}

	public void setNrocli(Long nrocli) {
		this.nrocli = nrocli;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer integer) {
		this.agencia = integer;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Integer getDac() {
		return dac;
	}

	public void setDac(Integer dac) {
		this.dac = dac;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getNrocli_conta() {
		return nrocliConta;
	}

	public void setNrocli_conta(Long nrocliConta) {
		this.nrocliConta = nrocliConta;
	}

	public Long getNrocli_cliente() {
		return nrocliCliente;
	}

	public void setNrocli_cliente(Long nrocliCliente) {
		this.nrocliCliente = nrocliCliente;
	}

	@Override
	public String toString() {
		return "Seguranca [nrocli=" + nrocli + ", agencia=" + agencia + ", numeroConta=" + numeroConta + ", dac=" + dac
				+ ", senha=" + senha + ", nrocli_conta=" + nrocliConta + ", nrocli_cliente=" + nrocliCliente + "]";
	}
	
	
}
