package br.com.VLbank.annotation;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SenhaConfirmacaoValidator implements ConstraintValidator<SenhaConfirmacao, Object> {

	private String senhaFieldName;
	private String confirmarSenhaFieldName;

	@Override
	public void initialize(SenhaConfirmacao constraintAnnotation) {
		senhaFieldName = constraintAnnotation.senhaFieldName();
		confirmarSenhaFieldName = constraintAnnotation.confirmarSenhaFieldName();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapper wrapper = new BeanWrapperImpl(value);
		String senha = (String) wrapper.getPropertyValue(senhaFieldName);
		String confirmarSenha = (String) wrapper.getPropertyValue(confirmarSenhaFieldName);

		if (senha == null || confirmarSenha == null || !senha.equals(confirmarSenha)) {
			context.buildConstraintViolationWithTemplate("As senhas não conferem")
					.addPropertyNode(confirmarSenhaFieldName).addConstraintViolation();
			return false;
		}

		return true;
	}

}