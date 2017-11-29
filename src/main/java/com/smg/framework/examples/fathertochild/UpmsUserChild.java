package com.smg.framework.examples.fathertochild;

import com.smg.framework.upms.dao.model.UpmsUser;

/**
 *
 * @author justincai
 */
public class UpmsUserChild extends UpmsUser {

    private String anotherProp;
    

    /**
     * Get the value of anotherProp
     *
     * @return the value of anotherProp
     */
    public String getAnotherProp() {
        return anotherProp;
    }

    /**
     * Set the value of anotherProp
     *
     * @param anotherProp new value of anotherProp
     */
    public void setAnotherProp(String anotherProp) {
        this.anotherProp = anotherProp;
    }

    @Override
    public void setCtime(Long ctime) {
        super.setCtime(ctime); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getCtime() {
        return super.getCtime(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLocked(Byte locked) {
        super.setLocked(locked); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Byte getLocked() {
        return super.getLocked(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSex(Byte sex) {
        super.setSex(sex); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Byte getSex() {
        return super.getSex(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEmail() {
        return super.getEmail(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPhone(String phone) {
        super.setPhone(phone); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPhone() {
        return super.getPhone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAvatar(String avatar) {
        super.setAvatar(avatar); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAvatar() {
        return super.getAvatar(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRealname(String realname) {
        super.setRealname(realname); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getRealname() {
        return super.getRealname(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSalt(String salt) {
        super.setSalt(salt); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSalt() {
        return super.getSalt(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPassword() {
        return super.getPassword(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUsername() {
        return super.getUsername(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUserId(Integer userId) {
        super.setUserId(userId); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getUserId() {
        return super.getUserId(); //To change body of generated methods, choose Tools | Templates.
    }

}
