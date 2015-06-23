/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primefaces.components;

import it.polimi.registration.business.security.entity.Auction;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Milica
 */
@Stateless
public class AuctionService {
    
    @PersistenceContext
    private EntityManager em;
    
    public void createAuction(Auction auc){
        em.persist(auc);
    }
}
