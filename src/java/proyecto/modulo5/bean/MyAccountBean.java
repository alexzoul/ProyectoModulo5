package proyecto.modulo5.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import proyecto.modulo5.entity.UserEntity;
import proyecto.modulo5.session.SessionBean;

@ManagedBean
@RequestScoped
public class MyAccountBean implements Serializable
{
    private UserEntity user;
    private String registerTime;
    private String registerDate;
    
    @PersistenceContext
    private EntityManager etm;
    
    public MyAccountBean () {
    }
    
    @PostConstruct
    public void init () 
    {
        ExternalContext exContexto = FacesContext.getCurrentInstance().getExternalContext();
        UserEntity userSession = (UserEntity) exContexto.getSessionMap().get(SessionBean.KEY_SESSION);
        user = (UserEntity)  etm.createNamedQuery("findUserById")
                                .setParameter("id", userSession.getId())
                                .getSingleResult();
        
        registerTime = convertTime(user.getDate());
        registerDate = convertDate(user.getDate());
    }
    
    public String convertTime(Date date)
    {
        try
        {
            DateFormat formatTimeFinal = new SimpleDateFormat("h:mm a");
            return formatTimeFinal.format(date).toLowerCase();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }
    
    public String convertDate(Date date)
    {
        try
        {
            DateFormat formatTimeFinal = new SimpleDateFormat("dd-MM-yyy");
            return formatTimeFinal.format(date).toLowerCase();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }
        
        
    
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }
}