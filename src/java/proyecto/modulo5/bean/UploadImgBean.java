package proyecto.modulo5.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.primefaces.event.FileUploadEvent;
import proyecto.modulo5.entity.FileEntity;
import proyecto.modulo5.entity.UserEntity;
import proyecto.modulo5.session.SessionBean;

@ManagedBean
@SessionScoped
public class UploadImgBean implements Serializable 
{
    @PersistenceContext
    private EntityManager etm;
    
    @Resource
    private UserTransaction ust;
    
    
    public UploadImgBean() {
    }
    
    public void uploadImg(FileUploadEvent event) 
    {
        int read = 0;
        byte[] bytes = new byte[1024];

        ExternalContext exContexto = FacesContext.getCurrentInstance().getExternalContext();
        UserEntity user = (UserEntity) exContexto.getSessionMap().get(SessionBean.KEY_SESSION);
        String mainRoute = exContexto.getInitParameter("ruta") + user.getFolder() + "/";
        String fileName = event.getFile().getFileName();

        try 
        {   
            InputStream input = event.getFile().getInputstream();
            OutputStream output = new FileOutputStream(new File(mainRoute + fileName));
            
            while((read = input.read(bytes)) != -1)
            {
                output.write(bytes, 0 , read);
            }
            
            input.close();
            output.flush();
            output.close();

            FileEntity fileEntity = new FileEntity();
            fileEntity.setName(fileName);
            fileEntity.setFile_user(user);

            ust.begin();    
            etm.persist(fileEntity);
            ust.commit();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }    
}
