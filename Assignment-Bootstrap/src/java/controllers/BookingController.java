/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.UserController.museumApplication;
import entities.TicketRecord;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private String updatedDate;
    private int bookingQty;
    private int bookingId;
    
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
    public String viewBooking(int bookingId) {
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
        
        else return "/user/view_booking";
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

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getBookingQty() {
        return bookingQty;
    }

    public void setBookingQty(int bookingQty) {
        this.bookingQty = bookingQty;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String updateBooking() {
        try {
            currentBooking = userManagedBean.getBookingById(bookingId);
            currentBooking.setBookingDate(new SimpleDateFormat("yyyy-MM-dd").parse(updatedDate));
            currentBooking.setQuantity(bookingQty);
            userManagedBean.updateBooking(currentBooking);
        } catch (ParseException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/user/bookings";
    }
    
}
