package br.com.VLbank.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.VLbank.annotation.CPFOrCNPJ;
import br.com.VLbank.annotation.RG;
import br.com.VLbank.annotation.SenhaConfirmacao;
import br.com.VLbank.annotation.ValidPhoneNumber;
import br.com.VLbank.conta.Conta;
import br.com.VLbank.model.endereco.Endereco;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "cliente", uniqueConstraints = {@UniqueConstraint(columnNames = {"rg", "cpf_cnpj", "email"})})
@SenhaConfirmacao(senhaFieldName = "senha", confirmarSenhaFieldName = "confirmarSenha", message = "Erro")
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nrocli;
	
	@NotBlank(message = "Nome é obrigatório")
	@Pattern(regexp = "^[a-zA-ZÀ-ú]+([\\s][a-zA-ZÀ-ú]+)*$", message = "O campo nome deve conter apenas letras e espaços.")
	private String nome;
	
	@Column(name = "estados")
	private String estados;
	
	@RG
	@NotBlank(message = "RG é obrigatório")
	@Column(nullable = false, unique = true)
	private String rg;
	
	@NotBlank(message = "Não pode ser nulo")
	@Column(name = "cpf_cnpj", nullable = false, unique = true)
	@CPFOrCNPJ
	@Size(max=20)
//	@Pattern(regexp="^((\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})|(\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}-\\d{2}))$", 
//		message="CPF ou CNPJ inválido")
	private String cpfCnpj;
	
//	@NotNull(message = "Campo obrigatório")
	@Column(name = "tipo_pessoa")
	private String tipo;
	
	@NotBlank(message = "Email é obrigatório")
	@Email(message = "Email inválido")
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, unique = true)
	@NotBlank(message = "Campo obrigatório.")
	@ValidPhoneNumber
	private String telefone;

	@NotNull(message = "Campo obrigatório.")
	@Past(message = "Data deve ser no passado")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataNascimento;

	@NotBlank(message = "Senha é obrigatório")
	@Size(max = 20, message = "Senha não pose passar de 20 caracteres")
	@Size(min = 8, message = "Senha deve ter no minimo 8 caracteres")
	private String senha;
	
	@NotBlank(message = "Confirmar senha é obrigatório")
	private String confirmarSenha;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Conta> conta = new ArrayList<>();
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Endereco> endereco = new ArrayList<>();

	public Long getNrocli() {
		return nrocli;
	}

	public void setNrocli(Long nrocli) {
		this.nrocli = nrocli;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
	
	public String getTipo() {
		return tipo;
	}

	public String getEstados() {
		return estados;
	}

	public void setEstados(String estados) {
		this.estados = estados;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	

	public List<Conta> getConta() {
		return conta;
	}

	public void setConta(List<Conta> conta) {
		this.conta = conta;
	}
	
	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public void addEndereco(Endereco endereco) {
		endereco.setCliente(this);
		this.getEndereco().add(endereco);
	}
	@Override
	public String toString() {
		return "Cliente [nrocli=" + nrocli + ", nome=" + nome + ", rg=" + rg + ", cpfCnpj=" + cpfCnpj + ", email="
				+ email + ", telefone=" + telefone + ", dataNascimento=" + dataNascimento + ", senha=" + senha
				+ ", confirmarSenha=" + confirmarSenha + "]";
	}

}
