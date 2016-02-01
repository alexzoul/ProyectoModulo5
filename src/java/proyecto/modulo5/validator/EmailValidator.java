package proyecto.modulo5.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="emailValidator")
public class EmailValidator  implements Validator
{
    private static final String REGEX_EMAIL = "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]";
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String email = value.toString();
        Pattern mask = Pattern.compile(REGEX_EMAIL);
        Matcher match = mask.matcher(email);
        
        if(!match.matches())
        {
            FacesMessage message = new FacesMessage();
            message.setSummary("Ingrese un correo válido");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }   
    }
}