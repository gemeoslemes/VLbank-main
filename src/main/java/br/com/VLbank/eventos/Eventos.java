package br.com.VLbank.eventos;

import br.com.VLbank.annotation.CPFOrCNPJ;
import br.com.VLbank.conta.Conta;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "evento")
@NoArgsConstructor
@AllArgsConstructor
public class Eventos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nrocli;

    @Column(name = "saldo")
    @NotNull(message = "Campo deve ser preenchido.")
    private Double saldo;


    @Column(name = "valor_transferencia")
    private Double valorTranferencia;

    @Column(name = "limite")
    private Double limite = Double.valueOf(200);

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf_cnpj", nullable = false, unique = true)
    @CPFOrCNPJ
    @Size(max=20)
//	@Pattern(regexp="^((\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})|(\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}-\\d{2}))$",
//		message="CPF ou CNPJ inválido")
    private String cpfCnpj;

    @Column(name = "banco")
    private Integer banco = 037;

    @Column(name = "tipo_transferencia")
    private String tipoTransferencia;

    @ManyToOne
    @JoinColumn(name = "nrocli_conta", nullable = false, referencedColumnName = "nrocli")
    private Conta conta;

    public Long getNrocli() {
        return nrocli;
    }

    public void setNrocli(Long nrocli) {
        this.nrocli = nrocli;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getValorTranferencia() {
        return valorTranferencia;
    }

    public void setValorTranferencia(Double valorTranferencia) {
        this.valorTranferencia = valorTranferencia;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Integer getBanco() {
        return banco;
    }

    public void setBanco(Integer banco) {
        this.banco = banco;
    }

    public String getTipoTransferencia() {
        return tipoTransferencia;
    }

    public void setTipoTransferencia(String tipoTransferencia) {
        this.tipoTransferencia = tipoTransferencia;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    @Override
    public String toString() {
        return "Eventos{" +
                "nrocli=" + nrocli +
                ", saldo=" + saldo +
                ", valorTranferencia=" + valorTranferencia +
                ", limite=" + limite +
                ", nome='" + nome + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", banco=" + banco +
                ", tipoTransferencia='" + tipoTransferencia + '\'' +
                ", conta=" + conta +
                '}';
    }
}
