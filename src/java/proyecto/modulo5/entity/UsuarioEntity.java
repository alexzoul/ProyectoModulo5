package proyecto.modulo5.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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

@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "buscarUsuario", query = "SELECT u FROM UsuarioEntity u WHERE u.login=:login AND u.clave=:clave"),
    @NamedQuery(name = "usuario", query = "SELECT u FROM UsuarioEntity u WHERE u.id=:id")
})
public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "apellido_paterno", nullable = false)
    private String apellido_paterno;
    
    @Column(name = "apellido_materno", nullable = false)
    private String apellido_materno;
    
    @Column(name = "edad", nullable = false)
    private Integer edad;
    
    @Column(name = "correo", nullable = false)
    private String correo;
    
    @Column(name = "login", nullable = false)
    private String login;
    
    @Column(name = "clave", nullable = false)
    private String clave;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutaArchivos", fetch = FetchType.LAZY)
    private Collection<ArchivoEntity> archivos = new ArrayList<>();
    
    
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public Collection<ArchivoEntity> getArchivos() {
        return archivos;
    }

    public void setArchivos(Collection<ArchivoEntity> archivos) {
        this.archivos = archivos;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioEntity)) {
            return false;
        }
        UsuarioEntity other = (UsuarioEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyecto.modulo5.entity.UsuarioEntity[ id=" + id + " ]";
    }
    
}
