/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primefaces.components;

import it.polimi.registration.business.security.boundary.UserManager;
import it.polimi.registration.business.security.entity.Auction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;


@ManagedBean(name = "auctionController")
@RequestScoped
public class AuctionController {
    @EJB
    UserManager um;
    
    @EJB
    private AuctionService as;
    
    private Auction newAuction;
    
    private Date date1;
    
    @PostConstruct
    public void init(){
        newAuction = new Auction();
    }
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }
 
    public Date getDate1() {
        return date1;
    }
 
    public void setDate1(Date date1) {
        this.date1 = date1;
    }
    public Auction getNewAuction() {
        return newAuction;
    }

    public void setNewAuction(Auction newAuction) {
        this.newAuction = newAuction;
    }
    
    public String createAuction() {
        newAuction.setAuctionEndtime(date1);
        newAuction.setUserAuctionEmail(um.getLoggedUser());
        as.createAuction(newAuction);
        return "index.xhtml?faces-redirect=true";
    }
}
