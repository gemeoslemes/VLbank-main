package br.com.VLbank.service;

import br.com.VLbank.conta.Conta;
import br.com.VLbank.eventos.Eventos;
import br.com.VLbank.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;
    public Eventos buscaPorId(Long nrocli) {
        return eventoRepository.buscaConta(nrocli);
    }

}
