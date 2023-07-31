package br.com.VLbank.service;

import java.util.Optional;

import br.com.VLbank.conta.Conta;
import br.com.VLbank.repository.ContaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.ModelAndView;

import br.com.VLbank.DTO.ClienteDto;
import br.com.VLbank.model.Cliente;
import br.com.VLbank.model.endereco.Endereco;
import br.com.VLbank.repository.ClienteRepository;
import br.com.VLbank.repository.EnderecoRepository;


@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private EnderecoRepository repositoryEndereco;

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	public ClienteDto criarCliente(ClienteDto dto) {
		Cliente cliente = modelMapper.map(dto, Cliente.class);
		repository.save(cliente);
		return modelMapper.map(cliente, ClienteDto.class);
	}
	
	public ModelAndView verificaSenha(ClienteDto dto) {
		Cliente cliente = modelMapper.map(dto, Cliente.class);
		
		if(!cliente.getSenha().equals(cliente.getConfirmarSenha())) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("home/criar_conta");
			mv.addObject("senhaInvalida", "Senha inválidas");
			return mv;
		}
		
		repository.save(cliente);
		return null;
	}
	
	public void addCliente(Cliente cliente) {
		repository.save(cliente);
		
	}

	public boolean existeCpfCnpj(String cpfCnpj) {
		return repository.existsByCpfCnpj(cpfCnpj);
	}

	public boolean existeTelefone(String telefone) {
		return repository.existsByTelefone(telefone);
	}
	
	public boolean existeEmail(String email) {
		return repository.existsByEmail(email);
	}
	
//	public void existeEstado(ClienteDto cliente, Estados estados, BindingResult bindingResult, Model model) {
//		boolean existe = false;
//		System.out.println(cliente.getEstados());
//		if(cliente.getEstados() == Estados.VAZIO) {
//			System.out.println("Entrei no if");
//			bindingResult.addError(new FieldError("estados", "estados", "Estado não pode ser vazio!"));
//			existe = true;
//		}
//		if(existe) {
//			
//			throw new IllegalArgumentException("Preencha o campo");
//		}
//
//	}
	
//	public void existeEstado(ClienteDto cliente, String estados, BindingResult bindingResult, Model model) {
//	    boolean existe = false;
//	    if(cliente != null && cliente.getEstados() == null) {
//	        bindingResult.addError(new FieldError("estados", "estados", "Estado não pode ser vazio!"));
//	        existe = true;
//	    }
//	    if(existe) {
//	        throw new IllegalArgumentException("Preencha o campo");
//	    }
//	}

	
//	public void tipoPessoaVazio(String tipo, BindingResult bindingResult, Model model) {
//		boolean existe = false;
//		if(tipo == null) {
//			System.out.println("Entrei Estado Vazio");
//			model.addAttribute("mensagemTipo", "Tipo Pessoa não pode ser vazio.");
//			existe = true;
//		}
//		if(existe) {
//			throw new IllegalArgumentException("Não pode ser Vazio");
//		}
//	}

	public void verificarExistencia(ClienteDto requisicao, BindingResult bindingResult) {
		boolean existe = false;
	    if (existeCpfCnpj(requisicao.getCpfCnpj())) {
	        bindingResult.addError(new FieldError("requisicao", "cpfCnpj", "CPF ou CNPJ já existe na base de dados!"));
	        existe = true;
	    }

	    if (existeTelefone(requisicao.getTelefone())) {
	        bindingResult.addError(new FieldError("requisicao", "telefone", "Telefone já existente na base de dados"));
	        existe = true;
	    }
	    if(existeEmail(requisicao.getEmail())) {
	    	bindingResult.addError(new FieldError("requisicao", "email", "Email já existente na base de dados"));
	    	existe = true;
	    }
	    
	    if(existe) {
	    	throw new IllegalArgumentException("Algum dos campos já existe na base de dados");
	    }
	}
//	@Transactional
//	public void salvarClienteComEndereco(ClienteDto dto, EnderecoDto enderecoDto) {
//		Cliente cliente = dto.toCliente();
//		repository.save(cliente);
//		Endereco endereco = enderecoDto.toEndereco();
//		repositoryEndereco.save(endereco);
//	}

	public Optional<Cliente> buscarClientePorId(Long clienteId) {
		return repository.findByNrocli(clienteId);
	}
	
	public void apagarCliente(Long clienteId) {
		System.out.println("Cliente Deletado");
		repository.deleteById(clienteId);
	}

}
