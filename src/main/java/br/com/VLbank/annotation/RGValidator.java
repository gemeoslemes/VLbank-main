package br.com.VLbank.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RGValidator implements ConstraintValidator<RG, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		System.out.println(value);
		if(value == null) {
			return true;
		}
		String value1= value.replaceAll("[^0-9]", "");
		//RG do Rio de Janeiro e o de São Paulo.
		if(value1.length() == 9) {
			int soma = 0;
			int contador = 9;
			for(int i = 0; i < 8; i++) {
				int algarismo = Character.getNumericValue(value1.charAt(i));
				soma = soma + (contador * algarismo);
				contador -= 1;
			}
			
			int resto = soma % 11;
			int digitoValidador;
			if(resto == 10) {
				 digitoValidador = 0;
				return digitoValidador == Character.getNumericValue(value1.charAt(8));
				
			} else {
				digitoValidador = resto;
				return digitoValidador == Character.getNumericValue(value1.charAt(8));
			
			}
			
		}
		//RG de Minas
//		if(value1.length() != 8) {
//			int soma = 0;
//			int contador = 2;
//			for(int i = 0; i < 7; i++) {
//				int algarismo = Character.getNumericValue(value1.charAt(i));
//				soma = soma + (contador * algarismo);
//				if(contador > 2) {
//					contador -= 2;
//				}
//				contador += 1;
//				int resto = soma % 10;
//				if(resto == Character.getNumericValue(value1.charAt(7))) {
//					return true;
//				}
//				
//			}
//		}
		
		return false;
		

	}	

}
