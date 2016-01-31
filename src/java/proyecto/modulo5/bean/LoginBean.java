package proyecto.modulo5.bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import proyecto.modulo5.entity.UserEntity;
import proyecto.modulo5.session.SessionBean;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable
{
    private String nickname;
    private String password;
    
    @PersistenceContext
    private EntityManager etm;

    public LoginBean() {
    }
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public String initSession()
    {
        try
        {
            UserEntity user = (UserEntity) etm
                                        .createNamedQuery("checkUser")
                                        .setParameter("nickname", nickname)
                                        .setParameter("password", password)
                                        .getSingleResult();
            if(user != null)
            {
                SessionBean sessionBean = new SessionBean();
                sessionBean.initSession(user);
                return "/private/Principal.jsf?faces-redirect=true";
            }
        }
        catch (NoResultException ex)
        {
            FacesMessage message = new FacesMessage();
            message.setDetail("Correo y/o Contraseña incorrectos");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null,  message);
        }
        return null;
    }   
}
