package com.eswinpalacios.clients.utils;

import com.eswinpalacios.clients.model.Client;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ClientValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;

        if(client.getDateOfBirth() != null && client.getAge() == 0){
            ClientUtil.completeAgeFromDateOfBirth(client);
        }

        if(validateMatchAgeWithDateOfBirth(client.getDateOfBirth(), client.getAge())){
            errors.rejectValue("age", "AgeError", "age not match with date of birth");
        }

    }

    private boolean validateMatchAgeWithDateOfBirth(Date dateOfBirth, int age){
        if(dateOfBirth == null || age <0){
            return true;
        }

        Date dateNow = new Date();
        long diffTime = dateNow.getTime() - dateOfBirth.getTime();
        int ageDaysNow = (int) TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS);
        int ageYear = ageDaysNow / 365;

        return ageYear != age;
    }
}
