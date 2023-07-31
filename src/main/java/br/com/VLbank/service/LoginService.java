package br.com.VLbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.VLbank.model.Cliente;
import br.com.VLbank.repository.ClienteRepository;

@Service
public class LoginService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente buscaEmailAndSenhaDeCliente(String email, String senha) {
//		try {
//			Cliente cliente = clienteRepository.buscaEmailAndSenhaBy(email, senha);
//			System.out.println(cliente + " deu certo");
//		} catch (NonUniqueResultException e) {
//			System.out.println("Email ou senhas inválidas");
//		}
		
		Cliente cliente = clienteRepository.buscaEmailAndSenhaBy(email, senha);
		System.out.println(cliente + " deu certo");
		if (cliente != null) {

			System.out.println("válido ************");

		} else {
			System.out.println("inválido ************");
		}
		return cliente;

	}

}
