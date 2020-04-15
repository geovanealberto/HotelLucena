/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.hotellucena.model;


import br.cesjf.hotellucena.dao.ItensDAO;
import br.cesjf.hotellucena.dao.ReservasDAO;
import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tassio
 */
@Entity
@Table(name = "itensreservas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservas.findAll", query = "SELECT r FROM Itens_Reservas r"),
    @NamedQuery(name = "Reservas.findByIdItem", query = "SELECT r FROM Itens_Reservas r WHERE r.idItem = :idItem"),
    @NamedQuery(name = "Reservas.findByCodigoReserva", query = "SELECT r FROM Itens_Reservas r WHERE r.codigoReserva = :codigoReserva"),
    @NamedQuery(name = "Reservas.findByQuandidadeItem", query = "SELECT r FROM Itens_Reservas r WHERE r.quantidadeItem = :quantidadeItem")})

public class ItensReservas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @JoinColumn(name = "itens_idItem", referencedColumnName = "idItem")
    @ManyToOne(optional = false)
    private Itens itensidItem;
    
    @JoinColumn(name = "Reservas_codigoreserva", referencedColumnName = "codigoreserva")
    @ManyToOne(optional = false)
    private Reservas reservascodigoReserva;
    
   
    @Basic(optional = false)
    @Column(name = "quantidadeItem")
    private Integer quantidadeItem;
       
    public ItensReservas() {
       
    }

    public Itens getItensidItem() {
        return itensidItem;
    }

    public void setItensidItem(Itens itensidItens) {
        this.itensidItem = itensidItem;
    }

    public Reservas getReservascodigoReserva() {
        return reservascodigoReserva;
    }

    public void setReservascodigoReserva(Reservas reservascodigoReserva) {
        this.reservascodigoReserva = reservascodigoReserva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getItensidItem() != null ? getItensidItem().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItensReservas)) {
            return false;
        }
        ItensReservas other = (ItensReservas) object;
        if ((this.itensidItem== null && other.itensidItem!= null) || (this.itensidItem!= null && !this.itensidItem.equals(other.itensidItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.cesjf.hotellucena.model.ItensReservas[ itensidItem=" + itensidItem + "]";
    }

    /**
     * @return the quantidadeItem
     */
    public Integer getQuantidadeItem() {
        return quantidadeItem;
    }

    /**
     * @param quantidadeItem the quantidadeItem to set
     */
    public void setQuantidadeItem(Integer quantidadeItem) {
        this.quantidadeItem = quantidadeItem;
    }

}
