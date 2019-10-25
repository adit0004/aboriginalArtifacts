/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.BookingController.userManagedBean;
import static controllers.MuseumController.museumApplication;
import entities.TicketRecord;
import entities.UserData;
import java.io.Serializable;
import java.util.Date;
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

    public void reinitManagedBean() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "userManagedBean");
    }
    
    public UserData getUser() {
        return userManagedBean.getCurrentLoggedInUser();
    }

    public boolean checkUserHasBooking() {
        reinitManagedBean();
        if (userManagedBean.getCurrentLoggedInUser() == null) {
            return false;
        }
        int userId = userManagedBean.getCurrentLoggedInUser().getUserId();
        int exhibitionId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("exhibitionId"));
        TicketRecord records = (TicketRecord) userManagedBean.fetchTicketRecord(userId, exhibitionId);
        if (records != null) {
            return true;
        }
        return false;
    }
    
    public List<TicketRecord> getUserBookings(){
        reinitManagedBean();
        System.out.println(userManagedBean.toString());
        return userManagedBean.getUserBookings();
    }
    
    
    public boolean getBookingEditable(int bookingId) {
        TicketRecord record = userManagedBean.getBookingById(bookingId);
        if (record.getPickedUp() || record.getBookingDate().before(new Date()))
            return false;
        return true;
    }
}
