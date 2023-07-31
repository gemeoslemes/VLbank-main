package br.com.VLbank.exceptions;

public class SenhasNaoDiferentesException extends RuntimeException {
	public SenhasNaoDiferentesException(String mensagem) {
		super(mensagem);
	}
}
