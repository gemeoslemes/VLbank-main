package br.com.VLbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.VLbank.model.seguranca.Seguranca;

@Repository
public interface SegurancaRepositoy extends JpaRepository<Seguranca, Long>{

}
