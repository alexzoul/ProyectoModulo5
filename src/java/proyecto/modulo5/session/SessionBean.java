package proyecto.modulo5.session;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import proyecto.modulo5.entity.UserEntity;

@ManagedBean
@RequestScoped
public class SessionBean
{
    public static final String KEY_SESSION = "usuario";
    
    public boolean checkSession() 
    {
        ExternalContext exContexto = FacesContext.getCurrentInstance().getExternalContext();
        return exContexto.getSessionMap().containsKey(SessionBean.KEY_SESSION);
    }
   
    public void initSession(UserEntity userEntity) 
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getSessionMap().put(KEY_SESSION, userEntity);
    }    
    
    public String closeSession() 
    {
        HttpSession currentSession = (HttpSession) FacesContext
                                        .getCurrentInstance()
                                        .getExternalContext()
                                        .getSession(false);
        currentSession.invalidate();
        return "/public/Login";
    }
}