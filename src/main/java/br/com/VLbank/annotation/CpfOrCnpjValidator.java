package br.com.VLbank.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfOrCnpjValidator implements ConstraintValidator<CPFOrCNPJ, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        String cleanValue = value.replaceAll("[^0-9]", "");

        if (cleanValue.length() == 11) {
            // Validar CPF
            int sum = 0;
            int[] weights = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

            for (int i = 0; i < 9; i++) {
                sum += Integer.parseInt(cleanValue.substring(i, i + 1)) * weights[i + 1];
            }

            int remainder = sum % 11;
            int digit1 = remainder < 2 ? 0 : 11 - remainder;

            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += Integer.parseInt(cleanValue.substring(i, i + 1)) * weights[i];
            }

            remainder = sum % 11;
            int digit2 = remainder < 2 ? 0 : 11 - remainder;
            
            
            return Integer.parseInt(cleanValue.substring(9, 10)) == digit1
                    && Integer.parseInt(cleanValue.substring(10, 11)) == digit2;

        } else if (cleanValue.length() == 14) {
            // Validar CNPJ
            int[] weights = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
            int sum = 0;
            for (int i = 0; i < 12; i++) {
                sum += Integer.parseInt(cleanValue.substring(i, i + 1)) * weights[i + 1];
            }

            int remainder = sum % 11;
            int digit1 = remainder < 2 ? 0 : 11 - remainder;

            sum = 0;
            for (int i = 0; i < 13; i++) {
                sum += Integer.parseInt(cleanValue.substring(i, i + 1)) * weights[i];
            }

            remainder = sum % 11;
            int digit2 = remainder < 2 ? 0 : 11 - remainder;

            return Integer.parseInt(cleanValue.substring(12, 13)) == digit1
                    && Integer.parseInt(cleanValue.substring(13, 14)) == digit2;

        } else {
            return false;
        }
    }
}
