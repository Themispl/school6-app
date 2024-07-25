package gr.aueb.cf.schoolapp.validator;

import gr.aueb.cf.schoolapp.dto.BaseDTO;

import java.util.HashMap;
import java.util.Map;

public class TeacherValidator<T> {
    private TeacherValidator(){

    }

    public static <T extends BaseDTO> Map<String, String> validate(T dto){
        Map<String, String> errors = new HashMap<>();

        if(dto.getFirstname().length() < 2 || dto.getFirstname().length() > 32){
            errors.put("firstname,", "Fistname should be between 2 and 32 charachters");
        }
        if(dto.getLastname().length() < 2 || dto.getLastname().length() > 32){
            errors.put("Lastname,", "Lastname should be between 2 and 32 charachters");
        }

        if(dto.getFirstname().matches("^.*\\s+.*$")){
            errors.put("Fistname,", "Firstname should not include whitespaces");
        }
        if(dto.getLastname().matches("^.*\\s+.*$")){
            errors.put("Lastname,", "Lastname should not include whitespaces");
        }
        return errors;
    }
}
