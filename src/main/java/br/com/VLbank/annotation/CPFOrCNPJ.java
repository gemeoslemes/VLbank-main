package br.com.VLbank.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CpfOrCnpjValidator.class)
@Documented
public @interface CPFOrCNPJ {
	String message() default "CPF ou CNPJ inválido";
	Class<?>[] groups() default {};
	Class <? extends Payload>[] payload() default {};
}
