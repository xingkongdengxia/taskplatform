package com.magicube.framework.examples.pagination;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author justincai
 */
public class ActionSetting {

    public int id;

    private String actionname;

    private String actionclass;

    private String actionshowname;

    public ActionSetting() {

    }

    public ActionSetting(int id, String actionname, String actionclass, String actionshowname) {
        this.id = id;
        this.actionname = actionname;
        this.actionclass = actionclass;
        this.actionshowname = actionshowname;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActionname() {
        return actionname;
    }

    public void setActionname(String actionname) {
        this.actionname = actionname;
    }

    public String getActionclass() {
        return actionclass;
    }

    public void setActionclass(String actionclass) {
        this.actionclass = actionclass;
    }

    public String getActionshowname() throws UnsupportedEncodingException {

        return actionshowname;
    }

    public void setActionshowname(String actionshowname) {
        this.actionshowname = actionshowname;
    }

    @Override
    public String toString() {
        return "ActionSetting{" + "id=" + id + ", actionname=" + actionname + ", actionclass=" + actionclass + ", actionshowname=" + actionshowname + '}';
    }

}
