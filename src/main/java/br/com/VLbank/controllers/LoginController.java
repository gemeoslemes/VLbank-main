package br.com.VLbank.controllers;

import java.util.Optional;

import br.com.VLbank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.VLbank.login.Login;
import br.com.VLbank.model.Cliente;
import br.com.VLbank.model.endereco.Endereco;
import br.com.VLbank.service.EnderecoService;
import br.com.VLbank.service.LoginService;
import jakarta.validation.Valid;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/login")
	public String login(Login login) {
		return "/login";
	}

	@PostMapping("/login")
	public ModelAndView fazerLogin(@Valid Login login, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        return new ModelAndView("login");
	    }

	    Cliente cliente = loginService.buscaEmailAndSenhaDeCliente(login.getEmail(), login.getSenha());


		if(cliente == null) {
			System.out.println(cliente + " está nulo");
			model.addAttribute("msg", "Email ou senha inválidos");
	        return new ModelAndView("login");
	    }
		Optional<Cliente> clienteId = clienteService.buscarClientePorId(cliente.getNrocli());

	    System.out.println(cliente.getNrocli() + " ************************");
	    Optional<Endereco> endereco = enderecoService.buscarClientePorId(cliente.getNrocli());

	    System.out.println(cliente + " Endereco ************************");

	    if (endereco.isEmpty()) {
	        System.out.println(cliente + " está nulo");
	        model.addAttribute("msg", "Endereço não foi preenchido");
	        return new ModelAndView("login");
	    }

		model.addAttribute("clienteId", clienteId);
		ModelAndView mv = new ModelAndView("redirect:/vlbank/{clienteId}");
		mv.addObject("clienteId", clienteId.get().getNrocli());
	    return mv;
	}

}
