package com.abclabs.client.validationcontraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class MatchFieldConstraintValidator implements ConstraintValidator<MatchField, Object> {

	private String firstField;
	private String secondField;
	private String message;

	@Override
	public void initialize(final MatchField constraintAnnotation) {
		this.firstField = constraintAnnotation.first();
		this.secondField = constraintAnnotation.second();
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		boolean valid = true;
		
		try {
			final Object firstObject = new BeanWrapperImpl(value).getPropertyValue(firstField);
			final Object secondObject = new BeanWrapperImpl(value).getPropertyValue(secondField);

			valid = (firstObject == null && secondObject == null)
					|| (firstObject != null && firstObject.equals(secondObject));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (!valid) {
			context.buildConstraintViolationWithTemplate(message).addPropertyNode(firstField)
					.addConstraintViolation().disableDefaultConstraintViolation();
		} 
		
		return valid;
	}

	
}
