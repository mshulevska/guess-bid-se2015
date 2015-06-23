/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primefaces.components;
import com.sun.tools.javac.util.Convert;
import it.polimi.registration.business.security.entity.Auction;
import it.polimi.registration.business.security.entity.User;
import it.polimi.registration.business.security.entity.Userbid;
import it.polimi.registration.business.security.entity.UserbidPK;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ManagedBean
@SessionScoped
public class AllFunctions implements Serializable {
    @EJB
    private BidService bidService;
     
    @PersistenceContext
    EntityManager em;
    
    
    
    private Userbid userBid;
    private UserbidPK userBidPK;
    
    private List<Auction> auctions;
    private Auction selectedAuction;
    
    private String bidAmount;

    public String getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(String bidAmount) {
        this.bidAmount = bidAmount;
    }
    
    public List<Auction> getAuctionsList(){
        List<Auction> auctions = em.createNamedQuery("Auction.findAll").getResultList();
        System.out.println("AuctionSize: "+auctions.size());
        return auctions;
    }
    
    @PostConstruct
    public void init() {
        auctions = getAuctionsList();
    }
 
    public List<Auction> getAuctions() {
        List<Auction> temp = getAuctionsList();
        return temp;
    }
 
    public Auction getSelectedAuction() {
        return selectedAuction;
    }
 
    public void setSelectedAuction (Auction selectedAuction) {
        this.selectedAuction = selectedAuction;
    }
    
    public String goToAuction(){
        return "auction?faces-redirect=true";
    }
    
    public void updateBid(String bid,Auction auction, User user ){
        
        if(utilities.isInteger(bid)){
            int bidInt = Integer.parseInt(bid);
            Auction tempAuc = new Auction();
            User tempUser = new User();
            userBidPK = new UserbidPK();
            userBid = new Userbid();
            
            userBid.setUserbidBid(bidInt);
            
            
            tempAuc = bidService.findAuction(auction.getAuctionId());
            tempUser = bidService.findUser(user.getUserEmail());
            int totalCredit = 2 + bidInt;
            if(tempUser.getUserCredit()>= totalCredit){
                userBidPK.setUserBidEmail(tempUser.getUserEmail());
                userBidPK.setUserbidAuction(tempAuc.getAuctionId());
                userBid.setUserbidPK(userBidPK);
                
                if(bidService.findUserBid(userBidPK)){
                    bidService.updateBid(userBid);
                }else {
                    bidService.createBid(userBid);
                }
                tempUser.setUserCredit(tempUser.getUserCredit() - 2);
                bidService.updateUser(tempUser);
                goToAuction();
            }else {
                System.out.println("Not enough credit");
            }
            
            
        }else {
            System.out.println("You have to enter a number");
        }
        
    }
}
