package br.com.VLbank.annotation;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null) {
			return true;
		}
		
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		Phonenumber.PhoneNumber phoneNumber;
		try {
			phoneNumber = phoneUtil.parse(value, "BR");
		}catch (NumberParseException e) {
			return false;
		}
		
		return phoneUtil.isValidNumber(phoneNumber);
	}
	
	
}
