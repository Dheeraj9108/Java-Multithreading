import factory.ParanthesesValidatorFactory;
import stratergy.IParanthesesValidator;

public class App {
    public static void main(String[] args) throws Exception {
        IParanthesesValidator validator = ParanthesesValidatorFactory.getParanthesesValidator("Open Close"); 
        boolean isValid = validator.validate("((((()()))))", 3);
        System.out.println(isValid);
    }
}
