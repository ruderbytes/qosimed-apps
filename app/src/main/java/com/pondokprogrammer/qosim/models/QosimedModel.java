package com.pondokprogrammer.qosim.models;

/**
 * Created by ocittwo on 12/12/15.
 */
public class QosimedModel {

    private String fullname, username, descriptions;
    private int imgpost, imgprofile;


    public QosimedModel(int imgprofile, String fullname, String username, int imgpost, String descriptions) {
        this.imgprofile = imgprofile;
        this.fullname = fullname;
        this.username = username;
        this.imgpost = imgpost;
        this.descriptions = descriptions;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public int getImgpost() {
        return imgpost;
    }

    public void setImgpost(int imgpost) {
        this.imgpost = imgpost;
    }

    public int getImgprofile() {
        return imgprofile;
    }

    public void setImgprofile(int imgprofile) {
        this.imgprofile = imgprofile;
    }
}
