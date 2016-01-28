package proyecto.modulo5.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "archivo")
public class ArchivoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "ruta")
    private String ruta;
    
    @ManyToOne
    @JoinColumn(name = "usuario_archivos_id")
    private UsuarioEntity rutaArchivos;

    
    
    
    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public UsuarioEntity getRutaArchivos() {
        return rutaArchivos;
    }

    public void setRutaArchivos(UsuarioEntity rutaArchivos) {
        this.rutaArchivos = rutaArchivos;
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
        if (!(object instanceof ArchivoEntity)) {
            return false;
        }
        ArchivoEntity other = (ArchivoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyecto.modulo5.entity.ArchivoEntity[ id=" + id + " ]";
    }
    
}
