package proyecto.modulo5.bean;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import proyecto.modulo5.entity.FileEntity;
import proyecto.modulo5.entity.UserEntity;
import proyecto.modulo5.session.SessionBean;


@ManagedBean
@SessionScoped
public class DownloadImgBean implements Serializable
{
    private List<String> files = new ArrayList<> ();
    private List<Integer> count = new ArrayList<>();
    private StreamedContent download;
    private Integer indexDownload;
    
    @PersistenceContext
    private EntityManager etm;
    
    
    public DownloadImgBean() {
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
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
        String mainRoute = exContexto.getInitParameter("ruta") + user.getFolder() + "/";
        
        List filesDB = etm.createNamedQuery("getFilesByIdUser").setParameter("user_id", user.getId()).getResultList();

        for(int i = 0; i < filesDB.size(); i++)
        {
            FileEntity currentFile = (FileEntity) filesDB.get(i);
            String route = mainRoute + currentFile.getName();
            files.add(route);
            count.add(i);
        }
    }
    
    public StreamedContent getResourceImg()
    {
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
            return new DefaultStreamedContent(
                        new FileInputStream(
                            files.get(Integer.valueOf(index))),"image/jpg");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    
    
    
    
    
    
    
    
    
    public StreamedContent getDownload()
    {
        try
        {   
            download = new DefaultStreamedContent(
                            new FileInputStream(
                                files.get(indexDownload)), "image/jpg", "download.jpg");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return download;
    }
    
    public void setDownload(StreamedContent download)
    {
        this.download = download;
    }
    
    public void setIndexDownload(Integer indice)
    {
        indexDownload = indice;
    }
    
}
