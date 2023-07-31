package br.com.VLbank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.VLbank.model.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	boolean existsByCpfCnpj(String cpfCnpj);

	boolean existsByRg(String rg);

	boolean existsByTelefone(String telefone);

	boolean existsByEmail(String email);
	Optional<Cliente> findByNrocli(Long nrocli);
	
	Cliente findClienteByNrocli(Long nrocli);
//	Optional<Cliente> findByCliente(Long clienteId);
	
	@Query("SELECT c FROM Cliente c WHERE c.email = :email AND c.senha = :senha")
	Cliente buscaEmailAndSenhaBy(@Param("email") String email,@Param("senha") String senha);
	
}
