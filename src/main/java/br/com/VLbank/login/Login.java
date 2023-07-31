package br.com.VLbank.login;

import jakarta.validation.constraints.NotBlank;

public class Login {
	@NotBlank(message = "Campo deve ser preenchido.")
	private String email;
	
	@NotBlank(message = "Campo deve ser preenchido.")
	private String senha;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
