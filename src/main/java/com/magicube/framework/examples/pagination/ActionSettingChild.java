package com.magicube.framework.examples.pagination;

import java.io.UnsupportedEncodingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 测试父类拷贝到子类
 *
 * @author justincai
 */
public class ActionSettingChild extends ActionSetting {

    private static final Log log = LogFactory.getLog(ActionSettingChild.class);

    private String anotherProp;

    public String getAnotherProp() {
        return anotherProp;
    }

    public void setAnotherProp(String anotherProp) {
        this.anotherProp = anotherProp;
    }

    @Override
    public void setActionshowname(String actionshowname) {
        super.setActionshowname(actionshowname); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getActionshowname() throws UnsupportedEncodingException {
        return super.getActionshowname(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setActionclass(String actionclass) {
        super.setActionclass(actionclass); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getActionclass() {
        return super.getActionclass(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setActionname(String actionname) {
        super.setActionname(actionname); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getActionname() {
        return super.getActionname(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(int id) {
        super.setId(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    public String toString() {
        try {
            return "ActionSettingChild{" + "id=" + super.getId()
                    + ", actionname=" + super.getActionname()
                    + ", actionclass=" + super.getActionclass()
                    + ", actionshowname=" + super.getActionshowname()
                    + ", anotherProp=" + anotherProp + '}';
        } catch (UnsupportedEncodingException ex) {
            log.error(ex);
        }
        return null;
    }

}
