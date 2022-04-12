package com.fsy2001.artwork.model;

import javax.persistence.*;

@Entity
@Table(name = "user_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Artwork artwork;
    private String username;
    private String status; // waiting, canceled, paid, received
    private Integer shippingStatus = 0;

    public Order() {
    }

    public Order(Artwork artwork, String username) {
        this.artwork = artwork;
        this.username = username;
        this.status = "waiting";
    }



    public Integer getId() {
        return id;
    }

    public Artwork getArtwork() {
        return artwork;
    }

    public void setArtwork(Artwork artwork) {
        this.artwork = artwork;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }
}
