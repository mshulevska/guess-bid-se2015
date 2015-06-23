/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polimi.registration.business.security.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Milica
 */
@Entity
@Table(name = "auction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auction.findAll", query = "SELECT a FROM Auction a"),
    @NamedQuery(name = "Auction.findByAuctionId", query = "SELECT a FROM Auction a WHERE a.auctionId = :auctionId"),
    @NamedQuery(name = "Auction.findByAuctionEndtime", query = "SELECT a FROM Auction a WHERE a.auctionEndtime = :auctionEndtime"),
    @NamedQuery(name = "Auction.findByAuctionProduct", query = "SELECT a FROM Auction a WHERE a.auctionProduct = :auctionProduct"),
    @NamedQuery(name = "Auction.findByAuctionDesc", query = "SELECT a FROM Auction a WHERE a.auctionDesc = :auctionDesc"),
    @NamedQuery(name = "Auction.findByAuctionTitle", query = "SELECT a FROM Auction a WHERE a.auctionTitle = :auctionTitle")})
public class Auction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "auction_id")
    private Integer auctionId;
    @Column(name = "auction_endtime")
    @Temporal(TemporalType.DATE)
    private Date auctionEndtime;
    @Size(max = 110)
    @Column(name = "auction_product")
    private String auctionProduct;
    @Size(max = 200)
    @Column(name = "auction_desc")
    private String auctionDesc;
    @Size(max = 45)
    @Column(name = "auction_title")
    private String auctionTitle;
    @JoinColumn(name = "user_auction_email", referencedColumnName = "user_email")
    @ManyToOne(optional = false)
    private User userAuctionEmail;

    public Auction() {
    }

    public Auction(Integer auctionId) {
        this.auctionId = auctionId;
    }

    public Integer getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Integer auctionId) {
        this.auctionId = auctionId;
    }

    public Date getAuctionEndtime() {
        return auctionEndtime;
    }

    public void setAuctionEndtime(Date auctionEndtime) {
        this.auctionEndtime = auctionEndtime;
    }

    public String getAuctionProduct() {
        return auctionProduct;
    }

    public void setAuctionProduct(String auctionProduct) {
        this.auctionProduct = auctionProduct;
    }

    public String getAuctionDesc() {
        return auctionDesc;
    }

    public void setAuctionDesc(String auctionDesc) {
        this.auctionDesc = auctionDesc;
    }

    public String getAuctionTitle() {
        return auctionTitle;
    }

    public void setAuctionTitle(String auctionTitle) {
        this.auctionTitle = auctionTitle;
    }

    public User getUserAuctionEmail() {
        return userAuctionEmail;
    }

    public void setUserAuctionEmail(User userAuctionEmail) {
        this.userAuctionEmail = userAuctionEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auctionId != null ? auctionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auction)) {
            return false;
        }
        Auction other = (Auction) object;
        if ((this.auctionId == null && other.auctionId != null) || (this.auctionId != null && !this.auctionId.equals(other.auctionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.polimi.registration.business.security.entity.Auction[ auctionId=" + auctionId + " ]";
    }
    
}
