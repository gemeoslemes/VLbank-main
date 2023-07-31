package br.com.VLbank.DTO;

import br.com.VLbank.model.Cliente;
import br.com.VLbank.model.endereco.Endereco;
import br.com.VLbank.model.endereco.UF;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Table(name = "enderecos")
public class EnderecoDto {
	
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
	@JoinColumn(name = "cliente_nrocli", nullable = false)
	private Cliente cliente;
	
	public String getRua() {
		return rua;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	
	public Cliente getCliente() {
		return cliente;
	}

	public Endereco toEndereco() {
		Endereco endereco = new Endereco();
		endereco.setRua(this.rua);
		endereco.setNumero(this.numero);
		endereco.setComplemento(this.complemento);
		endereco.setUf(this.uf);
		endereco.setCep(this.cep);
		endereco.setCidade(this.cidade);
		endereco.setCliente(cliente);
		return endereco;
	}
}
