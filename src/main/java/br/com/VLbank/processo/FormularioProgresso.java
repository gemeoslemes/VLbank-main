package br.com.VLbank.processo;

import org.springframework.stereotype.Component;

import br.com.VLbank.conta.Conta;
import br.com.VLbank.model.Cliente;
import br.com.VLbank.model.endereco.Endereco;

@Component
public class FormularioProgresso {
	private boolean formularioClientePreenchido;
	private boolean formularioEnderecoPreenchido;
	private boolean formularioContaPreenchido;
	
	private Cliente cliente;
	private Endereco endereco;
	private Conta conta;
	
	public boolean isFormulariosPreenchidos() {
		return formularioClientePreenchido && formularioEnderecoPreenchido && formularioContaPreenchido;
	}
	
	public void limparFormulario() {
		formularioClientePreenchido = false;
		formularioEnderecoPreenchido = false;
		formularioContaPreenchido = false;
		
		cliente = null;
		endereco = null;
		conta = null;
	}

	public void setFormularioClientePreenchido(boolean formularioClientePreenchido) {
		this.formularioClientePreenchido = formularioClientePreenchido;
	}

	public void setFormularioEnderecoPreenchido(boolean formularioEnderecoPreenchido) {
		this.formularioEnderecoPreenchido = formularioEnderecoPreenchido;
	}

	public boolean isFormularioContaPreenchido() {
		return formularioContaPreenchido;
	}

	public void setFormularioContaPreenchido(boolean formularioContaPreenchido) {
		this.formularioContaPreenchido = formularioContaPreenchido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	
	
}
