package proyecto.modulo5.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="matchPasswordsValidator")
public class MatchPasswordValidator  implements Validator
{
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String password = value.toString();
        UIInput uiInputConfirmPassword = (UIInput) component.getAttributes().get("password_confirm_attr");
	String confirmPassword = uiInputConfirmPassword.getSubmittedValue().toString();
        
        if (!password.equals(confirmPassword))
        {
            FacesMessage message = new FacesMessage();
            message.setDetail("Los passwords no coinciden");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("register_form:password_confirm",  message);
        }
    }
}