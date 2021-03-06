package com.worldsbestauctions.auctionsite.entities;

import com.worldsbestauctions.auctionsite.socketwrapper.Sendable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Entity
public class Bids  implements Sendable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userid;
    private long auctionid;
    private double bidamount;
    private LocalDateTime bidtime;

    @ManyToOne
    @JoinColumn(name="userid", referencedColumnName = "userid", insertable = false, updatable = false)
    private Users user;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public void setAuctionid(long auctionid) {
        this.auctionid = auctionid;
    }

    public long getAuctionid() {
        return auctionid;
    }

    public void setBidamount(double bidamount) {
        this.bidamount = bidamount;
    }

    public double getBidamount() {
        return bidamount;
    }

    public void setBidtime(LocalDateTime bidtime) {
        this.bidtime = bidtime;
    }

    public long getBidtime() {
        return bidtime.toEpochSecond(ZoneOffset.UTC);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
