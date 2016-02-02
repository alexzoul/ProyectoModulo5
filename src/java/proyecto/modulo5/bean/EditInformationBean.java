package proyecto.modulo5.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import proyecto.modulo5.entity.UserEntity;
import proyecto.modulo5.session.SessionBean;

@ManagedBean
@ViewScoped
public class EditInformationBean implements Serializable
{
    @PersistenceContext
    private EntityManager etm;
    
    @Resource
    private UserTransaction ust;
    
    private UserEntity userEntity = new UserEntity();
    
    public EditInformationBean() {
    }
    
    @PostConstruct
    public void init()
    {
        ExternalContext exContexto = FacesContext.getCurrentInstance().getExternalContext();
        UserEntity userSession = (UserEntity) exContexto.getSessionMap().get(SessionBean.KEY_SESSION);
        userEntity = (UserEntity)  etm.createNamedQuery("findUserById")
                                .setParameter("id", userSession.getId())
                                .getSingleResult();
    }
    
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    
    
    public String updateUser()
    {   
        ExternalContext exContexto = FacesContext.getCurrentInstance().getExternalContext();
        UserEntity userSession = (UserEntity) exContexto.getSessionMap().get(SessionBean.KEY_SESSION);

        UserEntity user = (UserEntity)  etm.createNamedQuery("findUserById").setParameter("id", userSession.getId()).getSingleResult();

        if(!this.userEntity.getEmail().equals(user.getEmail()))
        {
            try
            {      
                etm.createNamedQuery("checkEmail")
                   .setParameter("email", this.userEntity.getEmail())
                   .getSingleResult();

                FacesMessage message = new FacesMessage();
                message.setDetail("El email ya se encuentra registrado");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage("register_form:email",  message);
            }
            catch(NoResultException ex) {   
            }
        }
        else if(!this.userEntity.getNickname().equals(user.getNickname()))   
        {
            try
            {
                etm.createNamedQuery("checkNickname")
                   .setParameter("nickname", this.userEntity.getNickname())
                   .getSingleResult();

                FacesMessage message = new FacesMessage();
                message.setDetail("El nickname ya se encuentra registrado");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage("register_form:nickname",  message);
            }
            catch(Exception ex) {
            }
        }
        else if(!this.userEntity.getPassword().equals(user.getPassword()))
        {
            FacesMessage message = new FacesMessage();
            message.setDetail("Contraseña incorrecta");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("register_form:password",  message); 
        }
        else
        {
            try
            {
                ust.begin();    
                etm.merge(this.userEntity);
                ust.commit();
                return "/private/MyAccount.jsf?faces-redirect=true";
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
}
