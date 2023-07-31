package br.com.VLbank.DTO;

import jakarta.validation.constraints.*;

public class ConfirmarDto {
    @NotBlank(message = "Campo não pode ser vazio.")
    private String senha;

    @NotNull(message = "Campo não pode ser vazio.")
    @Positive(message = "O campo número deve ser positivo.")
    @DecimalMin(value = "0", inclusive = true, message = "Digite apenas números")
    private Double valor;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
