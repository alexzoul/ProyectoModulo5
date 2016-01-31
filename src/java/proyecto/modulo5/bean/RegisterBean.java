package proyecto.modulo5.bean;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import proyecto.modulo5.entity.UserEntity;
import proyecto.modulo5.session.SessionBean;

@ManagedBean
@ViewScoped
public class RegisterBean implements Serializable
{
    @PersistenceContext
    private EntityManager etm;
    
    @Resource
    private UserTransaction ust;
    
    private UserEntity userEntity = new UserEntity();
    
    public RegisterBean() {
    }
    
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    
    
    public String registerUser()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String mainRoute = facesContext.getExternalContext().getInitParameter("ruta");
        
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
        catch(NoResultException exEmail)
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
            catch(NoResultException exNickname)
            {   
                try
                {
                    String routeFolder = mainRoute + this.userEntity.getNickname();
                    File fileFolder = new File(mainRoute + this.userEntity.getNickname());
                    fileFolder.mkdir();

                    ust.begin();    
                    this.userEntity.setDate(new Date());
                    this.userEntity.setFolder(routeFolder);
                    etm.persist(this.userEntity);
                    ust.commit();

                    SessionBean sessionBean = new SessionBean();
                    sessionBean.initSession(userEntity);
                    return "/private/Principal.jsf?faces-redirect=true";
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            facesContext.addMessage(null, new FacesMessage(
                                        FacesMessage.SEVERITY_ERROR, 
                                        "Ocurrio un problema",""));
        }
        return null;
    }
    
    private void createFolder()
    {
       /*
        rutaUsuario.setRuta(userEntity.getLogin() + userEntity.getClave().substring(3));
        rutaUsuario.setRutaArchivos(userEntity);
        userEntity.getArchivos().add(rutaUsuario);
        */
    }
}
