package br.com.VLbank.controllers;

import br.com.VLbank.DTO.ConfirmarDto;
import br.com.VLbank.DTO.TransferenciaDto;
import br.com.VLbank.conta.Conta;
import br.com.VLbank.eventos.Eventos;
import br.com.VLbank.model.Cliente;
import br.com.VLbank.service.ClienteService;
import br.com.VLbank.service.ContaService;
import br.com.VLbank.service.EventoService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class EventoController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ContaService contaService;

    @RequestMapping("/vlbank/{clienteId}")
    public String vlbank(@PathVariable(name = "clienteId") Long clienteId, Model model) {
        if (clienteId != null) {
            // Use o clienteId para buscar o objeto Cliente no banco de dados
            Optional<Cliente> cliente = clienteService.buscarClientePorId(clienteId);
            System.out.println(cliente);
        }
        System.out.println(clienteId);
        Optional<Cliente> cliente = clienteService.buscarClientePorId(clienteId);

        Conta conta = contaService.buscarPorCliente(cliente.get().getNrocli());

        Eventos eventos = eventoService.buscaPorId(cliente.get().getNrocli());

        Double saldo = eventos.getSaldo();
        String nomeCliente = cliente.get().getNome();
        Integer agencia = conta.getAgencia();
        Integer banco = conta.getBanco();

        model.addAttribute("nomeCliente", nomeCliente);
        model.addAttribute("agencia", agencia);
        model.addAttribute("banco", banco);
        model.addAttribute("saldo", saldo);
        System.out.println(cliente.toString());
        return "vlbank";
    }


    @PostMapping("/vlbank/{clienteId}")
    public ModelAndView processarFormulario(@PathVariable(name = "clienteId") Long clienteId, Model model, String acao) {
        if (acao != null && acao.equals("transferencia")) {
            System.out.println("AAAAAA " + clienteId);
            ModelAndView mv = new ModelAndView("redirect:/transferencia/{clienteId}");
            mv.addObject("clienteId", clienteId);
            return mv;
        } else if (acao != null && acao.equals("extrato")) {
            ModelAndView mv = new ModelAndView("redirect:/extrato/{clienteId}");
            mv.addObject("clienteId", clienteId);
            return mv;
        } else {
            Optional<Cliente> cliente = clienteService.buscarClientePorId(clienteId);

            Conta conta = contaService.buscarPorCliente(cliente.get().getNrocli());

            Eventos eventos = eventoService.buscaPorId(cliente.get().getNrocli());

            Double saldo = eventos.getSaldo();
            String nomeCliente = cliente.get().getNome();
            Integer agencia = conta.getAgencia();
            Integer banco = conta.getBanco();

            model.addAttribute("nomeCliente", nomeCliente);
            model.addAttribute("agencia", agencia);
            model.addAttribute("banco", banco);
            model.addAttribute("saldo", saldo);
            System.out.println(cliente.toString());
            return new ModelAndView("vlbank");
        }
    }

    @RequestMapping("/transferencia/{clienteId}")
    public String transferencia(@Valid TransferenciaDto requisicaoTransferencia, BindingResult bindingResult, Model model, @PathVariable(name = "clienteId") Long clienteId) {
        return "transferencia";
    }

    @PostMapping("/transferencia/{clienteId}")
    public ModelAndView criarConta(@Valid TransferenciaDto requisicaoTransferencia, BindingResult bindingResult, Model model, @PathVariable(name = "clienteId") Long clienteId){
        if (bindingResult.hasErrors()) {
            return new ModelAndView("transferencia");
        }

        Optional<Cliente> cliente = clienteService.buscarClientePorId(clienteId);

        Conta conta = contaService.buscarInformacoesDaTranferencia(requisicaoTransferencia.getNumeroConta(), requisicaoTransferencia.getDac(), requisicaoTransferencia.getTipo());
        System.out.println("Conta: " + conta);
        if (conta == null) {
            System.out.println("Entrei");
            System.out.println(requisicaoTransferencia.getBanco());
            model.addAttribute("transferenciaDto", requisicaoTransferencia);
            model.addAttribute("msg", "Destinatário não encontrado.");

            return new ModelAndView("transferencia");
        }

        model.addAttribute("clienteValido", "Cliente válido");

        ModelAndView modelAndView = new ModelAndView("redirect:/confirmar/{clienteId}");
        modelAndView.addObject("clienteId", clienteId);
        Long contaId = conta.getNrocli();
        modelAndView.addObject("conta", contaId);
        return modelAndView;
    }

    @RequestMapping("/confirmar/{clienteId}")
    public ModelAndView mostrarTransferencia(@Valid ConfirmarDto confirmar, BindingResult bindingResult, Model model, @PathVariable(name = "clienteId") Long clienteId,
                                             @PathParam("conta") Long conta) {
        System.out.println(conta);
        Optional<Conta> contaId = contaService.buscaConta(conta);
        Long idConta = contaId.get().getNrocli();
        ModelAndView mv = new ModelAndView("confirmar");
        mv.addObject("idConta", idConta);
        return mv;
    }

    @PostMapping("/confirmar/{clienteId}")
    public ModelAndView executarTransferencia(@Valid ConfirmarDto confirmar, BindingResult bindingResult, Model model, @PathVariable(name = "clienteId") Long clienteId,
                                              @RequestParam("conta") Long contaId) {
        System.out.println(contaId);
        Conta informacoesDoClienteDeEnvio = contaService.buscarPorCliente(clienteId);
        Long numeroConta = informacoesDoClienteDeEnvio.getCliente().getNrocli();
        System.out.println("CONTAAAAAAAAAAAAAAAAAAAAAAAAAAA" + numeroConta);
        Eventos eventosT = eventoService.buscaPorId(numeroConta);
        System.out.println(eventosT);

        Optional<Cliente> cliente = clienteService.buscarClientePorId(clienteId);
        if (!cliente.get().getSenha().equals(confirmar.getSenha())) {
            Optional<Conta> contaId2 = contaService.buscaConta(contaId);
            Long idConta = contaId2.get().getNrocli();
            ModelAndView mv = new ModelAndView("confirmar");
            mv.addObject("idConta", idConta);
            mv.addObject("msg", "Senha inválida");
            return mv;
        }



        if (bindingResult.hasErrors()) {
            return new ModelAndView("confirmar");
        } else {
            contaService.atualizaSaldoDestinatario(confirmar.getValor(), clienteId);
            contaService.atualizaSaldoEnviado(confirmar.getValor(), contaId);
            ModelAndView mv = new ModelAndView("redirect:/transferencia/{clienteId}");
            mv.addObject("clienteId", clienteId);

            return mv;
        }



    }
    // Outros métodos do controlador
}



