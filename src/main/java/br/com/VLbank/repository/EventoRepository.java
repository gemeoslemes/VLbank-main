package br.com.VLbank.repository;

import br.com.VLbank.eventos.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Eventos, Long> {

    @Query("SELECT e FROM Eventos e WHERE e.nrocli = :nrocli_conta")
    Eventos buscaConta(@Param("nrocli_conta") Long nrocliCliente);
}
