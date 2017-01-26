package com.pondokprogrammer.qosim.models;

/**
 * Created by muhammad on 20/04/16.
 */
public class MenuDrawerModel {
    private String menutitle;
    private int menuimg;

    public MenuDrawerModel(String menutitle, int menuimg) {
        this.menutitle = menutitle;
        this.menuimg = menuimg;
    }

    public String getMenutitle() {
        return menutitle;
    }

    public void setMenutitle(String menutitle) {
        this.menutitle = menutitle;
    }

    public int getMenuimg() {
        return menuimg;
    }

    public void setMenuimg(int menuimg) {
        this.menuimg = menuimg;
    }
}
