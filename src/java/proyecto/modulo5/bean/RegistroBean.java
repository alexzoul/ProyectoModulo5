package proyecto.modulo5.bean;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import proyecto.modulo5.entity.UsuarioEntity;

@ManagedBean
@RequestScoped
public class RegistroBean 
{
    
    public RegistroBean() {
    }
    
    @PersistenceContext
    private EntityManager etm;
    
    @Resource
    private UserTransaction ust;
    
    private UsuarioEntity usuario = new UsuarioEntity();
    
    public void almacenar(ActionEvent evento)
    {
        try
        {
            ust.begin();
            etm.persist(usuario);
            ust.commit();
            usuario = new UsuarioEntity();
        }
        catch(Exception ex)
        {
            errorDB(ex);
        }
        
    }
    
    public void errorDB(Exception ex)
    {
        ex.printStackTrace();
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage mensaje =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un problema en DB", "Consulte con el admin");
        contexto.addMessage(null, mensaje);
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
    
    
    
}
