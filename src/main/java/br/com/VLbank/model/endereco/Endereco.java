package br.com.VLbank.model.endereco;

import br.com.VLbank.model.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "enderecos")
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nrocli;
	
	@NotBlank(message = "Campo não pode ser nulo")
	@Size(max = 100, message = "Ultrapassou 100 caracteres")
	private String rua;
	
	@NotNull(message = "Campo não pode ser nulo")
	@Positive(message = "O campo número deve ser positivo.")
	private Integer numero;
	
	private String complemento;
	
	@NotNull(message = "Campo não pode ser nulo")
	private UF uf;
	
	@NotBlank(message = "Campo não pode ser nulo")
	private String cep;
	
	@NotBlank(message = "Campo não pode ser nulo")
	@Pattern(regexp = "^[a-zA-ZÀ-ú]+([\\s][a-zA-ZÀ-ú]+)*$", message = "Cidade deve conter apenas lestras")
	private String cidade;
	
	@ManyToOne
	@JoinColumn(name = "cliente_nrocli", nullable = false, referencedColumnName = "nrocli")
	private Cliente cliente;
	
	public Cliente getCliente() {
		return cliente;
	}
	public Cliente setCliente(Cliente cliente) {
		return this.cliente = cliente;
	}
	public Long getNrocli() {
		return nrocli;
	}
	public void setNrocli(Long nrocli) {
		this.nrocli = nrocli;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public UF getUf() {
		return uf;
	}
	public void setUf(UF uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	@Override
	public String toString() {
		return "Endereco [nrocli=" + nrocli + ", rua=" + rua + ", numero=" + numero + ", complemento=" + complemento
				+ ", uf=" + uf + ", cep=" + cep + ", cidade=" + cidade + ", cliente=" + cliente + "]";
	}
	
	
	
}
