package br.com.VLbank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.VLbank.DTO.ContaDto;
import br.com.VLbank.conta.Conta;
import br.com.VLbank.repository.ContaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ContaService {
	private ContaDto conta;
	
	private List<String> numerosContaSelecionados = new ArrayList<>();
	
	@Autowired
	private ContaRepository contaRepository;
	
	public void criaConta(Conta conta) {
		numerosContaSelecionados.add(conta.getNumeroConta());
		contaRepository.save(conta);
	}

	public List<String> gerarNumerosDeConta(Integer agencia, List<String> conta) {
		List<String> numerosConta = new ArrayList<>();
//		Random gerador = new Random();
		for(int i = 1; i <= 1000; i++) {
			String numeroConta = String.format("%07d", i);
			
			Integer dac = gerarDac(agencia, numeroConta);
			numerosConta.add(numeroConta + dac);
		}
		System.out.println("Lista não atualizada: " + numerosConta);
		return numerosConta;
	}

	private Integer gerarDac(Integer agencia, String numeroConta) {
		String numeroSemDac = String.format("%d", agencia) + numeroConta;
		Integer soma = 0;
		for(int i = 0; i < 11; i++) {
			int valor = Integer.parseInt(String.valueOf(numeroSemDac.charAt(i)));
			soma += (i % 2 == 0) ? valor * 2 : valor;
		}
		
		int dv = 10 - (soma % 10);
		
		return (dv == 10) ? 0 : dv;
	}

	public Integer gerarDacs(List<String> numeroConta) {
	    // Implemente a lógica para obter o número da conta da lista de números de conta
		String numeroDaConta = String.format("%s", numeroConta.get(0));; // Substitua pela lógica para obter o número da conta

	    // Realize a soma ponderada dos dígitos do número da conta
	    int soma = 0;
	    int peso = 2;
	    for (int i = numeroDaConta.length() - 1; i >= 0; i--) {
	        int digito = Character.getNumericValue(numeroDaConta.charAt(i));
	        soma += digito * peso;
	        peso = (peso % 7) + 2;
	    }

	    // Calcule o DAC como o complemento de 11 da soma
	    int dac = (11 - (soma % 11)) % 10;

	    return dac;
	}

	public boolean existeNumeroConta(String numeroConta) {
		return contaRepository.existsByNumeroConta(numeroConta);
	}
	
	public List<String> obterNumerosContasExistentes() {
		List<String> numeroContaExistente = contaRepository.obterNumerosConta();
		return numeroContaExistente;
	}

    public Conta buscarPorCliente(Long nrocli_cliente) {
	return contaRepository.buscarCliente(nrocli_cliente);
    }

    public Conta buscarInformacoesDaTranferencia(String numeroConta, Integer dac, String tipo) {
		return contaRepository.buscarInformacoesTranferencia(numeroConta, dac, tipo);
	}

	public Optional<Conta> buscaConta(Long nrocli) {
		return contaRepository.findById(nrocli);
	}

	public Conta buscarPorNumeroConta(String conta) {
		return contaRepository.busscarInformacoesConta(conta);
	}

	@Transactional
	public void atualizaSaldoDestinatario(Double valor, Long clienteId) {
		 contaRepository.atualizaSaldoEnviado(valor, clienteId);
	}

	public void atualizaSaldoEnviado(Double valor, Long contaId) {
		contaRepository.atualizaSaldoRecebido(valor, contaId);
	}


//	public String gerarNumeroDeConta(Integer agencia, int indiceSelecionado) {
//	    String numeroConta = String.format("%07d", indiceSelecionado);
//
//	    Integer dac = gerarDac(agencia, numeroConta);
//	    String numeroContaCompleto = numeroConta + dac;
//
//	    return numeroContaCompleto;
//	}

	
	
}
