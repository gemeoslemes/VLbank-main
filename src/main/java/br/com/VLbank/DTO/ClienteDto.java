package br.com.VLbank.DTO;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.VLbank.annotation.CPFOrCNPJ;
import br.com.VLbank.annotation.RG;
import br.com.VLbank.annotation.SenhaConfirmacao;
import br.com.VLbank.annotation.ValidPhoneNumber;
import br.com.VLbank.model.Cliente;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table(name = "cliente", uniqueConstraints = {@UniqueConstraint(columnNames = { "cpf_cnpj", "email"})})
@SenhaConfirmacao(senhaFieldName = "senha", confirmarSenhaFieldName = "confirmarSenha", message = "Erro")
public class ClienteDto {
	
	
	@NotBlank(message = "Nome é obrigatório")
	@Pattern(regexp = "^[a-zA-ZÀ-ú]+([\\s][a-zA-ZÀ-ú]+)*$", message = "O campo nome deve conter apenas letras e espaços.")
	private String nome;

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
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
	

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	public Cliente toCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome(this.nome);
		cliente.setCpfCnpj(this.cpfCnpj);
		if(this.cpfCnpj.length() == 14) {
			cpfCnpj.replaceAll("[^0-9]", "");
			cliente.setTipo(this.tipo = "F");
		}else {
			cpfCnpj.replaceAll("[^0-9]", "");
			cliente.setTipo(this.tipo= "J");
		}
		cliente.setEmail(this.email);
		cliente.setTelefone(this.telefone);
		cliente.setSenha(this.senha);
		cliente.setConfirmarSenha(this.confirmarSenha);
		cliente.setDataNascimento(this.dataNascimento);

		return cliente;	
	}

	@Override
	public String toString() {
		return "ClienteDto [nome=" + nome + ", cpfCnpj=" + cpfCnpj + ", email=" + email + ", telefone="
				+ telefone + ", dataNascimento=" + dataNascimento + ", senha=" + senha + ", confirmarSenha="
				+ confirmarSenha + "]";
	}

}
