package br.com.VLbank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.VLbank.model.Cliente;
import br.com.VLbank.model.endereco.Endereco;
import br.com.VLbank.repository.ClienteRepository;
import br.com.VLbank.repository.EnderecoRepository;


@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public void criarEndereco(Endereco endereco) {
		repository.save(endereco);
	}
	
	public boolean addEndereco(Endereco endereco, Long clienteId) {
		repository.save(endereco);
		return true;
	}

	public Optional<Endereco> buscarEnderecoPorId(Long clienteIdConta) {
		return repository.findByNrocli(clienteIdConta);
	}

	public boolean verificaExistenciaEndereco(Endereco endereco1, Long clienteId) {
		Cliente cliente = clienteRepository.findClienteByNrocli(clienteId);
		if(cliente != null && cliente.getEndereco().equals(endereco1)) {
		 	return true;
		} else {
			return false;
		}
	}

	public Optional<Endereco> buscarClientePorId(Long clientenrocli) {
		// TODO Auto-generated method stub
		return repository.findByClienteNrocli(clientenrocli);
	}
	
}
