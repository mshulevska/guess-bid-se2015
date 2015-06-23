package it.polimi.registration.business.security.entity;

import it.polimi.registration.business.security.entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-23T03:50:22")
@StaticMetamodel(Auction.class)
public class Auction_ { 

    public static volatile SingularAttribute<Auction, Integer> auctionId;
    public static volatile SingularAttribute<Auction, Date> auctionEndtime;
    public static volatile SingularAttribute<Auction, User> userAuctionEmail;
    public static volatile SingularAttribute<Auction, String> auctionTitle;
    public static volatile SingularAttribute<Auction, String> auctionDesc;
    public static volatile SingularAttribute<Auction, String> auctionProduct;

}