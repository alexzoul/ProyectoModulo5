package proyecto.modulo5.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="nicknameValidator")
public class NicknameValidator  implements Validator
{
    private static final String REGEX_NAME = "[A-Za-zñÑ0-9]{8,20}";
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String name = value.toString();
        Pattern mask = Pattern.compile(REGEX_NAME);
        Matcher match = mask.matcher(name);
        
        if(!match.matches())
        {
            FacesMessage message = new FacesMessage();
            message.setSummary("Debe contener minimo 8 letras y/o numeros");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }   
    }
}