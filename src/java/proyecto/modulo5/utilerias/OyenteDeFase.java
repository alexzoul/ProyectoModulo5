package proyecto.modulo5.utilerias;


import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import proyecto.modulo5.bean.EnlaceBean;

public class OyenteDeFase implements PhaseListener
{


    @Override
    public PhaseId getPhaseId() 
    {
        return PhaseId.ANY_PHASE;
    }
    
    private boolean buscarUsuario(FacesContext contexto)
    {
        ExternalContext exContexto = contexto.getExternalContext();
        return (exContexto.getSessionMap().containsKey(EnlaceBean.USUARIO_LLAVE_SESSION));
    }
    
    
    private boolean peticionVista(PhaseEvent evento)
    {
        boolean paginaValida = false;
        String[] viewId = evento.getFacesContext().getViewRoot().getViewId().split("/");
        
        if(viewId[viewId.length-1].equals("index.xhtml")
                || viewId[viewId.length -1].equals("Enlace.xhtml")
                 || viewId[viewId.length -1].equals("Registro.xhtml"))
        {
            paginaValida = true;
        }
        return paginaValida;
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext contexto = event.getFacesContext();
        if(buscarUsuario(contexto))
        {
            return;
        }
        else
        {
            if(!peticionVista(event))
            {
                contexto.getApplication().getNavigationHandler().handleNavigation(contexto, null, "Enlace.jsf");
            }
        }
        }

    @Override
    public void beforePhase(PhaseEvent event) {
        }
    
}
