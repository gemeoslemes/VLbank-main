package br.com.VLbank.controllers;

import java.util.List;
import java.util.Optional;

import br.com.VLbank.eventos.Eventos;
import br.com.VLbank.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.VLbank.DTO.ClienteDto;
import br.com.VLbank.DTO.ContaDto;
import br.com.VLbank.DTO.EnderecoDto;
import br.com.VLbank.conta.Conta;
import br.com.VLbank.model.Cliente;
import br.com.VLbank.model.endereco.Endereco;
import br.com.VLbank.model.seguranca.Seguranca;
import br.com.VLbank.processo.FormularioProgresso;
import br.com.VLbank.service.ClienteService;
import br.com.VLbank.service.ContaService;
import br.com.VLbank.service.EnderecoService;
import br.com.VLbank.service.SegurancaService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@Controller
@Transactional
public class ContaController {
	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private FormularioProgresso formularioProgresso;

	@Autowired
	private ClienteService serviceCliente;

	@Autowired
	private ContaService service;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private SegurancaService segurancaService;

	@Autowired
	public ContaController(FormularioProgresso formularioProgresso) {
		this.formularioProgresso = formularioProgresso;
	}

	@GetMapping("/criar-conta")
	public String criarConta(ClienteDto cliente) {
		return "/criar_conta";
	}

	@PostMapping("/criar-conta")
	public ModelAndView create(@Valid @ModelAttribute ClienteDto requisicao, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("Erros de validação encontrados:");
			bindingResult.getAllErrors().forEach(error -> {
				System.out.println(error.getDefaultMessage());
			});
		}
		if (bindingResult.hasErrors()) {
			System.out.println("Entrei");
			model.addAttribute("menssagemErroRg", "Por favor, corrija o erro rg.");
			model.addAttribute("menssagemErroCPForCNPJ", "Por favor, corrija o erro cpf ou cnpj.");
			model.addAttribute("menssagemErroEmail", "Por favor, corrija o erro email.");
			model.addAttribute("menssagemErroTelefone", "Por favor, corrija o erro telefone.");

			return new ModelAndView("/criar_conta");
		}

		try {
			serviceCliente.verificarExistencia(requisicao, bindingResult);
//			service.existeEstado(requisicao,requisicao.getEstados(), bindingResult, model);
			System.out.println("Entrei try");
		} catch (IllegalArgumentException e) {
			System.out.println("Entrei no catch");
//			model.addAttribute("menssagemAR", e.getMessage());
			model.addAttribute("mensagemErro", e.getMessage());
			return new ModelAndView("/criar_conta");
		}

		System.out.println("Entrei em salvar");
		Cliente cliente = requisicao.toCliente();
		serviceCliente.addCliente(cliente);
		Long clienteId = cliente.getNrocli();
		ModelAndView modelAndView = new ModelAndView("redirect:/enderecos/{clienteId}");
		modelAndView.addObject("clienteId", clienteId);
		return modelAndView;

	}

	@RequestMapping("/enderecos/{clienteId}")
	public ModelAndView criarEndereco(@PathVariable("clienteId") Long clienteId, EnderecoDto endereco,
			Endereco endereco1) {
		System.out.println("Entrei no Controller endereco");
		System.out.println(serviceCliente.buscarClientePorId(clienteId));

		if (serviceCliente.buscarClientePorId(clienteId).isEmpty()) {
			System.out.println("Cliente veio nulo ou vazio");
			return new ModelAndView("redirect:/criar-conta");
		}
		ModelAndView modelAndView = new ModelAndView("enderecos");
		return modelAndView;
	}
	// Problema

	@PostMapping("/enderecos/{clienteId}")
	public ModelAndView createEndereco(@Valid EnderecoDto requisicaoEndereco, BindingResult bindingResult, Model model,
			@PathVariable Long clienteId) {
		System.out.println("Encontrando Cliente");
		Optional<Cliente> clienteSenha = serviceCliente.buscarClientePorId(clienteId);
		String clienteSenhaPassada = clienteSenha.get().getSenha();
		System.out.println(clienteSenhaPassada + "Okay");
//		if(serviceCliente.buscarClientePorId(clienteId).isEmpty()) {
//			System.out.println("Entrei if vazio");
//			return new ModelAndView("redirect:/criar-conta");
//		}
		// Busca o cliente correspondente pelo ID
		Optional<Cliente> optionalCliente = serviceCliente.buscarClientePorId(clienteId);
		if (!optionalCliente.isPresent()) {
			model.addAttribute("mensagem", "Cliente não encontrado");
			return new ModelAndView("enderecos");
		}

		if (bindingResult.hasErrors()) {
			System.out.println("Entrei em erros de Endereco");
			// model.addAttribute("msgErro", "ERRO HTML ");
			return new ModelAndView("enderecos");
		}

		// Cria o objeto Endereco e associa o cliente encontrado
		Endereco endereco = requisicaoEndereco.toEndereco();

		endereco.setCliente(optionalCliente.get());
		// Salva o Endereco
		enderecoService.criarEndereco(endereco);
		Long clienteIdConta = clienteId;
		Long enderecoId = endereco.getNrocli();
		ModelAndView modelAndView = new ModelAndView("redirect:/conta/{clienteIdConta}");
		modelAndView.addObject("clienteIdConta", clienteIdConta);
		modelAndView.addObject("endereco", enderecoId);
		modelAndView.addObject("clienteSeguranca", clienteSenhaPassada);

		// Redireciona para a página principal
		return modelAndView;
	}

	@RequestMapping("conta/{clienteIdConta}")
	public ModelAndView criarConta(ContaDto requisicaoConta, @PathParam("endereco") Endereco endereco, Model model,
			@PathVariable Long clienteIdConta) {
		model.addAttribute("contaDto", new ContaDto());
		System.out.println(endereco);
		if (endereco.getNrocli() == null) {
			serviceCliente.apagarCliente(clienteIdConta);
			return new ModelAndView("redirect:/criar-conta");
		}

		if ((serviceCliente.buscarClientePorId(clienteIdConta).isEmpty()
				&& enderecoService.buscarEnderecoPorId(clienteIdConta).isEmpty())) {
			return new ModelAndView("redirect:/criar-conta");
		}

		List<String> numeroConta = service.gerarNumerosDeConta(requisicaoConta.getAgencia(),
				requisicaoConta.getNumeroConta());
		List<String> numeroContaExistente = service.obterNumerosContasExistentes();
		numeroConta.removeAll(numeroContaExistente);
		model.addAttribute("numeroCont", numeroConta);
		model.addAttribute("clienteIdConta", clienteIdConta);

		if (requisicaoConta.getNumeroConta().isEmpty()) {
			model.addAttribute("msg", "A lista de números conta está vazia");
		} else {
			String numeroContaSelecionado = requisicaoConta.getNumeroConta().get(0);
			if (service.existeNumeroConta(numeroContaSelecionado)) {
				model.addAttribute("msg", "Número de conta já existente na base de dados!");

//                List<String> numeroContaExistente1 = service.obterNumerosContasExistentes();
				numeroConta.removeAll(numeroContaExistente);

				model.addAttribute("numeroCont", numeroConta);
				model.addAttribute("clienteIdConta", clienteIdConta);
			}
		}

		return new ModelAndView("conta");
	}

	@PostMapping("conta/{clienteIdConta}")
	public String criarConta(@Valid ContaDto requisicaoConta, BindingResult bindingResult, Model model,
			@PathVariable Long clienteIdConta) {
		if (serviceCliente.buscarClientePorId(clienteIdConta).isEmpty()) {
			System.out.println("Entrei if null");
			return "redirect:/criar-conta";
		}
		System.out.println(serviceCliente.buscarClientePorId(clienteIdConta));
		System.out.println(enderecoService.buscarEnderecoPorId(clienteIdConta));
		if (bindingResult.hasErrors()) {
			System.out.println("Entrei erros Conta");
			List<String> numeroConta = service.gerarNumerosDeConta(requisicaoConta.getAgencia(),
					requisicaoConta.getNumeroConta());

			model.addAttribute("numeroCont", numeroConta);

//        	model.addAttribute("clienteIdConta", clienteIdConta);

			String numeroContaSelecionado = requisicaoConta.getNumeroConta().get(0);
			if (service.existeNumeroConta(numeroContaSelecionado)) {
				model.addAttribute("msg", "Número de conta já existente na base de dados!");

				List<String> numeroContaExistente = service.obterNumerosContasExistentes();
				numeroConta.removeAll(numeroContaExistente);
				model.addAttribute("numeroCont", numeroConta);
//            	model.addAttribute("clienteIdConta", clienteIdConta);
			}

			return "conta";
		}

		Optional<Cliente> optionalCliente = serviceCliente.buscarClientePorId(clienteIdConta);

		if (optionalCliente.isPresent()) {
			Conta conta = requisicaoConta.toConta(service);
			conta.setCliente(optionalCliente.get());
			List<String> numeroConta = service.gerarNumerosDeConta(requisicaoConta.getAgencia(),
					requisicaoConta.getNumeroConta());

			model.addAttribute("numeroCont", numeroConta);

			model.addAttribute("clienteIdConta", clienteIdConta);

			String numeroContaSelecionado = requisicaoConta.getNumeroConta().get(0);
			if (service.existeNumeroConta(numeroContaSelecionado)) {

				System.out.println("Entrei Existe número conta");
				model.addAttribute("msg", "Número de conta já existente na base de dados!");

				List<String> numeroContaExistente = service.obterNumerosContasExistentes();
				numeroConta.removeAll(numeroContaExistente);
				model.addAttribute("numeroCont", numeroConta);
				model.addAttribute("clienteIdConta", clienteIdConta);
				return "conta";
			}
			conta.setNumeroConta(numeroContaSelecionado);

			service.criaConta(conta);

			Optional<Cliente> clienteSenha = serviceCliente.buscarClientePorId(clienteIdConta);
			String clienteSenhaPassada = clienteSenha.get().getSenha();

			Seguranca seguranca = new Seguranca();
			seguranca.setAgencia(conta.getAgencia());
			seguranca.setDac(conta.getDac());
			seguranca.setNumeroConta(conta.getNumeroConta());
			seguranca.setSenha(clienteSenhaPassada);
			seguranca.setNrocli_cliente(clienteIdConta);
			seguranca.setNrocli_conta(conta.getNrocli());

			segurancaService.criarSeguranca(seguranca);
			Optional<Cliente> cliente = serviceCliente.buscarClientePorId(clienteIdConta);
			Eventos eventos = new Eventos();
			eventos.setNome(cliente.get().getNome());
			eventos.setCpfCnpj(cliente.get().getCpfCnpj());
			eventos.setConta(conta);
			eventos.setSaldo(5000.0);
			eventos.setTipoTransferencia(conta.getTipo());
			eventoRepository.save(eventos);
//			System.out.println("Entrei e salvei");
//			serviceCliente.addCliente(formularioProgresso.getCliente());
//			enderecoService.addEndereco(formularioProgresso.getEndereco());
//			service.criaConta(formularioProgresso.getConta());

			List<String> numerosContaAtualizados = service.gerarNumerosDeConta(requisicaoConta.getAgencia(),
					requisicaoConta.getNumeroConta());
			model.addAttribute("numeroCont", numerosContaAtualizados);

//            } else {
//
//            	System.out.println("Entrei deu erri");
////            	//formularioProgresso.limparFormulario();
//            	model.addAttribute("msg", "Formulários incompletos");
//            	return "conta";
//            }

		}

		return "redirect:/login";
	}
}
