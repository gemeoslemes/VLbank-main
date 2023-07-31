package br.com.VLbank.repository;

import java.util.List;
import java.util.Optional;

import br.com.VLbank.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.VLbank.conta.Conta;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

	boolean existsByNumeroConta(String numeroConta);

	@Query("SELECT c.numeroConta FROM Conta c")
	List<String> obterNumerosConta();

	@Query("SELECT c FROM Conta c WHERE c.cliente.nrocli = :nrocli_cliente")
	Conta buscarCliente(@Param("nrocli_cliente") Long nrocliCliente);

	@Query("SELECT c FROM Conta c WHERE c.numeroConta = :numeroConta AND c.dac = :dac AND c.tipo = :tipo")
	Conta buscarInformacoesTranferencia(@Param("numeroConta") String numeroConta, @Param("dac") Integer dac, @Param("tipo") String tipo);

	@Query("SELECT c FROM Conta c WHERE c.nrocli = :nrocli_cliente")
	Conta buscarContaDestino(@Param("nrocli_cliente")Long nrocli);

	@Query("SELECT c FROM Conta c WHERE c.numeroConta = :numeroConta")
	Conta busscarInformacoesConta(@Param("numeroConta") String numeroConta);

	@Modifying
	@Query("UPDATE Eventos e SET e.saldo = e.saldo - :valor WHERE e.nrocli = :clienteId")
	void atualizaSaldoEnviado(@Param("valor") Double valor, @Param("clienteId") Long clienteId);

	@Modifying
	@Query("UPDATE Eventos e SET e.saldo = e.saldo + :valor WHERE e.nrocli = :contaId")
	void atualizaSaldoRecebido(@Param("valor") Double valor, @Param("contaId") Long contaId);

}
