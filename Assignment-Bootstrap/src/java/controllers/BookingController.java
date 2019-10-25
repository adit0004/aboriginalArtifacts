/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.UserController.museumApplication;
import entities.TicketRecord;
import java.util.Date;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import mbeans.UserManagedBean;

/**
 *
 * @author Aditya
 */
@Named(value = "bookingController")
@RequestScoped
public class BookingController {
    
    static @ManagedProperty(value = "#{museumApplication}")
    MuseumApplication museumApplication;

    private boolean accessDenied = false;
    
    static @ManagedProperty(value = "#{userManagedBean}")
    UserManagedBean userManagedBean;
    
    private TicketRecord currentBooking;
    
    public BookingController() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        museumApplication = (MuseumApplication) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "museumApplication");
        userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "userManagedBean");
    }
    
    public String fetchUserBooking(int bookingId) {
        if (userManagedBean == null) {
            ELContext elContext = FacesContext.getCurrentInstance().getELContext();
            museumApplication = (MuseumApplication) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "museumApplication");
            userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "userManagedBean");
        }
        currentBooking = userManagedBean.getBookingById(bookingId);
        if (currentBooking.getUserDetails().getUserId() != userManagedBean.getCurrentLoggedInUser().getUserId()) {
            // NOT the correct user, trigger a 403
            accessDenied = true;
            return "/access_denied";
        }
        
        else return "/user/booking";
    }

    public boolean isAccessDenied() {
        return accessDenied;
    }

    public void setAccessDenied(boolean accessDenied) {
        this.accessDenied = accessDenied;
    }

    public TicketRecord getCurrentBooking() {
        return currentBooking;
    }

    public void setCurrentBooking(TicketRecord currentBooking) {
        this.currentBooking = currentBooking;
    }
    
    public String deleteBooking(int bookingId) {
        userManagedBean.deleteBooking(bookingId);
        return "bookings";
    }

}
