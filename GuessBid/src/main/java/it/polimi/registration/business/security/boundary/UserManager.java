/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polimi.registration.business.security.boundary;

import it.polimi.registration.business.security.entity.Auction;
import it.polimi.registration.business.security.entity.Group;
import it.polimi.registration.business.security.entity.User;
import it.polimi.registration.business.security.entity.Userbid;
import java.security.Principal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import primefaces.components.utilities;

/**
 *
 * @author miglie
 */
@Stateless
public class UserManager {

    @PersistenceContext
    EntityManager em;
    List<Auction> auctionsList;
    @Inject
    Principal principal;
    
   

    public void save(User user) {
        user.setUserCredit(100);
        user.setUserGroup(Group.USERS);
        em.persist(user);
    }

    public void unregister() {
        em.remove(getLoggedUser());
    }

    public User getLoggedUser() {
        System.out.print("PRINCIPAL = " + principal.getName());
        return em.find(User.class, principal.getName());
    }    
  
}
