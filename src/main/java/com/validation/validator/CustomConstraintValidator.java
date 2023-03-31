package com.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.validation.validator.exceptions.InvalidAmountException;
import com.validation.validator.exceptions.NullFrequencyException;


public class CustomConstraintValidator implements ConstraintValidator<ValidatorAnnotation, RegularAmount> {
    @Autowired
    Frequency frequency;

    @Override
    public void initialize(ValidatorAnnotation constraintAnnotation) {
    }

    @Override
    public boolean isValid(RegularAmount regularAmount, ConstraintValidatorContext context) {
       boolean valid=false;
       double amountEntered;
       int frequencyInWeeks;

        if(regularAmount.getAmount().isEmpty()) {
           throw new InvalidAmountException(" Amount is empty");
        }

        try {
            amountEntered = Double.parseDouble(regularAmount.getAmount());
        } catch (NumberFormatException nfe) {
            throw new InvalidAmountException(" Amount is not numeric");
        }
        
        int penceAmount =returnPenceAmount(amountEntered);

        if(regularAmount.getFrequency() == null) {
            throw new NullFrequencyException(" frequency is empty is empty");
         }
         
     switch(regularAmount.getFrequency()){    
            case WEEK: frequencyInWeeks=1;   
                        break;  
            case TWO_WEEK: frequencyInWeeks=2;
                        break;
            case FOUR_WEEK: frequencyInWeeks=4;
                        break;
            case MONTH: frequencyInWeeks=1;
                         break;   
            case QUARTER: frequencyInWeeks=13; 
                        break;  
            case YEAR: frequencyInWeeks=52;
                        break;
            default: throw new NullFrequencyException(" invalid frequency");
            }    

    if(isWeeklyAmountWholeNumberOfPence(penceAmount, frequencyInWeeks))
         valid=true;

         return valid;
    }

    int returnPenceAmount(double amount){
        if(amount % 1 != 0)
        amount=amount*100;

       return (int)amount;
    }

    boolean isWeeklyAmountWholeNumberOfPence(int amountInPence, int frequencyDivisor){
        if(amountInPence % frequencyDivisor == 0)
            return true;
         else 
            return false;
    }

}