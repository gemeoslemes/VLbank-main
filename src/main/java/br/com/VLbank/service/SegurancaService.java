package br.com.VLbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.VLbank.model.seguranca.Seguranca;
import br.com.VLbank.repository.SegurancaRepositoy;

@Service
public class SegurancaService {
	
	@Autowired
	private SegurancaRepositoy segurancaRepositoy;

	public void criarSeguranca(Seguranca seguranca) {
		segurancaRepositoy.save(seguranca);	
	}
}
