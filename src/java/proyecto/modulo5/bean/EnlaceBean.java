package proyecto.modulo5.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import proyecto.modulo5.entity.UsuarioEntity;

@ManagedBean
@RequestScoped
public class EnlaceBean 
{   
    public static final String USUARIO_LLAVE_SESSION = "usuario";
    
    private String login;
    private String clave;
    
    @PersistenceContext
    private EntityManager etm;
    
    public EnlaceBean()
    {
        
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public String verificarEnlace()
    {
        FacesContext contexto = FacesContext.getCurrentInstance();
        UsuarioEntity usuario= buscarEnlace();
        
        if(usuario != null)
        {
            contexto.getExternalContext().getSessionMap().put(USUARIO_LLAVE_SESSION, usuario);
            return "Principal";
        }
        else
        {
            FacesMessage mensaje =  new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                                    "Lo Sentimos",
                                    "Credenciales no Validas");
           contexto.addMessage(null, mensaje);
           return null;
        }
    }
    
    private UsuarioEntity buscarEnlace()
    {
        try
        {
            UsuarioEntity usuario = (UsuarioEntity) etm.createNamedQuery("buscarUsuario")
                                                        .setParameter("login", login)
                                                        .setParameter("clave", clave).getSingleResult();
            return usuario;
        }
        catch(NoResultException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public String logout()
    {
        HttpSession sesion =  (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(sesion != null)
        {
            sesion.invalidate();
        }
        return "Enlace";
    }
}
