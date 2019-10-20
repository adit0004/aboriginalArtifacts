/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.MuseumController.museumApplication;
import entities.TicketRecord;
import entities.UserData;
import java.io.Serializable;
import java.util.List;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import mbeans.UserManagedBean;
import repository.UserRepository;

/**
 *
 * @author Aditya
 */
@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

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

    public boolean checkUserHasBooking() {
        if (userManagedBean.getCurrentLoggedInUser() == null) {
            return false;
        }
        int userId = userManagedBean.getCurrentLoggedInUser().getUserId();
        int exhibitionId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("exhibitionId"));
        if (userManagedBean.fetchTicketRecord(userId, exhibitionId) != null) {
            return true;
        }
        return false;
    }
    
    public List<TicketRecord> getUserBookings() {
        return userManagedBean.getUserBookings();
    }
}
