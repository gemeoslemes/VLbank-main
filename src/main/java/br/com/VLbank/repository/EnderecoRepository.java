package br.com.VLbank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.VLbank.model.Cliente;
import br.com.VLbank.model.endereco.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

	Optional<Endereco> findByNrocli(Long clienteIdConta);

	Optional<Endereco> findByClienteNrocli(Long nrocli);

}
