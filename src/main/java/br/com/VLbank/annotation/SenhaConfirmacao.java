package br.com.VLbank.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SenhaConfirmacaoValidator.class)
@ReportAsSingleViolation
public @interface SenhaConfirmacao {

    String message() default "Senha e confirmação de senha devem ser iguais";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

	String confirmarSenhaFieldName();

	String senhaFieldName();

}