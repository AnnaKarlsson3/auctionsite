package com.worldsbestauctions.auctionsite.repos;

import com.worldsbestauctions.auctionsite.entities.Bids;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BidRepo extends CrudRepository<Bids, Long> {

    Bids findDistinctById(long id);
    Iterable<Bids> findBidamountByAuctionid(long aLong);
}
