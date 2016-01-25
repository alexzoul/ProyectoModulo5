package proyecto.modulo5.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ManagedBean
@RequestScoped
public class HomeBean 
{   
    @PersistenceContext
    private EntityManager etm;
    
    
    public HomeBean() {
    }
    
    public String getHelloWorld() {
        return "Hello World";
    }
            
}
