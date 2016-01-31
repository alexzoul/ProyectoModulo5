package proyecto.modulo5.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "checkNickname", query = "SELECT u FROM UserEntity u WHERE u.nickname=:nickname"),
    @NamedQuery(name = "checkEmail", query = "SELECT u FROM UserEntity u WHERE u.email=:email"),
    @NamedQuery(name = "checkUser", query = "SELECT u FROM UserEntity u WHERE u.nickname=:nickname AND u.password=:password"),
    @NamedQuery(name = "usuario", query = "SELECT u FROM UserEntity u WHERE u.id=:id")
})
public class UserEntity implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "paternal_name", nullable = false)
    private String paternal_name;
    
    @Column(name = "maternal_name", nullable = false)
    private String maternal_name;
    
    @Column(name = "age", nullable = false)
    private Integer age;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "nickname", nullable = false)
    private String nickname;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "folder", nullable = false)
    private String folder;
    
    @Column(name="date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user_files", fetch = FetchType.LAZY)
    private List<FileEntity> files = new ArrayList<>();



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaternal_name() {
        return paternal_name;
    }

    public void setPaternal_name(String paternal_name) {
        this.paternal_name = paternal_name;
    }

    public String getMaternal_name() {
        return maternal_name;
    }

    public void setMaternal_name(String maternal_name) {
        this.maternal_name = maternal_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<FileEntity> getFiles() {
        return files;
    }

    public void setFiles(List<FileEntity> files) {
        this.files = files;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "proyecto.modulo5.entity.UserEntity[ id=" + id + " ]";
    }
}
