package com.fsy2001.artwork.model.support;

public class FriendDisplay {
    private String username;
    private String img;

    public FriendDisplay(String username, String img) {
        this.username = username;
        this.img = img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
