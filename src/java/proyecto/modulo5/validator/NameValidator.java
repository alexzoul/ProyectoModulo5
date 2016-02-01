package proyecto.modulo5.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="nameValidator")
public class NameValidator  implements Validator
{
    private static final String REGEX_NAME = "[A-Za-záéíóúñÁÉÍÓÚÑ ]{2,}";
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String name = value.toString();
        Pattern mask = Pattern.compile(REGEX_NAME);
        Matcher match = mask.matcher(name);
        
        if(!match.matches())
        {
            FacesMessage message = new FacesMessage();
            message.setSummary("Ingrese un Nombre válido");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }   
    }
}