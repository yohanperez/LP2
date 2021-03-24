/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "negocio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Negocio.findAll", query = "SELECT n FROM Negocio n"),
    @NamedQuery(name = "Negocio.findByIdNegocio", query = "SELECT n FROM Negocio n WHERE n.idNegocio = :idNegocio"),
    @NamedQuery(name = "Negocio.findByNombreN", query = "SELECT n FROM Negocio n WHERE n.nombreN = :nombreN"),
    @NamedQuery(name = "Negocio.findByDireccion", query = "SELECT n FROM Negocio n WHERE n.direccion = :direccion"),
    @NamedQuery(name = "Negocio.findByContacto", query = "SELECT n FROM Negocio n WHERE n.contacto = :contacto")})
public class Negocio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_negocio")
    private Integer idNegocio;
    @Size(max = 100)
    @Column(name = "nombre_n")
    private String nombreN;
    @Size(max = 100)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 100)
    @Column(name = "contacto")
    private String contacto;
    @OneToMany(mappedBy = "idNegocio")
    private List<Producto> productoList;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private Usuarios idUser;

    public Negocio() {
    }

    public Negocio(Integer idNegocio) {
        this.idNegocio = idNegocio;
    }

    public Integer getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(Integer idNegocio) {
        this.idNegocio = idNegocio;
    }

    public String getNombreN() {
        return nombreN;
    }

    public void setNombreN(String nombreN) {
        this.nombreN = nombreN;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    public Usuarios getIdUser() {
        return idUser;
    }

    public void setIdUser(Usuarios idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNegocio != null ? idNegocio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Negocio)) {
            return false;
        }
        Negocio other = (Negocio) object;
        if ((this.idNegocio == null && other.idNegocio != null) || (this.idNegocio != null && !this.idNegocio.equals(other.idNegocio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Entity.Negocio[ idNegocio=" + idNegocio + " ]";
    }
    
}
