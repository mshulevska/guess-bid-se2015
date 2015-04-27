module GuessBid

abstract sig Word{}
abstract sig Description{}


sig User{
	id: one Int,
	credit: one Int
}

sig Auction{
	id:one Int,
	name:one Word,
	description:one Description,
	dateCreate: one Int,
	dateExpire: one Int,
	creator: one User
}

sig Bid{
	bidder: one User,
	dateBid: one Int,
    value: one Int,
	cost: one Int
}{dateBid < Auction.dateExpire
	dateBid > Auction.dateCreate}


sig Notification{
	auction: one Auction,
	receive: one User
}

sig Bidding{
	auction: one Auction,
	bid: one Bid,
}

// Facts
// Every user has a unique ID and username
fact noTwoUsersWithSameID{
	no disj u1, u2 : User | u1.id = u2.id 
}

// Every auction is unique, with unique title and description
fact noTwoSameAuctions{
	no disj a1, a2 : Auction | a1.name = a2.name || a1.description = a2.description || a1.dateCreate = a2.dateCreate
}

// Every user can create only one auction at a time
fact onlyOneAuctionByUser{
	no disj a1, a2 : Auction | a1.creator = a2.creator and a1.dateCreate = a2.dateCreate
}

// User cannot bid on his own auction
fact userCannotBidOwnAuction{
	no b1 : Bid | all a1 : Auction | b1.bidder = a1.creator
}

//User cannot bid after the expiration date
fact noBidAfterDateExpire{
	no b1: Bid| all a1:Auction| b1.dateBid > a1.dateExpire
}

// User cannot bid if he doesn't have credit
fact noBidWithoutCredit{
	no b1: Bid | all u1: User| b1.cost > u1.credit
}

assert WinnerBid{
	no disj b1, b2: Bid | b1.value = b2.value and b1.value > b2.value
} 


//check WinnerBid for 4

pred show{
	#Auction >1 
	#User >10
	}

run show for 11
