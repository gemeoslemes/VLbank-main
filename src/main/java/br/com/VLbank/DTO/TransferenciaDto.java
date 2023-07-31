package br.com.VLbank.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TransferenciaDto {
    private Integer agencia = 7199;

    @NotNull(message = "Campo deve ser preenchido.")
    private Integer dac;

    @NotBlank(message = "Campo deve ser preenchido.")
    private String numeroConta;
    private Integer banco = 031;

    private String tipo;

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Integer getDac() {
        return dac;
    }

    public void setDac(Integer dac) {
        this.dac = dac;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Integer getBanco() {
        return banco;
    }

    public void setBanco(Integer banco) {
        this.banco = banco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
