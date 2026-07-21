package factory;

import stratergy.IParanthesesValidator;
import stratergy.ParanthesesValidatorUsingNetBalance;
import stratergy.ParanthesesValidatorUsingOpenClose;

public class ParanthesesValidatorFactory {
    
    private ParanthesesValidatorFactory(){}

    public static IParanthesesValidator getParanthesesValidator(String type){
        if(type.equals("Open Close")){
            return new ParanthesesValidatorUsingOpenClose();
        } else if (type.equals("Net Balance")){
            return new ParanthesesValidatorUsingNetBalance();
        } else {
            throw new RuntimeException("Invalid type");
        }
    }
}
