/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.BookingController.museumApplication;
import static controllers.BookingController.userManagedBean;
import static controllers.ExhibitionController.museumApplication;
import entities.Collection;
import entities.Exhibition;
import entities.Museum;
import entities.Ticket;
import entities.TicketRecord;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;
import mbeans.MuseumManagedBean;
import mbeans.UserManagedBean;
import org.apache.commons.io.*;

/**
 *
 * @author adity
 */
@Named(value = "adminController")
@SessionScoped

public class AdminController implements Serializable {

    static @ManagedProperty(value = "#{userManagedBean}")
    MuseumManagedBean museumManagedBean;

    static @ManagedProperty(value = "#{museumApplication}")
    MuseumApplication museumApplication;

    List<Exhibition> allExhibitions;

    private String updatedStartDate;
    private String updatedEndDate;
    private int updatedCollectionId;
    private int updatedMuseumId;

    private Part file;

    private int selectedExhibitionId;
    private Exhibition selectedExhibition;

    private TicketRecord currentBooking;
    private String updatedDate;
    private int bookingQty;
    private int bookingId;

    public AdminController() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        museumManagedBean = (MuseumManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "museumManagedBean");
        museumApplication = (MuseumApplication) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "museumApplication");
        allExhibitions = museumManagedBean.getAllExhibitions();
    }

    public List<Exhibition> getCurrentExhibitions() {
        List<Exhibition> returnArray = new ArrayList<Exhibition>();
        for (Exhibition exhibition : allExhibitions) {
            if (exhibition.getExhibitionStartDate().before(new Date()) && exhibition.getExhibitionEndDate().after(new Date())) {
                returnArray.add(exhibition);
            }
        }
        return returnArray;
    }

    public List<Exhibition> getPastExhibitions() {
        List<Exhibition> returnArray = new ArrayList<Exhibition>();
        for (Exhibition exhibition : allExhibitions) {
            if (exhibition.getExhibitionEndDate().before(new Date())) {
                returnArray.add(exhibition);
            }
        }
        return returnArray;
    }

    public List<Exhibition> getFutureExhibitions() {
        List<Exhibition> returnArray = new ArrayList<Exhibition>();
        for (Exhibition exhibition : allExhibitions) {
            if (exhibition.getExhibitionStartDate().after(new Date())) {
                returnArray.add(exhibition);
            }
        }
        return returnArray;
    }

    public String deleteExhibition(int exhibitionId) {
        museumManagedBean.deleteExhibition(exhibitionId);
        allExhibitions = museumManagedBean.getAllExhibitions();
        return "/admin/dash";
    }

    public List<Collection> getAllCollections() {
        return museumManagedBean.getAllCollections();
    }

    public List<Museum> getAllMuseums() {
        return museumManagedBean.getAllMuseums();
    }

    public String getUpdatedStartDate() {
        return updatedStartDate;
    }

    public void setUpdatedStartDate(String updatedStartDate) {
        this.updatedStartDate = updatedStartDate;
    }

    public String getUpdatedEndDate() {
        return updatedEndDate;
    }

    public void setUpdatedEndDate(String updatedEndDate) {
        this.updatedEndDate = updatedEndDate;
    }

    public int getUpdatedCollectionId() {
        return updatedCollectionId;
    }

    public void setUpdatedCollectionId(int updatedCollectionId) {
        this.updatedCollectionId = updatedCollectionId;
    }

    public int getUpdatedMuseumId() {
        return updatedMuseumId;
    }

    public void setUpdatedMuseumId(int updatedMuseumId) {
        this.updatedMuseumId = updatedMuseumId;
    }

    public int getSelectedExhibitionId() {
        selectedExhibitionId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("exhibitionId"));

        return selectedExhibitionId;
    }

    public void setSelectedExhibitionId(int selectedExhibitionId) {
        this.selectedExhibitionId = selectedExhibitionId;
    }

    public Exhibition getSelectedExhibition() {
        selectedExhibitionId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("exhibitionId"));
        if (selectedExhibition == null) {
            selectedExhibition = museumApplication.getExhibitionById(selectedExhibitionId);
        }
        return selectedExhibition;
    }

    public void setSelectedExhibition(Exhibition selectedExhibition) {
        this.selectedExhibition = selectedExhibition;
    }

    public String updateExhibition() {
        try {
            // Set dates
            selectedExhibition.setExhibitionStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(updatedStartDate));
            selectedExhibition.setExhibitionEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(updatedEndDate));
            // Set collection object
            selectedExhibition.setExhibitionCollection(museumApplication.getCollectionById(updatedCollectionId));
            // Set museum object
            selectedExhibition.setExhibitionMuseum(museumApplication.getMuseumById(updatedMuseumId));
            // Merge
            museumManagedBean.updateExhibition(selectedExhibition);
            allExhibitions = museumManagedBean.getAllExhibitions();
            return "/admin/dash";
        } catch (ParseException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/admin/edit_exhibition?exhibitionId=" + selectedExhibitionId;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String saveFile() {
        selectedExhibitionId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("exhibitionId"));

        try (InputStream input = file.getInputStream()) {
            Path folder = Paths.get("D:/Study/5042/Assignment/Assignment-Bootstrap/web/img/exhibitions/" + selectedExhibitionId);

            String filename = FilenameUtils.getBaseName(file.getSubmittedFileName());
            String extension = FilenameUtils.getExtension(file.getSubmittedFileName());
            String newExtension = extension.substring(extension.length() - 3, extension.length());

            String newFilename = new Date().getTime() + "_" + filename;

            Path newFile = Files.createTempFile(folder, newFilename + "-", "." + newExtension);

            Files.copy(input, newFile, StandardCopyOption.REPLACE_EXISTING);

            //Now that it's uploaded,also save it to the object
            String[] newFilenameForDb = newFile.toString().split("\\\\");
            selectedExhibition.setImagePath("img/exhibitions/" + selectedExhibitionId + "/" + newFilenameForDb[newFilenameForDb.length - 1]);
            // Merge
            museumManagedBean.updateExhibition(selectedExhibition);
            allExhibitions = museumManagedBean.getAllExhibitions();
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/admin/edit_exhibition?exhibitionId=" + selectedExhibitionId;
    }

    public String addExhibition(temporaryEntities.Exhibition exhibition) {
        try {
            // Set dates
            exhibition.setExhibitionStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(updatedStartDate));
            exhibition.setExhibitionEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(updatedEndDate));
            // Set collection object
            exhibition.setExhibitionCollection(museumApplication.getCollectionById(updatedCollectionId));
            // Set museum object
            exhibition.setExhibitionMuseum(museumApplication.getMuseumById(updatedMuseumId));
            exhibition.setImagePath("/img/exhibitions/placeholder.jpg");
            // Merge
            Ticket ticket = new Ticket(exhibition.getTicketName(), exhibition.getTicketPrice(), exhibition.getTicketDescription());
            Exhibition newExhibition = new Exhibition(0, exhibition.getExhibitionName(), exhibition.getExhibitionDescription(), exhibition.getExhibitionStartDate(), exhibition.getExhibitionEndDate(), exhibition.getExhibitionCollection(), exhibition.getExhibitionMuseum(), ticket, exhibition.getImagePath());
            museumManagedBean.addExhibition(newExhibition);
            allExhibitions = museumManagedBean.getAllExhibitions();
            return "/admin/dash";
        } catch (ParseException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/admin/edit_exhibition?exhibitionId=" + selectedExhibitionId;
    }

    public List<TicketRecord> getAllBookings() {
        return museumManagedBean.getAllBookings();
    }

    public String viewBooking(int bookingId) {
        currentBooking = userManagedBean.getBookingById(bookingId);
        return "/admin/view_booking";
    }

    public TicketRecord getCurrentBooking() {
        return currentBooking;
    }

    public void setCurrentBooking(TicketRecord currentBooking) {
        this.currentBooking = currentBooking;
    }

    public String fetchUserBooking(int bookingId) {
        currentBooking = userManagedBean.getBookingById(bookingId);
        return "/admin/booking";
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
        return "/admin/bookings";
    }
}
