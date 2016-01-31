package proyecto.modulo5.session;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import proyecto.modulo5.entity.UserEntity;

@ManagedBean
@RequestScoped
public class SessionBean
{
    public static final String KEY_SESSION = "usuario";
    
    public void checkSession(String roleType) 
    {
        String redirectUrl = "/public/Login.jsf";
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);
        
        if("Administrador".equals(roleType))
        {
            redirectUrl = "/private/Login.jsf";
        }

        if(httpSession.getAttribute(roleType +  "_id") == null)
        {
            try
            {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                String ctxPath = ((ServletContext) externalContext.getContext()).getContextPath();
                externalContext.redirect(ctxPath + redirectUrl);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
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
        return "/public/Home";
    }
}