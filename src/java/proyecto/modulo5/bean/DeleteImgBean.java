package proyecto.modulo5.bean;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.UserTransaction;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import proyecto.modulo5.entity.FileEntity;
import proyecto.modulo5.entity.UserEntity;
import proyecto.modulo5.session.SessionBean;


@ManagedBean
@SessionScoped
public class DeleteImgBean implements Serializable
{
    private List<FileEntity> files = new ArrayList<> ();
    private List<Integer> count = new ArrayList<>();

    @PersistenceContext
    private EntityManager etm;
    
    @Resource
    private UserTransaction ust;
    
    public DeleteImgBean() {
    }


    
    public List<Integer> getCount() {
        return count;
    }

    public void setCount(List<Integer> count) {
        this.count = count;
    }
    

    
    public void findFiles()
    {
        files.clear();
        count.clear();
        
        ExternalContext exContexto = FacesContext.getCurrentInstance().getExternalContext();
        UserEntity user = (UserEntity) exContexto.getSessionMap().get(SessionBean.KEY_SESSION);
        List filesDB = etm.createNamedQuery("getFilesByIdUser").setParameter("user_id", user.getId()).getResultList();

        for(int i = 0; i < filesDB.size(); i++)
        {
            FileEntity currentFile = (FileEntity) filesDB.get(i);
            
            files.add(currentFile);
            count.add(i);
        }
    }
    
    public void deleteFile(Integer indice)
    {
        try
        {
            ust.begin();
            FileEntity currentFile = (FileEntity) etm.createNamedQuery("getFileById").setParameter("id", files.get(indice).getId()).getSingleResult();
            etm.remove(currentFile);
            ust.commit();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public StreamedContent getResourceImg()
    {
        ExternalContext exContexto = FacesContext.getCurrentInstance().getExternalContext();
        UserEntity user = (UserEntity) exContexto.getSessionMap().get(SessionBean.KEY_SESSION);
        String index = FacesContext.getCurrentInstance()
                                    .getExternalContext()
                                    .getRequestParameterMap()
                                    .get("index");
        if(index == null)
        {
            return new DefaultStreamedContent(FacesContext
                                        .getCurrentInstance()
                                        .getExternalContext()
                                        .getResourceAsStream("/nulo.jpg")
                                        ,"image/jpg");
        }
        try
        {
            String routeImg = exContexto.getInitParameter("ruta") + user.getFolder() + "/" + files.get(Integer.valueOf(index)).getName();
            return new DefaultStreamedContent(
                        new FileInputStream(routeImg),"image/jpg");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    
}
