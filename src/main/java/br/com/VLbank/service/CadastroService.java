package br.com.VLbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.VLbank.conta.Conta;
import br.com.VLbank.model.Cliente;
import br.com.VLbank.model.endereco.Endereco;
import br.com.VLbank.repository.ClienteRepository;
import br.com.VLbank.repository.ContaRepository;
import br.com.VLbank.repository.EnderecoRepository;

@Service
public class CadastroService {
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Transactional
	public void salvarClienteEnderecoConta(Cliente cliente, Endereco endereco, Conta conta) {
		clienteRepository.save(cliente);
		this.getException();
		enderecoRepository.save(endereco);
		this.getException();
		contaRepository.save(conta);
	}

	private void getException() {
		throw new RuntimeException("Não foi possível realizar o cadastro.");
	}
}
