/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.MuseumController.museumApplication;
import entities.UserData;
import java.io.Serializable;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import mbeans.UserManagedBean;

/**
 *
 * @author Aditya
 */
@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable{
    static @ManagedProperty(value = "#{museumApplication}")
    MuseumApplication museumApplication;

    static @ManagedProperty(value = "#{userManagedBean}")
    UserManagedBean userManagedBean;
    
    public UserController() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        museumApplication = (MuseumApplication) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "museumApplication");
        userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "userManagedBean");
    }   
    
    public UserData getUser() {
        return userManagedBean.getCurrentLoggedInUser();
    }
}
